<%-- 
    Document   : index_questionnaire
    Created on : 2 avr. 2019, 15:00:11
    Author     : lolal
--%>

<%@page import="java.util.Hashtable"%>
<%@page import="model.Questionnaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>
    
    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Liste des questionnaires</h1>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
            
            <%
               Hashtable<Integer, Questionnaire> questionnaireTable=  (Hashtable<Integer, Questionnaire>)request.getAttribute("Questionnaires");
               for (int i=0; i< questionnaireTable.size(); i++){
                   out.println(questionnaireTable.get(i).getSubject());
                   out.println("</br>");
               }
            %> 
                </div>
            </div>
        </div>
    </body>
</html>
