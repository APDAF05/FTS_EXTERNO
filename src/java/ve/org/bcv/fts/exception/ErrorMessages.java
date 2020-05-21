/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.exception;

/**
 *
 * @author LP400102
 */
public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    COULD_NOT_STORE_THE_DATA("Could not store the data"),
    USER_INVALID("Usuario o Password Invalido"),
    COULD_NOT_UPDATE_USER_PROFILE("Could not update user profile"),
    PERSON_AGENT_AND_SELLER("La persona no pude ser agente y vendededor por favor validar."),
    INST_NO_AUTORIZ("INSTITUCION NO AUTORIZADA"),
    NO_RECORD_FOUND("No se encontró registro para la busqueda "),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Something went wrong. Please repeat this operation later."),
    ERROR_20012("INSTITUCION NO AUTORIZADA");
    
   private String errorMessage;
   
   ErrorMessages(String errorMessage)
   {
      this.errorMessage = errorMessage;    
   }
    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
