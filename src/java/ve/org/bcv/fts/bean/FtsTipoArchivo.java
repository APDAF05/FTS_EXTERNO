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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author furibe
 */
@Entity
@Table(name = "FTS_TIPO_ARCHIVO", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsTipoArchivo.findAll", query = "SELECT f FROM FtsTipoArchivo f")
    , @NamedQuery(name = "FtsTipoArchivo.findByIdTipoArchivo", query = "SELECT f FROM FtsTipoArchivo f WHERE f.idTipoArchivo = :idTipoArchivo")
    , @NamedQuery(name = "FtsTipoArchivo.findByTxTipoArchivo", query = "SELECT f FROM FtsTipoArchivo f WHERE f.txTipoArchivo = :txTipoArchivo")
    , @NamedQuery(name = "FtsTipoArchivo.findByFeCreacion", query = "SELECT f FROM FtsTipoArchivo f WHERE f.feCreacion = :feCreacion")
    , @NamedQuery(name = "FtsTipoArchivo.findByStActivo", query = "SELECT f FROM FtsTipoArchivo f WHERE f.stActivo = :stActivo")})
public class FtsTipoArchivo implements Serializable {

    @Size(max = 15)
    @Column(name = "CLASE_VALIDATE", length = 15)
    private String claseValidate;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_ARCHIVO", nullable = false)
    private Integer idTipoArchivo;
    @Basic(optional = false)
    @Column(name = "TX_TIPO_ARCHIVO", nullable = false, length = 5)
    private String txTipoArchivo;
    @Basic(optional = false)
    @Column(name = "FE_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feCreacion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoArchivo", fetch = FetchType.EAGER)
    private List<FtsArcPro> ftsArcProList;

    public FtsTipoArchivo() {
    }

    public FtsTipoArchivo(Integer idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
    }

    public FtsTipoArchivo(Integer idTipoArchivo, String txTipoArchivo, Date feCreacion, String stActivo) {
        this.idTipoArchivo = idTipoArchivo;
        this.txTipoArchivo = txTipoArchivo;
        this.feCreacion = feCreacion;
        this.stActivo = stActivo;
    }

    public Integer getIdTipoArchivo() {
        return idTipoArchivo;
    }

    public void setIdTipoArchivo(Integer idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
    }

    public String getTxTipoArchivo() {
        return txTipoArchivo;
    }

    public void setTxTipoArchivo(String txTipoArchivo) {
        this.txTipoArchivo = txTipoArchivo;
    }

    public Date getFeCreacion() {
        return feCreacion;
    }

    public void setFeCreacion(Date feCreacion) {
        this.feCreacion = feCreacion;
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
        hash += (idTipoArchivo != null ? idTipoArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsTipoArchivo)) {
            return false;
        }
        FtsTipoArchivo other = (FtsTipoArchivo) object;
        if ((this.idTipoArchivo == null && other.idTipoArchivo != null) || (this.idTipoArchivo != null && !this.idTipoArchivo.equals(other.idTipoArchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsTipoArchivo[ idTipoArchivo=" + idTipoArchivo + " ]";
    }

    public String getClaseValidate() {
        return claseValidate;
    }

    public void setClaseValidate(String claseValidate) {
        this.claseValidate = claseValidate;
    }
    
}
