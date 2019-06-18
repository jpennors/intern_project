<%-- 
    Document   : create_user
    Created on : 3 avr. 2019, 14:37:32
    Author     : Josselin
--%>

<%@page import="controller.Validation"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Hashtable"%>
<%@page import="model.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>

    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Ajouter un utilisateur</h1>
        <div class="row">
            <div class="card offset-sm-3 col-sm-6" style="width: 18rem;">
                <div class="card-body">
                    <div class="form-group">
                        <form action="create_user" method="post">
                            <div class="form-group row">
                                <input type="hidden" id="inputId" name="id_user" value=${user.id_user}>
                                <label for="inputName" class="col-sm-4 col-form-label">Nom</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputNameUser" name="name_user" required value=${user.name_user}>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputFirstName" class="col-sm-4 col-form-label"}>Prénom</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputFirstName" name="first_name" required value=${user.first_name}>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPhone" class="col-sm-4 col-form-label">Téléphone</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputPhone" name="phone" required value=${user.phone}>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputEmail" class="col-sm-4 col-form-label">Email</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="inputEmail" name="email" required value=${user.email}>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputType" class="col-sm-4 col-form-label">Type</label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="inputType" name="is_admin" required value=${user.is_admin}>
                                        <option value="false">Stagiaire</option>
                                        <option value="true">Admin</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputCompany" class="col-sm-4 col-form-label">Entreprise (Immatriculation)</label>
                                <div class="col-sm-8">
                                    <select class='form-control' id='inputCompany' name='company' required value=${user.company.matriculation}>
                                    <%
                                        List<Company> companies = (List<Company>)request.getAttribute("companies");
                                        for (int i=0; i< companies.size(); i++){     
                                            out.println("<option value='" + companies.get(i).getMatriculation() + "' companies.get(i).getMatriculation() == ${user.company.matriculation} ? selected='selected' : ''>" + companies.get(i).getName_company()+ " (" + companies.get(i).getMatriculation() + ")</option>");
                                        }
                                     %> 
                                    </select>
                                   
                                </div>
                                <input type="hidden" name="existingCompany" value="false"></input>
                            </div>                             
                            <button type="submit" class="btn btn-primary">Envoyer</button>
                        </form>
                        <%
                            if (request.getParameterMap().containsKey("errors")){
                                List<Validation> errors = (List<Validation>)request.getAttribute("errors");
                                if(!errors.isEmpty()){
                                    out.println("<div class='alert alert-warning' role='alert'>");
                                    out.println("<ul>");
                                    for (int i=0; i< errors.size(); i++){   
                                        out.println("<li>");
                                        out.println(errors.get(i).getMessage());
                                        out.println("</li>");
                                    }
                                    out.println("</ul></div>");
                                }
                            }
                         %>
                    </div>
                </div>
            </div>
        </div>			
    </body>
</html>