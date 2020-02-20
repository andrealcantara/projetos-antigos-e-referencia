package exemplos;

import java.util.List;

import control.Controller;
import model.Usuario;

public class ExemploForEach {
	private static List<Usuario> usuarios = Controller.todosUsuarios();
	
	
	public static void main(String[] args) {
		ExemploForEach.exemploAntigo();
		ExemploForEach.exemploJava8();
		ExemploForEach.exemploJava8ReferenceMethod();
		
	}
	
	public static void exemploAntigo() {
		for(Usuario u : usuarios){
			System.out.println(u);
		}
	}
	
	public static void exemploJava8() {
		usuarios.forEach(x -> System.out.println(x));
	}
	
	public static void exemploJava8ReferenceMethod() {
		usuarios.forEach(System.out::println);
	}

}
