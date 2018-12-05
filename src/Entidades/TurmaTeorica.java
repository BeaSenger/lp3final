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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "turma_teorica")
@NamedQueries({
    @NamedQuery(name = "TurmaTeorica.findAll", query = "SELECT t FROM TurmaTeorica t")})
public class TurmaTeorica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_turma")
    private Integer codigoTurma;
    @Column(name = "periodo_turma")
    private String periodoTurma;
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "quantidade_horas")
    private Integer quantidadeHoras;
    @ManyToMany(mappedBy = "turmaTeoricaList")
    private List<Aluno> alunoList;
    @JoinColumn(name = "professor_cpf_professor", referencedColumnName = "cpf_professor")
    @ManyToOne(optional = false)
    private Professor professorCpfProfessor;

    public TurmaTeorica() {
    }

    public TurmaTeorica(Integer codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public Integer getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(Integer codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public String getPeriodoTurma() {
        return periodoTurma;
    }

    public void setPeriodoTurma(String periodoTurma) {
        this.periodoTurma = periodoTurma;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getQuantidadeHoras() {
        return quantidadeHoras;
    }

    public void setQuantidadeHoras(Integer quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public Professor getProfessorCpfProfessor() {
        return professorCpfProfessor;
    }

    public void setProfessorCpfProfessor(Professor professorCpfProfessor) {
        this.professorCpfProfessor = professorCpfProfessor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTurma != null ? codigoTurma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurmaTeorica)) {
            return false;
        }
        TurmaTeorica other = (TurmaTeorica) object;
        if ((this.codigoTurma == null && other.codigoTurma != null) || (this.codigoTurma != null && !this.codigoTurma.equals(other.codigoTurma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TurmaTeorica[ codigoTurma=" + codigoTurma + " ]";
    }
    
}
