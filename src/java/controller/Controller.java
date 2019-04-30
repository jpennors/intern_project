/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOFactory;
import dao.QuestionnaireDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Questionnaire;
import model.User;
import model.Company;
import dao.UserDao;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Josselin
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/create_user", "/delete_user", "/users", "/edit_user/*", "/create_questionnaire", "/questionnaires", "/edit_questionnaire"})
public class Controller extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern_project", "root", "");
            st = connexion.createStatement();
        } catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Variables meanwhile databse doesn't exist
     */
    private static Hashtable<Integer, User> usersTable= new Hashtable<Integer, User>();
    private static Hashtable<Integer, Company> companiesTable= new Hashtable<Integer, Company>();
    private static Hashtable<Integer, Questionnaire> questionnaireTable= new Hashtable<Integer, Questionnaire>();
    Statement st = null;
    
    private static Questionnaire questionnaireCurrent; 
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        DAOFactory dao = new DAOFactory();
                
        if("GET".equals(request.getMethod())){

            ResultSet data = null;
            switch(request.getRequestURI()){
                case "/intern_project/create_user":
                    data = st.executeQuery("SELECT * FROM company;");
                    while(data.next()){
                        companiesTable.put(companiesTable.size(), new Company(data.getInt("matriculation"), data.getString("name_company")));
                    }
                    User user = new User();
                    request.setAttribute("Companies", companiesTable);
                    request.setAttribute("user", user);
                    returnView(request, response, "/WEB-INF/user/create_user.jsp");
                    companiesTable.clear();
                    break;
                    
                case "/intern_project/users":

                    UserDao user_dao = new UserDao(dao);
                    List<User> users = new ArrayList();
                    users = user_dao.index();
                    
                    request.setAttribute("Users", users);
                    returnView(request, response, "/WEB-INF/user/index_user.jsp");
                    break;
                    
                case "/intern_project/edit_user":
                    data = st.executeQuery("SELECT * FROM company;");
                    companiesTable.clear();
                    while(data.next()){
                        companiesTable.put(companiesTable.size(), new Company(data.getInt("matriculation"), data.getString("name_company")));
                    }
                    user = new User();
                    request.setAttribute("Companies", companiesTable);                    
                    
                    dao = new DAOFactory();
                    user_dao = new UserDao(dao);
                    int id = Integer.parseInt(request.getParameter("id_user"));
                    user = user_dao.show(id);

                    request.setAttribute("user", user);
                    request.setAttribute("selected_company", user.getCompany().getMatriculation());
                    returnView(request, response, "/WEB-INF/user/edit_user.jsp");
                    companiesTable.clear();
                    break;
                    
                case "/intern_project/create_questionnaire":
                    returnView(request, response, "/WEB-INF/question/create_questionnaire.jsp");
                    break;      
                    
                case "/intern_project/questionnaires":
                    QuestionnaireDao questionnaire_dao = new QuestionnaireDao(dao);
                    List<Questionnaire> questionnaires = new ArrayList();
                    questionnaires = questionnaire_dao.index();
                    request.setAttribute("Questionnaire", questionnaires);
                    
                    returnView(request, response, "/WEB-INF/question/index_questionnaire.jsp");
                    break;
                    
                case "/intern_project/edit_questionnaire":
                    request.setAttribute("Questionnaire", questionnaireCurrent);
                    returnView(request, response, "/WEB-INF/question/edit_questionnaire.jsp");
                    break;    
                    
            }
        } else if ("POST".equals(request.getMethod())){
            ResultSet data = null;
            switch(request.getRequestURI()){
                case "/intern_project/create_user":
                    System.out.println(request.getParameter("company"));
                    
                    User user = new User();
                    user.setFirst_name(request.getParameter("first_name"));
                    user.setEmail(request.getParameter("email"));
                    user.setName_user(request.getParameter("name_user"));
                    user.setPhone(request.getParameter("phone"));
                    user.setPassword(request.getParameter("password"));
                    
                    user.setIs_admin(Boolean.parseBoolean(request.getParameter("is_admin")));
                    data = st.executeQuery("SELECT * FROM company WHERE matriculation=" + request.getParameter("company"));
                    if(data.first()){
                        Company c = new Company(data.getInt("matriculation"), data.getString("name_company"));
                        user.setCompany(c);
                    }
                    UserDao user_dao = new UserDao(dao);
                    user_dao.create(user);
                    
                    response.sendRedirect("/intern_project/users");
                    break; 
                
                case "/intern_project/edit_user":
                    System.out.println(request.getParameter("company"));
                    
                    user = new User();
                    user.setId_user(Integer.parseInt(request.getParameter("id_user")));
                    user.setFirst_name(request.getParameter("first_name"));
                    user.setEmail(request.getParameter("email"));
                    user.setName_user(request.getParameter("name_user"));
                    user.setPhone(request.getParameter("phone"));
                    user.setStatus(Boolean.parseBoolean(request.getParameter("status")));
                    user.setIs_admin(Boolean.parseBoolean(request.getParameter("is_admin")));
                    data = st.executeQuery("SELECT * FROM company WHERE matriculation=" + request.getParameter("company"));
                    if(data.first()){
                        Company c = new Company(data.getInt("matriculation"), data.getString("name_company"));
                        user.setCompany(c);
                    }
                    dao = new DAOFactory();
                    user_dao = new UserDao(dao);
                    user_dao.update(user.getId_user(), user);
                    
                    response.sendRedirect("/intern_project/users");
                    break; 
                
                case "/intern_project/delete_user":
                    int id = Integer.parseInt(request.getParameter("id_user"));
                    dao = new DAOFactory();
                    user_dao = new UserDao(dao);
                    user_dao.delete(id);
                                       
                    response.sendRedirect("/intern_project/users");
                    
                    break;
                    
                case "/intern_project/create_questionnaire":
                    questionnaireTable.put(questionnaireTable.size(), new Questionnaire(request.getParameter("subject")));
                    questionnaireCurrent = questionnaireTable.get(questionnaireTable.size()-1);
                    response.sendRedirect("/intern_project/edit_questionnaire");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
