package br.tweetme.aspects;

import br.tweetme.entities.*;
//import java.sql.ResultSet;

public aspect AspectoPegarPalavras {
	
    public pointcut pegaPalavras(): call(String Post.getText());
	
	String around() : pegaPalavras(){
		String msg = proceed();
		
		msg = msg + " @Tweet-Me Trademark";
		 
		return msg;
	}
	

	
//	public pointcut alteraPalavras(): call(String ResultSet.getString(String));
//	
//	String around() : alteraPalavras(){
//		String msg = "";	
//		
//		
//		return msg;
//	}
	
	
	//filtro de Palavroes
	void around(String texto) : call(void Post.setText(..)) 
								&& args(texto){
		
		String palavraInvalida = checarPalavras(texto);
		if(!texto.endsWith("ok")){
			texto = texto.replace(palavraInvalida,"CENSURADO!!" );
		}
		
		
		proceed(texto);
	}

	
	
	/**
	 * Método que dado uma lista de palavras pre-definidas,checa
	 * se elas estao contidas numa dada frase a ser avaliada.
	 * Caso não contenha retorna ok,se tiver retorna a frase.
	 * 
	 * @param fraseAvaliada
	 * @return ok ou a frase a ser substituida.
	 */
	private String checarPalavras(String fraseAvaliada){
		String invalido = "ok";
		String[] listaDePalavras = new String[3];
		
		listaDePalavras[0] = "Aspectos";
		listaDePalavras[1] = "móvei";
		listaDePalavras[2] = "palavrao";
		int i = 0;
		
		while(i < 3){
			
			if(fraseAvaliada.indexOf(listaDePalavras[i]) >= 0){
				invalido = listaDePalavras[i];
				break;
			}
			i++;
		}
		
		return invalido;
	}
	
}
