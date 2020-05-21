/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author furibe
 */
@Embeddable
public class FtsInstAutProModPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NU_RIF_INST", nullable = false, length = 15)
    private String nuRifInst;
    @Basic(optional = false)
    @Column(name = "ID_PROP_MOD", nullable = false)
    private int idPropMod;

    public FtsInstAutProModPK() {
    }

    public FtsInstAutProModPK(String nuRifInst, int idPropMod) {
        this.nuRifInst = nuRifInst;
        this.idPropMod = idPropMod;
    }

    public String getNuRifInst() {
        return nuRifInst;
    }

    public void setNuRifInst(String nuRifInst) {
        this.nuRifInst = nuRifInst;
    }

    public int getIdPropMod() {
        return idPropMod;
    }

    public void setIdPropMod(int idPropMod) {
        this.idPropMod = idPropMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nuRifInst != null ? nuRifInst.hashCode() : 0);
        hash += (int) idPropMod;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsInstAutProModPK)) {
            return false;
        }
        FtsInstAutProModPK other = (FtsInstAutProModPK) object;
        if ((this.nuRifInst == null && other.nuRifInst != null) || (this.nuRifInst != null && !this.nuRifInst.equals(other.nuRifInst))) {
            return false;
        }
        if (this.idPropMod != other.idPropMod) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsInstAutProModPK[ nuRifInst=" + nuRifInst + ", idPropMod=" + idPropMod + " ]";
    }
    
}
