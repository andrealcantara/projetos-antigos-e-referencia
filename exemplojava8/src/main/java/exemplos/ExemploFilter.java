package exemplos;

import java.util.function.Predicate;

import control.Controller;
import model.Usuario;

public class ExemploFilter {

	public static void main(String[] args) {
		ExemploFilter.exemplo();
		
	}
	
	public static void exemplo(Predicate<Usuario> filter){
		Controller.todosUsuarios().stream().filter(filter).forEach(System.out::println);
	}
	
	public static void exemplo(){
		ExemploFilter.exemplo(u-> u.getIdade() > 20);
	}
}
