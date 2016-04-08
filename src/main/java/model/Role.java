package model;

public enum Role {
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
		Role retorno = null;
		for (Role r : Role.values()) {
			if (r.getNivel() == nivel) {
				retorno = r;
				break;
			}
			if (retorno == null) {
				throw new RuntimeException("Role " + nivel + " não encontrada");
			}
		}
		return retorno;
	}
}
