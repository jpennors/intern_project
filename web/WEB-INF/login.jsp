<%-- 
    Document   : login
    Created on : 15 mai 2019, 15:23:12
    Author     : Josselin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="section/html_head.jsp" %>

    <body>
        <h1 style="text-align: center; margin: 40px">Connexion</h1>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="login" method="post">
                            <div class="form-group row">
                                <label for="inputEmail" class="col-sm-4 col-form-label">Email</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputEmail" name="email">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-sm-4 col-form-label">Mot de passe</label>
                                <div class="col-sm-8">
                                    <input type=password" class="form-control" id="inputPassword" name="password">
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
