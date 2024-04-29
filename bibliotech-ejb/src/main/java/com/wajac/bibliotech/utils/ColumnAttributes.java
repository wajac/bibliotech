package com.wajac.bibliotech.utils;

import java.io.Serializable;
import java.lang.reflect.Method;

public class ColumnAttributes implements Serializable {
	public static final long serialVersionUID = 1;
	private String prefix;
	private String name;
	private Class<?> columnClass;
	private boolean nullable;
	private long length;
	private Method readMethod;
	private Method writeMethod;

	public ColumnAttributes(Class<?> clazz) {
		char[] chars = clazz.getSimpleName().toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		this.prefix = new String(chars);
	}

	public ColumnAttributes(String name, Class<?> columnClass, boolean nullable, long length, 
			Method readMethod, Method writeMethod) {
		char[] chars = columnClass.getSimpleName().toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		this.prefix = new String(chars);
		this.name = name;
		this.columnClass = columnClass;
		this.nullable = nullable;
		this.length = length;
		this.readMethod = readMethod;
		this.writeMethod = writeMethod;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogicalName() {
		return prefix + "." + name;
	}

	public Class<?> getColumnClass() {
		return columnClass;
	}

	public void setColumnClass(Class<?> columnClass) {
		this.columnClass = columnClass;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public Method getReadMethod() {
		return readMethod;
	}

	public void setReadMethod(Method readMethod) {
		this.readMethod = readMethod;
	}

	public Method getWriteMethod() {
		return writeMethod;
	}

	public void setWriteMethod(Method writeMethod) {
		this.writeMethod = writeMethod;
	}
}
