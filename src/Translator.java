import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

/**
 * Die Klasse <code>Translator</code>
 */
public class Translator {

	private static final Map<String, String> TO_LATIN = new HashMap<>();
	private static final Map<String, String> TO_SZKLY = new HashMap<>();

	/**
	 * Hier in diesem static-Block werden die beiden Wörterbücher gebildet.
	 */
	static {
     //Arbeite bei der Hinübersetzung ins Lateinische mit Groß- bei der Herübersetzung mit Kleinbuchstaben. 
		TO_LATIN.put(" ", " ");
		TO_LATIN.put("\"", "?"); ////TODO
		TO_LATIN.put("?", "?");
		TO_LATIN.put(".", ".");
		TO_LATIN.put("!", "!");
		TO_LATIN.put(new String(Character.toChars(0x10C80)), "A");
		TO_LATIN.put(new String(Character.toChars(0x10C81)), "Á");
		TO_LATIN.put(new String(Character.toChars(0x10C82)), "B");
		TO_LATIN.put(new String(Character.toChars(0x10C84)), "C");
		TO_LATIN.put(new String(Character.toChars(0x10C87)), "D");
		TO_LATIN.put(new String(Character.toChars(0x10C89)), "E");
		TO_LATIN.put(new String(Character.toChars(0x10C8B)), "É");
		TO_LATIN.put(new String(Character.toChars(0x10C8C)), "F");
		TO_LATIN.put(new String(Character.toChars(0x10C8D)), "G");
		TO_LATIN.put(new String(Character.toChars(0x10CCF)), "H");
		TO_LATIN.put(new String(Character.toChars(0x10C90)), "I");
		TO_LATIN.put(new String(Character.toChars(0x10C91)), "Í");
		TO_LATIN.put(new String(Character.toChars(0x10C92)), "J");
		TO_LATIN.put(new String(Character.toChars(0x10C93)), "K");
		TO_LATIN.put(new String(Character.toChars(0x10C96)), "L");
		TO_LATIN.put(new String(Character.toChars(0x10C98)), "M");
		TO_LATIN.put(new String(Character.toChars(0x10C9A)), "N");
		TO_LATIN.put(new String(Character.toChars(0x10C9C)), "O");
		TO_LATIN.put(new String(Character.toChars(0x10C9D)), "Ó");
		TO_LATIN.put(new String(Character.toChars(0x10CA0)), "P");
		TO_LATIN.put(new String(Character.toChars(0x10CA2)), "R");
		TO_LATIN.put(new String(Character.toChars(0x10CA4)), "S");
		TO_LATIN.put(new String(Character.toChars(0x10CA6)), "T");
		TO_LATIN.put(new String(Character.toChars(0x10CAA)), "U");
		TO_LATIN.put(new String(Character.toChars(0x10CAB)), "Ú");
		TO_LATIN.put(new String(Character.toChars(0x10CAE)), "V");
		TO_LATIN.put(new String(Character.toChars(0x10CAF)), "Z");
		TO_LATIN.put(new String(Character.toChars(0x10C9E)), "Ö");
		TO_LATIN.put(new String(Character.toChars(0x10CAC)), "Ü");
		TO_LATIN.put(new String(Character.toChars(0x10C9F)), "Ô");
		TO_LATIN.put(new String(Character.toChars(0x10CAD)), "Û");
		TO_LATIN.put(new String(Character.toChars(0x10C9F)), "Ő");
		TO_LATIN.put(new String(Character.toChars(0x10CAD)), "Ű");
		TO_LATIN.put(new String(Character.toChars(0x10C86)), "CS");
		TO_LATIN.put(new String(Character.toChars(0x10C8E)), "GY");
		TO_LATIN.put(new String(Character.toChars(0x10C97)), "LY");
		TO_LATIN.put(new String(Character.toChars(0x10C9B)), "NY");
		TO_LATIN.put(new String(Character.toChars(0x10CA8)), "TY");
		TO_LATIN.put(new String(Character.toChars(0x10CA5)), "SZ");
		String szkCSCS = new String(Character.toChars(0x10C86)) + new String(Character.toChars(0x10C86));
		TO_LATIN.put(szkCSCS, "CCS");
		String szkGYGY = new String(Character.toChars(0x10C8E)) + new String(Character.toChars(0x10C8E));
		TO_LATIN.put(szkGYGY, "GGY");
		String szkLYLY = new String(Character.toChars(0x10C97)) + new String(Character.toChars(0x10C97));
		TO_LATIN.put(szkLYLY, "LLY");
		String szkNYNY = new String(Character.toChars(0x10C9B)) + new String(Character.toChars(0x10C9B));
		TO_LATIN.put(szkNYNY, "NNY");
		String szkTYTY = new String(Character.toChars(0x10CA8)) + new String(Character.toChars(0x10CA8));
		TO_LATIN.put(szkTYTY, "TTY");
		String szkSZSZ = new String(Character.toChars(0x10CA5)) + new String(Character.toChars(0x10CA5));
		TO_LATIN.put(szkSZSZ, "SSZ");

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		TO_SZKLY.put(" ", " ");
		TO_SZKLY.put("\"", "?"); ////TODO
		TO_SZKLY.put("?", "?");
		TO_SZKLY.put(".", ".");
		TO_SZKLY.put("!", "!");
		TO_SZKLY.put("a", new String(Character.toChars(0x10C80)));
		TO_SZKLY.put("á", new String(Character.toChars(0x10C81)));
		TO_SZKLY.put("b", new String(Character.toChars(0x10C82)));
		TO_SZKLY.put("c", new String(Character.toChars(0x10C84)));
		TO_SZKLY.put("cs", new String(Character.toChars(0x10C86)));
		TO_SZKLY.put("ccs", new String(Character.toChars(0x10C86)) + new String(Character.toChars(0x10C86)));
		TO_SZKLY.put("d", new String(Character.toChars(0x10C87)));
		TO_SZKLY.put("e", new String(Character.toChars(0x10C89)));
		TO_SZKLY.put("é", new String(Character.toChars(0x10C8B)));
		TO_SZKLY.put("f", new String(Character.toChars(0x10C8C)));
		TO_SZKLY.put("g", new String(Character.toChars(0x10C8D)));
		TO_SZKLY.put("h", new String(Character.toChars(0x10CCF)));
		TO_SZKLY.put("gy", new String(Character.toChars(0x10C8E)));
		TO_SZKLY.put("ggy", new String(Character.toChars(0x10C8E)) + new String(Character.toChars(0x10C8E)));
		TO_SZKLY.put("i", new String(Character.toChars(0x10C90)));
		TO_SZKLY.put("í", new String(Character.toChars(0x10C91)));
		TO_SZKLY.put("j", new String(Character.toChars(0x10C92)));
		TO_SZKLY.put("k", new String(Character.toChars(0x10C93)));
		TO_SZKLY.put("l", new String(Character.toChars(0x10C96)));
		TO_SZKLY.put("m", new String(Character.toChars(0x10C98)));
		TO_SZKLY.put("n", new String(Character.toChars(0x10C9A)));
		TO_SZKLY.put("ny", new String(Character.toChars(0x10C9B)));
		TO_SZKLY.put("nny", new String(Character.toChars(0x10C9B)) + new String(Character.toChars(0x10C9B)));
		TO_SZKLY.put("o", new String(Character.toChars(0x10C9C)));
		TO_SZKLY.put("ó", new String(Character.toChars(0x10C9D)));
		TO_SZKLY.put("ö", new String(Character.toChars(0x10C9E)));
		TO_SZKLY.put("ô", new String(Character.toChars(0x10C9F)));
		TO_SZKLY.put("ő", new String(Character.toChars(0x10C9F)));
		TO_SZKLY.put("p", new String(Character.toChars(0x10CA0)));
		TO_SZKLY.put("r", new String(Character.toChars(0x10CA2)));
		TO_SZKLY.put("s", new String(Character.toChars(0x10CA4)));
		TO_SZKLY.put("sz", new String(Character.toChars(0x10CA5)));
		TO_SZKLY.put("ssz", new String(Character.toChars(0x10CA5)) + new String(Character.toChars(0x10CA5)));
		TO_SZKLY.put("t", new String(Character.toChars(0x10CA6)));
		TO_SZKLY.put("ty", new String(Character.toChars(0x10CA8)));
		TO_SZKLY.put("tty", new String(Character.toChars(0x10CA8)) + new String(Character.toChars(0x10CA8)));
		TO_SZKLY.put("u", new String(Character.toChars(0x10CAA)));
		TO_SZKLY.put("ú", new String(Character.toChars(0x10CAB)));
		TO_SZKLY.put("ü", new String(Character.toChars(0x10CAC)));
		TO_SZKLY.put("û", new String(Character.toChars(0x10CAD)));
		TO_SZKLY.put("ű", new String(Character.toChars(0x10CAD)));
		TO_SZKLY.put("v", new String(Character.toChars(0x10CAE)));
		TO_SZKLY.put("z", new String(Character.toChars(0x10CAF)));
		TO_SZKLY.put("zs", new String(Character.toChars(0x10CB0)));
		TO_SZKLY.put("ly", new String(Character.toChars(0x10C97)));
		TO_SZKLY.put("lly", new String(Character.toChars(0x10C97)) + new String(Character.toChars(0x10C97)));

	}

