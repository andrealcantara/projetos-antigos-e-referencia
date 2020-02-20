/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AndreAlcant
 */
@Entity
@Table(catalog = "projetofaculdade", schema = "")
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t"),
    @NamedQuery(name = "Turma.findByCodigo", query = "SELECT t FROM Turma t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Turma.findByAno", query = "SELECT t FROM Turma t WHERE t.ano = :ano"),
    @NamedQuery(name = "Turma.findBySemestre", query = "SELECT t FROM Turma t WHERE t.semestre = :semestre"),
    @NamedQuery(name = "Turma.findByTurno", query = "SELECT t FROM Turma t WHERE t.turno = :turno"),
    @NamedQuery(name = "Turma.findByMaximoAlunos", query = "SELECT t FROM Turma t WHERE t.maximoAlunos = :maximoAlunos")})
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    private String codigo;
    private Integer ano;
    private Integer semestre;
    @Size(max = 6)
    private String turno;
    private Integer maximoAlunos;
    @ManyToMany(mappedBy = "turmaList", fetch = FetchType.LAZY)
    private List<Professor> professorList;
    @ManyToMany(mappedBy = "turmaList", fetch = FetchType.LAZY)
    private List<Aluno> alunoList;
    @JoinColumn(name = "codigo_disciplina", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Disciplina disciplina;

    public Turma() {
    }

    public Turma(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Integer getMaximoAlunos() {
        return maximoAlunos;
    }

    public void setMaximoAlunos(Integer maximoAlunos) {
        this.maximoAlunos = maximoAlunos;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Turma[ codigo=" + codigo + " ]";
    }
    
}
