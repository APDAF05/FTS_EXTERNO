/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author furibe
 */
@Entity
@Table(name = "FTS_ERROR_ARC_REC", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsErrorArcRec.findAll", query = "SELECT f FROM FtsErrorArcRec f")
    , @NamedQuery(name = "FtsErrorArcRec.findByIdErrArcRec", query = "SELECT f FROM FtsErrorArcRec f WHERE f.idErrArcRec = :idErrArcRec")
    , @NamedQuery(name = "FtsErrorArcRec.findByFeRegistro", query = "SELECT f FROM FtsErrorArcRec f WHERE f.feRegistro = :feRegistro")})
public class FtsErrorArcRec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ERR_ARC_REC", nullable = false, length = 20)
    private String idErrArcRec;
    @Basic(optional = false)
    @Lob
    @Column(name = "IM_ERR_ARC_REC", nullable = false)
    private String imErrArcRec;
    @Basic(optional = false)
    @Column(name = "FE_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;
    @JoinColumn(name = "NU_RECEPCION", referencedColumnName = "NU_RECEPCION", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsArchivoRec nuRecepcion;

    public FtsErrorArcRec() {
    }

    public FtsErrorArcRec(String idErrArcRec) {
        this.idErrArcRec = idErrArcRec;
    }

    public FtsErrorArcRec(String idErrArcRec, String imErrArcRec, Date feRegistro) {
        this.idErrArcRec = idErrArcRec;
        this.imErrArcRec = imErrArcRec;
        this.feRegistro = feRegistro;
    }

    public String getIdErrArcRec() {
        return idErrArcRec;
    }

    public void setIdErrArcRec(String idErrArcRec) {
        this.idErrArcRec = idErrArcRec;
    }

    public String getImErrArcRec() {
        return imErrArcRec;
    }

    public void setImErrArcRec(String imErrArcRec) {
        this.imErrArcRec = imErrArcRec;
    }

    public Date getFeRegistro() {
        return feRegistro;
    }

    public void setFeRegistro(Date feRegistro) {
        this.feRegistro = feRegistro;
    }

    public FtsArchivoRec getNuRecepcion() {
        return nuRecepcion;
    }

    public void setNuRecepcion(FtsArchivoRec nuRecepcion) {
        this.nuRecepcion = nuRecepcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idErrArcRec != null ? idErrArcRec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsErrorArcRec)) {
            return false;
        }
        FtsErrorArcRec other = (FtsErrorArcRec) object;
        if ((this.idErrArcRec == null && other.idErrArcRec != null) || (this.idErrArcRec != null && !this.idErrArcRec.equals(other.idErrArcRec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsErrorArcRec[ idErrArcRec=" + idErrArcRec + " ]";
    }
    
}
