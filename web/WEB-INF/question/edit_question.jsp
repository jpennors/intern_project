<%-- 
    Document   : edit_question
    Created on : 22 mai 2019, 14:27:04
    Author     : lolal
--%>

<%@page import="model.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>
    
    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Editer une question</h1>
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
    </body>
</html>

