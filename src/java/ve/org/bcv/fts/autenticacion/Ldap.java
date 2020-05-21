/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.autenticacion;

/**
 *
 * @author brhernan
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import ve.org.bcv.seguridad.ldap.Atributo;
import ve.org.bcv.seguridad.ldap.Autenticador;
import ve.org.bcv.seguridad.ldap.Valor;
import ve.org.bcv.fts.util.AlmacenPropiedades;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase para validar un usuario con el directorio activo LDAP
 *
 * @author Richard Jimenez @copyright [This gets replaced though]
 * @version 1.0.0 @created 2012-07-31 11:13:37 @date 2012-07-31 11:13:37
 */
public class Ldap {

    /**
     * Direcion del servidor LDAP ldap-cert.intra.bcv
     */
    private final String SERVIDOR = AlmacenPropiedades.getPropiedad("ldap.servidor");

    /**
     * Direccion del servidor LDAP interno
     */
    private final String SERVIDOR_INTERNO = AlmacenPropiedades.getPropiedad("ldap.servidor.interno");
    /**
     * Cuenta navegante en LDAP cn=AdmOICI,ou=RECURSOS,o=BCV
     */
    private final String CUENTA_LDAP = AlmacenPropiedades.getPropiedad("ldap.manager-dn");
    /**
     * Cuenta navegante en LDAP INTERNO
     *
     */
    private final String CUENTA_LDAP_INTERNO = AlmacenPropiedades.getPropiedad("ldap.interno.manager-dn");
    /**
     * Clave de la cuenta navegante en LDAP admoicic
     */
    private final String PASSWORD = AlmacenPropiedades.getPropiedad("ldap.manager-password");

    private final String PASSWORD_INTERNO = AlmacenPropiedades.getPropiedad("ldap.interno.manager-password");

    private Logger log = LogManager.getLogger(Ldap.class.getName());

    /**
     * Lista de grupos de usuarios LDAP
     */
    ArrayList<String> grupo = null;
    /**
     * Atributo LDAP
     */
    private static final String ATRIBUTO_INTRUSO = "loginIntruderAttempts";
    /**
     * Atributo LDAP
     */
    private static final String ATRIBUTO_FULLNAME = "fullName";
    /**
     * Atributo LDAP
     */
    private static final String ATRIBUTO_GRUPO = "groupMembership";
    /**
     * Atributo LDAP
     */

    private static final String ATRIBUTO_PASSWORDEXPIRATIONTIME = "passwordExpirationTime";

    private static final String ROLE = "uniquemember";

    /**
     * Constructor de clase por defecto para inicializar los grupos de usuarios
     * autorizados para acceder a la aplicacion
     *
     */
    public Ldap() {
        grupo = new ArrayList<String>();
        //grupo.add(UtilMessage.getPropertyMessageBundle("ldap.grupo"));
        //cn=G_OICI_ADMIN,ou=Recursos,ou=OINTE,ou=CCS,o=BCV

    }

