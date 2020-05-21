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
@Table(name = "FTS_CONFIG_JMS", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsConfigJms.findAll", query = "SELECT f FROM FtsConfigJms f")
    , @NamedQuery(name = "FtsConfigJms.findByIdConfigJms", query = "SELECT f FROM FtsConfigJms f WHERE f.idConfigJms = :idConfigJms")
    , @NamedQuery(name = "FtsConfigJms.findByTxJndiJms", query = "SELECT f FROM FtsConfigJms f WHERE f.txJndiJms = :txJndiJms")
    , @NamedQuery(name = "FtsConfigJms.findByTxDescripcion", query = "SELECT f FROM FtsConfigJms f WHERE f.txDescripcion = :txDescripcion")
    , @NamedQuery(name = "FtsConfigJms.findByFeRegistro", query = "SELECT f FROM FtsConfigJms f WHERE f.feRegistro = :feRegistro")
    , @NamedQuery(name = "FtsConfigJms.findByFeActualizacion", query = "SELECT f FROM FtsConfigJms f WHERE f.feActualizacion = :feActualizacion")
    , @NamedQuery(name = "FtsConfigJms.findByStActivo", query = "SELECT f FROM FtsConfigJms f WHERE f.stActivo = :stActivo")})
public class FtsConfigJms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONFIG_JMS", nullable = false)
    private Integer idConfigJms;
    @Basic(optional = false)
    @Column(name = "TX_JNDI_JMS", nullable = false, length = 200)
    private String txJndiJms;
    @Basic(optional = false)
    @Column(name = "TX_DESCRIPCION", nullable = false, length = 200)
    private String txDescripcion;
    @Basic(optional = false)
    @Column(name = "FE_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;
    @Basic(optional = false)
    @Column(name = "FE_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feActualizacion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfigJms", fetch = FetchType.EAGER)
    private List<FtsArcPro> ftsArcProList;

    public FtsConfigJms() {
    }

    public FtsConfigJms(Integer idConfigJms) {
        this.idConfigJms = idConfigJms;
    }

    public FtsConfigJms(Integer idConfigJms, String txJndiJms, String txDescripcion, Date feRegistro, Date feActualizacion, String stActivo) {
        this.idConfigJms = idConfigJms;
        this.txJndiJms = txJndiJms;
        this.txDescripcion = txDescripcion;
        this.feRegistro = feRegistro;
        this.feActualizacion = feActualizacion;
        this.stActivo = stActivo;
    }

    public Integer getIdConfigJms() {
        return idConfigJms;
    }

    public void setIdConfigJms(Integer idConfigJms) {
        this.idConfigJms = idConfigJms;
    }

    public String getTxJndiJms() {
        return txJndiJms;
    }

    public void setTxJndiJms(String txJndiJms) {
        this.txJndiJms = txJndiJms;
    }

    public String getTxDescripcion() {
        return txDescripcion;
    }

    public void setTxDescripcion(String txDescripcion) {
        this.txDescripcion = txDescripcion;
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

    public String getStActivo() {
        return stActivo;
    }

    public void setStActivo(String stActivo) {
        this.stActivo = stActivo;
    }

    @XmlTransient
    public List<FtsArcPro> getFtsArcProList() {
        return ftsArcProList;
    }

    public void setFtsArcProList(List<FtsArcPro> ftsArcProList) {
        this.ftsArcProList = ftsArcProList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfigJms != null ? idConfigJms.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsConfigJms)) {
            return false;
        }
        FtsConfigJms other = (FtsConfigJms) object;
        if ((this.idConfigJms == null && other.idConfigJms != null) || (this.idConfigJms != null && !this.idConfigJms.equals(other.idConfigJms))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsConfigJms[ idConfigJms=" + idConfigJms + " ]";
    }
    
}
