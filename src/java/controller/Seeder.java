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
import dao.ResponseDao;
import dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import model.Company;
import model.Parcours;
import model.Question;
import model.Questionnaire;
import model.Response;
import model.User;

/**
 *
 * @author Josselin
 */
public class Seeder {
    
    
    Statement st;
    DAOFactory dao;

    static public void mainScript() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern_project", "root", "");
        Statement st = connexion.createStatement();
        DAOFactory dao = new DAOFactory();
        
        createCompanyTable(st, dao);
        createUserTable(st, dao);
        createQuestionnnaires(st, dao);
        createParcours(st, dao);
    }
    
    static protected void createCompanyTable(Statement st, DAOFactory dao) throws SQLException{
        st.execute("DROP TABLE IF EXISTS company");
        
        st.execute("CREATE TABLE `company` (\n" +
            " `matriculation` int(11) NOT NULL,\n" +
            " `name_company` varchar(50) NOT NULL,\n" +
            " PRIMARY KEY (`matriculation`)\n" +
            ") ENGINE=MyISAM DEFAULT CHARSET=latin1");
        
        System.out.println("Table Company créée");
        
        System.out.println("Insertion dans table Company ...");
        CompanyDao company_dao = dao.getCompanyDao();
        Company c1 = new Company();
        c1.setName_company("IBM");
        c1.setMatriculation(1);
        company_dao.create(c1);
        Company c2 = new Company();
        c2.setName_company("Fujitsu");
        c2.setMatriculation(2);
        company_dao.create(c2);
        Company c3 = new Company();
        c3.setName_company("HPE");
        c3.setMatriculation(3);
        company_dao.create(c3);
        Company c4 = new Company();
        c4.setName_company("Dell");
        c4.setMatriculation(4);
        company_dao.create(c4);
        Company c5 = new Company();
        c5.setName_company("Atos");
        c5.setMatriculation(5);
        company_dao.create(c5);
        Company c6 = new Company();
        c6.setName_company("Capgemini");
        c6.setMatriculation(6);
        company_dao.create(c6);
    }
    
    static protected void createUserTable(Statement st, DAOFactory dao) throws SQLException{
        st.execute("DROP TABLE IF EXISTS user");
        
        st.execute("CREATE TABLE `user` (\n" +
            " `id_user` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `email` varchar(100) NOT NULL,\n" +
            " `password` varchar(100) NOT NULL,\n" +
            " `name_user` varchar(30) NOT NULL,\n" +
            " `first_name` varchar(30) NOT NULL,\n" +
            " `status` tinyint(1) NOT NULL,\n" +
            " `phone` int(10) NOT NULL,\n" +
            " `is_admin` tinyint(1) NOT NULL,\n" +
            " `company` int(11) NOT NULL,\n" +
            " `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
            " PRIMARY KEY (`id_user`)\n" +
            ") ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1");
        
        System.out.println("Table User créée");
        
        System.out.println("Insertion dans table User ...");
        CompanyDao company_dao = dao.getCompanyDao();
        Company c1 = company_dao.show(1);
        Company c2 = company_dao.show(5);
        UserDao user_dao = dao.getUserDao();
        
        User u1 = new User();
        u1.setFirst_name("Lola");
        u1.setName_user("Lejeune");
        u1.setEmail("lola.lejeune@etu.utc.fr");
        u1.setIs_admin(true);
        u1.setPassword("mll");
        u1.setPhone("0610101010");
        u1.setCompany(c1);
        user_dao.create(u1);
        
        User u2 = new User();
        u2.setFirst_name("Josselin");
        u2.setName_user("Pennors");
        u2.setEmail("josselin.pennors@etu.utc.fr");
        u2.setIs_admin(true);
        u2.setPassword("mll");
        u2.setPhone("0630303030");
        u2.setCompany(c1);
        user_dao.create(u2);
        
        User u3 = new User();
        u3.setFirst_name("Josselin");
        u3.setName_user("Pennors");
        u3.setEmail("josselin.pennors@etu.utc.fr");
        u3.setIs_admin(true);
        u3.setPassword("mll");
        u3.setPhone("0644444444");
        u3.setCompany(c2);
        user_dao.create(u3);
        
        User u4 = new User();
        u4.setFirst_name("Julie");
        u4.setName_user("Vercoustre");
        u4.setEmail("julie.vercoustre@etu.utc.fr");
        u4.setIs_admin(false);
        u4.setPassword("mll");
        u4.setPhone("0623232320");
        u4.setCompany(c2);
        user_dao.create(u4);
    }
    
    static protected void createQuestionnnaires(Statement st, DAOFactory dao) throws SQLException{
        
        st.execute("DROP TABLE IF EXISTS questionnaire");
        
        st.execute("CREATE TABLE `questionnaire` (\n" +
            " `id_questionnaire` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `subject` varchar(30) NOT NULL,\n" +
            " `status_questionnaire` tinyint(1) NOT NULL,\n" +
            " `createur_id` int(11) NOT NULL,\n" +
            " PRIMARY KEY (`id_questionnaire`)\n" +
            ") ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1");
        
        st.execute("DROP TABLE IF EXISTS question_questionnaire");
        
        st.execute("CREATE TABLE `question_questionnaire` (\n" +
            " `questionnaire_id` varchar(30) NOT NULL,\n" +
            " `question_id` int(11) NOT NULL,\n" +
            " `question_order` int(11) DEFAULT NULL,\n" +
            " PRIMARY KEY (`questionnaire_id`,`question_id`)\n" +
            ") ENGINE=MyISAM DEFAULT CHARSET=latin1");
        
        System.out.println("Table Questionnaire créée");
        
        System.out.println("Table Question_Questionnaire créée");
        
        System.out.println("Création de questionnaires et ajout de questions");
        
        UserDao user_dao = dao.getUserDao();
        QuestionnaireDao questionnaire_dao = dao.getQuestionnaireDao();
        
        List<User> u = user_dao.index();
        User u1 = u.get(0);
        User u2 = u.get(1);
        
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setCreateur_id(u1);
        questionnaire.setSubject("Base de données");
        int questionnaire1 = questionnaire_dao.create(questionnaire);
        
        questionnaire = new Questionnaire();
        questionnaire.setCreateur_id(u2);
        questionnaire.setSubject("SR03");
        int questionnaire2 = questionnaire_dao.create(questionnaire);
        
        st.execute("DROP TABLE IF EXISTS question");
        
        st.execute("CREATE TABLE `question` (\n" +
            " `id_question` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `status_question` tinyint(1) NOT NULL,\n" +
            " `name` varchar(100) NOT NULL,\n" +
            " PRIMARY KEY (`id_question`)\n" +
            ") ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1");
                
        
        st.execute("DROP TABLE IF EXISTS response");
        
        System.out.println("Table Question créée");
        
        st.execute("CREATE TABLE `response` (\n" +
            " `id_response` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `status_response` tinyint(1) NOT NULL,\n" +
            " `name` varchar(100) NOT NULL,\n" +
            " `ordre` int(11) NOT NULL,\n" +
            " `validity` tinyint(1) NOT NULL,\n" +
            " `question_id` int(11) NOT NULL,\n" +
            " PRIMARY KEY (`id_response`)\n" +
            ") ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1");
        
        System.out.println("Table Response créée");
        
        System.out.println("Insertion de réponses et de questions dans les questionnaires ...");
        
        ResponseDao response_dao = dao.getResponseDao();
        QuestionDao question_dao = dao.getQuestionDao();
        
        int q_id;
        Question q = new Question();
        q.setSentence("Quel type TEXT a le nombre maximm d'octets?");
        q_id = question_dao.create(q);
        Response r1 = new Response();
        r1.setName("Tiny text");
        r1.setValidity(false);
        r1.setOrder(1);
        r1.setQuestion_id(q_id);
        response_dao.create(r1);
        Response r2 = new Response();
        r2.setValidity(false);
        r2.setName("Text");
        r2.setOrder(2);
        r2.setQuestion_id(q_id);
        response_dao.create(r2);
        Response r3 = new Response();
        r3.setValidity(false);
        r3.setName("Medium text");
        r3.setOrder(3);
        r3.setQuestion_id(q_id);
        response_dao.create(r3);
        Response r4 = new Response();
        r4.setName("Long text");
        r4.setValidity(true);
        r4.setOrder(4);
        r4.setQuestion_id(q_id);
        response_dao.create(r4);
        
        question_dao.linkQuestionnaire(questionnaire1, q_id);
        
        q = new Question();
        q.setSentence("Qeulle instruction apporte des modifications aux attributs de la base de données ?");
        q_id = question_dao.create(q);
        r1 = new Response();
        r1.setValidity(false);
        r1.setName("CHANGE");
        r1.setOrder(1);
        r1.setQuestion_id(q_id);
        response_dao.create(r1);
        r2 = new Response();
        r2.setName("ALTER");
        r2.setValidity(true);
        r2.setOrder(2);
        r2.setQuestion_id(q_id);
        response_dao.create(r2);
        r3 = new Response();
        r3.setValidity(false);
        r3.setName("ALTERNATE");
        r3.setOrder(3);
        r3.setQuestion_id(q_id);
        response_dao.create(r3);
        r4 = new Response();
        r4.setValidity(false);
        r4.setName("UPDATE");
        r4.setOrder(4);
        r4.setQuestion_id(q_id);
        response_dao.create(r4);
        
        question_dao.linkQuestionnaire(questionnaire1, q_id);
        
        q = new Question();
        q.setSentence("Quel sera la forme de float\"(4,2)\" dans Mysql ?");
        q_id = question_dao.create(q);
        r1 = new Response();
        r1.setValidity(true);
        r1.setName("Total de quatre chiffres, deux à gauche de la décimale et deux à droite de la décimale");
        r1.setOrder(1);
        r1.setQuestion_id(q_id);
        response_dao.create(r1);
        r2 = new Response();
        r2.setValidity(false);
        r2.setName("Total de six chiffres");
        r2.setOrder(2);
        r2.setQuestion_id(q_id);
        response_dao.create(r2);
        r3 = new Response();
        r3.setValidity(false);
        r3.setName("Totale de quatre chiffres, non répartis uniformément");
        r3.setOrder(3);
        r3.setQuestion_id(q_id);
        response_dao.create(r3);
        r4 = new Response();
        r4.setValidity(false);
        r4.setName("Aucune de ces réponses");
        r4.setOrder(4);
        r4.setQuestion_id(q_id);
        response_dao.create(r4);
        
        question_dao.linkQuestionnaire(questionnaire1, q_id);
        
        q = new Question();
        q.setSentence("MySQL est un système de gestion de base de données ... ?");
        q_id = question_dao.create(q);
        r1 = new Response();
        r1.setValidity(true);
        r1.setName("Orienté objet");
        r1.setOrder(1);
        r1.setQuestion_id(q_id);
        response_dao.create(r1);
        r2 = new Response();
        r2.setValidity(false);
        r2.setName("Hiérarchique");
        r2.setOrder(2);
        r2.setQuestion_id(q_id);
        response_dao.create(r2);
        r3 = new Response();
        r3.setValidity(false);
        r3.setName("Relationnel");
        r3.setOrder(3);
        r3.setQuestion_id(q_id);
        response_dao.create(r3);
        r4 = new Response();
        r4.setValidity(false);
        r4.setName("Réseau");
        r4.setOrder(4);
        r4.setQuestion_id(q_id);
        response_dao.create(r4);
        
        question_dao.linkQuestionnaire(questionnaire1, q_id);
        question_dao.linkQuestionnaire(questionnaire2, q_id);
        
    }
    
    static protected void createParcours(Statement st, DAOFactory dao) throws SQLException{
        
        st.execute("DROP TABLE IF EXISTS parcours");
        
        st.execute("CREATE TABLE `parcours` (\n" +
            " `id_parcours` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `duration` int NOT NULL,\n" +
            " `user_id` int(11) NOT NULL,\n" +
            " `questionnaire_id` int(11) NOT NULL,\n" +
            " PRIMARY KEY (`id_parcours`)\n" +
            ") ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1");
        
        st.execute("DROP TABLE IF EXISTS parcours_question");
        
        st.execute("CREATE TABLE `parcours_question` (\n" +
            " `parcours_id` int(11) NOT NULL,\n" +
            " `question_id` int(11) NOT NULL,\n" +
            " `response_id` int(11) NOT NULL,\n" +
            " PRIMARY KEY (`parcours_id`,`question_id`,`response_id`)\n" +
            ") ENGINE=MyISAM DEFAULT CHARSET=latin1");
        
        ParcoursDao parcours_dao = dao.getParcoursDao();
        QuestionnaireDao questionnaire_dao = dao.getQuestionnaireDao();
        UserDao user_dao = dao.getUserDao();
        ResponseDao response_dao = dao.getResponseDao();
        int parcours_id;
        
        List<Questionnaire> questionnaires = questionnaire_dao.index();
        List<User> users = user_dao.index();
        
        Parcours p = new Parcours();
        p.setQuestionnaire_id(questionnaires.get(0));
        p.setUser_id(users.get(2));
        p.setDuration(10);
        parcours_id = parcours_dao.create(p);
        
        List<Question> questions = questionnaire_dao.getQuestion(questionnaires.get(0).getId_questionnaire());
        for(int i=0; i<questions.size(); i++){
            List<Response> responses = response_dao.showByIdQuestion(questions.get(i).getId_question());
            parcours_dao.insertParcoursQuestion(parcours_id, questions.get(i).getId_question(), responses.get(0).getId());
        }
        

        p = new Parcours();
        p.setQuestionnaire_id(questionnaires.get(1));
        p.setUser_id(users.get(2));
        p.setDuration(140);
        parcours_id = parcours_dao.create(p);
        
        questions = questionnaire_dao.getQuestion(questionnaires.get(1).getId_questionnaire());
        for(int i=0; i<questions.size(); i++){
            List<Response> responses = response_dao.showByIdQuestion(questions.get(i).getId_question());
            parcours_dao.insertParcoursQuestion(parcours_id, questions.get(i).getId_question(), responses.get(0).getId());
        }
        
        p = new Parcours();
        p.setQuestionnaire_id(questionnaires.get(0));
        p.setUser_id(users.get(3));
        p.setDuration(200);
        parcours_id = parcours_dao.create(p);
        
        questions = questionnaire_dao.getQuestion(questionnaires.get(0).getId_questionnaire());
        for(int i=0; i<questions.size(); i++){
            List<Response> responses = response_dao.showByIdQuestion(questions.get(i).getId_question());
            parcours_dao.insertParcoursQuestion(parcours_id, questions.get(i).getId_question(), responses.get(0).getId());
        }
        
        p = new Parcours();
        p.setQuestionnaire_id(questionnaires.get(1));
        p.setUser_id(users.get(3));
        p.setDuration(50);
        parcours_id = parcours_dao.create(p);
        
        questions = questionnaire_dao.getQuestion(questionnaires.get(1).getId_questionnaire());
        for(int i=0; i<questions.size(); i++){
            List<Response> responses = response_dao.showByIdQuestion(questions.get(i).getId_question());
            parcours_dao.insertParcoursQuestion(parcours_id, questions.get(i).getId_question(), responses.get(0).getId());
        }
        
        
    }
   
}
