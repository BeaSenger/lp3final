/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "aula_pratica")
@NamedQueries({
    @NamedQuery(name = "AulaPratica.findAll", query = "SELECT a FROM AulaPratica a")})
public class AulaPratica implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AulaPraticaPK aulaPraticaPK;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_fim")
    @Temporal(TemporalType.TIME)
    private Date horaFim;
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "aluno_cpf_aluno", referencedColumnName = "cpf_aluno", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Aluno aluno;
    @JoinColumn(name = "professor_cpf_professor", referencedColumnName = "cpf_professor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Professor professor;
    @JoinColumn(name = "veiculo_codigo_veiculo", referencedColumnName = "codigo_veiculo")
    @ManyToOne(optional = false)
    private Veiculo veiculoCodigoVeiculo;

    public AulaPratica() {
    }

    public AulaPratica(AulaPraticaPK aulaPraticaPK) {
        this.aulaPraticaPK = aulaPraticaPK;
    }

    public AulaPratica(Date dataAula, String alunoCpfAluno, String professorCpfProfessor) {
        this.aulaPraticaPK = new AulaPraticaPK(dataAula, alunoCpfAluno, professorCpfProfessor);
    }

    public AulaPraticaPK getAulaPraticaPK() {
        return aulaPraticaPK;
    }

    public void setAulaPraticaPK(AulaPraticaPK aulaPraticaPK) {
        this.aulaPraticaPK = aulaPraticaPK;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Veiculo getVeiculoCodigoVeiculo() {
        return veiculoCodigoVeiculo;
    }

    public void setVeiculoCodigoVeiculo(Veiculo veiculoCodigoVeiculo) {
        this.veiculoCodigoVeiculo = veiculoCodigoVeiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aulaPraticaPK != null ? aulaPraticaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AulaPratica)) {
            return false;
        }
        AulaPratica other = (AulaPratica) object;
        if ((this.aulaPraticaPK == null && other.aulaPraticaPK != null) || (this.aulaPraticaPK != null && !this.aulaPraticaPK.equals(other.aulaPraticaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AulaPratica[ aulaPraticaPK=" + aulaPraticaPK + " ]";
    }
    
}
