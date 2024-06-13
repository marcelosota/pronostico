package ec.com.pronostico.utils;

import java.io.Serializable;
import java.util.Locale;
import java.util.Properties;

import javax.faces.context.FacesContext;

public class ArchivoProperties implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8621235641360706600L;

	public Properties getProperties() {

		try {
			Properties objPropiedades = new Properties();
			objPropiedades.load(getClass().getClassLoader().getResourceAsStream("propiedades.properties"));

			if (!objPropiedades.isEmpty()) {
				return objPropiedades;
			}

			return null;
		} catch (Exception e) {
		}

		return null;
	}

	public java.util.ResourceBundle getPropiedades() {
		//Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		//java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("propiedades.properties", locale);
		java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("propiedades");
		return rb;
	}
}
