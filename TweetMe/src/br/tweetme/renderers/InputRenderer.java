package br.tweetme.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.sun.faces.renderkit.html_basic.TextRenderer;

public class InputRenderer extends TextRenderer {

	private static final String[] attributes = new String[] { "placeholder",
			"type" };

	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {

		ResponseWriter writer = context.getResponseWriter();

		for (String attribute : attributes) {
			String value = (String) component.getAttributes().get(attribute);

			if (value != null) {
				writer.writeAttribute(attribute, value, attribute);
			}
		}

		super.getEndTextToRender(context, component, currentValue);
	}
}
