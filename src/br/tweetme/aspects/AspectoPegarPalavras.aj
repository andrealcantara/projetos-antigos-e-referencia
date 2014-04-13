package br.tweetme.aspects;

import br.tweetme.entities.*;

public aspect AspectoPegarPalavras {
	
    public pointcut pegaPalavras(): call(String Post.getText());
	
	String around() : pegaPalavras(){
		String msg = proceed();
		
		msg = msg + " _Edited";
		 
		return msg;
	}
	
//	public pointcut alteraPalavras() : call(void Post.setText(String));
//	
//	void around() : alteraPalavras(){
//	}
	
}
