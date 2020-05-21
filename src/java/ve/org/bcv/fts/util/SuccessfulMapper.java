/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ve.org.bcv.fts.bean.SuccessfulMessage;
import ve.org.bcv.fts.exception.ServicesExceptionMapper;

/**
 *
 * @author furibe
 */
public class SuccessfulMapper {
    
    public Response toResponse(String successfulMessageValue) {
        String valueMapper = null;
        ObjectMapper mapper = new ObjectMapper();
        SuccessfulMessage successfulMessage = new SuccessfulMessage(successfulMessageValue, "200");
                
       
        try {
            valueMapper = mapper.writeValueAsString(successfulMessage);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ServicesExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.OK).
                entity(valueMapper).type(MediaType.APPLICATION_JSON).
                build();
    }

    
}
