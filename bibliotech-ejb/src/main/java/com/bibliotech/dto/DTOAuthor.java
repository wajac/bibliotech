package com.bibliotech.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DTOAuthor implements Serializable {
	
	// ======================================
	// =             Attributes             =
	// ======================================
	
	private static final long serialVersionUID = 1;
	private Integer uid;
	private String civility;
	private Character gender;
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String zipCode;
	private String city;
	private String country;
	private Date majDate;
	private Set<DTOBook> books = new HashSet<DTOBook>();
	
	// ======================================
	// =            Constructors            =
	// ======================================
	
	public DTOAuthor() {
	}

	public DTOAuthor(String firstName, String lastName, Date majDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.majDate = majDate;
	}

	public DTOAuthor(String civility, Character gender, String firstName, String lastName, String address1,
			String address2, String zipCode, String city, String country, Date majDate, Set<DTOBook> books) {
		this.civility = civility;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.majDate = majDate;
		this.books = books;
	}

	public DTOAuthor(Integer uid, String civility, Character gender, String firstName, String lastName, String address1,
			String address2, String zipCode, String city, String country, Date majDate, Set<DTOBook> books) {
		this.uid = uid;
		this.civility = civility;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.majDate = majDate;
		this.books = books;
	}

	// ======================================
	// =          Getters & Setters         =
	// ======================================

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCivility() {
		return this.civility;
	}

	public void setCivility(String civility) {
		this.civility = civility;
	}

	public Character getGender() {
		return this.gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getMajDate() {
		return this.majDate;
	}

	public void setMajDate(Date majDate) {
		this.majDate = majDate;
	}

	public Set<DTOBook> getBooks() {
		return this.books;
	}

	public void setBooks(Set<DTOBook> books) {
		this.books = books;
	}
	
	// ======================================
	// =         hash, equals, toString     =
	// ======================================
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? super.hashCode() : uid.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOAuthor other = (DTOAuthor) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DTOAuthor [uid=" + uid + ", first name=" + firstName + ", last name=" + lastName + "]";
	}

}
