/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author LP400102
 */
@Provider
public class ServicesExceptionMapper implements ExceptionMapper<ServicesException> {

    @Override
    public Response toResponse(ServicesException e) {
        String valueMapper = null;
        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage errorMessage = new ErrorMessage(
                ErrorMessages.INTERNAL_SERVER_ERROR.name(),
                e.getMessage()
        );
        try {
            valueMapper = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ServicesExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(valueMapper).type(MediaType.APPLICATION_JSON).
                build();
    }

}
