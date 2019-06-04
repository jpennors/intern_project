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
        <h1 style="text-align: center; margin: 40px">Editer la réponse: ${r.name}</h1>
        <h3 style="text-align: center; margin: 40px">Informations de la réponse</h3>                
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="edit_response" method="post">
                                <div class="form-group row">
                                    <input type="hidden" id="inputId" name="id" value=${r.id}>
                                    <input type="hidden" id="inputId" name="id_questionnaire" value=${id_questionnaire}>
                                    <input type="hidden" id="inputId" name="id_question" value=${r.question_id}>
                                    <label for="inputSentence" class="col-sm-4 col-form-label">Intitulé</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="inputName" name="name" value="${r.name}">
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
                                    <label for="inputValidite" class="col-sm-4 col-form-label">Validité</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="inputValidity" name="validity" value=${r.validity}>
                                            <option value="true" ${r.validity == true?'selected':''}>Valide</option>
                                            <option value="false"${r.validity == false?'selected':''}>Non valide</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputOrder" class="col-sm-4 col-form-label">Ordre</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="inputOrder" name="order" value="${r.order}">
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
