/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author furibe
 */
@Entity
@Table(name = "FTS_INST_AUT_PRO_MOD", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsInstAutProMod.findAll", query = "SELECT f FROM FtsInstAutProMod f")
    , @NamedQuery(name = "FtsInstAutProMod.findByNuRifInst", query = "SELECT f FROM FtsInstAutProMod f WHERE f.ftsInstAutProModPK.nuRifInst = :nuRifInst")
    , @NamedQuery(name = "FtsInstAutProMod.findByIdPropMod", query = "SELECT f FROM FtsInstAutProMod f WHERE f.ftsInstAutProModPK.idPropMod = :idPropMod")
    , @NamedQuery(name = "FtsInstAutProMod.findAllCount", query = "SELECT COUNT(f) FROM FtsInstAutProMod f WHERE f.ftsInstAutProModPK.idPropMod = :idPropMod and f.ftsInstAutProModPK.nuRifInst = :nuRifInst")
    , @NamedQuery(name = "FtsInstAutProMod.findByInExtHorario", query = "SELECT f FROM FtsInstAutProMod f WHERE f.inExtHorario = :inExtHorario")
    , @NamedQuery(name = "FtsInstAutProMod.findByStActivo", query = "SELECT f FROM FtsInstAutProMod f WHERE f.stActivo = :stActivo")})
public class FtsInstAutProMod implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FtsInstAutProModPK ftsInstAutProModPK;
    @Basic(optional = false)
    @Column(name = "IN_EXT_HORARIO", nullable = false, length = 1)
    private String inExtHorario;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @JoinColumn(name = "NU_RIF_INST", referencedColumnName = "NU_RIF_INST", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsInstAutorizada ftsInstAutorizada;
    @JoinColumn(name = "ID_PROP_MOD", referencedColumnName = "ID_PROP_MOD", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsPropMod ftsPropMod;

    public FtsInstAutProMod() {
    }

    public FtsInstAutProMod(FtsInstAutProModPK ftsInstAutProModPK) {
        this.ftsInstAutProModPK = ftsInstAutProModPK;
    }

    public FtsInstAutProMod(FtsInstAutProModPK ftsInstAutProModPK, String inExtHorario, String stActivo) {
        this.ftsInstAutProModPK = ftsInstAutProModPK;
        this.inExtHorario = inExtHorario;
        this.stActivo = stActivo;
    }

    public FtsInstAutProMod(String nuRifInst, int idPropMod) {
        this.ftsInstAutProModPK = new FtsInstAutProModPK(nuRifInst, idPropMod);
    }

    public FtsInstAutProModPK getFtsInstAutProModPK() {
        return ftsInstAutProModPK;
    }

    public void setFtsInstAutProModPK(FtsInstAutProModPK ftsInstAutProModPK) {
        this.ftsInstAutProModPK = ftsInstAutProModPK;
    }

    public String getInExtHorario() {
        return inExtHorario;
    }

    public void setInExtHorario(String inExtHorario) {
        this.inExtHorario = inExtHorario;
    }

    public String getStActivo() {
        return stActivo;
    }

    public void setStActivo(String stActivo) {
        this.stActivo = stActivo;
    }

    public FtsInstAutorizada getFtsInstAutorizada() {
        return ftsInstAutorizada;
    }

    public void setFtsInstAutorizada(FtsInstAutorizada ftsInstAutorizada) {
        this.ftsInstAutorizada = ftsInstAutorizada;
    }

    public FtsPropMod getFtsPropMod() {
        return ftsPropMod;
    }

    public void setFtsPropMod(FtsPropMod ftsPropMod) {
        this.ftsPropMod = ftsPropMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ftsInstAutProModPK != null ? ftsInstAutProModPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsInstAutProMod)) {
            return false;
        }
        FtsInstAutProMod other = (FtsInstAutProMod) object;
        if ((this.ftsInstAutProModPK == null && other.ftsInstAutProModPK != null) || (this.ftsInstAutProModPK != null && !this.ftsInstAutProModPK.equals(other.ftsInstAutProModPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsInstAutProMod[ ftsInstAutProModPK=" + ftsInstAutProModPK + " ]";
    }
    
}
