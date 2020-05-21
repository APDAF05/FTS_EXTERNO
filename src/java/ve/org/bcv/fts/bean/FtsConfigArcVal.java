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
import javax.persistence.Lob;
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
@Table(name = "FTS_CONFIG_ARC_VAL", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsConfigArcVal.findAll", query = "SELECT f FROM FtsConfigArcVal f")
    , @NamedQuery(name = "FtsConfigArcVal.findByIdConfigArcVal", query = "SELECT f FROM FtsConfigArcVal f WHERE f.idConfigArcVal = :idConfigArcVal")
    , @NamedQuery(name = "FtsConfigArcVal.findByFeRegistro", query = "SELECT f FROM FtsConfigArcVal f WHERE f.feRegistro = :feRegistro")
    , @NamedQuery(name = "FtsConfigArcVal.findByFeActualizacion", query = "SELECT f FROM FtsConfigArcVal f WHERE f.feActualizacion = :feActualizacion")
    , @NamedQuery(name = "FtsConfigArcVal.findByTxDescripcion", query = "SELECT f FROM FtsConfigArcVal f WHERE f.txDescripcion = :txDescripcion")
    , @NamedQuery(name = "FtsConfigArcVal.findByStActivo", query = "SELECT f FROM FtsConfigArcVal f WHERE f.stActivo = :stActivo")})
public class FtsConfigArcVal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONFIG_ARC_VAL", nullable = false)
    private Integer idConfigArcVal;
    @Basic(optional = false)
    @Lob
    @Column(name = "IM_ARC_VAL", nullable = false)
    private String imArcVal;
    @Basic(optional = false)
    @Column(name = "FE_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;
    @Basic(optional = false)
    @Column(name = "FE_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feActualizacion;
    @Basic(optional = false)
    @Column(name = "TX_DESCRIPCION", nullable = false, length = 200)
    private String txDescripcion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfigArcVal", fetch = FetchType.EAGER)
    private List<FtsArcPro> ftsArcProList;

    public FtsConfigArcVal() {
    }

    public FtsConfigArcVal(Integer idConfigArcVal) {
        this.idConfigArcVal = idConfigArcVal;
    }

    public FtsConfigArcVal(Integer idConfigArcVal, String imArcVal, Date feRegistro, Date feActualizacion, String txDescripcion, String stActivo) {
        this.idConfigArcVal = idConfigArcVal;
        this.imArcVal = imArcVal;
        this.feRegistro = feRegistro;
        this.feActualizacion = feActualizacion;
        this.txDescripcion = txDescripcion;
        this.stActivo = stActivo;
    }

    public Integer getIdConfigArcVal() {
        return idConfigArcVal;
    }

    public void setIdConfigArcVal(Integer idConfigArcVal) {
        this.idConfigArcVal = idConfigArcVal;
    }

    public String getImArcVal() {
        return imArcVal;
    }

    public void setImArcVal(String imArcVal) {
        this.imArcVal = imArcVal;
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

    public String getTxDescripcion() {
        return txDescripcion;
    }

    public void setTxDescripcion(String txDescripcion) {
        this.txDescripcion = txDescripcion;
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
        hash += (idConfigArcVal != null ? idConfigArcVal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsConfigArcVal)) {
            return false;
        }
        FtsConfigArcVal other = (FtsConfigArcVal) object;
        if ((this.idConfigArcVal == null && other.idConfigArcVal != null) || (this.idConfigArcVal != null && !this.idConfigArcVal.equals(other.idConfigArcVal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsConfigArcVal[ idConfigArcVal=" + idConfigArcVal + " ]";
    }
    
}
