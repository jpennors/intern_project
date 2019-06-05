<%-- 
    Document   : index_user
    Created on : 3 avr. 2019, 14:21:39
    Author     : Josselin
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Hashtable"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="../section/html_head.jsp" %>

    <body>
        <%@ include file="../section/header.jsp" %>
        <h1 style="text-align: center; margin: 40px">Liste des utilisateurs</h1>
        <div class="row">
            <div class="card offset-sm-1 col-sm-10" style="width: 18rem;">
                <div class="card-body">
                    <table id="user" class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nom</th>
                                <th scope="col">Prénom</th>
                                <th scope="col">Email</th>
                                <th scope="col">Télephone</th>
                                <th scope="col">Type</th>
                                <th scope="col">Statut</th>
                                <th scope="col">Entreprise</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<User> users =  (List<User>)request.getAttribute("Users");
                                for (int i=0; i < users.size(); i++){
                                    Integer num = i + 1;
                                    out.println("<tr>");
                                    out.println("<td scope='row'>" + num + "</td>");
                                    out.println("<td>" + users.get(i).getName_user() + "</td>");
                                    out.println("<td>" + users.get(i).getFirst_name() + "</td>");
                                    out.println("<td>" + users.get(i).getEmail() + "</td>");
                                    out.println("<td>" + users.get(i).getPhone() + "</td>");
                                    out.println("<td>" + users.get(i).getType_name() + "</td>");
                                    out.println("<td>" + users.get(i).getStatus_name() + "</td>");
                                    out.println("<td>" + users.get(i).getCompany().getName_company() + "</td>");
                                    //out.println("<td><button class='btn btn-info'><a href='edit_user/" + users.get(i).getId() + "'>Voir</a></button></td>");
                                    out.println("<form action='edit_user'method='get'>");
                                    out.println("<input type='hidden' name='id_user' value='" + users.get(i).getId_user() + "'>");
                                    out.println("<td><button class='btn btn-info' type='submit'>Editer</button>");
                                    out.println("</form>");
                                    if(users.get(i).getStatus()){
                                        out.println("<form action='delete_user' method='post'>");
                                        out.println("<input type='hidden' name='id_user' value='" + users.get(i).getId_user() + "'>");
                                        out.println("<button type='submit' class='btn btn-info' href='delete_user?id='" + users.get(i).getId_user() + ">Supprimer</button></td>");
                                        out.println("</form>");
                                    }
                                    out.println("</tr>");
                                }
                             %> 
                        </tbody>
                    </table>
                </div>
            </div>
        </div>	
        <!-- Modal -->
        <div class="modal fade" id="exampleModalScrollable" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
          <div class="modal-dialog modal-dialog-scrollable" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalScrollableTitle">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                Etes-vous sûr de vouloir supprime cet utilisateur ?
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
                <button type="button" class="btn btn-primary">Oui</button>
              </div>
            </div>
          </div>
        </div>
        <script>
            $(document).ready(function() {
                $('#user').DataTable();
            } );
        </script>
    </body>
</html>

