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
@Table(name = "FTS_INST_AUTORIZADA", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsInstAutorizada.findAll", query = "SELECT f FROM FtsInstAutorizada f")
    , @NamedQuery(name = "FtsInstAutorizada.findByNuRifInst", query = "SELECT f FROM FtsInstAutorizada f WHERE f.nuRifInst = :nuRifInst")
    , @NamedQuery(name = "FtsInstAutorizada.findByNbInstitucion", query = "SELECT f FROM FtsInstAutorizada f WHERE f.nbInstitucion = :nbInstitucion")
    , @NamedQuery(name = "FtsInstAutorizada.findByCoTipInstitucion", query = "SELECT f FROM FtsInstAutorizada f WHERE f.coTipInstitucion = :coTipInstitucion")
    , @NamedQuery(name = "FtsInstAutorizada.findByInTipInstitucion", query = "SELECT f FROM FtsInstAutorizada f WHERE f.inTipInstitucion = :inTipInstitucion")
    , @NamedQuery(name = "FtsInstAutorizada.findByCoInstitucion", query = "SELECT f FROM FtsInstAutorizada f WHERE f.coInstitucion = :coInstitucion")
    , @NamedQuery(name = "FtsInstAutorizada.findByStActivo", query = "SELECT f FROM FtsInstAutorizada f WHERE f.stActivo = :stActivo")})
public class FtsInstAutorizada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NU_RIF_INST", nullable = false, length = 15)
    private String nuRifInst;
    @Basic(optional = false)
    @Column(name = "NB_INSTITUCION", nullable = false, length = 200)
    private String nbInstitucion;
    @Basic(optional = false)
    @Column(name = "CO_TIP_INSTITUCION", nullable = false, length = 2)
    private String coTipInstitucion;
    @Basic(optional = false)
    @Column(name = "IN_TIP_INSTITUCION", nullable = false, length = 2)
    private String inTipInstitucion;
    @Column(name = "CO_INSTITUCION")
    private Integer coInstitucion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nuRifInst", fetch = FetchType.EAGER)
    private List<FtsArchivoRec> ftsArchivoRecList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ftsInstAutorizada", fetch = FetchType.EAGER)
    private List<FtsInstAutProMod> ftsInstAutProModList;

    public FtsInstAutorizada() {
    }

    public FtsInstAutorizada(String nuRifInst) {
        this.nuRifInst = nuRifInst;
    }

    public FtsInstAutorizada(String nuRifInst, String nbInstitucion, String coTipInstitucion, String inTipInstitucion, String stActivo) {
        this.nuRifInst = nuRifInst;
        this.nbInstitucion = nbInstitucion;
        this.coTipInstitucion = coTipInstitucion;
        this.inTipInstitucion = inTipInstitucion;
        this.stActivo = stActivo;
    }

    public String getNuRifInst() {
        return nuRifInst;
    }

    public void setNuRifInst(String nuRifInst) {
        this.nuRifInst = nuRifInst;
    }

    public String getNbInstitucion() {
        return nbInstitucion;
    }

    public void setNbInstitucion(String nbInstitucion) {
        this.nbInstitucion = nbInstitucion;
    }

    public String getCoTipInstitucion() {
        return coTipInstitucion;
    }

    public void setCoTipInstitucion(String coTipInstitucion) {
        this.coTipInstitucion = coTipInstitucion;
    }

    public String getInTipInstitucion() {
        return inTipInstitucion;
    }

    public void setInTipInstitucion(String inTipInstitucion) {
        this.inTipInstitucion = inTipInstitucion;
    }

    public Integer getCoInstitucion() {
        return coInstitucion;
    }

    public void setCoInstitucion(Integer coInstitucion) {
        this.coInstitucion = coInstitucion;
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
        hash += (nuRifInst != null ? nuRifInst.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsInstAutorizada)) {
            return false;
        }
        FtsInstAutorizada other = (FtsInstAutorizada) object;
        if ((this.nuRifInst == null && other.nuRifInst != null) || (this.nuRifInst != null && !this.nuRifInst.equals(other.nuRifInst))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsInstAutorizada[ nuRifInst=" + nuRifInst + " ]";
    }
    
}
