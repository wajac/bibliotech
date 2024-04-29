package com.wajac.bibliotech.utils;

public class Utils {
	public static boolean isSet(Object parameter) {
		if (parameter == null)
			return false;
		if (parameter instanceof String) {
			if (((String) parameter).trim().length() == 0)
				return false;
			return true;
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isDifferent(Object object1, Object object2) {
		if ((object1 == null && object2 != null) || (object1 != null && object2 == null)) {
			return true;
		}
		if (object1 == null && object2 == null) {
			return false;
		}
		if (!object1.getClass().equals(object2.getClass())) {
			return true;
		}
		if (object1 instanceof Comparable) {
			return ((Comparable) object1).compareTo((Comparable) object2) == 0 ? false : true;
		}
		return false;
	}
}
