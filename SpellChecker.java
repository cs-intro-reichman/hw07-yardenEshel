
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if(str.length() == 1)
		{
			return "";
		}
		else
		{
			return str.substring(1);
		}
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.isEmpty()) {
			return word2.length();
		}
		else if(word2.isEmpty())
		{
			return word1.length();
		}
		else if(word1.charAt(0) == word2.charAt(0))
		{
			return levenshtein(tail(word1), tail(word2));
		}
		else
		{

			return 1 + Math.min(levenshtein(tail(word1), word2),Math.min(levenshtein(tail(word2), word1),levenshtein(tail(word1), tail(word2))) );
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for(int i=0;i<3000;i++)
		{
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase();
		int leastChanges = threshold;
		int current = 0;
		String leastString = "";
		for(int i =0;i<dictionary.length;i++)
		{
			current = levenshtein(dictionary[i], word);
			if (current<leastChanges) {
				leastChanges = current;
				leastString = dictionary[i];
			}
		}
		if (leastString.isEmpty()) {
			return word;
		}
		return leastString;
		
	}

}
