package it.unimol.my.exam;

import it.unimol.my.utils.Esse3AuthServlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa servlet da il via al parsing del libretto. Dopo l'elaborazione dei
 * dati da parte delle classi addette al parsing, mostra a video la lista degli
 * appelli disponibili in formato json
 * 
 * @author Christian De Rita
 */
@WebServlet(name = "ExamsListParser", urlPatterns = { "/getRecordBook" })
public class RecordBookServlet extends Esse3AuthServlet {

	/**
	 * L'id seriale della versione.
	 */
	private static final long serialVersionUID = 6704202796026709792L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unimol.my.utils.WebServiceServlet#serve(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String targetUrl = config.getRecordBookUrl();
		// recupero l'estrattore
		RecordBookExtractorInterface recordBookExtractor = RecordBookExtractorManager
				.getRecordBookExtractor();
		// estraggo il libretto degli esami
		try {
			RecordBook recordBook = recordBookExtractor.getExamsList(targetUrl,
					username, password);
			if (recordBook == null) {
				String unknownErrorMsg = config.getMessage("unknownError");
				writer.println("{\"result\":\"failure\",\"msg\":\""
						+ unknownErrorMsg + "\"}");
				return;
			}
			// converto il libretto in json
			String json = gson.toJson(recordBook);
			// stampo il json a video
			writer.println(json);
		} catch (UnirestException e) {
			e.printStackTrace();
			String unirestExceptionMsg = config.getMessage("unirestException");
			writer.print("{\"result\":\"failure\", \"msg\":\""
					+ unirestExceptionMsg + "\"}");
			return;
		} finally {
			writer.close();
		}
	}
}
