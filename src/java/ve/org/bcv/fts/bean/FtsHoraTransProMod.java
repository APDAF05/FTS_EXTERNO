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
@Table(name = "FTS_HORA_TRANS_PRO_MOD", catalog = "", schema = "TNCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtsHoraTransProMod.findAll", query = "SELECT f FROM FtsHoraTransProMod f")
    , @NamedQuery(name = "FtsHoraTransProMod.findByIdHoraTransProMod", query = "SELECT f FROM FtsHoraTransProMod f WHERE f.idHoraTransProMod = :idHoraTransProMod")
    , @NamedQuery(name = "FtsHoraTransProMod.findByTxHoraDesde", query = "SELECT f FROM FtsHoraTransProMod f WHERE f.txHoraDesde = :txHoraDesde")
    , @NamedQuery(name = "FtsHoraTransProMod.findByTxHoraHasta", query = "SELECT f FROM FtsHoraTransProMod f WHERE f.txHoraHasta = :txHoraHasta")
    , @NamedQuery(name = "FtsHoraTransProMod.findByFeRegistro", query = "SELECT f FROM FtsHoraTransProMod f WHERE f.feRegistro = :feRegistro")
    , @NamedQuery(name = "FtsHoraTransProMod.findByFeActualizacion", query = "SELECT f FROM FtsHoraTransProMod f WHERE f.feActualizacion = :feActualizacion")
    , @NamedQuery(name = "FtsHoraTransProMod.findByCoUsuario", query = "SELECT f FROM FtsHoraTransProMod f WHERE f.coUsuario = :coUsuario")
    , @NamedQuery(name = "FtsHoraTransProMod.findByStActivo", query = "SELECT f FROM FtsHoraTransProMod f WHERE f.stActivo = :stActivo")})
public class FtsHoraTransProMod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_HORA_TRANS_PRO_MOD", nullable = false)
    private Integer idHoraTransProMod;
    @Basic(optional = false)
    @Column(name = "TX_HORA_DESDE", nullable = false, length = 8)
    private String txHoraDesde;
    @Basic(optional = false)
    @Column(name = "TX_HORA_HASTA", nullable = false, length = 8)
    private String txHoraHasta;
    @Basic(optional = false)
    @Column(name = "FE_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;
    @Basic(optional = false)
    @Column(name = "FE_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feActualizacion;
    @Basic(optional = false)
    @Column(name = "CO_USUARIO", nullable = false, length = 16)
    private String coUsuario;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO", nullable = false, length = 1)
    private String stActivo;
    @JoinColumn(name = "ID_PROP_MOD", referencedColumnName = "ID_PROP_MOD", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FtsPropMod idPropMod;

    public FtsHoraTransProMod() {
    }

    public FtsHoraTransProMod(Integer idHoraTransProMod) {
        this.idHoraTransProMod = idHoraTransProMod;
    }

    public FtsHoraTransProMod(Integer idHoraTransProMod, String txHoraDesde, String txHoraHasta, Date feRegistro, Date feActualizacion, String coUsuario, String stActivo) {
        this.idHoraTransProMod = idHoraTransProMod;
        this.txHoraDesde = txHoraDesde;
        this.txHoraHasta = txHoraHasta;
        this.feRegistro = feRegistro;
        this.feActualizacion = feActualizacion;
        this.coUsuario = coUsuario;
        this.stActivo = stActivo;
    }

    public Integer getIdHoraTransProMod() {
        return idHoraTransProMod;
    }

    public void setIdHoraTransProMod(Integer idHoraTransProMod) {
        this.idHoraTransProMod = idHoraTransProMod;
    }

    public String getTxHoraDesde() {
        return txHoraDesde;
    }

    public void setTxHoraDesde(String txHoraDesde) {
        this.txHoraDesde = txHoraDesde;
    }

    public String getTxHoraHasta() {
        return txHoraHasta;
    }

    public void setTxHoraHasta(String txHoraHasta) {
        this.txHoraHasta = txHoraHasta;
    }

    public Date getFeRegistro() {
        return feRegistro;
    }

    public void setFeRegistro(Date feRegistro) {
        this.feRegistro = feRegistro;
    }

    public Date getFeActualizacion() {
        return feActualizacion;
    }

    public void setFeActualizacion(Date feActualizacion) {
        this.feActualizacion = feActualizacion;
    }

    public String getCoUsuario() {
        return coUsuario;
    }

    public void setCoUsuario(String coUsuario) {
        this.coUsuario = coUsuario;
    }

    public String getStActivo() {
        return stActivo;
    }

    public void setStActivo(String stActivo) {
        this.stActivo = stActivo;
    }

    public FtsPropMod getIdPropMod() {
        return idPropMod;
    }

    public void setIdPropMod(FtsPropMod idPropMod) {
        this.idPropMod = idPropMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHoraTransProMod != null ? idHoraTransProMod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtsHoraTransProMod)) {
            return false;
        }
        FtsHoraTransProMod other = (FtsHoraTransProMod) object;
        if ((this.idHoraTransProMod == null && other.idHoraTransProMod != null) || (this.idHoraTransProMod != null && !this.idHoraTransProMod.equals(other.idHoraTransProMod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.org.bcv.fts.bean.FtsHoraTransProMod[ idHoraTransProMod=" + idHoraTransProMod + " ]";
    }
    
}
