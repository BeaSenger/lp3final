/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "aluno")
@NamedQueries({
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")})
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf_aluno")
    private String cpfAluno;
    @Column(name = "nome_aluno")
    private String nomeAluno;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "rg_aluno")
    private String rgAluno;
    @Column(name = "rg_emissor_aluno")
    private String rgEmissorAluno;
    @Column(name = "endereco_aluno")
    private String enderecoAluno;
    @Column(name = "cidade_aluno")
    private String cidadeAluno;
    @Column(name = "bairro_aluno")
    private String bairroAluno;
    @Column(name = "cep_tipo_carteira")
    private String cepTipoCarteira;
    @Column(name = "telefone_aluno")
    private String telefoneAluno;
    @Column(name = "celular_aluno")
    private String celularAluno;
    @JoinTable(name = "turma_teorica_has_aluno", joinColumns = {
        @JoinColumn(name = "aluno_cpf_aluno", referencedColumnName = "cpf_aluno")}, inverseJoinColumns = {
        @JoinColumn(name = "turma_teorica_codigo_turma", referencedColumnName = "codigo_turma")})
    @ManyToMany
    private List<TurmaTeorica> turmaTeoricaList;
    @JoinColumn(name = "tipo_carteira_id_tipo_carteira", referencedColumnName = "id_tipo_carteira")
    @ManyToOne(optional = false)
    private TipoCarteira tipoCarteiraIdTipoCarteira;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno")
    private List<AulaPratica> aulaPraticaList;

    public Aluno() {
    }

    public Aluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRgAluno() {
        return rgAluno;
    }

    public void setRgAluno(String rgAluno) {
        this.rgAluno = rgAluno;
    }

    public String getRgEmissorAluno() {
        return rgEmissorAluno;
    }

    public void setRgEmissorAluno(String rgEmissorAluno) {
        this.rgEmissorAluno = rgEmissorAluno;
    }

    public String getEnderecoAluno() {
        return enderecoAluno;
    }

    public void setEnderecoAluno(String enderecoAluno) {
        this.enderecoAluno = enderecoAluno;
    }

    public String getCidadeAluno() {
        return cidadeAluno;
    }

    public void setCidadeAluno(String cidadeAluno) {
        this.cidadeAluno = cidadeAluno;
    }

    public String getBairroAluno() {
        return bairroAluno;
    }

    public void setBairroAluno(String bairroAluno) {
        this.bairroAluno = bairroAluno;
    }

    public String getCepTipoCarteira() {
        return cepTipoCarteira;
    }

    public void setCepTipoCarteira(String cepTipoCarteira) {
        this.cepTipoCarteira = cepTipoCarteira;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }

    public void setTelefoneAluno(String telefoneAluno) {
        this.telefoneAluno = telefoneAluno;
    }

    public String getCelularAluno() {
        return celularAluno;
    }

    public void setCelularAluno(String celularAluno) {
        this.celularAluno = celularAluno;
    }

    public List<TurmaTeorica> getTurmaTeoricaList() {
        return turmaTeoricaList;
    }

    public void setTurmaTeoricaList(List<TurmaTeorica> turmaTeoricaList) {
        this.turmaTeoricaList = turmaTeoricaList;
    }

    public TipoCarteira getTipoCarteiraIdTipoCarteira() {
        return tipoCarteiraIdTipoCarteira;
    }

    public void setTipoCarteiraIdTipoCarteira(TipoCarteira tipoCarteiraIdTipoCarteira) {
        this.tipoCarteiraIdTipoCarteira = tipoCarteiraIdTipoCarteira;
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
        hash += (cpfAluno != null ? cpfAluno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.cpfAluno == null && other.cpfAluno != null) || (this.cpfAluno != null && !this.cpfAluno.equals(other.cpfAluno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Aluno[ cpfAluno=" + cpfAluno + " ]";
    }
    
}
