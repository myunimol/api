package it.unimol.my.config;

import it.unimol.my.utils.WebServiceServlet;

import java.io.IOException;

import javax.servlet.ServletException;
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

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		if (!tokenIsValid(request, response)) {
			return;
		}
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
