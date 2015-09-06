/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import ledes.hidra.rest.model.ErrorMessage;

/**
 *
 * @author pedro
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage(), 404, "http://glpn.ledes.net:8080/");
        return Response.status(Status.NOT_FOUND).entity(error).build();
    }

}
