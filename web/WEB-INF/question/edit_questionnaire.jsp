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
