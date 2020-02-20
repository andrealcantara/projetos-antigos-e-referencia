package exemplos;

import java.util.function.Function;
import java.util.stream.Stream;

import control.Controller;
import model.Usuario;

public class ExemploMap {

	
	public static void main(String[] args) {
		ExemploMap.exemploFlatMap();
	}
	
	public static <T> void exemplo(Function<Usuario,T> function){
		Controller.todosUsuarios().stream().map(function).forEach(System.out::println);
	}
	
	public static void exemplo(){
		ExemploMap.exemplo(u-> u.getNome());
	}
	
	public static <T> void exemploFlatMap(Function<Usuario,Stream<T>> functionFlatMap){
		Controller.todosUsuarios().stream().flatMap(functionFlatMap).forEach(x -> System.out.println(x));
	}
	
	public static void exemploFlatMap(){
		ExemploMap.exemploFlatMap(u -> u.getPermissoes().stream());
	}
}
