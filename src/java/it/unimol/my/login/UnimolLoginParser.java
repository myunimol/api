package it.unimol.my.login;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.requesterhtml.HTMLRequester;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.my.utils.StringUtils;

/**
 * Classe che fa il parser per il login di esse3 Unimol
 *
 * @author Ivan Di Rienzo
 */
public class UnimolLoginParser implements LoginParser {

    @Override
    public UserInformation getLoginInformation(String username, String password) throws UnirestException {

        HTMLRequester requester = new HTMLRequester();
        try {

            String resPage = requester.get(new URL(ConfigurationManager.getInstance().getLogonUrl()),
                    username, password);

            Document doc = Jsoup.parse(resPage);

            if (!this.isLogged(doc)) {

                return null; // login non riuscito

            } else {

                UserInformation uInfo = this.parsingUserInfo(doc);

                // Prende la matricola da un'altra pagina (non login)
                uInfo.setStudentID(this.getStudentID(username, password));

                return uInfo;
            }

        } catch (MalformedURLException ex) {
            return null;
        }

    }

    /**
     * Metodo privato che fa il parsing dell'html e restituisce un
     * UserInformation
     *
     * @param doc il Document da parsare
     * @return Le informazioni dell'utente
     */
    private UserInformation parsingUserInfo(Document doc) {

        UserInformation uInfo = new UserInformation();

        // Getting Name And Surname
        Element name = doc.select("description").first();
        String[] nameAndSurname = name.html().split("\\u0026nbsp;");
        uInfo.setName(nameAndSurname[0]);
        uInfo.setSurname(nameAndSurname[1]);

        // Getting Class
        Element studentClass = doc.select(
                "#gu-textStatusStudenteCorsoFac-text-link2").first();
        Element studentCurrClass = doc.select("p.box-cfu-p description").get(1);
        uInfo.setStudentClass(studentClass.text() + " "
                + studentCurrClass.text() + " ANNO");

        // Getting additional information
        Elements addInform = doc.select("table[summary$=studente] td");
        /* scorre gli elementi td memorizzando il precedente, se il precedente
         * e' l'indicazione di una informazione utile allora vuol dire che
         * quello attuale e' l'informazione da recuperare
         */
        String precedente = "";
        for (Element tableCell : addInform) {
            if (precedente.equals("Tasse")) { // situazione tasse es: regolare
                uInfo.setTaxes(tableCell.text());
            } else if (precedente.equals("Piano carriera")) {
                // se il piano carriera e' modificabile
                uInfo.setCareerPlan(tableCell.text());
            } else if (precedente.equals("Appelli disponibili")) {
                // quanti appelli disponibili ci sono
                uInfo.setAvailableExams(tableCell.text());
            } else if (precedente.equals("Iscrizioni appelli")) {
                // a quanti appelli lo studente e' iscritto
                uInfo.setEnrolledExams(tableCell.text());
            }
            precedente = tableCell.text();
        }

        // getting course
        String course = doc.select("p[id=gu-textStatusStudenteCorsoFac").first().child(0).text();
        uInfo.setCourse(course);

        // getting department
        String department = doc.select("p[id=gu-textStatusStudenteCorsoFac").first().child(1).text();
        uInfo.setDepartment(department);

        // getting course path
        String coursePath = doc.select("p[id=gu-textStatusStudenteCorsoFac").first().child(2).text();
        uInfo.setCoursePath(coursePath);

        // getting course length
        String courseLengthUntrimmed = doc.select("p[class=box-cfu-p]").first().child(0).text();
        String courseLength = StringUtils.realTrim(courseLengthUntrimmed);
        uInfo.setCourseLength(courseLength);

        // getting registration date
        String registrationDate = doc.select("p[id=gu-textStatusStudenteImma").first().child(0).text();
        uInfo.setRegistrationDate(registrationDate);

        return uInfo;
    }

    /**
     * Metodo privato che controlla se il login ha avuto successo
     *
     * @param doc il Document da parsare
     * @return TRUE se il login ha avuto successo FALSE altrimenti
     */
    private boolean isLogged(Document doc) {
        Element firstMeta = doc.select("meta").first();

        /* quando il login non ha successo si ottiene una pagina con tag meta
         * per il redirect
         */
        if (firstMeta.attr("http-equiv").equals("refresh")) {
            // login non riuscito
            return false;
        }

        return true;
    }

    /**
     * Recupera la matricola da un'altra pagina! dato che non e' presente nella
     * home di esse3
     *
     * @return lo studentID
     */
    private String getStudentID(String username, String password)
            throws MalformedURLException, UnirestException {

        HTMLRequester req = new HTMLRequester();
        String resPage = req.get(new URL(ConfigurationManager.getInstance().getRecordBookUrl()),
                username, password);

        Document doc = Jsoup.parse(resPage);
        Element name = doc.select("div.titolopagina").first();

        /**
         * dato che la stringa ha questa forma: "Libretto di : IVAN DI RIENZO -
         * [MAT. 148432]" elimina la parentesi chiusa e infine splitta dividendo
         * da "MAT. " e prende la seconda stringa ottenuta cioe' la matricola
         */
        String ID = name.text().replaceAll("]", "").split("MAT. ")[1];

        return ID;
    }

}
