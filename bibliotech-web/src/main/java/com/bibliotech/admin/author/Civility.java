package com.bibliotech.admin.author;

public enum Civility {
	MR("author.civility.mr"), MISS("author.civility.miss"), MRS("author.civility.mrs"), 
	DR("author.civility.dr");

	private String label;

	Civility(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
