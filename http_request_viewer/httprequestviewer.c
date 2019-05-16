#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h> //for constants
#include <sys/socket.h> //for socket, bind, listen, and accept
#include <string.h> //for memset
#include <netinet/in.h> //for sockaddr_in and sockaddr
#include <unistd.h> //for read

//#define PORT 8080

int main(int argc, char ** argv)
{
    /*
    int socket(int domain, int type, int protocol)
    domain: communication domain in which the socket should be created (think of it as type of address)
        AF_INET (IP), AF_INET6 (IPv6), AF_UNIX (local channel, similar to pipes), AF_ISO (ISO protocols), and AF_NS (Xerox Network Systems protocols)
    type: type of service - defined by which domain you are using. Different domains have different services
        for AF_INET: SOCK_STREAM (virtual circuit service, TCP), SOCK_DGRAM (datagram service, UDP), SOCK_RAW (direct IP service)
    protocol: indicate specific protocol to use to provide specified services

    */
    int server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd <= 0)
    {
        fprintf(stderr, "failed to create socket\n");
        return EXIT_FAILURE;
    }

    /*
    struct sockaddr_in
    {
        __uint8_t         sin_len;      //length of struct (sizeof(struct sockaddr_in)): doesn't need to be set apparently (may not exist?)
        sa_family_t       sin_family;   //address family used to set up socket (AF_INET in this example)
        in_port_t         sin_port;     //the port number to use (can let OS decide by setting to 0)
        struct in_addr    sin_addr;     //the IP address (let OS decide which network interface to use for connection: INADDR_ANY)
        char              sin_zero[8];
    };
    struct in_addr
    {
        uint32_t          s_addr;       //address in network byte order
    };
    */
    struct sockaddr_in address;
    int addrlen = sizeof(address);
    const int PORT = 8080; //where client can reach at
    /* htonl converts a long integer (e.g. address) to a network representation */
    /* htons converts a short integer (e.g. port) to a network representation */

    memset((char*)&address, '\0', sizeof(address));
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons( PORT );

    memset(address.sin_zero, '\0', sizeof address.sin_zero);

    /*
    int bind(int socket, const struct sockaddr *address, socklen_t address_len)
    socket: socket created in socket system call
    address: generic container that allows OS to read first couple of bytes to determine address family. Address family determines
        what variant of sockaddr struct to use
        for ip networking use sockaddr_in
    address_len: length of passed address
    */
    int binderr = bind(server_fd, (struct sockaddr *)&address, sizeof(address));
    if (binderr < 0)
    {
        perror("in bind");
        fprintf(stdout, "%d\n", binderr);
        fprintf(stderr, "socket bind failed\n");
        return EXIT_FAILURE;
    }

    /*
    listen - preps socket to accept incoming connections - puts pending connections on queue
    int listen(int socket, int backlog);
    socket: the socket fild descriptor we've been working with
    backlog: defines max number of pending connections that can be queued before they are straight up refused
    */
    int listerr = listen(server_fd, 3);
    if (listerr < 0)
    {
        fprintf(stderr, "listen failed\n");
        return EXIT_FAILURE;
    }

    fprintf(stdout, "listening\n");

    int cont = 1;
    while (cont)
    {
        /*
        accept - takes connection off queue and creates a new socket for that connection
            by default, socket operations are blocking, and accept will block until there is a connection available on queue
        int accept(int socket, struct sockaddr *restrict address, socklen_t *restrict address_len);
        socket: the socket set up to listen for connections
        address: struct to get filled up by accept - gets filled with client connection info
        adderss_len: length of address (sizeof)
        */
        int new_socket = accept(server_fd, (struct sockaddr *) &address, (socklen_t *)&addrlen);
        if (new_socket < 0)
        {
            fprintf(stderr, "accpet error\n");
            return EXIT_FAILURE;
        }

        char buffer[30000] = {0};
        long valread = read(new_socket, buffer, 30000);
        if (valread > 0) 
        {
            fprintf(stdout, "%s\n", buffer);
        }
        close(new_socket);


        fscanf(stdin, "Continue 1|0: %d", &cont);
    }
    close(server_fd);
}
