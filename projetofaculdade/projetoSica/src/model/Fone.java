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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Fone.findAll", query = "SELECT f FROM Fone f"),
    @NamedQuery(name = "Fone.findById", query = "SELECT f FROM Fone f WHERE f.id = :id"),
    @NamedQuery(name = "Fone.findByDdd", query = "SELECT f FROM Fone f WHERE f.ddd = :ddd"),
    @NamedQuery(name = "Fone.findByNumero", query = "SELECT f FROM Fone f WHERE f.numero = :numero"),
    @NamedQuery(name = "Fone.findByMatricula", query = "SELECT f FROM Fone f WHERE f.matricula = :matricula")})
public class Fone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private Integer ddd;
    private Integer numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String matricula;
    @JoinTable(name = "fone_pessoa", joinColumns = {
        @JoinColumn(name = "id_fone", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "matricula_pessoa", referencedColumnName = "matricula")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pessoa> pessoaList;

    public Fone() {
    }

    public Fone(Integer id) {
        this.id = id;
    }

    public Fone(Integer id, String matricula) {
        this.id = id;
        this.matricula = matricula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Pessoa> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Fone)) {
            return false;
        }
        Fone other = (Fone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Fone[ id=" + id + " ]";
    }
    
}
