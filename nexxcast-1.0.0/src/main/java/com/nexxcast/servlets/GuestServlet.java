package com.nexxcast.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nexxcast.model.PartyQueue;
import com.nexxcast.model.QueueItem;
import com.nexxcast.utils.JsonConstructor;
import com.nexxcast.utils.MySqlDBUtil;
import com.nexxcast.utils.PartyQueueDB;
import com.nexxcast.utils.QueueItemDB;

/**
 * Servlet implementation class GuestServlet
 */
public class GuestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String operation = request.getParameter("operation");
		JsonConstructor retJson = new JsonConstructor();
		String retVal = null;
		
		if (operation == null) throw new ServletException("no operation specified");
		else if (operation.equals("join")) {
			String partycode = request.getParameter("code");
			if (partycode == null) throw new ServletException("no party code specified");
			
			PartyQueue queue = PartyQueueDB.getQueue(partycode);
			if (queue == null) throw new ServletException("queue with partycode " + partycode + " doesn't exist");
			
			HttpSession session = request.getSession();
			session.setAttribute("role", "guest");
			session.setAttribute("code", partycode);
			retJson.addKeyValuePair("status", "joined");
			retVal = retJson.getJson();
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.print(retVal);
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
		String operation = request.getParameter("operation");
		if (operation == null) throw new ServletException("operation not specified");
		
		HttpSession session = request.getSession(false);
		if (session == null) throw new ServletException("requester doesn't have an active session");
		String teststr;
		
		if (operation.contains("vote")) {
			long queueid;
			if ((teststr = request.getParameter("queueid")) != null) queueid = Long.parseLong(teststr);
			else throw new ServletException("no queue id specified");
			
			QueueItem queueItem = QueueItemDB.getQueueItem(queueid);
			if (queueItem == null) throw new ServletException("queue item specified doesn't exist");
			
			if (operation.equals("upvote")) queueItem.setVotes(queueItem.getVotes() + 1);
			else if (operation.equals("unvote")) queueItem.setVotes(queueItem.getVotes() - 1);
			
			MySqlDBUtil.merge(queueItem);
		}
		else if (operation.equals("add")) {
			if ((teststr = request.getParameter("uri")) == null) throw new ServletException("no uri specified");
			QueueItem queueItem = new QueueItem(teststr);
			
			String partyCode = (String) session.getAttribute("code");
			PartyQueue queue = PartyQueueDB.getQueue(partyCode);
			queue.addItem(queueItem);
			
			MySqlDBUtil.merge(queue);
		}
	}

}
