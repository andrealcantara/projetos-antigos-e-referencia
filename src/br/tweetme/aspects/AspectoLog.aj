package br.tweetme.aspects;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.*;


import br.tweetme.persistenceDAOJdbc.*;

public aspect AspectoLog {
	FileHandler arquivo;
	Logger log;
	
	public AspectoLog() throws SecurityException, IOException{
		log = Logger.getLogger("Tweet-Log.txt");
		arquivo = new FileHandler("ArquivoLog%u.%g.txt",true);
		arquivo.setFormatter(new SimpleFormatter());
		log.addHandler(arquivo);
	}
	
	//Log de Registro de Abertura de Conexoes Com o Banco
	public pointcut registarConexao() : execution(* 
					ConnectionDAOJdbc.getConnection(boolean));
	
	 after() : registarConexao(){
		 String mensagem = "Conexao com o Banco instanciada!";
		 this.log.info(mensagem);
		 arquivo.close();
	}
	 
	 
	//Aspecto Implementado para limpar o console dos erros "ta errado aqui!!"
	void around(String texto) : call(* PrintStream.println(String))
								&& args(texto){
		
		if(!texto.contains("errado")){
			proceed(texto);
		}
	} 
		
}
