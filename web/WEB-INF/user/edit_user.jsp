<%-- 
    Document   : edit_user
    Created on : 30 avr. 2019, 18:18:25
    Author     : Josselin
--%>

<%@page import="java.util.List"%>
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
    <%@ include file="../section/html_head.jsp" %>

    <body>
        <%@ include file="../section/header.jsp" %>
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
                                    <select class="form-control" id="inputType" name="is_admin" value=${user.is_admin}>
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
                                        List<Company> companies = (List<Company>)request.getAttribute("companies");
                                        Integer selected_company = (Integer)request.getAttribute("selected_company");

                                        for (int i=0; i< companies.size(); i++){     
                                            out.println("<option value='" + companies.get(i).getMatriculation() + "'");
                                            if(companies.get(i).getMatriculation() == selected_company){
                                                out.println("selected");
                                            }
                                            out.println(">" + companies.get(i).getName_company()+ " (" + companies.get(i).getMatriculation() + ")</option>");
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