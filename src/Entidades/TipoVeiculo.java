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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "tipo_veiculo")
@NamedQueries({
    @NamedQuery(name = "TipoVeiculo.findAll", query = "SELECT t FROM TipoVeiculo t")})
public class TipoVeiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_veiculo")
    private Integer idTipoVeiculo;
    @Column(name = "nome_tipo_veiculo")
    private String nomeTipoVeiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoVeiculoIdTipoVeiculo")
    private List<Veiculo> veiculoList;

    public TipoVeiculo() {
    }

    public TipoVeiculo(Integer idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }

    public Integer getIdTipoVeiculo() {
        return idTipoVeiculo;
    }

    public void setIdTipoVeiculo(Integer idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }

    public String getNomeTipoVeiculo() {
        return nomeTipoVeiculo;
    }

    public void setNomeTipoVeiculo(String nomeTipoVeiculo) {
        this.nomeTipoVeiculo = nomeTipoVeiculo;
    }

    public List<Veiculo> getVeiculoList() {
        return veiculoList;
    }

    public void setVeiculoList(List<Veiculo> veiculoList) {
        this.veiculoList = veiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoVeiculo != null ? idTipoVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVeiculo)) {
            return false;
        }
        TipoVeiculo other = (TipoVeiculo) object;
        if ((this.idTipoVeiculo == null && other.idTipoVeiculo != null) || (this.idTipoVeiculo != null && !this.idTipoVeiculo.equals(other.idTipoVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoVeiculo[ idTipoVeiculo=" + idTipoVeiculo + " ]";
    }
    
}
