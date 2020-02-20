package br.com.geradorOkaeri.beans.converter;

import java.util.function.Function;

import javax.faces.convert.FacesConverter;

import br.com.geradorOkaeri.model.EnumModelInterface;
import br.com.geradorOkaeri.model.Qualidade;

@FacesConverter("gerador.converter.qualidade")
public class QualidadeConverter implements ConverterEnumModelo {

	@Override
	public Function<String, ? extends EnumModelInterface> getResolver() {
		return r -> Qualidade.valueOf(Integer.valueOf(r).intValue());
	}
}
