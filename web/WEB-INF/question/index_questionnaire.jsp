<%-- 
    Document   : index_questionnaire
    Created on : 2 avr. 2019, 15:00:11
    Author     : lolal
--%>

<%@page import="java.util.List"%>
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
                    <table id="questionnaire" class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col"><i class="material-icons">arrow_drop_down</i> #</th>
                                <th scope="col"><i class="material-icons">arrow_drop_down</i> Sujet</th>
                                <th scope="col"><i class="material-icons">arrow_drop_down</i> Status</th>
                                <th scope="col">Action</th> 
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Questionnaire> questionnaires =  (List<Questionnaire>)request.getAttribute("questionnaires");
                                    for (int i=0; i< questionnaires.size(); i++){
                                        Integer num = i + 1;
                                        out.println("<tr>");
                                        out.println("<td scope='row'>" + num + "</td>");
                                        out.println("<td>" + questionnaires.get(i).getSubject() + "</td>");
                                        out.println("<td>" + questionnaires.get(i).getStatus_name() + "</td>" );
                                        //edit
                                        out.println("<form action='edit_questionnaire' method='get'>");
                                        out.println("<input type='hidden' name='id_questionnaire' value='" + questionnaires.get(i).getId_questionnaire() + "'>");
                                        out.println("<td><button class='btn btn-info' type='submit'>Editer</button>");
                                        out.println("</form>");
                                        //delete
                                        if(questionnaires.get(i).getStatus()){
                                            out.println("<form action='delete_questionnaire' method='post'>");
                                            out.println("<input type='hidden' name='id_questionnaire' value='" + questionnaires.get(i).getId_questionnaire() + "'>");
                                            out.println("<button type='submit' class='btn btn-info' href='delete_questionnaire?id='" + questionnaires.get(i).getId_questionnaire() + ">Supprimer</button></td>");
                                            out.println("</form>");
                                            out.println("</tr>");
                                        }
               }
                             %> 
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function() {
                $('#questionnaire').DataTable();
            } );
        </script>
    </body>
</html>
