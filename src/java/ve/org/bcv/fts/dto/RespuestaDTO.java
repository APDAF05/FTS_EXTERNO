/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author furibe
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlType(propOrder = {"mensaje", "estatus"})
public class RespuestaDTO {

    @JsonProperty("mensaje")
    private List<String> mensaje = new ArrayList<>();
    @JsonProperty("estatus")
    private String estatus;

    @JsonProperty("mensaje")
    public List<String> getMensaje() {
        return mensaje;
    }

    @JsonProperty("mensaje")
    public void setMensaje(List<String> mensaje) {
        this.mensaje = mensaje;
    }

    @JsonProperty("estatus")
    public String getEstatus() {
        return estatus;
    }

    @JsonProperty("estatus")
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return mensaje + ", estatus=" + estatus;
    }

}
