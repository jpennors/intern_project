<%-- 
    Document   : header
    Created on : 16 mai 2019, 15:22:27
    Author     : Josselin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="pos-f-t">
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
        <h5 class="text-white h4">Menu</h5>
      <%
            boolean is_admin = Boolean.parseBoolean(request.getAttribute("is_admin").toString());
            if (is_admin){
                out.println(
                    "<a href='create_user' class='text-muted text-decoration-none'>Créer un utilisateur</a><br>" +
                    "<a href='users' class='text-muted text-decoration-none'>Lister les utilisateurs</a><br>" +
                    "<a href='questionnaires' class='text-muted text-decoration-none'>Lister les questionnaires</a><br>" +
                    "<a href='create_questionnaire' class='text-muted text-decoration-none'>Créer un questionnaire</a><br>" 
                );
            } else {
                out.println(
                    "<a href='home' class='text-muted text-decoration-none'>A venir ... </a><br>"
                );
            }
         %>
    </div>
  </div>
  <nav class="navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </nav>
</div>
