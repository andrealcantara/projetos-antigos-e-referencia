package br.com.geradorOkaeri.AniDB.converter;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import br.com.geradorOkaeri.AniDB.modal.AnimeAniDBType;

public class AnimeAniDBTypeConverterXML extends AbstractSingleValueConverter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(AnimeAniDBType.class);
	}

	@Override
	public Object fromString(String str) {
		AnimeAniDBType retorno;
		try{
			retorno = AnimeAniDBType.valueOfName(str);
		}catch(RuntimeException e){
			retorno = null;
		}
		return retorno;
	}


}
