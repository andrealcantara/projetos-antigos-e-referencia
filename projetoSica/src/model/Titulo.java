/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author AndreAlcant
 */
@Entity
@Table(catalog = "projetofaculdade", schema = "")
@NamedQueries({
    @NamedQuery(name = "Titulo.findAll", query = "SELECT t FROM Titulo t"),
    @NamedQuery(name = "Titulo.findById", query = "SELECT t FROM Titulo t WHERE t.id = :id"),
    @NamedQuery(name = "Titulo.findByNome", query = "SELECT t FROM Titulo t WHERE t.nome = :nome")})
public class Titulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titulo", fetch = FetchType.LAZY)
    private List<Professor> professorList;

    public Titulo() {
    }

    public Titulo(Integer id) {
        this.id = id;
    }

    public Titulo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Titulo)) {
            return false;
        }
        Titulo other = (Titulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Titulo[ id=" + id + " ]";
    }
    
}