	/**
	 * Die Klasse <code> translateToOldHungarianEntireText</code> übersetzt den
	 * gesamten Text in altungar. Schrift.
	 * 
	 * @param text: der zu übersetzendeText
	 */
	public static StringBuilder translateToOldHungarianEntireText(StringBuilder text) {

		String input = text.toString();

		input = input.replaceAll("\\s+", " ");

		input = input.toLowerCase();

		StringBuilder result = new StringBuilder();

		int i = 0;

		translatorLatinRepeatedLetters(input, result, i);
		return result;
	}

	/**
	 * Die Klasse <code>translateToLatinEntireText</code> übersetzt den gesamten
	 * Text in lateinische Schrift.
	 * 
	 * @param text: der zu übersetzendeText
	 */
	public static StringBuilder translateToLatinEntireText(StringBuilder text) {

		String input = text.toString();

		input = input.replaceAll("\\s+", " ");

		StringBuilder result = new StringBuilder();

		int i = 0;
		while (i < input.length()) {
			int codePoint = input.codePointAt(i);
			String rune = new String(Character.toChars(codePoint));

			result.append(TO_LATIN.getOrDefault(rune, rune));

			i += Character.charCount(codePoint);
		}

		return result;

	}

	public static String translateToSzkly(String c) {

		return TO_SZKLY.getOrDefault(c, "");

	}

