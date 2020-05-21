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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FTS_ARC_PRO", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsArcPro.findAll", query = "SELECT f FROM FtsArcPro f")
    , @NamedQuery(name = "FtsArcPro.findByIdArcPro", query = "SELECT f FROM FtsArcPro f WHERE f.idArcPro = :idArcPro")
    , @NamedQuery(name = "FtsArcPro.findByTxDescripcion", query = "SELECT f FROM FtsArcPro f WHERE f.txDescripcion = :txDescripcion")
    , @NamedQuery(name = "FtsArcPro.findByStActivo", query = "SELECT f FROM FtsArcPro f WHERE f.stActivo = :stActivo")})
public class FtsArcPro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ARC_PRO", nullable = false)
    private Integer idArcPro;
    @Basic(optional = false)
    @Column(name = "TX_DESCRIPCION", nullable = false, length = 200)
    private String txDescripcion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @JoinColumn(name = "ID_CONFIG_ARC_VAL", referencedColumnName = "ID_CONFIG_ARC_VAL", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsConfigArcVal idConfigArcVal;
    @JoinColumn(name = "ID_CONFIG_JMS", referencedColumnName = "ID_CONFIG_JMS", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsConfigJms idConfigJms;
    @JoinColumn(name = "ID_TIPO_ARCHIVO", referencedColumnName = "ID_TIPO_ARCHIVO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsTipoArchivo idTipoArchivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArcPro", fetch = FetchType.EAGER)
    private List<FtsPropMod> ftsPropModList;

    public FtsArcPro() {
    }

    public FtsArcPro(Integer idArcPro) {
        this.idArcPro = idArcPro;
    }

    public FtsArcPro(Integer idArcPro, String txDescripcion, String stActivo) {
        this.idArcPro = idArcPro;
        this.txDescripcion = txDescripcion;
        this.stActivo = stActivo;
    }

    public Integer getIdArcPro() {
        return idArcPro;
    }

    public void setIdArcPro(Integer idArcPro) {
        this.idArcPro = idArcPro;
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

    public FtsConfigArcVal getIdConfigArcVal() {
        return idConfigArcVal;
    }

    public void setIdConfigArcVal(FtsConfigArcVal idConfigArcVal) {
        this.idConfigArcVal = idConfigArcVal;
    }

    public FtsConfigJms getIdConfigJms() {
        return idConfigJms;
    }

    public void setIdConfigJms(FtsConfigJms idConfigJms) {
        this.idConfigJms = idConfigJms;
    }

    public FtsTipoArchivo getIdTipoArchivo() {
        return idTipoArchivo;
    }

    public void setIdTipoArchivo(FtsTipoArchivo idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
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
        hash += (idArcPro != null ? idArcPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsArcPro)) {
            return false;
        }
        FtsArcPro other = (FtsArcPro) object;
        if ((this.idArcPro == null && other.idArcPro != null) || (this.idArcPro != null && !this.idArcPro.equals(other.idArcPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsArcPro[ idArcPro=" + idArcPro + " ]";
    }
    
}
