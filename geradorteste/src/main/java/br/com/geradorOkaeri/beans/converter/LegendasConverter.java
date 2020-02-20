package br.com.geradorOkaeri.beans.converter;

import java.util.function.Function;

import javax.faces.convert.FacesConverter;

import br.com.geradorOkaeri.model.EnumModelInterface;
import br.com.geradorOkaeri.model.Legendas;

@FacesConverter("gerador.converter.legendas")
public class LegendasConverter implements ConverterEnumModelo {

	@Override
	public Function<String, ? extends EnumModelInterface> getResolver() {
		return r -> Legendas.valueOf(Integer.valueOf(r).intValue());
	}
}
