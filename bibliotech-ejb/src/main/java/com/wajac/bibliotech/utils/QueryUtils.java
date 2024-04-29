package com.wajac.bibliotech.utils;

import javax.persistence.Query;

public class QueryUtils {
	public static String replaceJoker(String value) {
		if (value == null || value.trim().length() == 0)
			return null;
		
		value = value.replaceAll("\\*", "%");
		
		if (value.indexOf("%") == -1) {
			value = "%" + value + "%";
		}
		return value;
	}

	public static void addQueryParameter(StringBuilder sb, String column, String parameterName, String relation,
			String operation, Object value) {
		if (value == null)
			return;
		
		if (value instanceof String) {
			if (((String) value).trim().length() == 0)
				return;
		}
		
		sb.append(" ").append(relation).append(" ").append(column).append(" ").append(operation).append(" :")
				.append(parameterName);
	}

	public static void setQueryParameter(Query query, String parameterName, Object value) {
		if (value == null)
			return;
		
		if (value instanceof String) {
			if (((String) value).trim().length() == 0)
				return;
		}
		
		query.setParameter(parameterName, value);
	}
}
