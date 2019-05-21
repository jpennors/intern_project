<%-- 
    Document   : edit_questionnaire
    Created on : 3 avr. 2019, 14:27:43
    Author     : lolal
--%>

<%@page import="model.Questionnaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>
    
    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Questionnaire</h1>
        <h2 style="text-align: center; margin: 40px"> 
            Sujet : 
            <%
                   Questionnaire questionnaire = (Questionnaire)request.getAttribute("questionnaire");
                   out.println(questionnaire.getSubject());
            %>
        </h2>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <form action="create_questionnaire" method="post">
                            <div class="form-group row">
                                <label for="inputSubject" class="col-sm-4 col-form-label">Nouvelle question</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputQuestion" name="question">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Envoyer</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
