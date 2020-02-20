package padrao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuscaMediaFire {
	private int position;
	private File arquivo;
	private String nomeArquivo;
	private StringBuffer stringSaida;

	public BuscaMediaFire(File arquivo) {
		this.position = 0;
		this.setArquivo(arquivo);
		this.stringSaida = new StringBuffer();
	}

	public BuscaMediaFire(String nomeArquivo) {
		this.position = 0;
		this.setArquivo(new File(nomeArquivo));
		this.stringSaida = new StringBuffer();
	}

	public void buscarLinks() {
		String mediaInit = "http://www.mediafire.com/?";
		final int tamanho = 41;
		int tamanhoLinha = 0;
		int posicaoInicio = 0;
		int posicaoFinal = 0;
		String linhaEncontrada = "";
		try {
			FileReader arq = new FileReader(this.arquivo);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			while (linha != null) {
				tamanhoLinha = linha.length();

				while (this.position < tamanhoLinha) {
					posicaoInicio = linha.indexOf(mediaInit, this.position);

					
					posicaoFinal = posicaoInicio + tamanho;
					if(posicaoFinal > tamanhoLinha-1 || posicaoInicio == -1){
						break;
					}

					linhaEncontrada = linha.substring(posicaoInicio,
							posicaoFinal);

					if (linhaEncontrada != null && !stringSaida.toString().contains(linhaEncontrada)) {
						stringSaida.append(linhaEncontrada+"\n");
					}

					this.position = posicaoFinal;
				}

				this.position = 0;
				linha = lerArq.readLine();
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
		this.nomeArquivo = arquivo.getName();
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	private void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public StringBuffer getStringSaida() {
		return stringSaida;
	}

	public void setStringSaida(StringBuffer stringSaida) {
		this.stringSaida = stringSaida;
	}

	public static void main(String args[]) {
		BuscaMediaFire busca = new BuscaMediaFire("/home/andre.alcantara/Desktop/teste.txt");
		busca.buscarLinks();
		System.out.println(busca.getStringSaida().toString());
		

	}
}
