package model;

import java.util.function.Predicate;

public enum Role implements HelperEnum{
	ADMIN(1),
	COLLABORATOR(2),
	MODERATE(3),
	USER(1000);
	
	private int nivel;
	
	private Role(int nivel){
		this.nivel = nivel;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public static Role valueOf(int nivel) {
		Predicate<Role> filtro = f-> f.getNivel() == nivel;
		Role r = HelperEnum.of(filtro, Role.class, nivel);
//		Role r = Arrays.stream(Role.values())
//				.filter(f -> f.getNivel() == nivel)
//				.findFirst()
//				.orElseThrow(()-> new RuntimeException());
		return r;
	}
	
	
}
