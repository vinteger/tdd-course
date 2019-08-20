public class StringKata {
    public String startWord(String str, String word) {

        String result = "";

        if (str.equals("")) {
            return result;
        }

        int wordLength = word.length();
        String matcher = str.substring(0, wordLength);

        if(matcher.equals(word)) {
            result = word;
        }

        return result;
    }
}
