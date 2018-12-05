/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Asus
 */
@Embeddable
public class AulaPraticaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "data_aula")
    @Temporal(TemporalType.DATE)
    private Date dataAula;
    @Basic(optional = false)
    @Column(name = "aluno_cpf_aluno")
    private String alunoCpfAluno;
    @Basic(optional = false)
    @Column(name = "professor_cpf_professor")
    private String professorCpfProfessor;

    public AulaPraticaPK() {
    }

    public AulaPraticaPK(Date dataAula, String alunoCpfAluno, String professorCpfProfessor) {
        this.dataAula = dataAula;
        this.alunoCpfAluno = alunoCpfAluno;
        this.professorCpfProfessor = professorCpfProfessor;
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }

    public String getAlunoCpfAluno() {
        return alunoCpfAluno;
    }

    public void setAlunoCpfAluno(String alunoCpfAluno) {
        this.alunoCpfAluno = alunoCpfAluno;
    }

    public String getProfessorCpfProfessor() {
        return professorCpfProfessor;
    }

    public void setProfessorCpfProfessor(String professorCpfProfessor) {
        this.professorCpfProfessor = professorCpfProfessor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataAula != null ? dataAula.hashCode() : 0);
        hash += (alunoCpfAluno != null ? alunoCpfAluno.hashCode() : 0);
        hash += (professorCpfProfessor != null ? professorCpfProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AulaPraticaPK)) {
            return false;
        }
        AulaPraticaPK other = (AulaPraticaPK) object;
        if ((this.dataAula == null && other.dataAula != null) || (this.dataAula != null && !this.dataAula.equals(other.dataAula))) {
            return false;
        }
        if ((this.alunoCpfAluno == null && other.alunoCpfAluno != null) || (this.alunoCpfAluno != null && !this.alunoCpfAluno.equals(other.alunoCpfAluno))) {
            return false;
        }
        if ((this.professorCpfProfessor == null && other.professorCpfProfessor != null) || (this.professorCpfProfessor != null && !this.professorCpfProfessor.equals(other.professorCpfProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AulaPraticaPK[ dataAula=" + dataAula + ", alunoCpfAluno=" + alunoCpfAluno + ", professorCpfProfessor=" + professorCpfProfessor + " ]";
    }
    
}
