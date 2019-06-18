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
    private Integer duration; 
    private User user_id; // (/!\ BDD access) all User info 
    private Questionnaire questionnaire_id; // (/!\ BDD access) all Questionnaire info
    private Integer count_answers;
    private Integer count_good_answers;
    

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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
    
    public Integer getCount_answers(){
        return count_answers;
    }
    
    public void setCount_answers(Integer count_answers){
        this.count_answers = count_answers;
    }
    
    public Integer getCount_good_answers(){
        return count_good_answers;
    }
    
    public void setCount_good_answers(Integer count_good_answers){
        this.count_good_answers = count_good_answers;
    }


    
    /**
     * ToSting override
     * @return all the informations 
     */
    @Override
    public String toString() {
        return "parcours{" + "id=" + id + ", duration=" + duration + ", user_id=" + user_id + ", questionnaire_id=" + questionnaire_id + '}';
    }
    
    public String durationToString(){
        
        int duration = this.duration;
        
        int h = duration/3600;
        int min = (duration - h*3600)/60;
        int s = ((duration - h*3600)-min*60);
        
        return String.format("%02d", h) + ":" + String.format("%02d", min) + ":" + String.format("%02d", s);
        
    }
    
    //TO DO getScore() 
    //get the score thanks to the number of validated answers
   
    
    
}
