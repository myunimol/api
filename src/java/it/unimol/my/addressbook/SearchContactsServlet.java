package it.unimol.my.addressbook;

import it.unimol.my.utils.WebServiceServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlo Branca
 */
@WebServlet(name = "ContactServlet", urlPatterns = { "/searchContacts" })
public class SearchContactsServlet extends WebServiceServlet {

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = -4695089031840096808L;

	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter writer = resp.getWriter();
		// recupero l'estrattore
		ContactExtractorXML contactExtractor = new ContactExtractorXML();
		// estraggo il libretto degli esami
		try {
			String search = req.getParameter("search");
			if (search == null) {
				String msg = config.getMessage("badParameters");
				writer.println("{\"result\":\"failure\",\"msg\":\"" + msg
						+ "\"}");
				return;
			}
			List<Contact> contactList = contactExtractor.searchContacts(search);
			if (contactList == null) {
				String unknownErrorMsg = config.getMessage("unknownError");
				writer.println("{\"result\":\"failure\",\"msg\":\""
						+ unknownErrorMsg + "\"}");
				return;
			}
			// converto il libretto in json
			String json = gson.toJson(contactList);
			// stampo il json a video
			writer.println("{\"result\":\"success\",\"contacts\":" + json + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
