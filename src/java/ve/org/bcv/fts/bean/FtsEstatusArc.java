/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.bean;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author furibe
 */
@Entity
@Table(name = "FTS_ESTATUS_ARC", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsEstatusArc.findAll", query = "SELECT f FROM FtsEstatusArc f")
    , @NamedQuery(name = "FtsEstatusArc.findByIdEstatusArc", query = "SELECT f FROM FtsEstatusArc f WHERE f.idEstatusArc = :idEstatusArc")
    , @NamedQuery(name = "FtsEstatusArc.findByTxEstatusArc", query = "SELECT f FROM FtsEstatusArc f WHERE f.txEstatusArc = :txEstatusArc")
    , @NamedQuery(name = "FtsEstatusArc.findByStActivo", query = "SELECT f FROM FtsEstatusArc f WHERE f.stActivo = :stActivo")})
public class FtsEstatusArc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ESTATUS_ARC", nullable = false)
    private Integer idEstatusArc;
    @Basic(optional = false)
    @Column(name = "TX_ESTATUS_ARC", nullable = false, length = 200)
    private String txEstatusArc;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stArchivo", fetch = FetchType.EAGER)
    private List<FtsArchivoRec> ftsArchivoRecList;

    public FtsEstatusArc() {
    }

    public FtsEstatusArc(Integer idEstatusArc) {
        this.idEstatusArc = idEstatusArc;
    }

    public FtsEstatusArc(Integer idEstatusArc, String txEstatusArc, String stActivo) {
        this.idEstatusArc = idEstatusArc;
        this.txEstatusArc = txEstatusArc;
        this.stActivo = stActivo;
    }

    public Integer getIdEstatusArc() {
        return idEstatusArc;
    }

    public void setIdEstatusArc(Integer idEstatusArc) {
        this.idEstatusArc = idEstatusArc;
    }

    public String getTxEstatusArc() {
        return txEstatusArc;
    }

    public void setTxEstatusArc(String txEstatusArc) {
        this.txEstatusArc = txEstatusArc;
    }

    public String getStActivo() {
        return stActivo;
    }

    public void setStActivo(String stActivo) {
        this.stActivo = stActivo;
    }

    @XmlTransient
    public List<FtsArchivoRec> getFtsArchivoRecList() {
        return ftsArchivoRecList;
    }

    public void setFtsArchivoRecList(List<FtsArchivoRec> ftsArchivoRecList) {
        this.ftsArchivoRecList = ftsArchivoRecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatusArc != null ? idEstatusArc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsEstatusArc)) {
            return false;
        }
        FtsEstatusArc other = (FtsEstatusArc) object;
        if ((this.idEstatusArc == null && other.idEstatusArc != null) || (this.idEstatusArc != null && !this.idEstatusArc.equals(other.idEstatusArc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsEstatusArc[ idEstatusArc=" + idEstatusArc + " ]";
    }
    
}
