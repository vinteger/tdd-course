import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StartWordTest {

    /**
     * Given a string and a second "word" string, we'll say that the word matches the string if it appears at the front
     * of the string, except its first char does not need to match exactly. On a match, return the front of the string,
     * or otherwise return the empty string. So, so with the string "hippo" the word "hi" returns "hi" and "xip"
     * returns "hip". The word will be at least length 1.
     *
     *
     * startWord("hippo", "hi") → "hi"
     * startWord("hippo", "xip") → "hip"
     * startWord("hippo", "i") → "h"
     */
    StringKata kata;

    @Before
    public void setUp() {
        kata = new StringKata();
    }

    @Test
    public void return_emptyString_with_noMatch() {
        assertThat(kata.startWord("", "abc")).isEqualTo("");
    }

    @Test
    public void return_hi_with_hippo() {
        assertThat(kata.startWord("hippo", "hi")).isEqualTo("hi");
    }
}
