/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ListReportProvider;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import ve.org.bcv.fts.dto.RespuestaDTO;
import ve.org.bcv.fts.exception.ServicesException;

/**
 *
 * @author aandrade
 */
public class JsonUtils {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(JsonUtils.class.getName());
    private static List<String> errores = new ArrayList();

    /**
     * Validate the given JSON data against the given JSON schema
     *
     * @param jsonSchema as String
     * @param jsonData as String
     * @throws MessageBodyReaderValidationException in case of an error during
     * validation process
     */
    public static RespuestaDTO validate(final String jsonSchema, final String jsonData, final String xsdPath, final File xmlPath, InputStream filecontent)
            throws ServicesException {
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try {
            final JsonNode d = JsonLoader.fromString(jsonData);
            final JsonNode s = JsonLoader.fromString(jsonSchema);

            final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                    .setReportProvider(new ListReportProvider(LogLevel.ERROR, LogLevel.FATAL))
                    .freeze();
            JsonValidator v = factory.getValidator();

            ProcessingReport report = v.validate(s, d);

            System.out.println(report);
//            if (!report.toString().contains("success")) {
            if (!report.isSuccess()) {
                respuestaDTO.setEstatus("404");
                for (ProcessingMessage processingMessage : report) {
                    if (LogLevel.ERROR.equals(processingMessage.getLogLevel())) {
                        errores.add(processingMessage.getMessage());
                    }
                }
            }
            respuestaDTO.setMensaje(errores);
            return respuestaDTO;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServicesException("Failed to validate json data");
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new ServicesException("Failed to validate json data");
        }
    }

    /**
     * Taken from <a href=
     * "http://www.mkyong.com/java/how-to-convert-inputstream-to-string-in-java/"
     * >www.mkyong.com</a>
     *
     * @param is {@link InputStream}
     * @return Stream content as String
     */
    public static String getStringFromInputStream(InputStream is) throws ServicesException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ServicesException(e.getMessage());
                }
            }
        }

        return sb.toString();
    }
}
