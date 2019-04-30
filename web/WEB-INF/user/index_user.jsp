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
        <h1 style="text-align: center; margin: 40px">Liste des utilisateurs</h1>
        <div class="row">
            <div class="card offset-sm-1 col-sm-10" style="width: 18rem;">
                <div class="card-body">
                    <table class="table">
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
                                    out.println("<form action='delete_user' method='post'>");
                                    out.println("<input type='hidden' name='id_user' value='" + users.get(i).getId_user() + "'>");
                                    out.println("<button type='submit' class='btn btn-info' href='delete_user?id='" + users.get(i).getId_user() + ">Supprimer</button></td>");
                                    //out.println("<button class='btn btn-info' data-toggle='modal' data-target='#exampleModalScrollable' href='delete_user?id='" + users.get(i).getId_user() + ">Supprimer</button></td>");
                                    out.println("</form>");
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
    </body>
</html>

