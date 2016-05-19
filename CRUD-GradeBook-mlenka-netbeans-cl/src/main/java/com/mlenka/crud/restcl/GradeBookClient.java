/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlenka.crud.restcl;

import com.mlenka.crud.models.GradeItem;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sphinx
 */
public class GradeBookClient {
    private static final Logger LOG = LoggerFactory.getLogger(GradeBookClient.class);
    
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-mlenka-netbeans/webresources";
    public GradeBookClient(){
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("gradebook");
    }
    public ClientResponse createGradeItem(Object gradeItem) throws UniformInterfaceException{
        return webResource.path("/gradeitems").type(MediaType.APPLICATION_XML).post(ClientResponse.class,gradeItem);
    }
    public ClientResponse updateGradeItem(String itemId,String studentId,Object scoreDetails) throws UniformInterfaceException{
        return webResource.path(String.format("/gradeitems/%s/students/%s",itemId,studentId)).type(MediaType.APPLICATION_XML).put(ClientResponse.class,scoreDetails);
    }
    public <T> T retrieveGradeItem(Class<T> responseType, String itemId) throws UniformInterfaceException {
        return webResource.path(String.format("/gradeitems/%s",itemId)).accept(MediaType.APPLICATION_XML).get(responseType);
    }
    public <T> T retrieveStudentGrade(Class<T> responseType, String itemId,String studentId,String isStudent,String isInstructor) throws UniformInterfaceException {
        return webResource.path(String.format("/gradeitems/%s/students/%s",itemId,studentId)).queryParam("isStudent", isStudent).queryParam("isInstructor", isInstructor).accept(MediaType.APPLICATION_XML).get(responseType);
    }
    public ClientResponse deleteGradeItem(String itemId) throws UniformInterfaceException {
        return webResource.path(String.format("/gradeitems/%s",itemId)).delete(ClientResponse.class);
    }
    public ClientResponse deleteStudentGrade(String itemId,String studentId) throws UniformInterfaceException {
        return webResource.path(String.format("/gradeitems/%s/students/%s",itemId,studentId)).delete(ClientResponse.class);
    }
}
