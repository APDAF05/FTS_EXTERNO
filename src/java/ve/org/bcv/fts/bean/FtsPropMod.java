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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author furibe
 */
@Entity
@Table(name = "FTS_PROP_MOD", catalog = "", schema = "TNCC", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CO_PROP_MOD"})
    , @UniqueConstraint(columnNames = {"CO_PROP_MOD", "ID_MODULO", "ID_PROPIETARIO", "ID_ARC_PRO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsPropMod.findAll", query = "SELECT f FROM FtsPropMod f")
    , @NamedQuery(name = "FtsPropMod.findByIdPropMod", query = "SELECT f FROM FtsPropMod f WHERE f.idPropMod = :idPropMod")
    , @NamedQuery(name = "FtsPropMod.findByCoPropMod", query = "SELECT f FROM FtsPropMod f WHERE f.coPropMod = :coPropMod")
    , @NamedQuery(name = "FtsPropMod.findByTxDescripcion", query = "SELECT f FROM FtsPropMod f WHERE f.txDescripcion = :txDescripcion")
    , @NamedQuery(name = "FtsPropMod.findByStActivo", query = "SELECT f FROM FtsPropMod f WHERE f.stActivo = :stActivo")})
public class FtsPropMod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PROP_MOD", nullable = false)
    private Integer idPropMod;
    @Basic(optional = false)
    @Column(name = "CO_PROP_MOD", nullable = false, length = 5)
    private String coPropMod;
    @Basic(optional = false)
    @Column(name = "TX_DESCRIPCION", nullable = false, length = 200)
    private String txDescripcion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPropMod", fetch = FetchType.EAGER)
    private List<FtsHoraTransProMod> ftsHoraTransProModList;
    @JoinColumn(name = "ID_ARC_PRO", referencedColumnName = "ID_ARC_PRO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsArcPro idArcPro;
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID_MODULO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsModulo idModulo;
    @JoinColumn(name = "ID_PROPIETARIO", referencedColumnName = "ID_PROPIETARIO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsPropietario idPropietario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ftsPropMod", fetch = FetchType.EAGER)
    private List<FtsInstAutProMod> ftsInstAutProModList;

    public FtsPropMod() {
    }

    public FtsPropMod(Integer idPropMod) {
        this.idPropMod = idPropMod;
    }

    public FtsPropMod(Integer idPropMod, String coPropMod, String txDescripcion, String stActivo) {
        this.idPropMod = idPropMod;
        this.coPropMod = coPropMod;
        this.txDescripcion = txDescripcion;
        this.stActivo = stActivo;
    }

    public Integer getIdPropMod() {
        return idPropMod;
    }

    public void setIdPropMod(Integer idPropMod) {
        this.idPropMod = idPropMod;
    }

    public String getCoPropMod() {
        return coPropMod;
    }

    public void setCoPropMod(String coPropMod) {
        this.coPropMod = coPropMod;
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
    public List<FtsHoraTransProMod> getFtsHoraTransProModList() {
        return ftsHoraTransProModList;
    }

    public void setFtsHoraTransProModList(List<FtsHoraTransProMod> ftsHoraTransProModList) {
        this.ftsHoraTransProModList = ftsHoraTransProModList;
    }

    public FtsArcPro getIdArcPro() {
        return idArcPro;
    }

    public void setIdArcPro(FtsArcPro idArcPro) {
        this.idArcPro = idArcPro;
    }

    public FtsModulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(FtsModulo idModulo) {
        this.idModulo = idModulo;
    }

    public FtsPropietario getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(FtsPropietario idPropietario) {
        this.idPropietario = idPropietario;
    }

    @XmlTransient
    public List<FtsInstAutProMod> getFtsInstAutProModList() {
        return ftsInstAutProModList;
    }

    public void setFtsInstAutProModList(List<FtsInstAutProMod> ftsInstAutProModList) {
        this.ftsInstAutProModList = ftsInstAutProModList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPropMod != null ? idPropMod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsPropMod)) {
            return false;
        }
        FtsPropMod other = (FtsPropMod) object;
        if ((this.idPropMod == null && other.idPropMod != null) || (this.idPropMod != null && !this.idPropMod.equals(other.idPropMod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsPropMod[ idPropMod=" + idPropMod + " ]";
    }

}
