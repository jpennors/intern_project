/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CompanyDao;
import dao.DAOFactory;
import dao.ParcoursDao;
import dao.QuestionDao;
import dao.QuestionnaireDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
import model.Question;

/**
 *
 * @author Josselin
 */
@WebServlet(name = "Controller", urlPatterns = {"/", "/home", "/login", "/Controller",
    "/create_user", "/delete_user", "/users", "/edit_user/*", "/create_questionnaire",
    "/questionnaires", "/edit_questionnaire/*", "/questions", "/delete_question", "/create_question"})
public class Controller extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern_project", "root", "");
            st = connexion.createStatement();
            dao = new DAOFactory();
        } catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Variables meanwhile databse doesn't exist
     */
    DAOFactory dao;
    Statement st;
   
    
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
        
        // Init DAO Factory
        DAOFactory dao = new DAOFactory();
        
        // Utilisateur
        UserDao user_dao = null;
        
        if("/intern_project/login".equals(request.getRequestURI())){
            if("GET".equals(request.getMethod())){
                returnView(request, response, "/WEB-INF/login.jsp");
            }
            else if("POST".equals(request.getMethod())){
                login(request, response, dao, user_dao);
                //returnView(request, response, "index.html");
                response.sendRedirect("/intern_project/home");
            }
            return;
        }
        if(!isConnected(request, response)){
            response.sendRedirect("/intern_project/login");
            return;
        }
        
        setRequestAttributes(request,response);
        
        // GET HTTP METHOD
        if("GET".equals(request.getMethod())){
            
            // ADMIN ROUTES
            if((Boolean) request.getAttribute("is_admin")){
                
                switch(request.getRequestURI()){
                    
                    case "/intern_project/home":
                        returnView(request, response, "/WEB-INF/home.jsp");
                        break;
                    
                    /**
                    * USER GET METHOD
                    */
                   case "/intern_project/create_user":
                       userCreation(request, response);
                       break;

                   case "/intern_project/users":
                       indexUser(request, response);
                       break;

                   case "/intern_project/edit_user":
                       userEdition(request, response);
                       break;
                       
                    /**
                     * QUESTIONNAIRE GET METHOD
                     */
                    case "/intern_project/create_questionnaire":
                        questionnaireCreation(request, response);
                        break;      

                    case "/intern_project/questionnaires":
                        indexQuestionnaire(request, response);
                        break;

                    case "/intern_project/edit_questionnaire":
                        questionnaireEdition(request, response);
                        break;
                        
                    /**
                     * QUESTION GET METHOD
                     */
                    case "/intern_project/create_question":
                        questionCreation(request, response);
                        break;      

                    case "/intern_project/questions":
                        indexQuestion(request, response);
                        break;

                    case "/intern_project/edit_question":
                        questionEdition(request, response);
                        break;    
                        
                   default:
                       returnView(request, response, "/WEB-INF/home.jsp");
                       break;
                }
            } else {
                switch(request.getRequestURI()){
                    case "/intern_project/home":
                        ParcoursDao parcours_dao = dao.getParcoursDao();
                        // To DO récupérer uer_id
                        parcours_dao.indexForUser(0);
                        // Itérer dans les parcous et récupérer answers et good answers
                        request.setAttribute("parcours", dao);
                        request.setAttribute("count_anwsers", dao);
                        request.setAttribute("count_good_answers", dao);
                        
                        returnView(request, response, "/WEB-INF/home.jsp");
                        break;
                    default : 
                        response.sendRedirect("/intern_project/home");
                        break;
                }
            }
            
        } else if ("POST".equals(request.getMethod())){
            
            // ADMIN ROUTES
            if((Boolean) request.getAttribute("is_admin")){
                switch(request.getRequestURI()){
                    
                    /**
                     * USER POST METHOD
                     */
                    case "/intern_project/create_user":
                        createUser(request, response);
                        break; 

                    case "/intern_project/edit_user":
                        updateUser(request, response);                    
                        break; 

                    case "/intern_project/delete_user":                    
                        deleteUser(request,response);
                        break;
                        
                    /**
                     * QUESTIONNAIRE POST METHOD
                     */
                    case "/intern_project/create_questionnaire":
                        createQuestionnaire(request, response);
                        break;
                        
                    case "/intern_project/edit_questionnaire":
                        updateQuestionnaire(request, response);
                        break;
                        
                    case "/intern_project/delete_questionnaire":                    
                        deleteQuestionnaire(request,response);
                        break;
                        
                    /**
                     * QUESTIONNAIRE POST METHOD
                     */
                    case "/intern_project/create_question":
                        createQuestion(request, response);
                        break;
                        
                    case "/intern_project/edit_question":
                        updateQuestion(request, response);
                        break;
                        
                    case "/intern_project/delete_question":                    
                        deleteQuestion(request,response);
                        break;    
                        
                    default :
                        response.sendRedirect("/intern_project/home");
                        break; 
                }
            } else {
                switch(request.getRequestURI()){
                    default :
                        response.sendRedirect("/intern_project/home");
                        break;
                }
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
    
    protected boolean isConnected(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return Middleware.isConnected(request, response);
    }
    
    protected void login(HttpServletRequest request, HttpServletResponse response, DAOFactory dao, UserDao user_dao){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        user_dao = dao.getUserDao();
        user_dao.login(email, password);
        User user = user_dao.login(email, password);
        if(user != null){
            request.getSession().setAttribute("user", user);
        }
    }
    
    protected void setRequestAttributes(HttpServletRequest request, HttpServletResponse response){
        boolean is_admin = Middleware.isAdmin(request, response);
        User logged_user = Middleware.getLoggedUser(request, response);
        request.setAttribute("is_admin", is_admin);
        request.setAttribute("logged_user", logged_user);
    }
    
    /*********************************************************************************
     * Fonctions des controllers
 
     ********************************************************************************/
    
    /**
     * USER
     */
    
    protected void indexUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UserDao user_dao = dao.getUserDao();
        List<User> users = user_dao.index();
        request.setAttribute("Users", users);
        returnView(request, response, "/WEB-INF/user/index_user.jsp");
    }
    
    protected void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        // Validation
        //List<Validation> errors = Validation.userRequestValidation(request);
        System.out.println("ici");
        System.out.println(request.getParameter("is_admin"));
        User user = User.mapRequestToUser(request);
        /*if(!errors.isEmpty()){
            CompanyDao company_dao = dao.getCompanyDao();
            List<Company> companies = company_dao.index();
            request.setAttribute("companies", companies);
            request.setAttribute("errors", errors);
            response.sendRedirect("/intern_project/create_user");
            //returnView(request, response, "/WEB-INF/user/create_user.jsp");
            return;
        }*/
                
        int company_id = Integer.parseInt(request.getParameter("company"));
        CompanyDao company_dao = dao.getCompanyDao();
        Company company = company_dao.show(company_id);
        user.setCompany(company);
        UserDao user_dao = dao.getUserDao();
        user_dao.create(user);
        response.sendRedirect("/intern_project/users");
    }
    
    protected void userCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CompanyDao company_dao = dao.getCompanyDao();
        List<Company> companies = company_dao.index();                         
        User user = new User();
        request.setAttribute("companies", companies);
        request.setAttribute("user", user);
        returnView(request, response, "/WEB-INF/user/create_user.jsp");
    }
    
    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        User user = User.mapRequestToUser(request);
        int company_id = Integer.parseInt(request.getParameter("company"));
        CompanyDao company_dao = dao.getCompanyDao();
        Company company = company_dao.show(company_id);
        user.setCompany(company);
        UserDao user_dao = dao.getUserDao();
        int user_id = Integer.parseInt(request.getParameter("id_user"));
        user_dao.update(user_id, user);
        response.sendRedirect("/intern_project/users");
    }
    
    protected void userEdition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CompanyDao company_dao = dao.getCompanyDao();
        List<Company> companies = company_dao.index();            
        UserDao user_dao = dao.getUserDao();
        int user_id = Integer.parseInt(request.getParameter("id_user"));
        User user = user_dao.show(user_id);
        request.setAttribute("user", user);
        request.setAttribute("companies", companies); 
        request.setAttribute("selected_company", user.getCompany().getMatriculation());
        returnView(request, response, "/WEB-INF/user/edit_user.jsp");
    }
    
    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        UserDao user_dao = dao.getUserDao();
        int user_id = Integer.parseInt(request.getParameter("id_user"));
        user_dao.delete(user_id);
        response.sendRedirect("/intern_project/users");
    }
    
    /**
     * QUESTIONNAIRE
     */
    
    protected void indexQuestionnaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        QuestionnaireDao questionnaire_dao = dao.getQuestionnaireDao();
        List<Questionnaire> questionnaires = questionnaire_dao.index();
        request.setAttribute("questionnaires", questionnaires);
        returnView(request, response, "/WEB-INF/question/index_questionnaire.jsp");
    }
    
    //(get)
    protected void questionnaireCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{                    
        Questionnaire questionnaire = new Questionnaire();
        request.setAttribute("questionnaire", questionnaire);
        returnView(request, response, "/WEB-INF/question/create_questionnaire.jsp");
    }
    
    //traitement (post)
    protected void createQuestionnaire(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Questionnaire questionnaire = Questionnaire.mapRequestToQuestionnaire(request);
        QuestionnaireDao questionnaire_dao = dao.getQuestionnaireDao();
        User createur = Middleware.getLoggedUser(request, response);
        questionnaire.setCreateur_id(createur);
        questionnaire_dao.create(questionnaire);
        response.sendRedirect("/intern_project/questionnaires");
    }
    
    //(get)
    protected void questionnaireEdition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{          
        QuestionnaireDao questionnaire_dao = dao.getQuestionnaireDao();
        int id = Integer.parseInt(request.getParameter("id_questionnaire"));
        Questionnaire questionnaire = questionnaire_dao.show(id);
        List<Question> questions = questionnaire_dao.getQuestion(id);
        request.setAttribute("questionnaire", questionnaire);
        request.setAttribute("questions", questions);
        returnView(request, response, "/WEB-INF/question/edit_questionnaire.jsp");
    }
    
    //traitement (post)
    protected void updateQuestionnaire(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Questionnaire questionnaire = Questionnaire.mapRequestToQuestionnaire(request);
        QuestionnaireDao questionnaire_dao = dao.getQuestionnaireDao();
        int id = Integer.parseInt(request.getParameter("id_questionnaire"));
        questionnaire_dao.update(id, questionnaire);     
        response.sendRedirect("/intern_project/questionnaires");
    }
    
    protected void deleteQuestionnaire(HttpServletRequest request, HttpServletResponse response) throws IOException{
        QuestionnaireDao questionnaire_dao = dao.getQuestionnaireDao();
        int id = Integer.parseInt(request.getParameter("id_questionnaire"));
        questionnaire_dao.delete(id);
        response.sendRedirect("/intern_project/questionnaires");
    }
    
    /**
     * QUESTION
     */
    
    protected void indexQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        QuestionDao question_dao = dao.getQuestionDao();
        List<Question> questions = question_dao.index();
        request.setAttribute("questions", questions);
        returnView(request, response, "/WEB-INF/question/index_question.jsp");
    }
    
    protected void deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        QuestionDao question_dao = dao.getQuestionDao();
        int id = Integer.parseInt(request.getParameter("id_question"));
        question_dao.delete(id);
        response.sendRedirect("/intern_project/questions");
    }
    
    //(get)
    protected void questionEdition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{          
        QuestionDao question_dao = dao.getQuestionDao();
        int id = Integer.parseInt(request.getParameter("id_question"));
        Question question = question_dao.show(id);
        request.setAttribute("question", question);
        returnView(request, response, "/WEB-INF/question/edit_question.jsp");
    }
    
    //traitement (post)
    protected void updateQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Question question = Question.mapRequestToQuestion(request);
        QuestionDao question_dao = dao.getQuestionDao();
        int id = Integer.parseInt(request.getParameter("id_question"));
        question_dao.update(id, question);     
        response.sendRedirect("/intern_project/questions");
    }
    
    //(get)
    protected void questionCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{                    
        Question question = new Question();
        request.setAttribute("question", question);
        returnView(request, response, "/WEB-INF/question/create_question.jsp");
    }
    
    //traitement (post)
    protected void createQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Question question = Question.mapRequestToQuestion(request);
        QuestionDao question_dao = dao.getQuestionDao();
        question_dao.create(question);
        response.sendRedirect("/intern_project/questions");
    }    
    
}
