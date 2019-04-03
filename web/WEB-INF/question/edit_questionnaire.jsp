<%-- 
    Document   : edit_questionnaire
    Created on : 3 avr. 2019, 14:27:43
    Author     : lolal
--%>

<%@page import="model.Questionnaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8">
        <title>Intern Project</title>
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </head>
    <body>
        <h1 style="text-align: center; margin: 40px">Questionnaire</h1>
        <h2 style="text-align: center; margin: 40px"> 
            Sujet : 
            <%
                   Questionnaire questionnaire = (Questionnaire)request.getAttribute("Questionnaire");
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
