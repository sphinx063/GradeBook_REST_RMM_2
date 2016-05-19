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
@XmlRootElement(name="GradeItem")
@XmlType(propOrder = {"id","name","weight","max","students"})
public class GradeItem {
    private int id;
    private String name;
    private int weight;
    private int max;
    private List<Student> students;

    public GradeItem(){
    }
    /**
     * @return the id
     */
    public GradeItem(int id,String name,int weight,int max){
        this.id=id;
        this.name=name;
        this.weight=weight;
        this.max=max;
    }
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @XmlElement(name="id")
    public void setId(int id) {
        this.id = id;
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
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    @XmlElement(name="weight")
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    @XmlElement(name="max")
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the students
     */
    
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    @XmlElement(name="Students")
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "GradeItem{" + "id=" + id + ", name=" + name + ", weight=" + weight + ", max=" + max + ", students=" + students + '}';
    }
      
}
