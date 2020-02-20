package br.com.geradorOkaeri.beans.converter;

import java.util.function.Function;

import javax.faces.convert.FacesConverter;

import br.com.geradorOkaeri.model.EnumModelInterface;
import br.com.geradorOkaeri.model.ExibicaoCompartilhamento;

@FacesConverter("gerador.converter.compartilhamentoExibicao")
public class CompartilhamentoExibicaoConverter implements ConverterEnumModelo {

	@Override
	public Function<String, ? extends EnumModelInterface> getResolver() {
		return r -> ExibicaoCompartilhamento.valueOf(Integer.valueOf(r).intValue());
	}
}
