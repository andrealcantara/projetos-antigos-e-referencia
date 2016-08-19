package br.com.geradorOkaeri.Util.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class LocalDateConverterXML implements Converter{

	private final DateTimeFormatter formatter;
	
	private static final String dataInvalida = "1970-01-01";
	
	
	public LocalDateConverterXML() {
		this("yyyy-MM-dd",new Locale("pt","BR"));
	}
	
	public LocalDateConverterXML(String pattern_date, Locale locale){
		formatter = DateTimeFormatter.ofPattern(pattern_date, locale);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
		return type.equals(LocalDate.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		LocalDate local = (LocalDate) source;
		String strLocal = null;
		try{
			if(!local.equals(dataInvalida)){
				strLocal = formatter.format(local);
			}
		}catch(DateTimeParseException e){
			strLocal = "0000-00-00";
		}
				
		writer.setValue(strLocal);
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		LocalDate local = null;
		String strDate = null;
		try{
			strDate = reader.getValue();
			if(!strDate.equals(dataInvalida)){
				local = LocalDate.from(formatter.parse(reader.getValue()));
			}
		}catch(DateTimeParseException e){
			local = null;
		}
		return local;
	}

}
