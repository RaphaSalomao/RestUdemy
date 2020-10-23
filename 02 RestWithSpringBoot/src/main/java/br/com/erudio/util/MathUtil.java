package br.com.erudio.util;

import br.com.erudio.exception.UnsupportedMathOperationException;

public class MathUtil {

	public static Double convertToDouble(String strNumber) {
		if (strNumber == null || strNumber.isEmpty() || strNumber.isBlank()) {
			return 0D;
		}
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number))
			return Double.parseDouble(number);
		return 0D;
	}

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null || strNumber.isEmpty() || strNumber.isBlank()) {
			return false;
		}
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	public static void validMathOperation(String... numbers) throws UnsupportedMathOperationException {
		for (String s : numbers) {
			if (!isNumeric(s)) {
				throw new UnsupportedMathOperationException("Please, set a numeric value.");
			}
		}
	}

}
