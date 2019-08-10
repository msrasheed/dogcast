package com.nexxcast.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nexxcast.Gson.MyGsonBuilder;
import com.nexxcast.model.PartyQueue;
import com.nexxcast.utils.JsonConstructor;
import com.nexxcast.utils.MySqlDBUtil;
import com.nexxcast.utils.PartyQueueDB;
import com.nexxcast.utils.RandomStringGenerator;

/**
 * Servlet implementation class QueueServelt
 */
public class QueueServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueueServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session;
		String operation = request.getParameter("operation");
		String retVal = null;
		
		if (operation == null) {
			session = request.getSession(false);
			if (session == null) throw new ServletException("invalid request");
			
			String partyCode = (String) session.getAttribute("code");
			PartyQueue queue = PartyQueueDB.getQueue(partyCode);
			retVal = MyGsonBuilder.getGson().toJson(queue);
		}
		else if (operation.equals("create")) {
			RandomStringGenerator gen = (RandomStringGenerator) getServletContext().getAttribute("partyCodeGen");
			String createPlaylistParam = request.getParameter("playlist");
			boolean createPlaylist = false;
			if (createPlaylistParam != null && createPlaylistParam.equals("true")) createPlaylist = true;
			PartyQueue queue = new PartyQueue(gen.nextString(), createPlaylist);
			MySqlDBUtil.insert(queue);
			
			session = request.getSession();
			session.setAttribute("queue", queue.getCode());
			session.setAttribute("role", "host");
			
			JsonConstructor retJson = new JsonConstructor();
			retJson.addKeyValuePair("code", queue.getCode());
			retVal = retJson.getJson();	
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.print(retVal);
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
