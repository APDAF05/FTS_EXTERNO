/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LP400102
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class ErrorMessage {

    @JsonProperty("errorMessageValue")
    private String errorMessageValue;
    @JsonProperty("errorMessageKey")
    private String errorMessageKey;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessageValue, String errorMessageKey) {
        this.errorMessageValue = errorMessageValue;
        this.errorMessageKey = errorMessageKey;
    }

    /**
     * @return the errorMessageValue
     */
    @JsonProperty("errorMessageValue")
    public String getErrorMessageValue() {
        return errorMessageValue;
    }

    /**
     * @param errorMessageValue the errorMessageValue to set
     */
    @JsonProperty("errorMessageValue")
    public void setErrorMessageValue(String errorMessageValue) {
        this.errorMessageValue = errorMessageValue;
    }

    /**
     * @return the errorMessageKey
     */
    @JsonProperty("errorMessageKey")
    public String getErrorMessageKey() {
        return errorMessageKey;
    }

    /**
     * @param errorMessageKey the errorMessageKey to set
     */
    @JsonProperty("errorMessageKey")
    public void setErrorMessageKey(String errorMessageKey) {
        this.errorMessageKey = errorMessageKey;
    }

}
