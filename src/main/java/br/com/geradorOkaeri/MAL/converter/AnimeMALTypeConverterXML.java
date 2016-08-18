package br.com.geradorOkaeri.MAL.converter;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import br.com.geradorOkaeri.MAL.modal.AnimeMALType;

public class AnimeMALTypeConverterXML extends AbstractSingleValueConverter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(AnimeMALType.class);
	}

	@Override
	public Object fromString(String str) {
		AnimeMALType retorno;
		try{
			retorno = AnimeMALType.valueOfName(str);
		}catch(RuntimeException e){
			retorno = null;
		}
		return retorno;
	}


}
