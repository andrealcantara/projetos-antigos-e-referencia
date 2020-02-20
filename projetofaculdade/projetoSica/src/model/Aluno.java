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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a"),
    @NamedQuery(name = "Aluno.findByMatricula", query = "SELECT a FROM Aluno a WHERE a.matricula = :matricula"),
    @NamedQuery(name = "Aluno.findByNomeMae", query = "SELECT a FROM Aluno a WHERE a.nomeMae = :nomeMae"),
    @NamedQuery(name = "Aluno.findByNomePai", query = "SELECT a FROM Aluno a WHERE a.nomePai = :nomePai"),
    @NamedQuery(name = "Aluno.findByEscolaAnterior", query = "SELECT a FROM Aluno a WHERE a.escolaAnterior = :escolaAnterior")})
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String matricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    private String nomeMae;
    @Size(max = 500)
    private String nomePai;
    @Size(max = 500)
    private String escolaAnterior;
    @JoinTable(name = "aprender", joinColumns = {
        @JoinColumn(name = "matricula_aluno", referencedColumnName = "matricula")}, inverseJoinColumns = {
        @JoinColumn(name = "codigo_turma", referencedColumnName = "codigo")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Turma> turmaList;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;

    public Aluno() {
    }

    public Aluno(String matricula) {
        this.matricula = matricula;
    }

    public Aluno(String matricula, String nomeMae) {
        this.matricula = matricula;
        this.nomeMae = nomeMae;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getEscolaAnterior() {
        return escolaAnterior;
    }

    public void setEscolaAnterior(String escolaAnterior) {
        this.escolaAnterior = escolaAnterior;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Aluno[ matricula=" + matricula + " ]";
    }
    
}
