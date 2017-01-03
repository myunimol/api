package it.unimol.my.utils;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.tokenmanagement.TokenManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WebServiceServlet extends HttpServlet {

    /**
     * Lo uid seriale della versione.
     */
    private static final long serialVersionUID = 6356032067166704623L;

    /**
     * Una istanza di Gson che serve per tramutare oggetti java POJO in stringa
     * json.
     */
    protected Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy")
            .setPrettyPrinting()
            .create();
    /**
     * Istanza del configuration handler che permette di recuperare tutte le
     * informazioni base necessarie all'applicazione
     */
    protected ConfigurationManager config = ConfigurationManager.getInstance();
    /**
     * Istanza del gestore dei token. Serve a verificare la validità del token.
     */
    protected TokenManager tokenManager = TokenManager.getInstance();

    /**
     * Il token utilizzato per il dialogo coi client
     */
    protected String token;

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        this.setHeaders(resp);
        String noGetRequestMsg = config.getMessage("noGetRequest");
        resp.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
        writer = resp.getWriter();
        writer.print("{\"result\":\"failure\", \"msg\":\"" + noGetRequestMsg
                + "\"}");
        writer.close();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.setHeaders(resp);
        // ottengo un writer dalla response
        if (tokenIsValid(req, resp)) {
            this.serve(req, resp);
        }
    }

    /**
     * Metodo che controlla se il token è valido. Se non lo è stampa un
     * messaggio di errore.
     *
     * @param req La richiesta http.
     * @param resp La risposta http.
     * @return Un boolean che indica se il token è valido o meno.
     * @throws IOException Nel caso in cui non si riesce a recuperare il
     * PrintWriter dalla response.
     */
    protected boolean tokenIsValid(HttpServletRequest req,
            HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        boolean tokenIsValid = false;
        // Ottengo il token
        token = req.getParameter("token");

        // controllo token
        if (token == null || token.length() < 16
                || !tokenManager.tokenExists(token)) {
            String invalidTokenMsg = config.getMessage("invalidToken");
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            writer.println("{\"result\":\"failure\",\"msg\":\""
                    + invalidTokenMsg + "\"}");
            tokenIsValid = false;
        } else {
            tokenIsValid = true;
        }
        return tokenIsValid;
    }

    protected void serve(HttpServletRequest req,
            HttpServletResponse resp) throws IOException {
        System.err.println("Called empty serve method in WebServiceServlet");
    }

    protected void setHeaders(HttpServletResponse response) {
        // imposto il character encoding
        response.setCharacterEncoding("utf8");
        // setto il tipo del contenuto
        response.setContentType("application/json; charset=UTF-8");
        // abilito le richieste cors
        response.addHeader("Access-Control-Allow-Origin", "*");
    }

}