    /**
     * Metodo para autenticar un usuario contra el servidor LDAP
     *
     * @param request
     * @param usuario
     * @param clave
     * @param nuevaClave
     * @return codigo de respuesta
     */
    public String autentico(String usuario, String clave, String nuevaClave) {
        //      No se solicita passwordExpirationTime porque Oscar Trejo comentó que las cuentas del LDAP
        //      interno no se les coloca valor en ese atributo para que no expire su contraseña

//	String[] propiedades = {ATRIBUTO_FULLNAME, ATRIBUTO_GRUPO,ATRIBUTO_PASSWORDEXPIRATIONTIME,ATRIBUTO_INTRUSO}; comentado mientras se agrega el atributo intruso
        grupo.add(AlmacenPropiedades.getPropiedad("ldap.grupo"));
        String[] propiedades = {ATRIBUTO_FULLNAME, ATRIBUTO_GRUPO, ATRIBUTO_PASSWORDEXPIRATIONTIME};
        ArrayList atributos;
        Autenticador auten;
        log.debug("Iniciando autenticación");
        try {
            log.debug("SERVIDOR: " + SERVIDOR);
            log.debug("CUENTA_LDAP: " + CUENTA_LDAP);
            auten = new Autenticador(SERVIDOR, CUENTA_LDAP, PASSWORD);
            log.debug("Autenticando usuario en directorio activo..");
            log.debug("usuario:" + usuario);
//             log.debug("propiedades: " + propiedades.toString());
            atributos = new ArrayList(auten.busquedad(usuario, clave, null, propiedades));
            for (Iterator it = atributos.iterator(); it.hasNext();) {
                Object object = it.next();
                log.debug("object = " + object);

            }
            log.debug("atributos = " + atributos);
            if (!PerteneceAlGrupo(atributos)) {
                log.debug("usuario no pertenece a ningun grupo de la aplicacion");
                return "MF012";
            }
            if (claveExpirada(atributos)) {
                log.debug("clave expirada");
                if (nuevaClave == null) {
                    log.debug("clave expirada");
                    return "MV128";
                } else {
                    log.debug("Iniciando cambio de clave");
                    auten.cambiarPassword(usuario, clave, nuevaClave, null);
                    log.debug("Cambio de clave finalizado");
                }
            }
//            if (usuarioBean == null) {
//                usuarioBean = new BeanAutenticacion();
//                HttpSession session = request.getSession(true);
//                session.setAttribute("usuarioBean", usuarioBean);
//            }
//
//            usuarioBean.setFullName(obtenerValorAtributoLDAP(ATRIBUTO_FULLNAME, atributos));
//            usuarioBean.setHoraLogin(new Date());
//            usuarioBean.setLogin(usuario);
//            usuarioBean.setGrupos(obtenerValorListaAtributosLDAP(ATRIBUTO_GRUPO, atributos));
//            usuarioBean.setMaxInactiveSession(request.getSession().getMaxInactiveInterval());
//            usuarioBean.setAdminGroup(usuarioBean.perteneceAlGrupoAdmin(UtilMessage.getPropertyMessageBundle("ldap.administrador")));
//            log.debug("usuarioBean = " + usuarioBean);
//	    session.setAttribute("SPRING_SECURITY_LAST_USERNAME", bean.getFullName());

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("Cliente No existe")) {
                return "MF012";
            } else if (e.getMessage().contains("Clave invalida")) {
                if (nuevaClave != null) {
                    return "MV014";
                } else {
                    return "MV017";
                }
            } else {
                return "MF011";
            }
        }
        return "OK";
    }

    public String autenticoInterno(String usuario, String clave, String nuevaClave) {
        //      No se solicita passwordExpirationTime porque Oscar Trejo comentó que las cuentas del LDAP
        //      interno no se les coloca valor en ese atributo para que no expire su contraseña

//	String[] propiedades = {ATRIBUTO_FULLNAME, ATRIBUTO_GRUPO,ATRIBUTO_PASSWORDEXPIRATIONTIME,ATRIBUTO_INTRUSO}; comentado mientras se agrega el atributo intruso
        grupo.add(AlmacenPropiedades.getPropiedad("ldap.interno.grupo"));
        String[] propiedades = {ATRIBUTO_FULLNAME, ATRIBUTO_GRUPO};
        ArrayList atributos;
        Autenticador auten;
        log.debug("Iniciando autenticación");
        try {
            log.debug("SERVIDOR_INTERNO: " + SERVIDOR_INTERNO);
            log.debug("CUENTA_LDAP_INTERNO: " + CUENTA_LDAP_INTERNO);
            auten = new Autenticador(SERVIDOR_INTERNO, CUENTA_LDAP_INTERNO, PASSWORD_INTERNO);
            log.debug("Autenticando usuario en directorio activo..");
            log.debug("usuario:" + usuario);
            log.debug("propiedades: " + propiedades.toString());
            atributos = new ArrayList(auten.busquedad(usuario, clave, null, propiedades));
            for (Iterator it = atributos.iterator(); it.hasNext();) {
//                Object object = it.next();
                Atributo a = (Atributo) it.next();
                log.debug("object = " + a.getDn());

            }
            log.debug("atributos = " + atributos);
            if (!PerteneceAlGrupo(atributos)) {
                log.debug("usuario no pertenece a ningun grupo de la aplicacion");
                return "MF012";
            }
            if (claveExpirada(atributos)) {
                log.debug("clave expirada");
                if (nuevaClave == null) {
                    log.debug("clave expirada");
                    return "MV128";
                } else {
                    log.debug("Iniciando cambio de clave");
                    auten.cambiarPassword(usuario, clave, nuevaClave, null);
                    log.debug("Cambio de clave finalizado");
                }
            }
//            if (usuarioBean == null) {
//                usuarioBean = new BeanAutenticacion();
//                HttpSession session = request.getSession(true);
//                session.setAttribute("usuarioBean", usuarioBean);
//            }
//
//            usuarioBean.setFullName(obtenerValorAtributoLDAP(ATRIBUTO_FULLNAME, atributos));
//            usuarioBean.setHoraLogin(new Date());
//            usuarioBean.setLogin(usuario);
//            usuarioBean.setGrupos(obtenerValorListaAtributosLDAP(ATRIBUTO_GRUPO, atributos));
//            usuarioBean.setMaxInactiveSession(request.getSession().getMaxInactiveInterval());
//            usuarioBean.setAdminGroup(usuarioBean.perteneceAlGrupoAdmin(UtilMessage.getPropertyMessageBundle("ldap.administrador")));
//            log.debug("usuarioBean = " + usuarioBean);
//	    session.setAttribute("SPRING_SECURITY_LAST_USERNAME", bean.getFullName());

        } catch (Exception e) {
//            e.printStackTrace();
            if (e.getMessage().contains("Cliente No existe")) {
                return "MF012";
            } else if (e.getMessage().contains("Clave invalida")) {
                if (nuevaClave != null) {
                    return "MV014";
                } else {
                    return "MV017";
                }
            } else {
                return "MF011";
            }
        }
        return "OK";
    }

    /**
     * Metodo para validar si el usuario pertenece a un grupo de LDAP
     *
     * @param atributos
     * @return
     */
    private boolean PerteneceAlGrupo(ArrayList atributos) {
        List<String> grupos = obtenerValorListaAtributosLDAP(ATRIBUTO_GRUPO, atributos);
        log.debug("grupo = " + grupo);
        for (String atributoGrupo : grupos) {
            log.debug("atributoGrupo = " + atributoGrupo);
            if (grupo.contains(atributoGrupo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para verificar si la clave esta expirada
     *
     * @param atributos
     * @return boolean
     * @throws Exception
     */
    private boolean claveExpirada(ArrayList atributos) throws Exception {
        log.debug("claveExpirada = ");
        String fecha = obtenerValorAtributoLDAP(ATRIBUTO_PASSWORDEXPIRATIONTIME, atributos);
        log.debug("fecha = " + fecha);
        Date fechaD = null;
        if (fecha == null) {
            return false;
        } else {
            try {
                int dia;
                int mes;
                int anio;
                dia = Integer.parseInt(fecha.substring(6, 8));
                mes = Integer.parseInt(fecha.substring(4, 6));
                anio = Integer.parseInt(fecha.substring(0, 4));
                fechaD = new GregorianCalendar(anio, mes - 1, dia).getTime();

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (fechaD.getTime() < new Date().getTime()) {
                log.debug("fechaD = " + fechaD);
                return true;
            } else {
                log.debug("nooooooo");
                return false;
            }
        }
    }

    /**
     * Metodo para verificar si existe un intruso
     *
     * @param atributos
     * @return boolean
     */
    private boolean existeIntruso(ArrayList atributos) {
        String intruso = obtenerValorAtributoLDAP(ATRIBUTO_INTRUSO, atributos);
        if (intruso != null && Integer.parseInt(intruso) > 0) {
            return true;
        }
        return false;
    }

    /**
     * Metodo para verificar si existe el atributo en la lista de atributos
     *
     * @param atributo
     * @param listaAtributo
     * @return Lista de atributos
     */
    private List<String> obtenerValorListaAtributosLDAP(String atributo, ArrayList listaAtributo) {
        ArrayList<Valor> valores = new ArrayList<Valor>(((Atributo) listaAtributo.get(0)).getAtributo());
        for (Valor val : valores) {
            if (val.getAtributo().trim().equalsIgnoreCase(atributo)) {
                return (List) val.getValor();
            }
        }
        return null;
    }

    /**
     * Metodo para verificar si existe el atributo en la lista de atributos
     *
     * @param atributo
     * @param listaAtributo
     * @return atributo
     */
    private String obtenerValorAtributoLDAP(String atributo, ArrayList listaAtributo) {
        ArrayList<Valor> valores = new ArrayList<Valor>(((Atributo) listaAtributo.get(0)).getAtributo());
        for (Valor val : valores) {
            if (val.getAtributo().trim().equalsIgnoreCase(atributo)) {
                return (String) (new ArrayList(val.getValor())).get(0);
            }
        }
        return null;
    }

    /**
     * Metodo para obtener el DN del LDAP
     *
     * @param lista
     * @return DN
     */
    private String obtenerDNLDAP(ArrayList lista) {
        Atributo dn = (Atributo) lista.get(0);
        return dn.getDn();
    }

    public String cambiarClave(String usuario, String clave, String nuevaClave) {
//        Autenticador auten = null;
//        ArrayList atributos = null;
        Boolean cambioClaave = null;
//        try {
//
//            String[] propiedades = {ATRIBUTO_FULLNAME, ATRIBUTO_GRUPO, ATRIBUTO_PASSWORDEXPIRATIONTIME};
//            auten = new Autenticador(SERVIDOR, CUENTA_LDAP, PASSWORD);
//            atributos = new ArrayList(auten.busquedad(usuario, clave, null, propiedades));
//            for (Iterator it = atributos.iterator(); it.hasNext();) {
//                Object object = it.next();
//                 log.debug("object = " + object);
//
//            }
//            if (!PerteneceAlGrupo(atributos)) {
//                 log.debug("usuario no pertenece a ningun grupo de la aplicacion");
//                return "MF012";
//            }
//            if (claveExpirada(atributos)) {
////                if (StringUtils.isEmpty(clave)) {
////                    return "MV003";
////                }
////                if (StringUtils.isEmpty(nuevaClave)) {
////                    return "MV004";
////                }
////                if (StringUtils.isEmpty(usuario)) {
////                    return "MV002";
////                }
//                 log.debug("usuario = " + usuario);
//                 log.debug("clave = " + clave);
//                 log.debug("nuevaClave = " + nuevaClave);
//                cambioClaave = auten.cambiarPassword(usuario, clave, nuevaClave, null);
//                 log.debug("cambioClaave = " + cambioClaave);
//
//            }
//            if (cambioClaave) {
//                return "OK";
//            } else {
//                return "MF001";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "MF002";
//        }

        grupo.add(AlmacenPropiedades.getPropiedad("ldap.grupo"));
        String[] propiedades = {ATRIBUTO_FULLNAME, ATRIBUTO_GRUPO, ATRIBUTO_PASSWORDEXPIRATIONTIME};
        ArrayList atributos;
        Autenticador auten;
        log.debug("Iniciando cambio clave Ldap");
        try {

            auten = new Autenticador(SERVIDOR, CUENTA_LDAP, PASSWORD);

            atributos = new ArrayList(auten.busquedad(usuario, clave, null, propiedades));
            for (Iterator it = atributos.iterator(); it.hasNext();) {
//                Object object = it.next();
                Atributo a = (Atributo) it.next();
                log.debug("object = " + a.getDn());

            }
            log.debug("atributos = " + atributos);
            if (!PerteneceAlGrupo(atributos)) {
                log.debug("usuario no pertenece a ningun grupo de la aplicacion");
                return "MF012";
            }

            if (StringUtils.isEmpty(clave)) {
                return "MV003";
            }
            if (StringUtils.isEmpty(nuevaClave)) {
                return "MV004";
            }
            if (StringUtils.isEmpty(usuario)) {
                return "MV002";
            }
            log.debug("usuario = " + usuario);
            log.debug("clave = " + clave);
            log.debug("nuevaClave = " + nuevaClave);
            log.debug("Iniciando cambio de clave");
            cambioClaave = auten.cambiarPassword(usuario, clave, nuevaClave, null);
            log.debug("Cambio de clave finalizado");

            if (cambioClaave) {
                return "MS004";
            } else {
                return "MF001";
            }
//            if (usuarioBean == null) {
//                usuarioBean = new BeanAutenticacion();
//                HttpSession session = request.getSession(true);
//                session.setAttribute("usuarioBean", usuarioBean);
//            }
//
//            usuarioBean.setFullName(obtenerValorAtributoLDAP(ATRIBUTO_FULLNAME, atributos));
//            usuarioBean.setHoraLogin(new Date());
//            usuarioBean.setLogin(usuario);
//            usuarioBean.setGrupos(obtenerValorListaAtributosLDAP(ATRIBUTO_GRUPO, atributos));
//            usuarioBean.setMaxInactiveSession(request.getSession().getMaxInactiveInterval());
//            usuarioBean.setAdminGroup(usuarioBean.perteneceAlGrupoAdmin(UtilMessage.getPropertyMessageBundle("ldap.administrador")));
//            log.debug("usuarioBean = " + usuarioBean);
//	    session.setAttribute("SPRING_SECURITY_LAST_USERNAME", bean.getFullName());

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("Cliente No existe")) {
                return "MF012";
            } else if (e.getMessage().contains("Clave invalida")) {
                if (nuevaClave != null) {
                    return "MV014";
                } else {
                    return "MV017";
                }
            } else {
                return "MF011";
            }
        }

    }

//    public String changepassword(String newclave, Stri) throws Exception {
//
//    
//        MessageContext mctx = wsContext.getMessageContext();
//        String respuesta = "";
//        String institucion = "";
//        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
//        List userList = (List) http_headers.get("Username");
//        List passList = (List) http_headers.get("Password");
//        String username = "";
//        String password = "";
//
//        try {
//            if (userList != null) {
//                this.username = userList.get(0).toString();
//            }
//            if (passList != null) {
//                password = passList.get(0).toString();
//            }
//             log.debug(new StringBuilder().append("username = ").append(this.username).toString());
//
//            if ((this.username.trim().equals("")) || (password.trim().equals(""))) {
//                 log.debug(new StringBuilder().append("respuesta = ").append("Debe proveer USUARIO (Username) y PASSWORD (Password) en el header HTTP").toString());
//                throw new Exception("Debe proveer USUARIO (Username) y PASSWORD (Password) en el header HTTP");
//            }
//            if (RegexValidator.pregMatch("^$", newclave) != null) {
//                 log.debug(new StringBuilder().append("respuesta = ").append("La nueva clave no puede ser vacia.").toString());
//                throw new Exception("La nueva clave no puede ser vacia.");
//            }
//
//            Ldap ldap = new Ldap();
//            if (ldap.validarUsuarioExt(this.username)) {
//                respuesta = ldap.autentico(this.username, password, newclave);
//                 log.debug(new StringBuilder().append("respuesta newclave= ").append(respuesta).toString());
//                if (!(respuesta.equals("OK"))) {
//                     log.debug(new StringBuilder().append("respuesta = ").append(respuesta).toString());
//                    throw new Exception("USUARIO O PASSWORD INVALIDO");
//                }
//                 log.debug(new StringBuilder().append("institucion = ").append(institucion).toString());
//            } else {
//                 log.debug(new StringBuilder().append("respuesta = ").append("-USUARIO INVALIDO-").toString());
//                throw new Exception("-USUARIO INVALIDO-");
//            }
//             log.debug(new StringBuilder().append("Institución autenticada exitosamente, cambio de clave exitoso = ").append(institucion).toString());
//        } catch (Exception e) {
//            
//            e.printStackTrace();
//            throw new Exception(e.getMessage());
//        } finally {
//           
//        }
//        return respuesta;
//    }
}
