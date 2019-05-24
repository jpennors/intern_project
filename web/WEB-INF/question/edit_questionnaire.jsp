<%-- 
    Document   : edit_questionnaire
    Created on : 3 avr. 2019, 14:27:43
    Author     : lolal
--%>

<%@page import="java.util.List"%>
<%@page import="model.Question"%>
<%@page import="model.Questionnaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>
    
    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Editer un questionnaire</h1>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="edit_questionnaire" method="post">
                                <div class="form-group row">
                                    <input type="hidden" id="inputId" name="id_questionnaire" value=${questionnaire.id_questionnaire}>
                                    <label for="inputSubject" class="col-sm-4 col-form-label">Sujet</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="inputSubject" name="subject" value="${questionnaire.subject}">
                                    </div>
                                </div>
                                <div class="form-group row">    
                                    <label for="inputStatus" class="col-sm-4 col-form-label">Status</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="inputType" name="status" value=${questionnaire.status}>
                                            <option value="true" ${questionnaire.status == true?'selected':''}>Actif</option>
                                            <option value="false"${questionnaire.status == false?'selected':''}>Inactif</option>
                                        </select>
                                    </div>
                                </div>
                        </form>
                    </div>                
                </div>
            </div>
        </div>
        <h3 style="text-align: center; margin: 40px">Questions du questionnaire:</h3>                             
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
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="edit_questionnaire" method="post">
                                <div class="form-group row">
                                    <label for="inputSubject" class="col-sm-4 col-form-label">Nouvelle Question</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="inputSubject" name="subject" value="Ajout question TO DO">
                                    </div>
                                </div>        
                                <button type="submit" class="btn btn-primary">Envoyer</button>
                        </form>
                    </div>                
                </div>
            </div>
        </div>                
    </body>
</html>
