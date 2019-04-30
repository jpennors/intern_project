/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.Duration;

/**
 *
 * @author lolal
 */
public class Parcours {
    
    /*
    ATTRIBUTES 
    */
    private Integer id; 
    private Duration duration; 
    private User user_id; // (/!\ BDD access) all User info 
    private Questionnaire questionnaire_id; // (/!\ BDD access) all Questionnaire info
    
    /**
     * CONSTUCTOR
     * @param id
     * @param duration
     * @param user_id
     * @param questionnaire_id 
     */
    public Parcours(Integer id, Duration duration, User user_id, Questionnaire questionnaire_id) {
        this.id = id;
        this.duration = duration;
        this.user_id = user_id;
        this.questionnaire_id = questionnaire_id;
    }

    public Parcours() {}
    
    /**
     GETTERS SETTERS
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Questionnaire getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(Questionnaire questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }


    
    /**
     * ToSting override
     * @return all the informations 
     */
    @Override
    public String toString() {
        return "parcours{" + "id=" + id + ", duration=" + duration + ", user_id=" + user_id + ", questionnaire_id=" + questionnaire_id + '}';
    }
    
    //TO DO getScore() 
    //get the score thanks to the number of validated answers
   
    
    
}
