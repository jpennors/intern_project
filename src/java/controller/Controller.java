/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Questionnaire;
import model.User;

/**
 *
 * @author Josselin
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/create_user", "/users", "/users_encours", "/create_questionnaire", "/questionnaires"})
public class Controller extends HttpServlet {
    
    /**
     * Variables meanwhile databse doesn't exist
     */
    private static Hashtable<Integer, User> usersTable= new Hashtable<Integer, User>();
    private static Hashtable<Integer, Questionnaire> questionnaireTable= new Hashtable<Integer, Questionnaire>();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        System.out.println(request.getRequestURI());
        System.out.println(request.getMethod());
                
        if("GET".equals(request.getMethod())){
            switch(request.getRequestURI()){
                case "/intern_project/create_user":
                    returnView(request, response, "/WEB-INF/user/create_user.html");
                    break;
                    
                case "/intern_project/users":
                    System.out.println("Bien arrivé !");
                    displayUser(request, response);
                    break;
                    
                case "/intern_project/users_encours":
                    returnView(request, response, "/WEB-INF/user/index_user.html");
                    break;
                    
                case "/intern_project/create_questionnaire":
                    returnView(request, response, "/WEB-INF/question/create_questionnaire.jsp");
                    break;      
                    
                case "/intern_project/questionnaires":
                    returnView(request, response, "/WEB-INF/question/index_questionnaire.jsp");
                    break;    
                    
            }
        } else if ("POST".equals(request.getMethod())){
            switch(request.getRequestURI()){
                case "/intern_project/create_user":
                    boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
                    usersTable.put(usersTable.size(), new User(request.getParameter("email"),request.getParameter("password"),request.getParameter("name"),request.getParameter("first_name"), request.getParameter("phone"), isAdmin));
                    response.sendRedirect("/intern_project/users");
                    break; 
                case "/intern_project/create_questionnaire":
                    questionnaireTable.put(questionnaireTable.size(), new Questionnaire(request.getParameter("subject")));
                    response.sendRedirect("/intern_project/questionnaires");
                    break;
                    
                    
            
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


    /**
     * Retourn la liste des utilisateurs stockés dans la variable @usersTable
     * @param request
     * @param response
     * @throws IOException 
     */
    protected void displayUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try (PrintWriter out = response.getWriter()) {
            //TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Utilisateurs</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Liste des utilisateurs</h1>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Nom</th>");
            out.println("<th>Prénom</th>");
            out.println("<th>Email</th>");
            out.println("<th>Téléphone</th>");
            out.println("<th>Type</th>");
            out.println("</tr>");
            int i;
            for (i=0; i< usersTable.size(); i++){
                out.println("<tr>");
                out.println("<td>" + usersTable.get(i).getName() + "</td>");
                out.println("<td>" + usersTable.get(i).getFirst_name() + "</td>");
                out.println("<td>" + usersTable.get(i).getEmail() + "</td>");
                out.println("<td>" + usersTable.get(i).getPhone() + "</td>");
                if (usersTable.get(i).getIs_admin()){
                    out.println("<td>Administrateur</td>");
                } else {
                    out.println("<td>Stagiaire</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    /**
     * Retourne une vue HTML avec le @path donné en paramètre
     * @param request
     * @param response
     * @param path
     * @throws ServletException
     * @throws IOException 
     */
    protected void returnView(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        
        RequestDispatcher view = request.getRequestDispatcher(path);
        view.forward(request, response);

    }
}
