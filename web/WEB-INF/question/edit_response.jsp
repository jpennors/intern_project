<%-- 
    Document   : edit_response
    Created on : 3 juin 2019, 15:57:45
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
        <h1 style="text-align: center; margin: 40px">Editer la réponse ${response.sentence}</h1>
        <h3 style="text-align: center; margin: 40px">Informations de la réponse</h3>                
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
                                        <input class="form-control" id="inputSubject" name="sentence" value="${r.name}">
                                    </div>
                                </div>
                                <div class="form-group row">    
                                    <label for="inputStatus" class="col-sm-4 col-form-label">Status</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="inputType" name="status" value=${r.status}>
                                            <option value="true" ${r.status == true?'selected':''}>Actif</option>
                                            <option value="false"${r.status == false?'selected':''}>Inactif</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputSentence" class="col-sm-4 col-form-label">Validité</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="inputType" name="validity" value=${r.validity}>
                                            <option value="true" ${r.validity == true?'selected':''}>Actif</option>
                                            <option value="false"${r.validity == false?'selected':''}>Inactif</option>
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
