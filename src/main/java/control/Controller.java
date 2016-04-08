package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import model.Role;
import model.Usuario;

public class Controller {
	
	public static List<Usuario> todosUsuarios() {
		try{
	        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
	        return Arrays.asList(
	            new Usuario("EFRAIM FILHO", "11111111111", sdf.parse("1989/08/05"), Arrays.asList(Role.USER, Role.MODERATE, Role.COLLABORATOR, Role.ADMIN)),
	            new Usuario("JULIANA LETICIA GOMES SANTANA DA SILVA", "06080428466", sdf.parse("2000/12/25"), Arrays.asList(Role.USER, Role.MODERATE)), 
	            new Usuario("CELSO PEREIRA DA SILVA", "08185342490", sdf.parse("2000/12/12"), Arrays.asList(Role.USER)), 
	            new Usuario("ABBYLENE MERILANE DA SILVA", "09737319400", sdf.parse("2000/11/08"), Arrays.asList(Role.USER)), 
	            new Usuario("VALESKA LACERDA MELQUIADES", "03291078446", sdf.parse("2000/10/28"), Arrays.asList(Role.USER)), 
	            new Usuario("VALESSA DOS SANTOS MONTEIROS", "08267025464", sdf.parse("2000/09/18"), Arrays.asList(Role.USER)), 
	            new Usuario("LUCELIA LIMA FEITOSA", "04586068477", sdf.parse("2000/09/08"), Arrays.asList(Role.USER, Role.COLLABORATOR)), 
	            new Usuario("BENJAMIM BRITO MELO NETO", "05366152410", sdf.parse("2000/07/19"), Arrays.asList(Role.USER, Role.COLLABORATOR)), 
	            new Usuario("JOAO PEDRO HENRIQUE SANTOS DUARTE", "10944538460", sdf.parse("2000/06/17"), Arrays.asList(Role.USER, Role.MODERATE, Role.COLLABORATOR, Role.ADMIN)), 
	            new Usuario("FRANCINA MARIA DE SOUZA", "04469573485", sdf.parse("2000/05/24"), Arrays.asList(Role.USER)), 
	            new Usuario("NELSON WANDERLEY DE PAULA", "07684198408", sdf.parse("2000/05/08"), Arrays.asList(Role.USER)) 
	        );
		}catch (ParseException e){
			throw new RuntimeException(e);
		}
    }
}
