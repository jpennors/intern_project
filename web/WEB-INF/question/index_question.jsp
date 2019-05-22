<%-- 
    Document   : index_question
    Created on : 22 mai 2019, 11:58:30
    Author     : lolal
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Hashtable"%>
<%@page import="model.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>
    
    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Liste des questions</h1>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Sujet</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th> 
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Question> questions =  (List<Question>)request.getAttribute("questions");
                                    for (int i=0; i< questions.size(); i++){
                                        Integer num = i + 1;
                                        out.println("<tr>");
                                        out.println("<td scope='row'>" + num + "</td>");
                                        out.println("<td>" + questions.get(i).getSentence() + "</td>");
                                        out.println("<td>" + questions.get(i).getStatus_name() + "</td>" );
                                        //edit
                                        out.println("<form action='edit_question' method='get'>");
                                        out.println("<input type='hidden' name='id_question' value='" + questions.get(i).getId_question() + "'>");
                                        out.println("<td><button class='btn btn-info' type='submit'>Editer</button>");
                                        out.println("</form>");
                                        //delete
                                        out.println("<form action='delete_question' method='post'>");
                                        out.println("<input type='hidden' name='id_question' value='" + questions.get(i).getId_question() + "'>");
                                        out.println("<button type='submit' class='btn btn-info' href='delete_question?id='" + questions.get(i).getId_question() + ">Supprimer</button></td>");
                                        out.println("</form>");
                                        out.println("</tr>");
                                    }
                             %> 
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
