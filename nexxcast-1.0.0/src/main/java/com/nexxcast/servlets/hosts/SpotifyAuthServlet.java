package com.nexxcast.servlets.hosts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nexxcast.spotify.SpotifyAppClient;
import com.nexxcast.spotify.SpotifyScopes;
import com.nexxcast.spotify.SpotifyUserCredentials;
import com.nexxcast.utils.HttpUtility;
import com.nexxcast.utils.JsonConstructor;
import com.nexxcast.utils.RandomStringGenerator;

/**
 * Servlet implementation class SpotifyAuthServlet
 */
public class SpotifyAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String redirect_uri = "http://10.0.0.4:8080/nexxcast-1.0.0/host/spotifyauth";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpotifyAuthServlet() {
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
		ServletContext sc = getServletContext();
		HttpSession session = request.getSession(false);
		String state = (String) session.getAttribute("state");
		
		if (state.equals(request.getParameter("state"))) {
			//HttpSession session = (HttpSession) sc.getAttribute(state);
			//sc.removeAttribute(state);
			
			String code;
			if ((code = request.getParameter("code"))!= null) {
				String url = "https://accounts.spotify.com/api/token";
				
				Properties headers = new Properties();
				headers.setProperty("Authorization", "Basic " + SpotifyAppClient.clikey);
				
				Properties params = new Properties();
				params.setProperty("grant_type", "authorization_code");
				params.setProperty("code", code);
				params.setProperty("redirect_uri", redirect_uri);
				
				SpotifyUserCredentials suc;
				suc = HttpUtility.httpPostGetObject(SpotifyUserCredentials.class, url, headers, params);
				
				session.setAttribute("credentials", suc);
				System.out.println(suc.getAccess_token());
				response.sendRedirect("/nexxcast-1.0.0/signInSuccess.html");
			}
			else {
				res = "no code";
			}
		}
		else {
			res = "none or incorrect state";
		}
				
		try {
			PrintWriter out = response.getWriter();
			out.print(res);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		RandomStringGenerator gen = (RandomStringGenerator) sc.getAttribute("randStringGen");
		
		String state = gen.nextString();
		//sc.setAttribute(state, session);
		session.setAttribute("state", state);
		
		JsonConstructor retVal = new JsonConstructor();
		retVal.addKeyValuePair("client_id", SpotifyAppClient.clientID);
		retVal.addKeyValuePair("state", state);
		retVal.addKeyValuePair("scope", SpotifyScopes.getHostScopes());
		retVal.addKeyValuePair("redirect_uri", redirect_uri);
		
		try {
			PrintWriter out = response.getWriter();
			out.print(retVal.getJson());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
