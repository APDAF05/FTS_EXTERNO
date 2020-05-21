/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.Services.service;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import ve.org.bcv.fts.autenticacion.Ldap;
import ve.org.bcv.fts.bean.FtsTipoArchivo;
import ve.org.bcv.fts.exception.ExceptionTreatment;
import ve.org.bcv.fts.exception.GenericExceptionMapper;
import ve.org.bcv.fts.exception.ServicesException;
import ve.org.bcv.fts.exception.ServicesExceptionMapper;
import ve.org.bcv.fts.persistence.FtsTipoArchivoPersistence;
import ve.org.bcv.fts.util.AlmacenPropiedades;
import ve.org.bcv.fts.util.Jwt;
import ve.org.bcv.fts.util.SuccessfulMapper;

/**
 *
 * @author aandrade
 */
@Path("/autenticacion")
public class FtsAuthenticationFacadeREST {

    private Jwt tokenObject = new Jwt();

    private org.apache.logging.log4j.Logger log = LogManager.getLogger(FtsAuthenticationFacadeREST.class.getName());

    public FtsAuthenticationFacadeREST() {
        super();
    }

    @GET
    @Path("/iniciarSesion")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@Context HttpHeaders headers) {
        String rif = "";
        String token = "";
        log.info("Entrando al servicio de autenticacion");
        log.debug("Entrando al servicio de autenticacion");
        log.trace("Entrando al servicio de autenticacion");
        try {
            
            String username = headers.getRequestHeader("username").get(0);
            String password = headers.getRequestHeader("password").get(0);
            log.info("username = " + headers.getRequestHeader("username").get(0));
            log.info("password = " + headers.getRequestHeader("password").get(0));

            if ((username.trim().equals("")) || (password.trim().equals(""))) {
                log.info(new StringBuilder().append("respuesta = ").append("Debe proveer USUARIO (Username) y PASSWORD (Password) en el header HTTP").toString());
                throw new ServicesException(AlmacenPropiedades.getPropiedad("MV001"));
            } else {
                rif = username.substring(0, 10);
                String resp = this.auth(username, password);
                log.debug("resp = " + resp);
                /**
                 * fin de autenticacion
                 */
                if (isAutentico(resp)) {
                    log.info("#### username/password : " + username + "/" + password);

                    //Validar institucion autorizada rif
                    String TIME_MILLISECONDS_EXPIRATION_TOKEN = AlmacenPropiedades.getPropiedad("TIME_MILLISECONDS_EXPIRATION_TOKEN");
                    String APLICATION_CODE = AlmacenPropiedades.getPropiedad("APLICATION_CODE");
                    // Issue a token for the user
                    token = tokenObject.createJWT(username, APLICATION_CODE, rif, Long.parseLong(TIME_MILLISECONDS_EXPIRATION_TOKEN));

                    log.info("token: " + token);
                } else {
                    throw new ServicesException(AlmacenPropiedades.getPropiedad(resp));
                }
            }
            // Return the token on the response
        } catch (Exception ex) {
            try {
                log.error("HA OCURRIDO UNA EXCEPCION ");
                log.error("MENSAJE : " + ex.getMessage());
                log.error("CAUSA DE LA EXCEPCION : " + ex.getCause());
                log.error("STACK TRACE DE LA EXCEPCION : " + ExceptionUtils.getStackTrace(ex));
                ex.printStackTrace();
                new ExceptionTreatment().treat(ex);
            } catch (ServicesException e) {
                throw new WebApplicationException(new ServicesExceptionMapper().toResponse(e));
            }

            throw new WebApplicationException(new GenericExceptionMapper().toResponse(ex));
        }
        return Response.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

    @GET
    @Path("/cambiarClave")
    @Produces({MediaType.APPLICATION_JSON})
    public Response cambiarClave(@Context HttpHeaders headers, @QueryParam("claveNueva") String claveNueva) {
        String resp = "";
        Response response = null;
        try {
            String username = headers.getRequestHeader("username").get(0);
            String password = headers.getRequestHeader("password").get(0);
            log.info("username = " + headers.getRequestHeader("username").get(0));
            log.info("password = " + headers.getRequestHeader("password").get(0));
            if ((username.trim().equals("")) || (password.trim().equals(""))) {

                throw new ServicesException(AlmacenPropiedades.getPropiedad("MV001"));
            }

            resp = chagerPassword(username, password, claveNueva);

            if (resp.equals("MS004")) {
                response = new SuccessfulMapper().toResponse(AlmacenPropiedades.getPropiedad(resp));
            } else {
                throw new ServicesException(AlmacenPropiedades.getPropiedad(resp));
            }
        } catch (Exception ex) {
            try {
                new ExceptionTreatment().treat(ex);
            } catch (ServicesException e) {
                throw new WebApplicationException(new ServicesExceptionMapper().toResponse(e));
            }

            throw new WebApplicationException(new GenericExceptionMapper().toResponse(ex));
        }
        return response;
    }

    private String chagerPassword(String username, String password, String newPassword) {

        String respuesta = "";
        String institucion = "";
        Ldap ldap = new Ldap();

        log.debug("username = " + username);
        log.debug("password = " + password);
        try {
            log.debug("llamando el cambio de clave en LDAP");
            respuesta = ldap.cambiarClave(username, password, newPassword);
            log.debug("respuesta = " + respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("respuesta = " + respuesta);

        return respuesta;
    }

    private String auth(String username, String password) {
        String respuesta = "";
        Ldap ldap = new Ldap();

        log.debug("username = " + username);
        try {
            respuesta = ldap.autentico(username, password, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    private boolean isAutentico(String auth) {

        if (auth.equals("OK")) {
            return true;
        } else {
            return false;
        }
    }

}
