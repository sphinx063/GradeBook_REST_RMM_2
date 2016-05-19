/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlenka.crud.services;

import com.mlenka.crud.models.GradeItem;
import com.mlenka.crud.models.ScoreDetails;
import com.mlenka.crud.models.Student;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author sphinx
 */
public class GradeService {
   static List<GradeItem> gradeItems = new ArrayList<GradeItem>();
   static int currentGId = 0;
   static int currentSId = 0;
   //List<Student> students = new ArrayList<Student>();
   public List<Student> loadStudents(){
       List<Student> students = new ArrayList<Student>();
       students.add(new Student(380,"Mark",0,null,false,Student.Appeal.NOT_APPEALED));
       students.add(new Student(381,"Jack",0,null,false,Student.Appeal.NOT_APPEALED));
       students.add(new Student(382,"Peter",0,null,false,Student.Appeal.NOT_APPEALED));
       students.add(new Student(383,"Theo",0,null,false,Student.Appeal.NOT_APPEALED));
       return students;
   }
   public GradeService(){
   }
   public int addGradeItem(GradeItem item){
       List<Student> students = loadStudents();
       if(isGradeItemExists(item.getName())==0){
        currentGId+=1;
        item.setId(currentGId);   
        item.setStudents(students);
        gradeItems.add(item);
        return currentGId;
       }
       return 0;
   }
   
   public List<GradeItem> getGradeItems(){
       return gradeItems;
   }
   public int isGradeItemExists(String gname){
       int gid = 0;
       if(gradeItems.size()>0){
           for(GradeItem item:gradeItems){
               if(item.getName().equalsIgnoreCase(gname)){
                   gid = item.getId();
                   break;
               }
           }
       }
       return gid;
   }
  public GradeItem getGradeItemByGradeName(int itemId){
      for(GradeItem item:gradeItems){
          if(item.getId()==itemId){
              return item;
          }
      }
      return null;
  }
  public GradeItem getStudentGradeItem(int itemId,int sid){
      for(GradeItem item:gradeItems){
          if(item.getId()==itemId){
              for(Student student:item.getStudents()){
                  if(student.getSid()==sid){
                      GradeItem studentGrade = new GradeItem();
                      studentGrade.setId(itemId);
                      studentGrade.setMax(item.getMax());
                      studentGrade.setName(item.getName());
                      studentGrade.setWeight(item.getWeight());
                      ArrayList<Student> list = new ArrayList<Student>();
                      list.add(student);
                      studentGrade.setStudents(list);
                      return studentGrade;
                  }
              }
          }
      }
      return null;
  }
  public boolean updateScore(int itemId,int sid,ScoreDetails scoreDetails){
      int itemIndex=-1;
      int studentIndex=-1;
      boolean isSuccessful=false;
      for(GradeItem item:gradeItems){
          itemIndex++;
          if(item.getId()==itemId){
              for(Student student:item.getStudents()){
                  studentIndex++;
                  if(student.getSid()==sid){
                      gradeItems.get(itemIndex).getStudents().get(studentIndex).setScore(scoreDetails.getScore());
                      gradeItems.get(itemIndex).getStudents().get(studentIndex).setFeedback(scoreDetails.getFeedback());
                      gradeItems.get(itemIndex).getStudents().get(studentIndex).setIsGraded(true);
                      gradeItems.get(itemIndex).getStudents().get(studentIndex).setAppealStatus(scoreDetails.getAppealStatus());
                      isSuccessful=true;
                      break;
                  }
              }
          }
          if(isSuccessful)
              break;
      }
      return isSuccessful;
  }
  public boolean removeGradeItem(int itemId){
      boolean status=false;
      for(int i=0;i<gradeItems.size();i++){
          if(gradeItems.get(i).getId()==itemId){
              gradeItems.remove(i);
              status = true;
              break;
          }
      }
      return status;
  }
  public boolean removeStudentGrade(int itemId,int sid){
      boolean status = false;
      for(int i=0;i<gradeItems.size();i++){
          if(gradeItems.get(i).getId()==itemId){
              for(int j=0;j<gradeItems.get(i).getStudents().size();j++){
                  if(gradeItems.get(i).getStudents().get(j).getSid()==sid){
                      resetStudent(i,j);
                      status = true;
                      break;
                  }
              }
          }
          if(status)
              break;
      }
      return status;
  }
  void resetStudent(int gradeIndex,int studentIndex){
      gradeItems.get(gradeIndex).getStudents().get(studentIndex).setScore(0);
      gradeItems.get(gradeIndex).getStudents().get(studentIndex).setFeedback(null);
      gradeItems.get(gradeIndex).getStudents().get(studentIndex).setIsGraded(false);
      gradeItems.get(gradeIndex).getStudents().get(studentIndex).setAppealStatus(Student.Appeal.NOT_APPEALED);
  }
}
