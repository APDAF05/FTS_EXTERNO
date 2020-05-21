/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author furibe
 */
@Entity
@Table(name = "FTS_ARCHIVO_REC", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsArchivoRec.findAll", query = "SELECT f FROM FtsArchivoRec f")
    , @NamedQuery(name = "FtsArchivoRec.findByNuRecepcion", query = "SELECT f FROM FtsArchivoRec f WHERE f.nuRecepcion = :nuRecepcion")
    , @NamedQuery(name = "FtsArchivoRec.findByCoUsuario", query = "SELECT f FROM FtsArchivoRec f WHERE f.coUsuario = :coUsuario")
    , @NamedQuery(name = "FtsArchivoRec.findByFeRegistro", query = "SELECT f FROM FtsArchivoRec f WHERE f.feRegistro = :feRegistro")
    , @NamedQuery(name = "FtsArchivoRec.findByFeActualizacion", query = "SELECT f FROM FtsArchivoRec f WHERE f.feActualizacion = :feActualizacion")
    , @NamedQuery(name = "FtsArchivoRec.findByCoPropMod", query = "SELECT f FROM FtsArchivoRec f WHERE f.coPropMod = :coPropMod")})
public class FtsArchivoRec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NU_RECEPCION", nullable = false, length = 20)
    private String nuRecepcion;
    @Basic(optional = false)
    @Lob
    @Column(name = "IM_ARCHIVO", nullable = false)
    private String imArchivo;
    @Basic(optional = false)
    @Column(name = "CO_USUARIO", nullable = false, length = 16)
    private String coUsuario;
    @Basic(optional = false)
    @Column(name = "FE_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;
    @Basic(optional = false)
    @Column(name = "FE_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feActualizacion;
    @Basic(optional = false)
    @Column(name = "CO_PROP_MOD", nullable = false, length = 5)
    private String coPropMod;
    @JoinColumn(name = "ST_ARCHIVO", referencedColumnName = "ID_ESTATUS_ARC", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsEstatusArc stArchivo;
    @JoinColumn(name = "ST_PROCESO", referencedColumnName = "ID_ESTATUS_PRO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsEstatusPro stProceso;
    @JoinColumn(name = "NU_RIF_INST", referencedColumnName = "NU_RIF_INST", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsInstAutorizada nuRifInst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nuRecepcion", fetch = FetchType.EAGER)
    private List<FtsErrorArcRec> ftsErrorArcRecList;

    public FtsArchivoRec() {
    }

    public FtsArchivoRec(String nuRecepcion) {
        this.nuRecepcion = nuRecepcion;
    }

    public FtsArchivoRec(String nuRecepcion, String imArchivo, String coUsuario, Date feRegistro, Date feActualizacion, String coPropMod) {
        this.nuRecepcion = nuRecepcion;
        this.imArchivo = imArchivo;
        this.coUsuario = coUsuario;
        this.feRegistro = feRegistro;
        this.feActualizacion = feActualizacion;
        this.coPropMod = coPropMod;
    }

    public String getNuRecepcion() {
        return nuRecepcion;
    }

    public void setNuRecepcion(String nuRecepcion) {
        this.nuRecepcion = nuRecepcion;
    }

    public String getImArchivo() {
        return imArchivo;
    }

    public void setImArchivo(String imArchivo) {
        this.imArchivo = imArchivo;
    }

    public String getCoUsuario() {
        return coUsuario;
    }

    public void setCoUsuario(String coUsuario) {
        this.coUsuario = coUsuario;
    }

    public Date getFeRegistro() {
        return feRegistro;
    }

    public void setFeRegistro(Date feRegistro) {
        this.feRegistro = feRegistro;
    }

    public Date getFeActualizacion() {
        return feActualizacion;
    }

    public void setFeActualizacion(Date feActualizacion) {
        this.feActualizacion = feActualizacion;
    }

    public String getCoPropMod() {
        return coPropMod;
    }

    public void setCoPropMod(String coPropMod) {
        this.coPropMod = coPropMod;
    }

    public FtsEstatusArc getStArchivo() {
        return stArchivo;
    }

    public void setStArchivo(FtsEstatusArc stArchivo) {
        this.stArchivo = stArchivo;
    }

    public FtsEstatusPro getStProceso() {
        return stProceso;
    }

    public void setStProceso(FtsEstatusPro stProceso) {
        this.stProceso = stProceso;
    }

    public FtsInstAutorizada getNuRifInst() {
        return nuRifInst;
    }

    public void setNuRifInst(FtsInstAutorizada nuRifInst) {
        this.nuRifInst = nuRifInst;
    }

    @XmlTransient
    public List<FtsErrorArcRec> getFtsErrorArcRecList() {
        return ftsErrorArcRecList;
    }

    public void setFtsErrorArcRecList(List<FtsErrorArcRec> ftsErrorArcRecList) {
        this.ftsErrorArcRecList = ftsErrorArcRecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nuRecepcion != null ? nuRecepcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsArchivoRec)) {
            return false;
        }
        FtsArchivoRec other = (FtsArchivoRec) object;
        if ((this.nuRecepcion == null && other.nuRecepcion != null) || (this.nuRecepcion != null && !this.nuRecepcion.equals(other.nuRecepcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsArchivoRec[ nuRecepcion=" + nuRecepcion + " ]";
    }
    
}
