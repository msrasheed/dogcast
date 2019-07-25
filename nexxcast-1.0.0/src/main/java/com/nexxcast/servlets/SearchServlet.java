package com.nexxcast.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nexxcast.spotify.SpotifyAppClient;
import com.nexxcast.utils.HttpUtility;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String res = null;
		
		if (request.getParameter("q") != null) {
			Properties headers = new Properties();
			String authVal = SpotifyAppClient.getTokenType() + " " + SpotifyAppClient.getAccessToken();
			headers.setProperty("Authorization", authVal);
			
			Properties parameters = new Properties();
			parameters.setProperty("q", request.getParameter("q"));
			parameters.setProperty("type", "track");
			parameters.setProperty("limit", "5");
			
			String url = "https://api.spotify.com/v1/search";
			
			try {
				res = HttpUtility.httpGetGetContent(url, headers, parameters);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			res = "failed";
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.print(res);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
