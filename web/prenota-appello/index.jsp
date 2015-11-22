<%-- 
    Document   : index
    Created on : 20-nov-2015, 21.11.04
    Author     : Giuseppe
--%>

<%@page import="jdk.nashorn.internal.parser.JSONParser"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="it.unimol.my.examsession.ExamSessionsExtractorManager"%>
<%@page import="com.mashape.unirest.http.exceptions.UnirestException"%>
<%@page import="it.unimol.my.examsession.ExamSessionsExtractorInterface"%>
<%@page import="it.unimol.my.config.ConfigurationManager"%>
<%@page import="it.unimol.my.examsession.DetailedExamSession"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String username = "g.zarrilli";
    String password = "W5WKBJWD";
    String targetURL = ConfigurationManager.getInstance()
            .getExamSessionsUrl();
    ExamSessionsExtractorInterface extractor = ExamSessionsExtractorManager
            .getExtractor();
    List<DetailedExamSession> examSessions = null;
    try {
        examSessions = extractor.getExamSessions(targetURL, username,
                password);
    } catch (UnirestException e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Enroll exam driver page</title>
    </head>
    <body>
        <p>This is a driver page. Submit the desired form to see results.</p>
        <p>Questa &egrave; una pagina driver. Sottometti il form desiderato
            per osservare i risultati.</p>
        <hr>
        <br>
        <div>    
            <form action="../enrollExam" method="POST">
                <input type="text" name="token" value="13d0d64c9f4a4181728631b98ed75703" /><br>
                <input type="text" name="username" value="g.zarrilli"><br>
                <input type="text" name="password" value="W5WKBJWD"><br>
                <select name="exam-id">
                    <%
                        for (DetailedExamSession exam : examSessions) {
                    %>
                    <option value='<%= exam.getInfo().toJson() %>'><%= exam.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <input type="submit" value="PRENOTA">
            </form>
        </div>
        <br>
        <br>
        <br>
        <br>
        <hr>
        <p>
            If you want to contribute developing please click here to go to the <a
                href="https://github.com/cbranca/myunimol-webservices">GitHub
                repository</a>
        </p>
        <p>
            Se vuoi contribuire allo sviluppo perfavore clicca qui per andare alla
            <a href="https://github.com/cbranca/myunimol-webservices">repository
                GitHub</a>
        </p>
    </body>
</html>
