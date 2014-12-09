package it.unimol.my.addressbook;

import it.unimol.my.utils.WebServiceServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Descrizione servlet
 *
 * @author Carlo Branca
 */
@WebServlet(name = "AddressBookServlet", urlPatterns = {"/getAddressBook"})
public class AddressBookServlet extends WebServiceServlet {

    @Override
    protected void serve(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //recupero l'estrattore
        AddressBookExtractorXML addressBookExtractor = new AddressBookExtractorXML();
        // estraggo il libretto degli esami
        try {
            List<Contact> addressBook = addressBookExtractor.getAddressBook();
            if (addressBook.isEmpty()) {
                String unknownErrorMsg = config.getMessage("unknownError");
                writer.println("{\"result\":\"failure\",\"msg\":\""
                        + unknownErrorMsg + "\"}");
                return;
            }
            // converto il libretto in json
            String json = gson.toJson(addressBook);
            // stampo il json a video
            writer.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}
