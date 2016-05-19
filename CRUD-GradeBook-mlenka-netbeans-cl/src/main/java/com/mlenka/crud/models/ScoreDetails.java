/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlenka.crud.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author sphinx
 */
@XmlRootElement(name="ScoreDetails")
@XmlType(propOrder = {"score","feedback","appealStatus"})
public class ScoreDetails {
    private int score;
    private String feedback;
    private Student.Appeal appealStatus;
    public ScoreDetails(){
        
    }
    public ScoreDetails(int score,String feedback,Student.Appeal appealStatus){
        this.score = score;
        this.feedback = feedback;
        this.appealStatus = appealStatus;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    @XmlElement(name="score")
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * @param feedback the feedback to set
     */
    @XmlElement(name="feedback")
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    /**
     * @return the appealStatus
     */
    public Student.Appeal getAppealStatus() {
        return appealStatus;
    }

    /**
     * @param appealStatus the appealStatus to set
     */
    @XmlElement(name="appealStatus")
    public void setAppealStatus(Student.Appeal appealStatus) {
        this.appealStatus = appealStatus;
    }

    @Override
    public String toString() {
        return "ScoreDetails{" + "score=" + score + ", feedback=" + feedback + ", appealStatus=" + appealStatus + '}';
    }

    
}
