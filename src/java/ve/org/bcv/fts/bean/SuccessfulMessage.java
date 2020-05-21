/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LP400102
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class SuccessfulMessage {

    @JsonProperty("successfulMessageValue")
    private String successfulMessageValue;
    @JsonProperty("successfulMessageKey")
    private String successfulMessageKey;

    public SuccessfulMessage() {
    }


    public SuccessfulMessage(String successfulMessageValue, String successfulMessageKey) {
        this.successfulMessageValue = successfulMessageValue;
        this.successfulMessageKey = successfulMessageKey;
    }

    @JsonProperty("successfulMessageValue")
    public String getSuccessfulMessageValue() {
        return successfulMessageValue;
    }

    @JsonProperty("successfulMessageValue")
    public void setSuccessfulMessageValue(String successfulMessageValue) {
        this.successfulMessageValue = successfulMessageValue;
    }

    @JsonProperty("successfulMessageKey")
    public String getSuccessfulMessageKey() {
        return successfulMessageKey;
    }

    @JsonProperty("successfulMessageKey")
    public void setSuccessfulMessageKey(String successfulMessageKey) {
        this.successfulMessageKey = successfulMessageKey;
    }

}
