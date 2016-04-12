package exemplos;

import control.Controller;

public class ExemploMatch {
	
	public static void main(String[] args) {
		
	}

	public static void exemploMatch(){
		System.out.println(Controller.todosUsuarios().stream().allMatch(p -> p.getIdade() > 20));
	}
	
	public static void exemploMatchWork(){
		System.out.println(Controller.todosUsuarios().stream().filter(u -> u.getIdade() > 20).allMatch(p -> p.getIdade() > 20));
	}
	
	public static void exemploAnyMatch(){
		System.out.println(Controller.todosUsuarios().stream().anyMatch(u -> u.getCpf().equals("11111111111")));
	}
	
	public static void exemploNoneMatch(){
		System.out.println(Controller.todosUsuarios().stream().noneMatch(u -> u.getNome().split(" ")[0].equals("JOSIVALDO")));
	}
}
