package exemplos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import control.Controller;
import model.Usuario;

public class ExemploCollectors {
	
	public static void main(String[] args) {
		ExemploCollectors.exemploLista();
		ExemploCollectors.exemploHashMapLista();
	}
	
	public static void exemploLista() {
		List lista = Controller.todosUsuarios().stream().filter(x -> x.getNome().charAt(0) == 'V').collect(Collectors.toList());
		System.out.println(lista);
	}
	
	public static void exemploHashMapLista(){
		Map<Integer,List<Usuario>> map = Controller.todosUsuarios().stream().collect(Collectors.groupingBy(u -> u.getIdade()));
		System.out.println(map);
	}
	
	public static void exemploHashMapBoolean(){
		Map<Boolean, List<Usuario>> map = Controller.todosUsuarios().stream().collect(Collectors.partitioningBy(u -> u.getIdade() <= 20));
		System.out.println(map);
	}

}
