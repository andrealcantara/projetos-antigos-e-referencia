package teste;

import java.util.Date;
import model.Pessoa;
import model.Professor;
import model.Titulo;
import dao.PessoaDAO;
import dao.ProfessorDAO;
import dao.TituloDAO;

public class TesteJPA {

	public static void main(String[] args) {
		Pessoa p = new Pessoa();
		Date d= new Date(System.currentTimeMillis());
		PessoaDAO dao = new PessoaDAO();
		Professor pr = new Professor();
		ProfessorDAO professorDAO = new ProfessorDAO();
		Titulo titulo = new Titulo();
		TituloDAO tituloDAO = new TituloDAO();
		
		p.setNome("Diego Antonio Ferreira");
		p.setCpf(06712351413);
		p.setDataNascimento(d);
		p.setEmail("dantonio808@gmail.com");
		p.setEndereco("Rua dos Bobos numero 0");
		p.setMatricula("20111Y6-RC0089");
		
		dao.insert(p);
		
		titulo.setNome("Mestre em PN");
		tituloDAO.insert(titulo);
		
		pr.setMatricula(p.getMatricula());
		pr.setPessoa(p);
		pr.setSalario((float) 3000.02);
		pr.setTitulo(titulo);
		
		professorDAO.insert(pr);
		
		System.out.println("ACABOU!!!!");
	}

}
