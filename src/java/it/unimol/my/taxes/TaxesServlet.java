package it.unimol.my.taxes;

import it.unimol.my.utils.Esse3AuthServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet della funzionalità tasse
 * 
 * @author Carlo Branca
 */
@WebServlet(name = "TaxesServlet", urlPatterns = {"/getTaxes"})
public class TaxesServlet extends Esse3AuthServlet {

    /**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = -453669874315713950L;

	@Override
    protected void serve(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		
        String targetUrl = config.getTaxesUrl();
        // recupero l'estrattore
        TaxesExtractorInterface extractor = TaxesExtractorManager.getTaxesExtractor();
        // Richiamo l'estrattore del manager e la funzione che effettua il
        // parsing della pagina/file
        // Il risultato è la lista delle tasse
        try {
            List<Tax> taxes = extractor.getTaxesList(targetUrl,
                    username, password, careerId);
            if (taxes == null) {
                String unknownErrorMsg = config.getMessage("unknownError");
                writer.println("{\"result\":\"failure\",\"msg\":\""
                        + unknownErrorMsg + "\"}");
                return;
            }
            // conversione della "List" di ExamSession in json e stampa a video
            String json = gson.toJson(taxes);
            writer.println("{\"result\":\"success\",\"taxes\":" + json + "}");
        } catch (UnirestException e) {
        	String unirestExceptionMsg = config.getMessage("unirestException");
            writer.println("{\"result\":\"failure\", \"msg\":\"" + unirestExceptionMsg + "\"}");
        }
    }

}
