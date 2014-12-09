package it.unimol.my.news;

import it.unimol.my.utils.WebServiceServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet della funzionalità bacheca degli avvisi
 *
 * @author Carlo Branca
 */
@WebServlet(name = "NewsBoardServlet", urlPatterns = {"/getNewsBoard"})
public class NewsBoardServlet extends WebServiceServlet {

    @Override
    protected void serve(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //recupero l'estrattore
        NewsBoardExtractor newsBoardExtractor = new NewsBoardExtractor();
        //recupero l'url della bacheca
        String url = config.getInformaticaNewsBoardUrl();
        // estraggo il libretto degli esami        
        try {
            List<News> newsBoard = newsBoardExtractor.getNewsBoard(url);
            if (newsBoard == null) {
                String unknownErrorMsg = config.getMessage("unknownError");
                writer.println("{\"result\":\"failure\",\"msg\":\""
                        + unknownErrorMsg + "\"}");
                return;
            }
            // converto il libretto in json
            String json = gson.toJson(newsBoard);
            // stampo il json a video
            writer.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}
