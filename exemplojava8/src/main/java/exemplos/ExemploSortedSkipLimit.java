package exemplos;

import java.util.Comparator;

import control.Controller;
import model.Usuario;

public class ExemploSortedSkipLimit {
	
	public static void main(String[] args) {
		ExemploSortedSkipLimit.exemplo();
	}
	
	public static void exemplo(Comparator<Usuario> compare, int skip, int limit){
		Controller.todosUsuarios().stream().skip(skip).sorted(compare).limit(limit).forEach(System.out::println);
	}
	
	public static void exemplo(){
		ExemploSortedSkipLimit.exemplo(Comparator.comparing(Usuario::getNome), 1, 5);
	}

}
