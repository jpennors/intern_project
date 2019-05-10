/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CompanyDao;
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
        
        System.out.println("OK");
        
        response.setContentType("text/html;charset=UTF-8");
        
        // Init DAO Factory
        DAOFactory dao = new DAOFactory();
        
        // Utilisateur
        UserDao user_dao;
        User user;
        
        // Company
        CompanyDao company_dao;
        Company company;
        List<Company> companies;
        
        // ResultSet 
        ResultSet data;
                
        // GET HTTP METHOD
        if("GET".equals(request.getMethod())){
            
            switch(request.getRequestURI()){
                
                /**
                 * USER GET METHOD
                 */
                case "/intern_project/create_user":

                    company_dao = dao.getCompanyDao();
                    companies = company_dao.index();                         
                    user = new User();
                    
                    request.setAttribute("companies", companies);
                    request.setAttribute("user", user);
                    returnView(request, response, "/WEB-INF/user/create_user.jsp");
                    break;
                    
                case "/intern_project/users":
                    user_dao = dao.getUserDao();
                    List<User> users = user_dao.index();
                    
                    request.setAttribute("Users", users);
                    returnView(request, response, "/WEB-INF/user/index_user.jsp");
                    break;
                    
                case "/intern_project/edit_user":
                    company_dao = dao.getCompanyDao();
                    companies = company_dao.index();            
                    user_dao = dao.getUserDao();
                    int user_id = Integer.parseInt(request.getParameter("id_user"));
                    user = user_dao.show(user_id);

                    request.setAttribute("user", user);
                    request.setAttribute("companies", companies); 
                    request.setAttribute("selected_company", user.getCompany().getMatriculation());
                    returnView(request, response, "/WEB-INF/user/edit_user.jsp");
                    break;
                
                /**
                 * QUESTIONNAIRE GET METHOD
                 */
                case "/intern_project/create_questionnaire":
                    returnView(request, response, "/WEB-INF/question/create_questionnaire.jsp");
                    break;      
                    
                case "/intern_project/questionnaires":
                    QuestionnaireDao questionnaire_dao = new QuestionnaireDao(dao);
                    List<Questionnaire> questionnaires = questionnaire_dao.index();
                    request.setAttribute("Questionnaire", questionnaires);
                    
                    returnView(request, response, "/WEB-INF/question/index_questionnaire.jsp");
                    break;
                    
                case "/intern_project/edit_questionnaire":
                    request.setAttribute("Questionnaire", questionnaireCurrent);
                    returnView(request, response, "/WEB-INF/question/edit_questionnaire.jsp");
                    break;    
                    
            }
        } else if ("POST".equals(request.getMethod())){
            
            switch(request.getRequestURI()){
            
                /**
                 * USER POST METHOD
                 */
                case "/intern_project/create_user":
                    
                    user = User.mapRequestToUser(request);
                    int company_id = Integer.parseInt(request.getParameter("company"));
                    company_dao = dao.getCompanyDao();
                    company = company_dao.show(company_id);
                    user.setCompany(company);
                    
                    user_dao = dao.getUserDao();
                    user_dao.create(user);
              
                    response.sendRedirect("/intern_project/users");
                    break; 
                
                case "/intern_project/edit_user":
                    
                    user = User.mapRequestToUser(request);
                    company_id = Integer.parseInt(request.getParameter("company"));
                    company_dao = dao.getCompanyDao();
                    company = company_dao.show(company_id);
                    user.setCompany(company);
                    user_dao = dao.getUserDao();
                    
                    int user_id = Integer.parseInt(request.getParameter("id_user"));
                    user_dao.update(user_id, user);
                    
                    response.sendRedirect("/intern_project/users");
                    break; 
                
                case "/intern_project/delete_user":                    
                    user_dao = dao.getUserDao();
                    user_id = Integer.parseInt(request.getParameter("id_user"));
                    user_dao.delete(user_id);
                                       
                    response.sendRedirect("/intern_project/users");
                    break;
                    
                /**
                 * QUESTIONNAIRE POST METHOD
                 */
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
