package com.bibliotech.kernel.jsf.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Class that allows managing the language of the user interface (i18n & l10n).
 */
@Named
@SessionScoped
public class LocaleManager implements Serializable {
	public static final long serialVersionUID = 1;
	private List<Locale> locales = new ArrayList<>();
	private Locale locale;

	@PostConstruct
	public void postConstruct() {
		Iterator<Locale> it = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		while (it.hasNext()) {
			locales.add(it.next());
		}
	}

	public List<Locale> getLocales() {
		return locales;
	}

	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}

	public Locale getLocale() {
		if (locale == null) {
			try {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				locale = facesContext.getViewRoot().getLocale();
				if (locale == null) {
					locale = facesContext.getExternalContext().getRequestLocale();
				}
			} catch (Exception e) {
			}
			if (locale == null) {
				locale = Locale.FRANCE;
			}
		}
		
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void changeLocale(String language) {
		setLocale(new Locale(language));
	}

	public void changeLocale(String language, String country) {
		setLocale(new Locale(language, country));
	}
}
