/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruben
 */
@Entity
@Table(name = "transferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transferencia.findAll", query = "SELECT t FROM Transferencia t")
    , @NamedQuery(name = "Transferencia.findByIdTransferencia", query = "SELECT t FROM Transferencia t WHERE t.idTransferencia = :idTransferencia")
    , @NamedQuery(name = "Transferencia.findByCantidad", query = "SELECT t FROM Transferencia t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "Transferencia.findByFecha", query = "SELECT t FROM Transferencia t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "Transferencia.findByConcepto", query = "SELECT t FROM Transferencia t WHERE t.concepto = :concepto")})
public class Transferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transferencia")
    private Integer idTransferencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "concepto")
    private String concepto;
    @JoinColumn(name = "destino", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario destino;
    @JoinColumn(name = "origen", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario origen;

    public Transferencia() {
    }

    public Transferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Transferencia(Integer idTransferencia, BigDecimal cantidad, Date fecha, String concepto) {
        this.idTransferencia = idTransferencia;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.concepto = concepto;
    }

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }

    public Usuario getOrigen() {
        return origen;
    }

    public void setOrigen(Usuario origen) {
        this.origen = origen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransferencia != null ? idTransferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) object;
        if ((this.idTransferencia == null && other.idTransferencia != null) || (this.idTransferencia != null && !this.idTransferencia.equals(other.idTransferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bancoonline.entity.Transferencia[ idTransferencia=" + idTransferencia + " ]";
    }
    
}
