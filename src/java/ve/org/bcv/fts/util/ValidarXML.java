package ve.org.bcv.fts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.xml.sax.SAXException;
import ve.org.bcv.fts.dto.RespuestaDTO;
import ve.org.bcv.fts.exception.ErrorHandlerValidXML;
import ve.org.bcv.fts.exception.ExceptionTreatment;
import ve.org.bcv.fts.exception.ServicesException;

/**
 *
 * @author furibe
 */
public class ValidarXML {

    private String message;
    private Marshaller marshalle;
    private List<ServicesException> exceptions = new ArrayList();
    private List<String> errores = new ArrayList();
    private org.apache.logging.log4j.Logger log = LogManager.getLogger(ValidarXML.class.getName());

    public ValidarXML() {

        try {
            Locale.setDefault(new Locale("es", "VE"));

            JAXBContext context = JAXBContext.newInstance(ResponseXML.class);
            marshalle = context.createMarshaller();
            marshalle.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshalle.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        } catch (JAXBException ex) {
            log.error(ex);
        }

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RespuestaDTO validate(final String jsonSchema, final String jsonData, final String xsdPath, final File xmlPath, final InputStream filecontent) throws ServicesException {
        System.out.println("validateXMLSchema..... ");
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        SchemaFactory factory
                = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = factory.newSchema(new File(xsdPath));
        } catch (SAXException ex) {
            System.out.println("111111111111111 = ");
            ex.printStackTrace();
        }
        // creating a Validator instance
        Validator validator = schema.newValidator();
        // setting my own error handler
        ErrorHandlerValidXML errorHandlerValidXML = new ErrorHandlerValidXML();
        validator.setErrorHandler(errorHandlerValidXML);
        // preparing the XML file as a SAX source
        log.debug("VALIDANDO= ");
        try {
            Reader rdr = new InputStreamReader(new FileInputStream(xmlPath), "ISO-8859-1");
            StreamSource source = new StreamSource(rdr);
            validator.validate(source);
        } catch (SAXException ex) {
            log.debug("222222222222222 = ");
            throw new ServicesException("Failed to validate xml data");
        } catch (IOException ex) {
            log.debug("33333333333333333333333 = ");
            throw new ServicesException("Failed to validate xml data");
        }
        log.debug("FIN VALIDANDO= ");
        errores = errorHandlerValidXML.getErrores();
        log.debug("exceptions.size() = " + exceptions.size());
        if (errores.size() > 0) {
            respuestaDTO.setEstatus("404");
            ArrayList<String> mensajes = new ArrayList<String>();

            for (String error : errores) {
                log.debug("error = " + error);
                mensajes.add(error);
            }
            respuestaDTO.setMensaje(mensajes);
            log.debug("mensajes = " + mensajes);
            return respuestaDTO;
//                System.out.println("Failed with errors: " + errorCount);
        } else {
            respuestaDTO.setEstatus("200");
            return respuestaDTO;
        }

    }

}
