<%-- 
    Document   : edit_question
    Created on : 22 mai 2019, 14:27:04
    Author     : lolal
--%>

<%@page import="model.Response"%>
<%@page import="java.util.List"%>
<%@page import="model.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>
    
    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Editer la question: ${question.sentence}</h1>
        <h3 style="text-align: center; margin: 40px">Réponses de la question</h3>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <table id="reponse" class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col"><i class="material-icons">arrow_drop_down</i> #</th>
                                <th scope="col"><i class="material-icons">arrow_drop_down</i> Sujet</th>
                                <th scope="col"><i class="material-icons">arrow_drop_down</i> Status</th>
                                <th scope="col"><i class="material-icons">arrow_drop_down</i> Validité</th>
                                <th scope="col">Action</th> 
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Response> responses =  (List<Response>)request.getAttribute("responses");
                                Integer id_questionnaire = (Integer)request.getAttribute("id_questionnaire");
                                    for (int i=0; i< responses.size(); i++){
                                        Integer num = i + 1;
                                        out.println("<tr>");
                                        out.println("<td scope='row'>" + num + "</td>");
                                        out.println("<td>" + responses.get(i).getName() + "</td>");
                                        out.println("<td>" + responses.get(i).getStatus_name() + "</td>" );
                                        out.println("<td>" + responses.get(i).getValidity_name() + "</td>" );
                                        //edit
                                        out.println("<form action='edit_response' method='get'>");
                                        out.println("<input type='hidden' id='inputId' name='id_questionnaire' value='" + id_questionnaire + "'>");
                                        out.println("<input type='hidden' name='id_response' value='" + responses.get(i).getId() + "'>");
                                        out.println("<td><button class='btn btn-info' type='submit'>Editer</button>");
                                        out.println("</form>");
                                        //delete
                                        if(responses.get(i).getStatus() && !responses.get(i).getValidity()){
                                            out.println("<form action='delete_response' method='post'>");
                                            out.println("<input type='hidden' id='inputId' name='id_questionnaire' value='" + id_questionnaire + "'>");
                                            out.println("<input type='hidden' name='id_response' value='" + responses.get(i).getId() + "'>");
                                            out.println("<button type='submit' class='btn btn-info' href='delete_question?id='" + responses.get(i).getId() + ">Supprimer</button></td>");
                                            out.println("</form>");
                                        }
                                        out.println("</tr>");
                                    }
                             %> 
                        </tbody>
                    </table>
                </div>
            </div>
        </div> 
        <h3 style="text-align: center; margin: 40px">Ajout d'une réponse</h3>                
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="add_response" method="post">
                                <div class="form-group row">
                                    <input type="hidden" id="inputId" name="id_question" value=${question.id_question}>
                                    <input type="hidden" id="inputId" name="id_questionnaire" value=${id_questionnaire}>
                                    <label for="inputName" class="col-sm-4 col-form-label">Intitulé</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="inputSubject" name="name">
                                    </div>
                                </div>
                                <div class="form-group row">    
                                    <label for="inputName" class="col-sm-4 col-form-label">Validité</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="inputType" name="validity">
                                            <option value="true">Vraie</option>
                                            <option value="false">Fausse</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">    
                                    <label for="inputName" class="col-sm-4 col-form-label">Ordre</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="inputType" name="order">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                        </select>
                                    </div>
                                </div>      
                                <button type="submit" class="btn btn-primary">Envoyer</button>
                        </form>
                    </div>                
                </div>
            </div>
        </div>                
        <h3 style="text-align: center; margin: 40px">Informations de la question</h3>                
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="edit_question" method="post">
                                <div class="form-group row">
                                    <input type="hidden" id="inputId" name="id_question" value=${question.id_question}>
                                    <input type="hidden" id="inputId" name="id_questionnaire" value=${id_questionnaire}>
                                    <label for="inputSentence" class="col-sm-4 col-form-label">Intitulé</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="inputSubject" name="sentence" value="${question.sentence}">
                                    </div>
                                </div>
                                <div class="form-group row">    
                                    <label for="inputStatus" class="col-sm-4 col-form-label">Status</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="inputType" name="status" value=${question.status}>
                                            <option value="true" ${question.status == true?'selected':''}>Actif</option>
                                            <option value="false"${question.status == false?'selected':''}>Inactif</option>
                                        </select>
                                    </div>
                                </div>      
                                <button type="submit" class="btn btn-primary">Envoyer</button>
                        </form>
                    </div>                
                </div>
            </div>
        </div>  
        <script>
            $(document).ready(function() {
                $('#reponse').DataTable();
            } );
        </script>
    </body>
</html>

