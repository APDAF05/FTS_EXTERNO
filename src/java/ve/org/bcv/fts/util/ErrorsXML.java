
package ve.org.bcv.fts.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ERROR_XML")
@XmlType(propOrder={"line_Number", "column_Number", "message"})
public class ErrorsXML {
 
    private String line_number;
    private String column_number;
    private String message;
    
    public ErrorsXML(){
        
    }
    
    @XmlElement(name="LINE_NUMBER")
    public String getLine_Number() {
        return line_number;
    }
    
    public void setLine_Number(String line_number) {
        this.line_number = line_number;
    }
    
    @XmlElement(name="COLUMN_NUMBER")
    public String getColumn_Number() {
        return column_number;
    }
    
    public void setColumn_Number( String column_number ) {
        this.column_number = column_number;
    }

    @XmlElement(name="MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
