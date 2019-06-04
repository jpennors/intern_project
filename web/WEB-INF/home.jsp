<%-- 
    Document   : home
    Created on : 16 mai 2019, 12:07:34
    Author     : Josselin
--%>

<%@page import="model.Questionnaire"%>
<%@page import="model.Parcours"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@ include file="section/html_head.jsp" %>
    
    <body>
        
        <%@ include file="section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Bonjour ${logged_user.first_name}</h1>
        <div class="row">
            <div class="card offset-sm-1 col-sm-10" style="width: 18rem;">
                <div class="card-body">
                    <%
                        //boolean is_admin = Boolean.parseBoolean(request.getAttribute("is_admin").toString());
                        if (is_admin){
                            out.println("<div class='row'>" +
                                "<button class='btn btn-lg btn-info mb-1 col-sm-4 offset-sm-4'>" +
                                "<a href='create_user' class='text-decoration-none text-reset'>Créer un utilisateur</a>" +
                                "</button></div><br><div class='row'>" +
                                "<button class='btn btn-lg btn-info mb-1 col-sm-4 offset-sm-4'>" +
                                "<a href='users' class='text-decoration-none text-reset'>Lister les utilisateurs</a>" +
                                "</button></div><br><div class='row'>" +
                                "<button class='btn btn-lg btn-info mb-1 col-sm-4 offset-sm-4'>" +
                                "<a href='questionnaires' class='text-decoration-none text-reset'>Lister les questionnaires</a>" +
                                "</button></div><br><div class='row'>" +
                                "<button class='btn btn-lg btn-info mb-1 col-sm-4 offset-sm-4'>" +
                                "<a href='create_questionnaire' class='text-decoration-none text-reset'>Créer un questionnaire</a>" +
                                "</button></div><br><div class='row'>" +
                                "<button class='btn btn-lg btn-info mb-1 col-sm-4 offset-sm-4'>" +
                                "<a href='questions' class='text-decoration-none text-reset'>Lister les questions</a>" +
                                "</button></div><br><div class='row'>" +
                                "<button class='btn btn-lg btn-info mb-1 col-sm-4 offset-sm-4'>" +
                                "<a href='create_question' class='text-decoration-none text-reset'>Créer une question</a>" +
                                "</button></div>"
                            );
                        } else {
                            out.println(
                                "<div class='row'>" +
                                "<div class='col-md-6'>" +
                                "<div class='card' style='width: 18rem;'>" +
                                "<div class='card-body'>" +
                                "<h5 class='card-title'>Mes parcours</h5>" +
                                "<p class='card-text'></p>"
                            );

                            List<Parcours> parcours = (List<Parcours>)request.getAttribute("parcours");
                            for (int i=0; i< parcours.size(); i++){ 
                                
                                out.println(
                                    "<div class='alert alert-secondary' role='alert'><h6>" +
                                    parcours.get(i).getQuestionnaire_id().getSubject() +
                                    "</h6><br><span><strong>Réponse : </strong>" +
                                    parcours.get(i).getCount_good_answers().toString() +
                                    "/" +
                                    parcours.get(i).getCount_answers() +
                                    "</div>"
                                );
                            }
                            out.println("</div>"+
                                "</div>" +
                                "</div>" +
                                "<div class='col-md-6'>" +
                                "<div class='card' style='width: 18rem;'>" +
                                "<div class='card-body'>" +
                                "<h5 class='card-title'>Mes statistiques</h5>" +
                                "<p class='card-text'></p>"+
                                "<a href='#' class='card-link'>Card link</a>"+
                                "</div>"+
                                "</div>" +
                                "</div>" +
                                "</div><br>" +
                                "<form action='parcours'><div class='row' method='post'>" +
                                "<select class='form-control col-md-4 offset-md-4' id='inputQuestionnaire' name='questionnaire' required>"
                            );
                        
                            List<Questionnaire> questionnaires = (List<Questionnaire>)request.getAttribute("questionnaires");
                            for (int i=0; i< questionnaires.size(); i++){     
                                out.println("<option value='" + questionnaires.get(i).getId_questionnaire() + "' selected>" + questionnaires.get(i).getSubject() + "</option>");
                            }
                        }
                     %>
                    </select>
                    </div>
                    <br>
                    <div class="row">
                        <button type="submit" class="col-md-2 offset-md-5 btn btn-info">
                            Commencer un parcours
                         </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>			
    </body>
</html>