package br.tweetme.aspects;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.*;

import br.tweetme.persistenceDAOJdbc.*;

public aspect AspectoLog {
	FileHandler arquivo;
	Logger log;
	
	public AspectoLog() throws SecurityException, IOException{
		log = Logger.getLogger("Tweet-Log.txt");
		arquivo = new FileHandler("%t/ArquivoLog.txt",true);
		arquivo.setFormatter(new SimpleFormatter());
		log.addHandler(arquivo);
	}
	
	//Log de Registro de Abertura de Conexoes Com o Banco
	public pointcut registarConexao() : execution(* 
					ConnectionDAOJdbc.getConnection(boolean));
	
	 after() : registarConexao(){
		 String mensagem = "Conexao com o Banco instanciada!\n";
		 this.log.info(mensagem);
	}
	 
	 
	//Aspecto Implementado para limpar o console dos erros "ta errado aqui!!"
	void around(String texto) : call(* PrintStream.println(String))
								&& args(texto){
		
		if(!texto.contains("errado")){
			proceed(texto);
		}else{
			this.log.info("Erro gerado por: " + thisJoinPoint.getThis().toString());
		}
	} 
	
	//Exemplo para a captura de Exceções
	//point cut que captura qualquer exceção na execução do programa, o within no final da linha especifica pacotes em que o pointcut é //ignorado  
//	public pointcut printStackTrace(): execution(* *.*(..)) /*&& !within(teste)*/;  
	 
	//advice que pega a Exception no retorno da chamada do método printStackTrace dessa Exception  
	   after(Exception e) returning: call(* *.printStackTrace(..)) && target(e){         
	       this.log.log(Level.SEVERE, "Excecao levantada: " + e.getMessage());
	   }  
		
}
