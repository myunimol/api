<%-- 
    Document   : index
    Created on : 20-nov-2015, 21.11.04
    Author     : Giuseppe
--%>

<%@page import="it.unimol.my.examsession.DetailedExamSession"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String username = request.getParameter("user");
    String password = request.getParameter("password");
    String examSessions = request.getParameter("exams");
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
            <p>Esami: <%= examSessions %></p>
            <h3>Elenco appelli</h3>
            <form id="exam-sessions-form" action="../getExamSessions" method="POST">
                Token <input type="text" name="token" value="13d0d64c9f4a4181728631b98ed75703" /><br>
                Username <input type="text" name="username" value="<%= username %>"/><br>
                Password <input type="password" name="password" value="<%= password %>"/><br>
                <input type="submit" value="submit">
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
