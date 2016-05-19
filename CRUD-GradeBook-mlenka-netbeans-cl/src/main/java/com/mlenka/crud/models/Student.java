/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlenka.crud.models;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author sphinx
 */
@XmlRootElement(name="Student")
@XmlType(propOrder = {"sid","name","score","feedback","isGraded","appealStatus"})
public class Student {
    private int sid;
    private String name;
    private int score;
    private String feedback;
    private boolean isGraded;
    private Appeal appealStatus;
    public enum Appeal{NOT_APPEALED,APPEALED,ACCEPTED,REJECTED}
    public Student(){
        
    }
    public Student(int sid,String name,int score,String feedback,boolean isGraded,Appeal appealStatus){
        this.sid=sid;
        this.name=name;
        this.score=score;
        this.feedback=feedback;
        this.isGraded=isGraded;
        this.appealStatus=appealStatus;
    }
    /**
     * @return the sid
     */
    public int getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     */
    @XmlElement(name="sid")
    public void setSid(int sid) {
        this.sid = sid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @XmlElement(name="name")
    public void setName(String name) {
        this.name = name;
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
     * @return the isGraded
     */
    public boolean getIsGraded() {
        return isGraded;
    }

    /**
     * @param isGraded the isGraded to set
     */
    @XmlElement(name="isGraded")
    public void setIsGraded(boolean isGraded) {
        this.isGraded = isGraded;
    }
    
    /**
     * @return the appealStatus
     */
    public Appeal getAppealStatus() {
        return appealStatus;
    }

    /**
     * @param appealStatus the appealStatus to set
     */
    @XmlElement(name="appealStatus")
    public void setAppealStatus(Appeal appealStatus) {
        this.appealStatus = appealStatus;
    }
    
    @Override
    public String toString() {
        return "Student{" + "sid=" + sid + ", name=" + name + ", score=" + score + ", feedback=" + feedback + ", isGraded=" + isGraded + ", appealStatus=" + appealStatus + '}';
    }
    
    
   
    
}
