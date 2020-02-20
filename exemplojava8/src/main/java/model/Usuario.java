package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Usuario {
	private String nome;
	private String cpf;
	private Date dtNascimento;
	private List<Role> permissoes;
	
	public Usuario(){}
	
	public Usuario(String nome, String cpf, Date dtNascimento, List<Role> permissoes) {
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.permissoes = permissoes;
	}
	
	public String toString(){
		return "Nome:" + nome + ", Idade: " + getIdade() + 
				" Permissoes: " + permissoes + ".";
	}
	
	public int getIdade(){
		int retorno = -1;
		if(dtNascimento != null){
			Period p = Period.between(dtNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
			retorno = p.normalized().getYears();
		}	
		return retorno;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Date getDtNascimento() {
		return dtNascimento;
	}
	
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	public List<Role> getPermissoes() {
		return permissoes;
	}
	
	public void setPermissoes(List<Role> permissoes) {
		this.permissoes = permissoes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null) {
				return false;
			}
		} else if (!cpf.equals(other.cpf)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}
}
