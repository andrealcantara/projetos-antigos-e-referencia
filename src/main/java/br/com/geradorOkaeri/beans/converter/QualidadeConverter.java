package br.com.geradorOkaeri.beans.converter;

import java.util.function.Function;

import javax.faces.convert.FacesConverter;

import br.com.geradorOkaeri.model.EnumID;
import br.com.geradorOkaeri.model.Qualidade;

@FacesConverter("QualidadeConverter")
public class QualidadeConverter implements ConverterEnumModelo {

	@Override
	public Function<String, ? extends EnumID> getResolver() {
		return r -> Qualidade.valueOf(Integer.valueOf(r).intValue());
	}



}
