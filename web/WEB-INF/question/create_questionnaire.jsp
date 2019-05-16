<%-- 
    Document   : create_questionnaire
    Created on : 2 avr. 2019, 14:29:53
    Author     : lolal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="section/html_head.jsp" %>
    
    <body>
        <%@ include file="section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Ajouter un questionnaire</h1>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="create_questionnaire" method="post">
                            <div class="form-group row">
                                <label for="inputSubject" class="col-sm-4 col-form-label">Sujet</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputSubject" name="subject">
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
