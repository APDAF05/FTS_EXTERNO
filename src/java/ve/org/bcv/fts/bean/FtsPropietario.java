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
@Table(name = "FTS_PROPIETARIO", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsPropietario.findAll", query = "SELECT f FROM FtsPropietario f")
    , @NamedQuery(name = "FtsPropietario.findByIdPropietario", query = "SELECT f FROM FtsPropietario f WHERE f.idPropietario = :idPropietario")
    , @NamedQuery(name = "FtsPropietario.findByTxPropietario", query = "SELECT f FROM FtsPropietario f WHERE f.txPropietario = :txPropietario")
    , @NamedQuery(name = "FtsPropietario.findByTxDescripcion", query = "SELECT f FROM FtsPropietario f WHERE f.txDescripcion = :txDescripcion")
    , @NamedQuery(name = "FtsPropietario.findByStActivo", query = "SELECT f FROM FtsPropietario f WHERE f.stActivo = :stActivo")})
public class FtsPropietario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PROPIETARIO", nullable = false)
    private Integer idPropietario;
    @Basic(optional = false)
    @Column(name = "TX_PROPIETARIO", nullable = false, length = 200)
    private String txPropietario;
    @Basic(optional = false)
    @Column(name = "TX_DESCRIPCION", nullable = false, length = 200)
    private String txDescripcion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPropietario", fetch = FetchType.EAGER)
    private List<FtsPropMod> ftsPropModList;

    public FtsPropietario() {
    }

    public FtsPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }

    public FtsPropietario(Integer idPropietario, String txPropietario, String txDescripcion, String stActivo) {
        this.idPropietario = idPropietario;
        this.txPropietario = txPropietario;
        this.txDescripcion = txDescripcion;
        this.stActivo = stActivo;
    }

    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getTxPropietario() {
        return txPropietario;
    }

    public void setTxPropietario(String txPropietario) {
        this.txPropietario = txPropietario;
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
    public List<FtsPropMod> getFtsPropModList() {
        return ftsPropModList;
    }

    public void setFtsPropModList(List<FtsPropMod> ftsPropModList) {
        this.ftsPropModList = ftsPropModList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPropietario != null ? idPropietario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsPropietario)) {
            return false;
        }
        FtsPropietario other = (FtsPropietario) object;
        if ((this.idPropietario == null && other.idPropietario != null) || (this.idPropietario != null && !this.idPropietario.equals(other.idPropietario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsPropietario[ idPropietario=" + idPropietario + " ]";
    }
    
}
