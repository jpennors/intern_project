<%-- 
    Document   : parcours
    Created on : 28 mai 2019, 18:12:40
    Author     : Josselin
--%>

<%@page import="model.Question"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>

    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Parcours ${questionnaire.subject}</h1>
        <div class="row">
            <div class="card offset-sm-1 col-sm-10" style="width: 18rem;">
                <div class="card-body">
                    <form>
                        <%
                            List<Question> questions = (List<Question>)request.getAttribute("questions");
                            for (int i=0; i< questions.size(); i++){  

                                out.println(
                                    "<div class='alert alert-secondary' role='alert'>" +
                                    "<h4>Question n°" + (i+1) + "</h4><br>" +
                                    "<p>"+ questions.get(i).getSentence() + "</p>" 
                                );
                                for (int j=0; j<questions.get(i).get_reponses().size();j++){
                                    out.println(
                                        "<div class='form-check'>" +
                                        "<input class='form-check-input' type='radio' checked name='" + questions.get(i).getId_question().toString() +
                                        "' id='id" + questions.get(i).getSentence() + "-" + questions.get(i).get_reponses().get(j).getName() +
                                        "' value='" + questions.get(i).get_reponses().get(j).getId().toString() +"' >" +
                                        "<label class='form-check-label' for='id" + questions.get(i).getSentence() + "-" +
                                        questions.get(i).get_reponses().get(j).getName() + "'>" +
                                        questions.get(i).get_reponses().get(j).getName() +
                                        "</label></div>" 
                                    );
                                }
                                out.println("</div>");
                            }
                         %> 
                        <div class="row">
                            <button type="submit" class="btn btn-primary col-md-2 offset-md-5">Envoyer</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
