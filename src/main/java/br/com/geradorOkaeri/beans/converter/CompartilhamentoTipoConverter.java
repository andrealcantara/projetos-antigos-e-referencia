package br.com.geradorOkaeri.beans.converter;

import java.util.function.Function;

import javax.faces.convert.FacesConverter;

import br.com.geradorOkaeri.model.CompartilhamentoTipo;
import br.com.geradorOkaeri.model.EnumModelInterface;

@FacesConverter("gerador.converter.compartilhamentoTipo")
public class CompartilhamentoTipoConverter implements ConverterEnumModelo {

	@Override
	public Function<String, ? extends EnumModelInterface> getResolver() {
		return r -> CompartilhamentoTipo.valueOf(Integer.valueOf(r).intValue());
	}
}
