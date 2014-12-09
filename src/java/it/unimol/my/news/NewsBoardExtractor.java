package it.unimol.my.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Classe che estrae le news dalla bacheca degli avvisi di un corso
 *
 * @author Carlo Branca
 */
public class NewsBoardExtractor implements NewsBoardExtractorInterface {

    @Override
    public List<News> getNewsBoard(String url) {

        List<News> newsList = new ArrayList<News>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements html = doc.select("div[class=testo]");

            // Estraggo le date delle news
            String newsDates = html.select("strong:matches([0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]),"
                    + "b:matches([0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9])").text();
            String[] arrayDates = newsDates.split("\\s");

            // Estraggo il testo delle news
            String newsBodies = html.select("div").text();
            String[] arrayBodies = newsBodies.split("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");

            // Creo la lista di news
            for (int i = 0; i < arrayDates.length; i++) {
                /*
                 * L'index di arrayBodies è shiftato di 1 perché quando vengono estratti i 
                 * corpi delle news, Jsoup estrae anche un div vuoto a inizio pagina.
                 */
                //arrayBodies[i+1]=StringUtils.realTrim(arrayBodies[i+1]);
                arrayBodies[i + 1] = arrayBodies[i + 1].replace(String.valueOf((char) 160), " ").trim();
                News news = new News(arrayDates[i], arrayBodies[i + 1]);
                newsList.add(news);
            }

        } catch (IOException ex) {
            Logger.getLogger(NewsBoardExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newsList;
    }

}
