package MAL.converter;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import MAL.modal.AnimeMALStatus;

public class AnimeMALStatusConverterXML extends AbstractSingleValueConverter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(AnimeMALStatus.class);
	}

	@Override
	public Object fromString(String str) {
		AnimeMALStatus retorno;
		try{
			retorno = AnimeMALStatus.valueOfName(str);
		}catch(RuntimeException e){
			retorno = null;
		}
		return retorno;
	}


}
