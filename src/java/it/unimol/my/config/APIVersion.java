package it.unimol.my.config;

import it.unimol.my.utils.WebServiceServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet per il controllo della versione delle API utilizzata
 * 
 * @author Ivan Di Rienzo
 */
public class APIVersion extends WebServiceServlet {

	/**
	 * Lo UID di versione seriale
	 */
	private static final long serialVersionUID = 4983785233318772251L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unimol.my.utils.WebServiceServlet#serve(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter writer = resp.getWriter();
		try {
			// recupera la versione dal web.xml (context-param)
			String output = "{\"APIVersion\" : \""
					+ getServletContext().getInitParameter("API.Version")
					+ "\"}";
			writer.print(output);
		} finally {
			writer.close();
		}
	}

}