	/**
	 * Die Methode <code> translatorLatinRepeatedLetters</code> ist für die korrekte
	 * Übersetzung der Doppelbuchstaben in der Herübersetzung aus Runen zuständig.
	 * 
	 * @param input
	 * @param result
	 * @param i
	 */
	private static void translatorLatinRepeatedLetters(String input, StringBuilder result, int i) {
		while (i < input.length()) {
			if (i + 2 < input.length() && TO_SZKLY.containsKey(input.substring(i, i + 3))) {
				result.append(TO_SZKLY.get(input.substring(i, i + 3)));
				i += 3;
			} else if (i + 1 < input.length() && TO_SZKLY.containsKey(input.substring(i, i + 2))) {
				result.append(TO_SZKLY.get(input.substring(i, i + 2)));
				i += 2;
			} else {
				char letter = input.charAt(i);
				result.append(TO_SZKLY.getOrDefault(String.valueOf(letter), String.valueOf(letter)));
				i += 1;
			}
		}
	}

	/**
	 * Die Methode <code>translatorOldHungarian</code> ist für die korrekte
	 * Übersetzung der Doppelbuchstaben in der Hinübersetzung in Runen zuständig.
	 * 
	 * @param sb
	 * @param sbLatin
	 * @param topTextArea
	 * @param bottomTextArea
	 */
	static void translatorOldHungarianRepeatedLetters(StringBuilder sb, StringBuilder sbLatin, JTextArea topTextArea,
			JTextArea bottomTextArea) {

		String entry = bottomTextArea.getText().toLowerCase();

		char[] charArray = entry.toCharArray();

		if (charArray.length == 0) {
			return;
		}

		sbLatin.append(entry);

		if (sbLatin.length() >= 2 && sbLatin.substring(sbLatin.length() - 2).equals("cs")
				&& (!(sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("ccs")))) {

			sb.append(Translator.translateToSzkly("cs"));

			// Rune als String bestimmen
			String runeToRemove = new String(Character.toChars(0x10C84));

			// Letzten Index finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));

		}

		else if (sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("ccs")) {
			// Rune für "ccs" einfügen
			sb.append(Translator.translateToSzkly("ccs"));

			// Rune als String bestimmen
			String runeToRemove = new String(Character.toChars(0x10C84)) + new String(Character.toChars(0x10C84));

			// Letzten Index von 𐲄 finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));
		}

		else if (sbLatin.length() >= 2 && sbLatin.substring(sbLatin.length() - 2).equals("ly")
				&& (!(sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("lly")))) {

			sb.append(Translator.translateToSzkly("ly"));

			String runeToRemove = new String(Character.toChars(0x10C96));

			// Letzten Index finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));

		}

		else if (sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("lly")) {
			// Rune für "lly" einfügen
			sb.append(Translator.translateToSzkly("lly"));

			// Rune als String bestimmen
			String runeToRemove = new String(Character.toChars(0x10C96)) + new String(Character.toChars(0x10C96));

			// Letzten Index von 𐲄 finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));
		}

		else if (sbLatin.length() >= 2 && sbLatin.substring(sbLatin.length() - 2).equals("gy")
				&& (!(sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("ggy")))) {

			sb.append(Translator.translateToSzkly("gy"));

			String runeToRemove = new String(Character.toChars(0x10C8D));

			// Letzten Index finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));

		}

		else if (sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("ggy")) {
			// Rune für "ggy" einfügen
			sb.append(Translator.translateToSzkly("ggy"));

			// Rune als String bestimmen
			String runeToRemove = new String(Character.toChars(0x10C8D)) + new String(Character.toChars(0x10C8D));

			// Letzten Index von finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));
		}

		else if (sbLatin.length() >= 2 && sbLatin.substring(sbLatin.length() - 2).equals("ny")
				&& (!(sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("nny")))) {

			sb.append(Translator.translateToSzkly("ny"));

			String runeToRemove = new String(Character.toChars(0x10C9A));

			// Letzten Index finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));

		}

		else if (sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("nny")) {
			// Rune für "nny" einfügen
			sb.append(Translator.translateToSzkly("nny"));

			// Rune als String bestimmen
			String runeToRemove = new String(Character.toChars(0x10C9A)) + new String(Character.toChars(0x10C9A));

			// Letzten Index von finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));
		}

		else if (sbLatin.length() >= 2 && sbLatin.substring(sbLatin.length() - 2).equals("sz")
				&& (!(sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("ssz")))) {

			sb.append(Translator.translateToSzkly("sz"));

			String runeToRemove = new String(Character.toChars(0x10CA4));

			// Letzten Index finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));

		}

		else if (sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("ssz")) {
			// Rune für "ssz" einfügen
			sb.append(Translator.translateToSzkly("ssz"));

			// Rune als String bestimmen
			String runeToRemove = new String(Character.toChars(0x10CA4)) + new String(Character.toChars(0x10CA4));

			// Letzten Index von finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));
		}

		else if (sbLatin.length() >= 2 && sbLatin.substring(sbLatin.length() - 2).equals("zs")
				&& (!(sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("zzs")))) {

			sb.append(Translator.translateToSzkly("zs"));

			String runeToRemove = new String(Character.toChars(0x10CAF));

			// Letzten Index finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));

		}

		else if (sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("zzs")) {
			// Rune für "zzs" einfügen
			sb.append(Translator.translateToSzkly("zzs"));

			// Rune als String bestimmen
			String runeToRemove = new String(Character.toChars(0x10CB0)) + new String(Character.toChars(0x10CB0));

			// Letzten Index von finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));
		}

		else if (sbLatin.length() >= 2 && sbLatin.substring(sbLatin.length() - 2).equals("ty")
				&& (!(sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("tty")))) {

			sb.append(Translator.translateToSzkly("ty"));

			String runeToRemove = new String(Character.toChars(0x10CA6));

			// Letzten Index finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));

		}

		else if (sbLatin.length() >= 3 && sbLatin.substring(sbLatin.length() - 3).equals("tty")) {
			// Rune für "tty" einfügen
			sb.append(Translator.translateToSzkly("tty"));

			// Rune als String bestimmen
			String runeToRemove = new String(Character.toChars(0x10CA6)) + new String(Character.toChars(0x10CA6));

			// Letzten Index von finden
			int index = sb.lastIndexOf(runeToRemove);

			// Falls gefunden, entfernen
			if (index != -1) {
				sb.delete(index, index + runeToRemove.length());
			}

			topTextArea.setText(punctuation(sb.toString()));
		}

		else {

			sb.append(Translator.translateToSzkly(entry));

			topTextArea.setText(punctuation(sb.toString()));

		} 
	}

	private static String punctuation(String str) {

	   ////TODO
		if(str.endsWith("?") || str.endsWith(".") || str.endsWith("!")) {
			    
			   if (str == null || str.length() <= 1) {
		            return str;
		        }
		        // Letztes Zeichen extrahieren
		        char lastChar = str.charAt(str.length() - 1);
		        // Neues String mit dem letzten Zeichen vorne
		        return lastChar + str.substring(0, str.length() - 1);
			     
 
			}
	
		 	return str;
	}

}