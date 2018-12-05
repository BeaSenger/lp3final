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
@Table(name = "professor")
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p")})
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf_professor")
    private String cpfProfessor;
    @Column(name = "nome_professor")
    private String nomeProfessor;
    @Column(name = "habilitacao")
    private String habilitacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<AulaPratica> aulaPraticaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professorCpfProfessor")
    private List<TurmaTeorica> turmaTeoricaList;

    public Professor() {
    }

    public Professor(String cpfProfessor) {
        this.cpfProfessor = cpfProfessor;
    }

    public String getCpfProfessor() {
        return cpfProfessor;
    }

    public void setCpfProfessor(String cpfProfessor) {
        this.cpfProfessor = cpfProfessor;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }

    public List<AulaPratica> getAulaPraticaList() {
        return aulaPraticaList;
    }

    public void setAulaPraticaList(List<AulaPratica> aulaPraticaList) {
        this.aulaPraticaList = aulaPraticaList;
    }

    public List<TurmaTeorica> getTurmaTeoricaList() {
        return turmaTeoricaList;
    }

    public void setTurmaTeoricaList(List<TurmaTeorica> turmaTeoricaList) {
        this.turmaTeoricaList = turmaTeoricaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpfProfessor != null ? cpfProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.cpfProfessor == null && other.cpfProfessor != null) || (this.cpfProfessor != null && !this.cpfProfessor.equals(other.cpfProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Professor[ cpfProfessor=" + cpfProfessor + " ]";
    }
    
}
