package br.com.geradorOkaeri.Util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ApplicationScoped
public class Mensagem implements Serializable {
	private static final long serialVersionUID = -1549157101237590081L;
	private static ResourceBundle rb = ResourceBundle.getBundle("properties.sys-messages");

	public static String get(MensagemEnum msg) {
		return rb.getString(msg.getMensagemKey());
	}

	public static String get(MensagemEnum msg, Object... params) {
		return MessageFormat.format(rb.getString(msg.getMensagemKey()), params);
	}

	public enum MensagemEnum {
		Mensagem_Error_Enum_Param("mensagemErrorEnumParam");

		private String resourceMensagem;

		private MensagemEnum(String resourceMensagem) {
			this.resourceMensagem = resourceMensagem;
		}

		private String getMensagemKey() {
			return resourceMensagem;
		}
	}

	/**
	 * http://stackoverflow.com/a/9407732
	 * @author andre
	 *
	 */
	public static class MensagemLoader {
		/**
		 * For proper localization, you will want to retrieve error messages
		 * from a message bundle. Doing that involves some busywork with locales
		 * and class Loader.
		 *
		 * @param bundleName
		 * @param resourceId
		 * @param params
		 * @return
		 */
		public static String getMessage(String bundleName, String resourceId, Object... params) {
			FacesContext context = FacesContext.getCurrentInstance();

			Application app = context.getApplication();
			String appBundle = app.getMessageBundle();
			Locale locale = getLocale(context);
			ClassLoader loader = getClassLoader();
			String summary = getString(appBundle, bundleName, resourceId, locale, loader, params);

			if (summary != null) {
				return summary;
			}
			String detail = getString(appBundle, bundleName, resourceId + "detail", locale, loader, params);
			return detail;

		}

		public static String getString(String bundle, String resourceId, Object... params) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application app = context.getApplication();
			String appBundle = app.getMessageBundle();
			Locale locale = getLocale(context);
			ClassLoader loader = getClassLoader();
			return getString(appBundle, bundle, resourceId, locale, loader, params);
		}

		public static Locale getLocale(FacesContext context) {
			Locale locale = null;
			UIViewRoot viewRoot = context.getViewRoot();
			if (viewRoot != null) {
				locale = viewRoot.getLocale();
			} else {
				locale = Locale.getDefault();
			}
			return locale;
		} 

		public static ClassLoader getClassLoader() {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			if (loader == null) {
				loader = ClassLoader.getSystemClassLoader();
			}
			return loader;

		}
		public static String getString(String bundle1, String bundle2, String resourceId, Locale locale,
				ClassLoader loader, Object... params) {
			String resource = null;
			ResourceBundle bundle;
			if (bundle1 != null) {
				bundle = ResourceBundle.getBundle(bundle1, locale, loader);
				if (bundle != null) {
					try {
						resource = bundle.getString(resourceId);
					} catch (MissingResourceException e) {
					}
				}
			} 
			if (resource == null) {
				bundle = ResourceBundle.getBundle(bundle2, locale, loader);
				if (bundle != null) {
					try {
						resource = bundle.getString(resourceId);
					} catch (MissingResourceException e) {
					}
				}
			} 
			if (resource == null) {
				return null; // no match
			}

			if (params == null) {
				return resource;
			}
			MessageFormat formatter = new MessageFormat(resource, locale);
			return formatter.format(params);

		} 
	}
}
