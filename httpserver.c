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
    int server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd <= 0)
    {
        fprintf(stderr, "failed to create socket\n");
        return EXIT_FAILURE;
    }


    char * httpHead = "HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: %d\n\n";
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

    int binderr = bind(server_fd, (struct sockaddr *)&address, sizeof(address));
    if (binderr < 0)
    {
        perror("in bind");
        fprintf(stdout, "%d\n", binderr);
        fprintf(stderr, "socket bind failed\n");
        return EXIT_FAILURE;
    }

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

        FILE * indfptr = NULL;

        if (buffer[4] == '/' && buffer[5] == ' ')
        {
            indfptr = fopen("dogcast.html", "r");
        }
        else if (buffer[5] != '.' && buffer[6] != '.')
        {
            char ** tokens = strtok(buffer, ' ');
            indfptr = fopen(tokens[1], "r");
        }

        char * sendbuff = malloc(valread + sizeof(httpHead));
        fseek(indfptr, 0, SEEK_END);
        long sizefile = ftell(indfptr);
        fseek(indfptr, 0, SEEK_SET);
        

        // if (valread > 0) 
        // {
        //     fprintf(stdout, "%s\n", buffer);
        // }
        close(new_socket);


        fscanf(stdin, "Continue 1|0: %d", &cont);
    }
    close(server_fd);
}
