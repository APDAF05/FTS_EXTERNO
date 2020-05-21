/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.exception;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ve.org.bcv.fts.util.RegexValidator;

/**
 *
 * @author furibe
 */
public class ErrorHandlerValidXML extends DefaultHandler {

    private String element = "";
    private List<ServicesException> exceptions = new ArrayList();
    private List<String> errores = new ArrayList();
    private Logger log = LogManager.getLogger(ErrorHandlerValidXML.class.getName());

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (localName != null && !localName.isEmpty()) {
            element = localName;
        } else {
            element = qName;
        }

    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        String mensaje = "";
        log.debug("warning   " + element + ": " + exception.getMessage());

        if (RegexValidator.pregMatch("(^cvc-type)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }

        if (RegexValidator.pregMatch("(^cvc-complex)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^cvc-attribute)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^cvc-elt.1.a)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^The element type)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^The value following)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        String mensaje = "";
        log.debug("error   " + element + ": " + exception.getMessage());

        if (RegexValidator.pregMatch("(^cvc-type)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^cvc-complex)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^cvc-attribute)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^cvc-elt.1.a)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^The element type)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^The value following)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
//            exception.printStackTrace();
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        String mensaje = "";
        log.debug("fatalError   " + element + ": " + exception.getMessage());

        if (RegexValidator.pregMatch("(^cvc-type)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^cvc-complex)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^cvc-attribute)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^cvc-elt.1.a)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^The element type)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        if (RegexValidator.pregMatch("(^The value following)+", exception.getMessage()) != null) {
            log.debug("En la linea  = " + exception.getLineNumber() + " " + exception.getMessage());
            mensaje = "En la linea  = " + exception.getLineNumber() + " " + exception.getMessage();
            errores.add(mensaje);
        }
        log.debug("exceptions.size() = " + exceptions.size());
    }

    public String getElement() {
        return element;
    }

    public List<ServicesException> getExceptions() {
        return exceptions;
    }

    public List<String> getErrores() {
        return errores;
    }

}
