/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruben
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")
    , @NamedQuery(name = "Cuenta.findByIdCuenta", query = "SELECT c FROM Cuenta c WHERE c.idCuenta = :idCuenta")
    , @NamedQuery(name = "Cuenta.findByCuentaCorriente", query = "SELECT c FROM Cuenta c WHERE c.cuentaCorriente = :cuentaCorriente")
    , @NamedQuery(name = "Cuenta.findBySaldo", query = "SELECT c FROM Cuenta c WHERE c.saldo = :saldo")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 23)
    @Column(name = "cuenta_corriente")
    private String cuentaCorriente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private BigDecimal saldo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuenta")
    private List<Movimiento> movimientoList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Cuenta() {
    }

    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(Integer idCuenta, String cuentaCorriente, BigDecimal saldo) {
        this.idCuenta = idCuenta;
        this.cuentaCorriente = cuentaCorriente;
        this.saldo = saldo;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(String cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bancoonline.entity.Cuenta[ idCuenta=" + idCuenta + " ]";
    }
    
}
