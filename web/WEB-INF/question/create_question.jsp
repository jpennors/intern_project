<%-- 
    Document   : create_question
    Created on : 22 mai 2019, 14:51:20
    Author     : lolal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>
    
    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Ajouter une question</h1>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="create_question" method="post">
                            <div class="form-group row">
                                <label for="inputSentence" class="col-sm-4 col-form-label">Intitulé</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputSubject" name="sentence">
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

