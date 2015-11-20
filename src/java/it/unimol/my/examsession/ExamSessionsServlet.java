package it.unimol.my.examsession;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.utils.Esse3AuthServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa servlet da il via al parsing ella lista degli appelli d'esame. Dopo
 * l'elaborazione dei dati da parte delle classi addette al parsing, mostra a
 * video la lista degli appelli disponibili in formato json
 *
 * @author Giuseppe Bianco
 */
@WebServlet(name = "ExamSessionsServlet", urlPatterns = {"/getExamSessions"})
public class ExamSessionsServlet extends Esse3AuthServlet {

    /**
     * Lo uid seriale della versione.
     */
    private static final long serialVersionUID = -5917061959759909645L;

    /*
	 * (non-Javadoc)
	 * 
	 * @see it.unimol.my.utils.WebServiceServlet#serve(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void serve(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String targetURL = ConfigurationManager.getInstance()
                .getExamSessionsUrl();
        // commentare per testare online
        // decommentare per testare in locale
        // targetURL =
        // "http://localhost:8080/myunimol-webservices/pagine-target/elencoappelliUNIMOL.html";

        // recupero l'estrattore
        ExamSessionsExtractorInterface extractor = ExamSessionsExtractorManager
                .getExtractor();
        // Richiamo l'estrattore del manager e la funzione che effettua il
        // parsing della pagina/file
        // Il risultato è la lista di tutti gli appelli disponibili
        List<DetailedExamSession> examSessions;
        try {
            examSessions = extractor.getExamSessions(targetURL, username,
                    password);
            // conversione della "List" di ExamSession in json e stampa a video
            String json = gson.toJson(examSessions);
            writer.println("{\"result\":\"success\",\"exams\":" + json + "}</br></br><hr>");

            writer.println("<table><thead><th>ESAME</th><th>DATA</th><th>PRENOTA</th></thead><tbody>");
            for (DetailedExamSession exam : examSessions) {
                writer.println("<tr><td>" + exam.getName() + "</td><td>" + exam.getDate() + "</td><td>PRENOTA</td></tr>");
            }
            writer.println("</tbody></table></br>");
            writer.println("<form method=\"POST\" action=\"enrollExam\">");
            writer.println("<input type=\"hidden\" name=\"token\" value=\"13d0d64c9f4a4181728631b98ed75703\">");
            writer.println("<input type=\"hidden\" name=\"username\" value=\""+ username +"\">");
            writer.println("<input type=\"hidden\" name=\"password\" value=\""+ password +"\">");
            writer.println("<select name=\"exam-info\">");
            for (DetailedExamSession exam : examSessions) {
                writer.println("<option value=\"" + exam.getInfo() + "\">" + exam.getName() + "</option>");
            }
            writer.println("</select> <input type=\"submit\" value=\"PRENOTA\"></form>");

        } catch (UnirestException e) {
            e.printStackTrace();
            writer.println("{\"result\":\"failure\", \"msg\":\"unirest exception\"}");
        }
    }

}
