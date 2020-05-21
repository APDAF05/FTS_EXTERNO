package ve.org.bcv.fts.util;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "BANK_DATA")
@XmlType(propOrder = {"errorsXML"})
public class ResponseXML {

    private ArrayList<ErrorsXML> errorsXML = new ArrayList<>();

    public ResponseXML() {

    }

    //@XmlElementWrapper(name="ERROR_XML")
    @XmlElement(name="ERROR_XML")
    public ArrayList<ErrorsXML> getErrorsXML() {
        return errorsXML;
    }

    public void setErrorsXML(ArrayList<ErrorsXML> errorsXML) {
        this.errorsXML = errorsXML;
    }

}
