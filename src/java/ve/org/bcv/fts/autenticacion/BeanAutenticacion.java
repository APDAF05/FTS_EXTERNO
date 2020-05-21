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
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import ve.org.bcv.fts.util.RegexValidator;


/**
 * Clase responsable de la administracion de usuario, menu y control de sesion
 *
 * @author Richard Jimenez
 * @copyright [This gets replaced though]
 * @version 1.0.0
 * @created 2012-07-23 10:47:16
 * @date 2012-07-23 10:47:16
 */
public class BeanAutenticacion implements Serializable {

    /**
     * Maximo tiempo de sesion
     */
    private int maxInactiveSession;
    /**
     * Flag para mostrar o no el menu lateral
     */
    private boolean rightCollapsed = false;
    /**
     * Fecha y hora de inicio de sesion
     */
    private Date horaLogin;
    /**
     * Nombre y apellido completo de usuario en ldap
     */
    private String fullName;
    /**
     * Usuario
     */
    private String login;
    /**
     * Lista de grupos
     */
    private List<String> grupos;
    /**
     * Lista de menu de usuario
     */
    private boolean adminGroup = false;

    public void init() {
//        System.out.println("PostConstruct = ");
//        perteneceAlGrupoAdmin("G_OITM_ADMIN");
    }

    /**
     * Metodo para obtener grupos
     *
     * @return lista de grupos
     */
    public List<String> getGrupos() {
        return grupos;
    }

    /**
     * Setea un listado de grupos
     *
     * @param grupos
     */
    public void setGrupos(List<String> grupos) {
        this.grupos = grupos;
    }

    public BeanAutenticacion() {
    }

    /**
     * Devuelve la hora de login
     *
     * @return Date
     */
    public Date getHoraLogin() {
        return horaLogin;
    }

    /**
     * Constructor para inicializar fecha y nombre completo
     *
     * @param horaLogin
     * @param fullName
     */
    public BeanAutenticacion(Date horaLogin, String fullName) {
        this.horaLogin = horaLogin;
        this.fullName = fullName;
    }

    /**
     * Setea la hora de login
     *
     * @param horaLogin
     */
    public void setHoraLogin(Date horaLogin) {
        this.horaLogin = horaLogin;
    }

    /**
     * Devuelve el nombre y apellido del usuario grabado en LDAP
     *
     * @return String
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setea el nombre y apellido
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Devuelve el login de usuario
     *
     * @return String
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setea el login de usuario
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Verifica si el usuario pertenece al nombre del grupo definido en
     * <code>nombreGrupo</code>.
     *
     * @param nombreGrupo nombre del grupo que se esta buscando en la colleccion
     * del grupos al que pertenece el usuario.
     * @return retorna <code>True</code> si el usuario pertenece al grupo.
     */
    public boolean perteneceAlGrupo(String nombreGrupo) {

        System.out.println("nombreGrupo = " + nombreGrupo);

        for (String grupo : grupos) {
            System.out.println("grupo = " + grupo);
            if (grupo.trim().equalsIgnoreCase(nombreGrupo)) {
                return true;
            }


        }
        return false;
    }

    public boolean perteneceAlGrupoAdmin(String nameGroup) {
        System.out.println("perteneceAlGrupoAdmin = " );
        for (String grupo : grupos) {
            System.out.println("grupo = " + grupo);
            if (RegexValidator.pregMatch("cn=" + nameGroup + ",", grupo) != null) {
                adminGroup = true;
            }

        }
        return adminGroup;
    }

    /**
     * Maximo tiempo de sesion
     *
     * @return the maxInactiveSession
     */
    public int getMaxInactiveSession() {
        return maxInactiveSession;
    }

    /**
     * Maximo tiempo de sesion
     *
     * @param maxInactiveSession the maxInactiveSession to set
     */
    public void setMaxInactiveSession(int maxInactiveSession) {
        this.maxInactiveSession = maxInactiveSession;
    }

    public boolean isAdminGroup() {
        return adminGroup;
    }

    public void setAdminGroup(boolean adminGroup) {
        this.adminGroup = adminGroup;
    }

    /**
     * Devuelve todos los valores de los metodos existentes en la clase
     *
     * @return String
     */
    @Override
    public String toString() {
        return "BeanAutenticacion{" + "maxInactiveSession=" + maxInactiveSession + ", rightCollapsed=" + rightCollapsed + ", horaLogin=" + horaLogin + ", fullName=" + fullName + ", login=" + login + ", grupos=" + grupos + '}';
    }
}
