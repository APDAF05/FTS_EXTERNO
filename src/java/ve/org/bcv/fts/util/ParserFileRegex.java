/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import ve.org.bcv.fts.bean.ParserError;
import ve.org.bcv.fts.dto.RespuestaDTO;

/**
 *
 * @author furibe
 */
public class ParserFileRegex implements Serializable {

    private String fileName;
    private boolean valid;
    public String FORMATO_ENCODING = "ISO-8859-1";
    public String ARCHIVO_CONFIGURACION_DETALLE = "configXML";
    private ArrayList registros;
    private List<String> errores;
    private BigDecimal cotizado;
    private BigDecimal total;
    private InputStream config;
    RespuestaDTO respuestaDTO = new RespuestaDTO();
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(JsonUtils.class.getName());

    public ParserFileRegex() {
        this.valid = false;
        this.registros = new ArrayList();
        this.errores = new ArrayList();
        this.cotizado = new BigDecimal(0);
        this.total = new BigDecimal(0);
        this.config = null;
    }

    public InputStream getConfig() {
        return config;
    }

    public void setConfig(InputStream config) {
        this.config = config;
    }

    public boolean isValid() {
        return this.valid;
    }

    public ArrayList getRegistros() {
        return registros;
    }

//    public ArrayList getErrores() {
//        return errores;
//    }
    public BigDecimal getCotizado() {
        return cotizado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public RespuestaDTO validate(final String jsonSchema, final String jsonData, final String xsdPath, final File xmlPath, final InputStream filecontent) {
        try {

            String trama;
            int[] indices;
            String[] tipos;
            ArrayList[] pregall;
            String[] str_fields;
            String[] expresiones;
            String[] names;
            String regex_fields = "";
            String regex_fields_error = "";
            InputStreamReader reader = null;
            FileInputStream stream = null;
            Element e;
            int field_size;
            int size;
            Element raizXML = obtenerRaizXML(ARCHIVO_CONFIGURACION_DETALLE);
            List atributos = raizXML.getChildren("atributo");
            String sep = raizXML.getAttributeValue("separator");
            String field_regex;
            //System.out.println("sep = " + sep);
            Iterator iter = atributos.iterator();
            int numeroCampo = 0;
            int linea = 1;
            int ind = 0;
            int i = 0;
            int subregex = 1;
            this.valid = true;
            indices = new int[atributos.size()];
            tipos = new String[atributos.size()];
            expresiones = new String[atributos.size()];
            names = new String[atributos.size()];
            RespuestaDTO respuestaDTO = new RespuestaDTO();
            while (iter.hasNext()) {
                //indices[ind]=subregex;
                e = (Element) iter.next();
                //columnaInicial = Integer.parseInt(e.getAttributeValue("columnaInicial"));
                //columnaFinal = Integer.parseInt(e.getAttributeValue("columnaFinal"));
                //field_size=columnaFinal-columnaInicial+1;
                field_size = Integer.parseInt(e.getAttributeValue("subregex"));
                size = Integer.parseInt(e.getAttributeValue("size"));
                field_regex = e.getAttributeValue("dato");
                //regex_fields = regex_fields + "(.{" + field_size + "})";
                indices[ind] = field_size;
                tipos[ind] = e.getAttributeValue("tipo");
                names[ind] = e.getAttributeValue("nombre");
                expresiones[ind] = field_regex;
                if (!regex_fields.equals("")) {
                    regex_fields = regex_fields + sep;
                }
//                regex_fields = regex_fields + "(" + size + ")";
                regex_fields = regex_fields + "(.{" + size + "})";
                ind++;
            }
            regex_fields = "^" + regex_fields + "$";

            log.debug("EXPRESION REGULAR COMPLETA NEGOCIO: '" + regex_fields + "'");

            reader = new InputStreamReader(filecontent, FORMATO_ENCODING);
            BufferedReader bf = new BufferedReader(reader);
            while ((trama = bf.readLine()) != null) {
                if (linea >= 1) {

                    log.debug("VALIDANDO LINEA: '" + trama + "'");
                    str_fields = RegexValidator.pregMatch(regex_fields, trama);

                    for (String str_field : str_fields) {

                        log.debug("str_field = " + str_field);

                    }

                    if (str_fields != null) {
                        ArrayList objetos = new ArrayList();

                        //Cliente client=new Cliente();
                        //client.setMonto(new BigDecimal(str_fields[3]));
                        //client.setRif(str_fields[1]);
                        //this.total=this.total.add(client.getMonto());
                        //this.registros.add(client);
                        for (i = 0; i < indices.length; i++) {
                            if (tipos[i].equals("BigDecimal")) {
                                objetos.add(new BigDecimal(str_fields[indices[i]]));
                            } else {
                                objetos.add(str_fields[indices[i]]);
                            }
                            //System.out.println("DATO "+i+": '"+str_fields[i]+"'");
                        }

                        /**
                         * for(i=0;i<str_fields.length;i++){
                         * System.out.println("DATO "+i+":
                         * '"+str_fields[i]+"'"); }*
                         */
                        List<String> errosLine = validLinea(str_fields, linea, expresiones, names);

                        if (errosLine != null || errosLine.size() >= 0) {
                            Stream.of(errosLine, errores).forEach(errores::addAll);
                        }
                        this.registros.add(objetos);
                    } else {
                        boolean error = false;
                        regex_fields_error = "";
                        ParserError perror = new ParserError();
                        perror.setLinea(linea);
                        perror.setDato(trama);
                        for (i = 0; (i < expresiones.length) && !error; i++) {
                            regex_fields_error = regex_fields_error + expresiones[i];
                            if (i < (expresiones.length - 1)) {
                                regex_fields_error = regex_fields_error + sep;
                            }
                            if (i == (expresiones.length - 1)) {
                                str_fields = RegexValidator.pregMatch("^" + regex_fields_error + "$", trama);
                            } else {
                                str_fields = RegexValidator.pregMatch("^" + regex_fields_error, trama);
                            }
                            if (str_fields == null) {
                                perror.setFieldname(names[i]);
                                perror.setIndicefield(i + 1);
                                error = true;
                            }
                        }
                        this.valid = false;
                        //this.errores.add(linea+"");
//                        this.errores.add(perror);
                    }

                }
                linea++;
            }
            this.respuestaDTO.setMensaje(errores);
        } catch (Exception e) {
            this.valid = false;
            e.printStackTrace();
        }
        return respuestaDTO;
    }

    public ArrayList validLinea(String[] str_fields, int linea, String[] expresiones, String[] names) {
        int count = 0;
        ArrayList errors = new ArrayList();
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstatus("200");
        for (int j = 1; j < str_fields.length; j++) {
            String str_field = str_fields[j];
            log.debug("str_field = " + str_field);
            log.debug("expresion = " + expresiones[count]);
            log.debug("names = " + names[count]);

            if (RegexValidator.pregMatch(expresiones[count], str_field) == null) {
                respuestaDTO.setEstatus("404");
                String mensaje = "En la linea  = " + linea + "el campo  " + str_field + "No coincide con el formato ";
                errors.add(mensaje);

            }

            count++;
        }

        return errors;
    }

    public Element obtenerRaizXML(InputStream xmlConfig) {

        Document doc = null;

        try {
            SAXBuilder builder = new SAXBuilder(false);
            //SAXBuilder builder = new SAXBuilder(false);
            //InputStream xmlConfig = getClass().getResourceAsStream(nombreArchivoXML);
            //InputStream xmlConfig = new InputStream();
            doc = builder.build(xmlConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc.getRootElement();
    }

    public Element obtenerRaizXML(String nombreArchivoXML) {

        Document doc = null;

        try {
            SAXBuilder builder = new SAXBuilder(false);
            //SAXBuilder builder = new SAXBuilder(false);
            InputStream xmlConfig;
            if (this.config == null) {
                xmlConfig = getClass().getResourceAsStream(nombreArchivoXML);
            } else {
                xmlConfig = this.config;
            }
            //InputStream xmlConfig = new InputStream();
            doc = builder.build(xmlConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc.getRootElement();
    }
}
