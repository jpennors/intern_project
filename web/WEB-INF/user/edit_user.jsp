<%-- 
    Document   : edit_user
    Created on : 30 avr. 2019, 18:18:25
    Author     : Josselin
--%>

<%-- 
    Document   : create_user
    Created on : 3 avr. 2019, 14:37:32
    Author     : Josselin
--%>

<%@page import="java.util.Hashtable"%>
<%@page import="model.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Intern Project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </head>

    <body>
        <h1 style="text-align: center; margin: 40px">Editer un utilisateur</h1>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="edit_user" method="post">
                            <div class="form-group row">
                                <input type="hidden" id="inputId" name="id_user" value=${user.id_user}>
                                <label for="inputName" class="col-sm-4 col-form-label">Nom</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputNameUser" name="name_user" value=${user.name_user}>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputFirstName" class="col-sm-4 col-form-label"}>Prénom</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputFirstName" name="first_name" value=${user.first_name}>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPhone" class="col-sm-4 col-form-label">Téléphone</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputPhone" name="phone" value=${user.phone}>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputEmail" class="col-sm-4 col-form-label">Email</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputEmail" name="email" value=${user.email}>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputType" class="col-sm-4 col-form-label">Type</label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="inputType" name="isAdmin" value=${user.is_admin}>
                                        <option value="false" ${user.is_admin == false?'selected':''}>Stagiaire</option>
                                        <option value="true" ${user.is_admin == true?'selected':''}>Admin</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputType" class="col-sm-4 col-form-label">Statut</label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="inputType" name="status" value=${user.status}>
                                        <option value="true" ${user.status == true?'selected':''}>Actif</option>
                                        <option value="false"${user.status == false?'selected':''}>Inactif</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputCompany" class="col-sm-4 col-form-label">Entreprise (Immatriculation)</label>
                                <div class="col-sm-8">
                                    <select class='form-control' id='inputCompany' name='company' value=${user.company.matriculation}>
                                    
                                    <%
                                        Hashtable<Integer, Company> companiesTable = (Hashtable<Integer, Company>)request.getAttribute("Companies");
                                        Integer selected_company = (Integer)request.getAttribute("selected_company");

                                        for (int i=0; i< companiesTable.size(); i++){     

                                            out.println("<option value='" + companiesTable.get(i).getMatriculation() + "'");
                                            if(companiesTable.get(i).getMatriculation() == selected_company){
                                                out.println("selected");
                                            }
                                            out.println(">" + companiesTable.get(i).getName_company()+ " (" + companiesTable.get(i).getMatriculation() + ")</option>");
                                            //companiesTable.get(i).getMatriculation() == selected_company
                                        }

                                     %> 
                                    </select>
                                   
                                   
                                </div>
                            </div>                             
                            <button type="submit" class="btn btn-primary">Mettre à jour</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>			
    </body>
</html>