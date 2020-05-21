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
@Table(name = "FTS_ESTATUS_PRO", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsEstatusPro.findAll", query = "SELECT f FROM FtsEstatusPro f")
    , @NamedQuery(name = "FtsEstatusPro.findByIdEstatusPro", query = "SELECT f FROM FtsEstatusPro f WHERE f.idEstatusPro = :idEstatusPro")
    , @NamedQuery(name = "FtsEstatusPro.findByTxEstatusPro", query = "SELECT f FROM FtsEstatusPro f WHERE f.txEstatusPro = :txEstatusPro")
    , @NamedQuery(name = "FtsEstatusPro.findByStActivo", query = "SELECT f FROM FtsEstatusPro f WHERE f.stActivo = :stActivo")})
public class FtsEstatusPro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ESTATUS_PRO", nullable = false)
    private Integer idEstatusPro;
    @Basic(optional = false)
    @Column(name = "TX_ESTATUS_PRO", nullable = false, length = 200)
    private String txEstatusPro;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stProceso", fetch = FetchType.EAGER)
    private List<FtsArchivoRec> ftsArchivoRecList;

    public FtsEstatusPro() {
    }

    public FtsEstatusPro(Integer idEstatusPro) {
        this.idEstatusPro = idEstatusPro;
    }

    public FtsEstatusPro(Integer idEstatusPro, String txEstatusPro, String stActivo) {
        this.idEstatusPro = idEstatusPro;
        this.txEstatusPro = txEstatusPro;
        this.stActivo = stActivo;
    }

    public Integer getIdEstatusPro() {
        return idEstatusPro;
    }

    public void setIdEstatusPro(Integer idEstatusPro) {
        this.idEstatusPro = idEstatusPro;
    }

    public String getTxEstatusPro() {
        return txEstatusPro;
    }

    public void setTxEstatusPro(String txEstatusPro) {
        this.txEstatusPro = txEstatusPro;
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
        hash += (idEstatusPro != null ? idEstatusPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsEstatusPro)) {
            return false;
        }
        FtsEstatusPro other = (FtsEstatusPro) object;
        if ((this.idEstatusPro == null && other.idEstatusPro != null) || (this.idEstatusPro != null && !this.idEstatusPro.equals(other.idEstatusPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsEstatusPro[ idEstatusPro=" + idEstatusPro + " ]";
    }
    
}
