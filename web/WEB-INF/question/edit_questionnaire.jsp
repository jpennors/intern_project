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
        <h1 style="text-align: center; margin: 40px">Editer le questionnaire: ${questionnaire.subject}</h1>
        <h3 style="text-align: center; margin: 40px">Questions du questionnaire:</h3>                             
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <table id="question" class="table">
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
                                List<Question> questions =  (List<Question>)request.getAttribute("questions");
                                    for (int i=0; i< questions.size(); i++){
                                        Integer num = i + 1;
                                        out.println("<tr>");
                                        out.println("<td scope='row'>" + num + "</td>");
                                        out.println("<td>" + questions.get(i).getSentence() + "</td>");
                                        out.println("<td>" + questions.get(i).getStatus_name() + "</td>" );
                                        //edit
                                        out.println("<form action='edit_question' method='get'>");%>
                                        <input type="hidden" id="inputId" name="id_questionnaire" value=${questionnaire.id_questionnaire}>
                                        <%
                                        out.println("<input type='hidden' name='id_question' value='" + questions.get(i).getId_question() + "'>");
                                        out.println("<td><button class='btn btn-info' type='submit'>Editer</button>");
                                        out.println("</form>");
                                        //delete
                                        if(questions.get(i).getStatus()){
                                            out.println("<form action='delete_question' method='post'>");%>
                                            <input type="hidden" id="inputId" name="id_questionnaire" value=${questionnaire.id_questionnaire}>
                                            <%
                                            out.println("<input type='hidden' name='id_question' value='" + questions.get(i).getId_question() + "'>");
                                            out.println("<button type='submit' class='btn btn-info' href='delete_question?id='" + questions.get(i).getId_question() + ">Supprimer</button></td>");
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
        <h3 style="text-align: center; margin: 40px">Ajout de nouvelles questions:</h3>                 
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                   <form action="create_question" method="get">
                            <div class="form-group row">
                                <input type="hidden" id="inputId" name="id_questionnaire" value=${questionnaire.id_questionnaire}>
                            </div>        
                            <button type="submit" class="btn btn-primary">Créer une nouvelle question</button>
                    </form>
                </div>
            </div>
        </div>
        <br>                    
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <form action="add_question" method="post">
                            <div class="form-group row">    
                                    <label for="inputStatus" class="col-sm-4 col-form-label">Questions existantes</label>
                                    <input type="hidden" id="inputId" name="id_questionnaire" value=${questionnaire.id_questionnaire}>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="inputQuestions" name="id_question">
                                            <% 
                                                List<Question> all_questions =  (List<Question>)request.getAttribute("all_questions");
                                                for(int i=0; i <all_questions.size(); i ++)
                                                {
                                                    Question q = all_questions.get(i);
                                                    System.out.println(q.getSentence());
                                                    out.println("<option value=" +q.getId_question()+">"+q.getSentence()+"</option>");
                                                }
                                            %>            
                                        </select>
                                    </div>
                                </div>     
                            <button type="submit" class="btn btn-primary">Ajouter la question</button>
                    </form>        
                            
                </div>
            </div>
        </div>                                
        <h3 style="text-align: center; margin: 40px">Informations du questionnaire:</h3>                 
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
                                <button type="submit" class="btn btn-primary">Envoyer</button>
                        </form>
                    </div>                
                </div>
            </div>
        </div>   
        <script>
            $(document).ready(function() {
                $('#question').DataTable();
            } );
        </script>
    </body>
</html>
