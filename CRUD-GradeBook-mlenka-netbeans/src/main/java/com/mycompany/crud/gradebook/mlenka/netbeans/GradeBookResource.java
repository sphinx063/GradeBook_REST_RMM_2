/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crud.gradebook.mlenka.netbeans;

import com.mlenka.crud.models.GradeItem;
import com.mlenka.crud.models.ScoreDetails;
import com.mlenka.crud.services.GradeService;
import com.mlenka.crud.utils.Converter;
import java.net.URI;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST Web Service
 *
 * @author sphinx
 */
@Path("gradebook")
public class GradeBookResource {

    private static GradeService gradeService = new GradeService();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GradeBookResource
     */
    public GradeBookResource() {
       // gradeService = new GradeService();
    }

    @POST
    @Path("/gradeitems")
    @Consumes(MediaType.APPLICATION_XML)
    public Response createGradeItem(String content){
        Response response;
        GradeItem gradeItem=null;
        try{
            gradeItem=(GradeItem)Converter.convertFromXmlToObject(content, GradeItem.class);
            int createdId = gradeService.addGradeItem(gradeItem);
            if(createdId>0){
                String xmlString = Converter.convertFromObjectToXml(gradeItem, GradeItem.class);
                URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(createdId));
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(xmlString).build();
            }
            else{     
                response = Response.status(Response.Status.CONFLICT).entity(content).build();
            }
            
        } catch (JAXBException ex) {
            response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
        }catch(RuntimeException e){
             response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(content).build();
        }
        return response;
    }
    @PUT
    @Path("/gradeitems/{itemid}/students/{studentId}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateGradeItem(@PathParam("itemid") String itemId,@PathParam("studentId")String studentId,String content){
        System.out.println("Inside PUT");
        Response response;
        ScoreDetails scoreDetails=null;
        try{
            scoreDetails=(ScoreDetails)Converter.convertFromXmlToObject(content, ScoreDetails.class);         
            int sid=Integer.parseInt(studentId);
            int gradeId=Integer.parseInt(itemId);
            boolean status=gradeService.updateScore(gradeId, sid, scoreDetails);
            if(status){
                response = Response.status(Response.Status.OK).entity(content).build();
                System.out.println("Status True");
            }
            else{
                response = Response.status(Response.Status.CONFLICT).entity(content).build();
            }
        } catch (JAXBException ex) {
            response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
        }catch(RuntimeException e){
             response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(content).build();
        }
        return response;
    }
    @GET
    @Path("/gradeitems")
    @Produces(MediaType.APPLICATION_XML)
    public Response getGradeItems(){
        Response response;
        List<GradeItem> allGradeItems = gradeService.getGradeItems();
        if(allGradeItems.size()>0){
            String xmlString = "";
            GradeItem currentItem=null;
            for(int i =0;i<allGradeItems.size();i++){
                currentItem=allGradeItems.get(i);
                xmlString+=Converter.convertFromObjectToXml(currentItem,GradeItem.class);
            }
            response = Response.status(Response.Status.OK).entity(xmlString).build();
        }
        else{           
            response = Response.status(Response.Status.NOT_FOUND).entity("No GradeItem Resource to return").build();
        }
        return response;
    }
    @GET
    @Path("/gradeitems/{itemid}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getGradeItem(@PathParam("itemid")String itemId){
        Response response;
        GradeItem gradeItem = gradeService.getGradeItemByGradeName(Integer.parseInt(itemId));
        if(gradeItem!=null){
            String xmlString = Converter.convertFromObjectToXml(gradeItem, GradeItem.class);
            response = Response.status(Response.Status.OK).entity(xmlString).build();
        }
        else{
            response = Response.status(Response.Status.NOT_FOUND).entity("No GradeItem Resource to return").build();    
        }
        return response;
    }
    @GET
    @Path("/gradeitems/{itemid}/students/{studentId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getStudentGrade(@PathParam("itemid")String itemId,@PathParam("studentId")String studentId,@QueryParam("isStudent")String isStudent,@QueryParam("isInstructor")String isInstructor){
        System.out.println("$$$$$$  "+isStudent);
        System.out.println("%%%%%%%% "+isInstructor);
        Response response;
        GradeItem gradeItem = gradeService.getStudentGradeItem(Integer.parseInt(itemId), Integer.parseInt(studentId));
        if(gradeItem!=null){
            
            if(isStudent.equals("No") && isInstructor.equals("Yes")){
                String xmlString = Converter.convertFromObjectToXml(gradeItem, GradeItem.class);
                response = Response.status(Response.Status.OK).entity(xmlString).build();
            }
            else if(isStudent.equals("Yes") && isInstructor.equals("No")){
                if(gradeItem.getStudents().get(0).getIsGraded()){
                   String xmlString = Converter.convertFromObjectToXml(gradeItem, GradeItem.class);
                   response = Response.status(Response.Status.OK).entity(xmlString).build();
                }
                else{
                    
                    response = Response.status(Response.Status.NO_CONTENT).entity("Not Yet Graded").build();
                }
            }
            else{
                response = Response.status(Response.Status.NO_CONTENT).entity("Not Yet Graded").build();
            }
        }
        else{
            response = Response.status(Response.Status.NOT_FOUND).entity("No GradeItem Resource to return").build();    
        }
        return response;
    }
    @DELETE
    @Path("/gradeitems/{itemid}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteGradeItem(@PathParam("itemid")String itemId){
        System.out.print("In DELETE");
        Response response;
        boolean status = gradeService.removeGradeItem(Integer.parseInt(itemId));
        if(status){
           
           response = Response.status(Response.Status.NO_CONTENT).build();
        }
        else{
           
           response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }
    @DELETE
    @Path("/gradeitems/{itemid}/students/{studentId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteStudentGrade(@PathParam("itemid")String itemId,@PathParam("studentId")String studentId){
        Response response=null;
        System.out.print("In DELETE");
        boolean status = gradeService.removeStudentGrade(Integer.parseInt(itemId), Integer.parseInt(studentId));;
        if(status){
           response = Response.status(Response.Status.NO_CONTENT).build();
        }
        else{
           response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }
}
