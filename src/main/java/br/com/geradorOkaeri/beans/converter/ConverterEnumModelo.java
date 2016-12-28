package br.com.geradorOkaeri.beans.converter;

import java.util.function.Function;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.geradorOkaeri.model.EnumID;

public interface ConverterEnumModelo extends Converter {

	@Override
	default Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(component != null && !value.trim().isEmpty()){
			return getResolver().apply(value); 
		}
		return null;

	}
	
	@Override
	default String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return String.format("%s", ((EnumID)value).getId());
		}
		return null;
	}
	
	public Function<String,? extends EnumID> getResolver();
}
