package exemplos;


import static java.util.stream.LongStream.rangeClosed;

import com.google.common.base.Preconditions;

public class ExemploParallel {
										//10kk
	public final static long rangeTeste = 10000000;
	
	public static void main(String[] args) {
		
		ExemploParallel.calculoTempo(() -> {		
			System.out.println(ExemploParallel.quantidadePrimosComParallels(ExemploParallel.rangeTeste));
		});
		
		ExemploParallel.calculoTempo(() -> {		
			System.out.println(ExemploParallel.quantidadePrimosSemParallels(ExemploParallel.rangeTeste));
		});
	}
	
	
	public static long quantidadePrimosSemParallels(long range){
		Preconditions.checkArgument(range > 0l);
		return rangeClosed(0, range).filter(ExemploParallel::ehPrimo).count();
	}
	
	
	public static long quantidadePrimosComParallels(long range){
		Preconditions.checkArgument(range > 0l);
		return rangeClosed(0, range).parallel().filter(ExemploParallel::ehPrimo).count();
	}
	
	public static boolean ehPrimo(long numero){
		return numero > 1 && rangeClosed(2, (long) Math.sqrt(numero)).noneMatch(diviso -> numero % diviso == 0);
	}
	
	
	public static void calculoTempo(Runnable calculaTempo){
		long time = System.currentTimeMillis();
		calculaTempo.run();
		long fimTempo =  System.currentTimeMillis() - time;
		System.out.println(fimTempo/1000f);
	}
}