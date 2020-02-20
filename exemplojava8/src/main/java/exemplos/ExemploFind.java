package exemplos;

import control.Controller;
import model.Usuario;

public class ExemploFind {
	
	public static void main(String[] args) {
		
	}
	
	public static void exemploFind(){
		System.out.println(Controller.todosUsuarios().stream().findAny().get());
	}
	
	public static void exemploFindFirst(){
		Controller.todosUsuarios().stream().findFirst().ifPresent(System.out::println);
	}
	
	public static void exemploOrElseFind(){
		System.out.println(Controller.todosUsuarios().stream().filter(f -> f.getCpf().equals("000")).findFirst().orElse(null));
	}
	
	public static void exemploOrElseGetFind(){
		System.out.println(Controller.todosUsuarios().stream().filter(f -> f.getCpf().equals("000")).findFirst().orElseGet(() -> new Usuario()));
	}
	
	public static void exemploOrElseThrowFind(){
		System.out.println(Controller.todosUsuarios().stream().filter(f -> f.getCpf().equals("000")).findFirst().orElseThrow(() -> new RuntimeException("Erro Massa")));
	}

}
