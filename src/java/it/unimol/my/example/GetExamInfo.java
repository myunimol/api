/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.example;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Carlo
 */
@WebServlet(name = "GetExamInfo", urlPatterns = {"/get-exam-info"})
public class GetExamInfo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String url = "https://unimol.esse3.cineca.it/Guide/PaginaADErogata.do;jsessionid=5E36E5C2D23CFDEBC1F5992D516B1ADD.jvm_unimol_esse3web01?ad_er_id=2014*N0*N0*S1*6634*31306497&ANNO_ACCADEMICO=2014&mostra_percorsi=S";
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);

        PrintWriter out = response.getWriter();

        Element cfuTag = doc.select("#header-1 > p").get(0);
        String cfu = cfuTag.text();

        Element goalTag = doc.select("#header-2 > p").get(0);
        String goal = goalTag.text();

        Element bookTag = doc.select("#header-3 > p").get(0);
        String book = bookTag.text();

        Map<String, String> result = new HashMap<String, String>();
        result.put("cfu", cfu);
        result.put("goal", goal);
        result.put("book", book);

        Gson gson = new Gson();
        out.print(gson.toJson(result));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
