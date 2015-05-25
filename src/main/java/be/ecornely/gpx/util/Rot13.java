package be.ecornely.gpx.util;

public class Rot13 {

	public static String applyTo(String from) {
		StringBuilder converted = new StringBuilder();
		for (char c : from.toCharArray()) {
			if ((c >= 65 && c < 78) || (c >= 97 && c < 110)) {
				converted.append((char) (c + 13));
			} else if ((c >= 78 && c <= 90) || (c >= 110 && c <= 122)) {
				converted.append((char)(c - 13));
			} else {
				converted.append((char)c);
			}
		}
		return converted.toString();
	}

}
