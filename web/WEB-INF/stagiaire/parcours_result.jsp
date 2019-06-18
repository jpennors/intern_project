<%-- 
    Document   : parcours_result
    Created on : 11 juin 2019, 17:35:08
    Author     : Josselin
--%>

<%@page import="model.Parcours"%>
<%@page import="java.sql.Date"%>
<%@page import="model.Questionnaire"%>
<%@page import="model.Question"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>

    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Parcours terminé</h1>
        
        <div class="card col-md-10 offset-md-1">
            <div class="card-body">
            <%
                Parcours parcours = (Parcours)request.getAttribute("parcours");  

                out.println(
                    "<p>" + parcours.getQuestionnaire_id().getSubject() + "<br>" +
                    "<strong>Résultat :</strong> " + parcours.getCount_good_answers() + "/" + parcours.getCount_answers() + "<br>" +
                    "<strong>Temps :</strong> " + parcours.durationToString() + "</p>"
                );
            %>
            </div>
            
            <%

                List<Question> questions = (List<Question>)request.getAttribute("questions");


                int index = 1;
                for (int i=0; i< questions.size(); i++){ 

                    if(questions.get(i).getStatus()){

                        out.println(
                            "<div class='alert alert-success' role='alert'>" +
                            "<h4>Question n°" + (index) + "</h4><br>" +
                            "<p>"+ questions.get(i).getSentence() + "</p>" 
                        );
                        for (int j=0; j<questions.get(i).get_reponses().size();j++){
                            if(questions.get(i).get_reponses().get(j).getValidity()){
                                out.println(
                                    "<div class='form-check'>" +
                                    "<label class='form-check-label' for='id" + questions.get(i).getSentence() + "-" +
                                    questions.get(i).get_reponses().get(j).getName() + "'>" +
                                    questions.get(i).get_reponses().get(j).getName() +
                                    "</label>"
                                    + "</div>" 
                                );
                            }
                        }
                        out.println("</div>");
                        index += 1;
                    }
                }
             %> 
            <button class='btn btn-lg btn-info mb-1 col-sm-2 offset-sm-5'>
                <a href='home' class='text-decoration-none text-reset'>Accueil</a>
            </button>
        </div>
    </body>
</html>