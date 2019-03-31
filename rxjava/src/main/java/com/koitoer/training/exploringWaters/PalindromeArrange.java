package com.koitoer.training.exploringWaters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by mmena on 2/28/18.
 */
public class PalindromeArrange {

    boolean palindromeRearranging(String inputString) {
        if (inputString.length() == 1) {
            return true;
        }

        Map<Character, Integer> map = this.stringToMap(inputString);
        //Pair
        if (inputString.length() % 2 == 0) {
            List<Integer> listInteger = map.values().stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
            return listInteger.size() == 0;
        } else {
            List<Integer> listInteger = map.values().stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
            return !(listInteger.size() >= 2);
        }
    }

    private Map<Character, Integer> stringToMap(String inputString) {
        HashMap<Character, Integer> map = new HashMap();
        char[] charArray = inputString.toCharArray();
        for (char one : charArray) {
            if (map.get(one) == null) {
                map.put(one, 1);
            } else {
                map.put(one, map.get(one) + 1);
            }
        }

        return map;
    }


    boolean palindromeRearranging2(String inputString) {
        Set<Character> chars = new HashSet<Character>();

        for (int i = 0; i < inputString.length();  ++i) {
            char c = inputString.charAt(i);
            if (chars.contains(c)) {
                chars.remove(c);
            }
            else {
                chars.add(c);
            }
        }
        return chars.size() <= 1 ? true : false;
    }


    @Test
    public void test() {
        Assertions.assertThat("pppppppppp".length()).isEqualTo(10);
        Assertions.assertThat("xxxxxxxxxxaaaaaaaaaabbbbbbbbbbiiiiiiiiiipppppppppp".length()).isEqualTo(50);
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("xxxxxxxxxxaaaaaaaaaabbbbbbbbbbiiiiiiiiiipppppppppp")).isTrue();
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("z")).isTrue();
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("zaa")).isTrue();
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("abbcabb")).isTrue();
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("aaaddc")).isFalse();
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("abc")).isFalse();
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("abaab")).isTrue();
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("aaa")).isTrue();
        Assertions.assertThat(new PalindromeArrange().palindromeRearranging("bbbaaacc")).isFalse();




    }
}
