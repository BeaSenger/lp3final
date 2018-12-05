/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "veiculo")
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v")})
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_veiculo")
    private Integer codigoVeiculo;
    @Column(name = "nome_veiculo")
    private String nomeVeiculo;
    @JoinColumn(name = "tipo_veiculo_id_tipo_veiculo", referencedColumnName = "id_tipo_veiculo")
    @ManyToOne(optional = false)
    private TipoVeiculo tipoVeiculoIdTipoVeiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veiculoCodigoVeiculo")
    private List<AulaPratica> aulaPraticaList;

    public Veiculo() {
    }

    public Veiculo(Integer codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public Integer getCodigoVeiculo() {
        return codigoVeiculo;
    }

    public void setCodigoVeiculo(Integer codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    public void setNomeVeiculo(String nomeVeiculo) {
        this.nomeVeiculo = nomeVeiculo;
    }

    public TipoVeiculo getTipoVeiculoIdTipoVeiculo() {
        return tipoVeiculoIdTipoVeiculo;
    }

    public void setTipoVeiculoIdTipoVeiculo(TipoVeiculo tipoVeiculoIdTipoVeiculo) {
        this.tipoVeiculoIdTipoVeiculo = tipoVeiculoIdTipoVeiculo;
    }

    public List<AulaPratica> getAulaPraticaList() {
        return aulaPraticaList;
    }

    public void setAulaPraticaList(List<AulaPratica> aulaPraticaList) {
        this.aulaPraticaList = aulaPraticaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoVeiculo != null ? codigoVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.codigoVeiculo == null && other.codigoVeiculo != null) || (this.codigoVeiculo != null && !this.codigoVeiculo.equals(other.codigoVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Veiculo[ codigoVeiculo=" + codigoVeiculo + " ]";
    }
    
}
