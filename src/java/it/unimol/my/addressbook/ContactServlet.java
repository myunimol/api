package it.unimol.my.addressbook;

import it.unimol.my.utils.WebServiceServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlo Branca
 */
@WebServlet(name = "ContactServlet", urlPatterns = {"/searchContacts"})
public class ContactServlet extends WebServiceServlet {

    @Override
    protected void serve(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //recupero l'estrattore
        ContactExtractorXML contactExtractor = new ContactExtractorXML();
        // estraggo il libretto degli esami
        try {
            String name = req.getParameter("name");            
            String surname = req.getParameter("surname");
            if (name == null || surname == null) {
                String msg = config.getMessage("badParameters");
                writer.println("{\"result\":\"failure\",\"msg\":\""
                        + msg + "\"}");
                return;
            }
            List<Contact> contactList = contactExtractor.getContact(name, surname);
            if (contactList == null) {
                String unknownErrorMsg = config.getMessage("unknownError");
                writer.println("{\"result\":\"failure\",\"msg\":\""
                        + unknownErrorMsg + "\"}");
                return;
            }
            // converto il libretto in json
            String json = gson.toJson(contactList);
            // stampo il json a video
            writer.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}
