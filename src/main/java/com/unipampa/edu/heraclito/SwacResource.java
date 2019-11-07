/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unipampa.edu.heraclito;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jcgluz
 */
@Path("/swac")
public class SwacResource {

    private static final String SWAC_INTERNAL_SERVICE_BASE = 
            "http://localhost:8090/swac/";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwacResource
     */
    public SwacResource() {
    }

    /**
     * Retrieves representation of an instance of heraclito.SwacResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Path("/{student}/ndpl_tutor")
    public Response getMsg(@PathParam("student") String studentId) {
        String swacInternalServiceUrl=
                SWAC_INTERNAL_SERVICE_BASE+studentId+"/student_profile";
        String output = NetClientGet.Get(swacInternalServiceUrl);
        if (output==null) {          
            return Response.noContent().build();
        } else {
            return Response.ok(output, MediaType.TEXT_PLAIN).build();
        }
    }

    /**
     * POST method for sending a request message for SWAC service
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    @Path("/ndpl_tutor/proof_editor/request")
    public void postRequestMsg(String content) {
        String swacInternalServiceUrl = 
                SWAC_INTERNAL_SERVICE_BASE+"student_profile/proof_editor/request";
        //System.out.println(swacInternalServiceUrl);
        NetClientPost.Post(swacInternalServiceUrl,content);
    }

   /**
     * POST method for sending an inform message for SWAC service
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    @Path("/ndpl_tutor/proof_editor/inform")
    public void postInformMsg(String content) {
        String swacInternalServiceUrl=
                    SWAC_INTERNAL_SERVICE_BASE+"student_profile/proof_editor/inform";
        //System.out.println(swacInternalServiceUrl);
        NetClientPost.Post(swacInternalServiceUrl,content);
    }

    /**
     * PUT method for updating or creating an instance of SwacResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
  
     
}
