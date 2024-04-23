package com.bibliotech.admin.author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CivilityData {
	public Civility[] getCivilities() {
		return Civility.values();
	}
}
