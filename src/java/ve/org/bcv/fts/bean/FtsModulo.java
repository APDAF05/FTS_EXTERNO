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
@Table(name = "FTS_MODULO", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsModulo.findAll", query = "SELECT f FROM FtsModulo f")
    , @NamedQuery(name = "FtsModulo.findByIdModulo", query = "SELECT f FROM FtsModulo f WHERE f.idModulo = :idModulo")
    , @NamedQuery(name = "FtsModulo.findByTxModulo", query = "SELECT f FROM FtsModulo f WHERE f.txModulo = :txModulo")
    , @NamedQuery(name = "FtsModulo.findByTxDescripcion", query = "SELECT f FROM FtsModulo f WHERE f.txDescripcion = :txDescripcion")
    , @NamedQuery(name = "FtsModulo.findByStActivo", query = "SELECT f FROM FtsModulo f WHERE f.stActivo = :stActivo")})
public class FtsModulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MODULO", nullable = false)
    private Integer idModulo;
    @Basic(optional = false)
    @Column(name = "TX_MODULO", nullable = false, length = 200)
    private String txModulo;
    @Basic(optional = false)
    @Column(name = "TX_DESCRIPCION", nullable = false, length = 200)
    private String txDescripcion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModulo", fetch = FetchType.EAGER)
    private List<FtsPropMod> ftsPropModList;

    public FtsModulo() {
    }

    public FtsModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public FtsModulo(Integer idModulo, String txModulo, String txDescripcion, String stActivo) {
        this.idModulo = idModulo;
        this.txModulo = txModulo;
        this.txDescripcion = txDescripcion;
        this.stActivo = stActivo;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getTxModulo() {
        return txModulo;
    }

    public void setTxModulo(String txModulo) {
        this.txModulo = txModulo;
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
        hash += (idModulo != null ? idModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsModulo)) {
            return false;
        }
        FtsModulo other = (FtsModulo) object;
        if ((this.idModulo == null && other.idModulo != null) || (this.idModulo != null && !this.idModulo.equals(other.idModulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsModulo[ idModulo=" + idModulo + " ]";
    }
    
}
