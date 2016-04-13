package model;

import java.util.Arrays;
import java.util.function.Predicate;

import mensagem.Mensagem;
import mensagem.Mensagem.MensagemEnum;

public interface HelperEnum {
	public static <T extends Enum<T> & HelperEnum> T of(Predicate<T> filtro, Class<T> clazz, Object valor){
		T retorno = Arrays.stream(clazz.getEnumConstants()).filter(filtro).findFirst()
				.orElseThrow(() -> 
				new IllegalArgumentException(Mensagem.get(MensagemEnum.Mensagem_Error_Enum_Param, clazz.getName(),valor.toString())));
		return retorno;
	}
}
