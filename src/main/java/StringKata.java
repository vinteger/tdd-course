public class StringKata {
    public String startWord(String str, String word) {

        String result = "";
        String wordIgnoringFirstChar = word.substring(1);
        int wordLength = word.length();

        if (str.equals("") || wordLength > str.length()) {
            return result;
        }

        String matcher = str.substring(1, wordLength);

        if(matcher.equals(wordIgnoringFirstChar)) {
            result = str.substring(0, wordLength);
        }

        return result;
    }
}
