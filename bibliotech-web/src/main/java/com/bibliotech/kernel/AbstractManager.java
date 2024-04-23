package com.bibliotech.kernel;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import com.bibliotech.kernel.enums.ApplicationPageName;
import com.bibliotech.utils.Utils;

public abstract class AbstractManager implements Serializable {
	public static final long serialVersionUID = 1;
	public static final String REDIRECT_PART = "faces-redirect";
	public static final String RETURN_POINT_PART = "rp";
	public static final String UID_PART = "uid";
	private static String contextRoot;
	private static ResourceBundle bundle;
	private String returnPoint;

	// ======================================
	// =          Getters & Setters         =
	// ======================================

	public String getContextRoot() {
		if (contextRoot == null) {
			contextRoot = "" + getFacesContext().getExternalContext().getApplicationContextPath();
		}
		
		return contextRoot;
	}

	public String getReturnPoint() {
		return returnPoint;
	}

	public void setReturnPoint(String returnPoint) {
		this.returnPoint = returnPoint;
	}

	public ResourceBundle getResourceBundle() {
		if (bundle == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			bundle = getFacesContext().getApplication().getResourceBundle(facesContext, "msg");
		}
		
		return bundle;
	}

	public String getResourceBundleMessage(String key) {
		if (bundle == null) {
			getResourceBundle();
		}
		
		if (bundle.containsKey(key)) {
			return bundle.getString(key);
		}
		
		return key;
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public Locale getLocale() {
		Locale locale = null;
		
		try {
			locale = getFacesContext().getViewRoot().getLocale();
		} catch (Exception e) {
			try {
				locale = getFacesContext().getExternalContext().getRequestLocale();
			} catch (Exception e2) {
				locale = new Locale("fr", "FR");
			}
		}
		
		return locale;
	}

	// ======================================
	// =           Public Methods           =
	// ======================================

	/**
	 * Formatting of the return to caller URL
	 * 
	 * @param redirect
	 * @return
	 */
	public String formatToCaller(boolean redirect) {
		if (!Utils.isSet(returnPoint)) {
			return null;
		}
		
		return formatURL(getPageURL(ApplicationPageName.valueOf(returnPoint)), redirect);
	}

	/**
	 * Formatting of the URL with/out redirection
	 * 
	 * @param url
	 * @param redirect
	 * @return
	 */
	public String formatURL(String url, boolean redirect) {
		if (!Utils.isSet(url)) {
			return null;
		}
		
		if (redirect) {
			return addURLParameter(new StringBuilder(url), REDIRECT_PART, true).toString();
		}
		
		return url;
	}

	/**
	 * Adding a URL parameter
	 * 
	 * @param url
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public StringBuilder addURLParameter(StringBuilder url, String attributeName, Object attributeValue) {
		if (!Utils.isSet(url)) {
			return null;
		}

		if (!Utils.isSet(attributeName)) {
			return url;
		}

		if (url.indexOf("?") == -1) {
			url.append("?");
		} else {
			url.append("&");
		}

		url.append(attributeName).append("=");

		if (Utils.isSet(attributeValue)) {
			url.append(attributeValue.toString());
		}

		return url;
	}

	/**
	 * Formatting the URL of a standard call to the manager
	 * 
	 * @param url
	 * @param redirect
	 * @param returnPointURL
	 * @param uid
	 * @return
	 */
	public String formatStandardCallURL(String url, boolean redirect, String returnPointURL, Integer uid) {
		StringBuilder sb = new StringBuilder();
		sb.append(url);

		if (redirect) {
			addURLParameter(sb, REDIRECT_PART, true);
		}
		
		if (returnPointURL != null) {
			addURLParameter(sb, RETURN_POINT_PART, returnPointURL);
		}
		
		if (uid != null) {
			addURLParameter(sb, UID_PART, uid);
		}

		return sb.toString();
	}

	/**
	 * Programmatic navigation
	 * 
	 * @param url
	 * @param redirect
	 * @param returnPointURL
	 * @param uid
	 */
	public void navigateTo(String url, boolean redirect, String returnPointURL, Integer uid) {
		ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) getFacesContext()
				.getApplication().getNavigationHandler();
		navigationHandler.performNavigation(formatStandardCallURL(url, redirect, returnPointURL, uid));
	}

	/**
	 * Retrieving the URL associated to the enum
	 * 
	 * @param page
	 * @return
	 */
	public String getPageURL(ApplicationPageName page) {
		String url = getResourceBundleMessage(page.toString());
		return url;
	}

	/**
	 * Sending a message
	 * 
	 * @param text            : Text or message identifier in the ApplicationResources.properties file
	 * @param severity        : Message severity
	 * @param useFlashContext : Using the flash context (in case of a redirection)
	 */
	public void sendFacesMessage(String text, Severity severity, boolean useFlashContext) {
		FacesContext facesContext = getFacesContext();

		facesContext.getExternalContext().getFlash().setKeepMessages(useFlashContext);

		text = getResourceBundleMessage(text);

		if (severity == null) {
			severity = FacesMessage.SEVERITY_INFO;
		}

		FacesMessage facesMessage = new FacesMessage(severity, null, text);

		facesContext.addMessage(null, facesMessage);
	}

	/**
	 * Sending a message related to an Exception
	 * 
	 * @param e               : The Exception to handle
	 * @param useFlashContext : Using the flash context (in case of a redirection)
	 */
	public void sendFacesMessage(Exception e, boolean useFlashContext) {
		FacesContext facesContext = getFacesContext();

		facesContext.getExternalContext().getFlash().setKeepMessages(useFlashContext);
		
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), e.toString());
		facesContext.addMessage(null, facesMessage);
	}

	/**
	 * Formatting the URL with eventual parameters
	 * 
	 * @param message    : Text or message identifier in the ApplicationResources.properties file
	 * @param parameters : Array of parameters or null
	 * @return The formatted message
	 */
	public String formatMessage(String message, Object[] parameters) {
		String formatted = message;
		int p1, p2 = 0;
		String value = "";

		try {
			if (getResourceBundle().containsKey(message)) {
				formatted = getResourceBundle().getString(message);
			}

			while ((p1 = formatted.indexOf("{")) != -1) {
				p2 = formatted.indexOf("}", p1);
				int idx = Integer.valueOf(formatted.substring(p1 + 1, p2));
				if (idx < parameters.length) {
					value = parameters[idx].toString();
				} else {
					value = "";
				}

				value = getResourceBundleMessage(value);
				formatted = formatted.substring(0, p1) + value + formatted.substring(p2 + 1);
			}
		} catch (Exception e) {
		}
		
		return formatted;
	}
}
