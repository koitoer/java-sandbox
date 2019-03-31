package com.koitoer.training.exploringWaters;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by mmena on 2/27/18.
 */
public class AddBorder {


    String[] addBorder2(String[] picture) {
        String[] framedPicture = new String[picture.length + 2];

        for(int i = 0 ; i < picture.length ; i++) {
            framedPicture[i+1] = '*' + picture[i] + '*';
        }

        framedPicture[0] = framedPicture[picture.length + 1] = framedPicture[1].replaceAll(".","*");

        this.printArray(framedPicture);

        return framedPicture;
    }


    String[] addBorder(String[] picture) {
        int maxSize = Arrays.asList(picture).stream().max((p1, p2) -> Integer.compare(p1.length(), p2.length())).get().length();
        int longer = maxSize + 2;

        String[] result = new String[picture.length + 2];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.createString(i, result.length, longer, picture);
        }
        this.printArray(result);
        return result;
    }

    private void printArray(String[] result) {
        for (String x : result) {
            System.out.println(x);
        }
    }

    private String createString(int index, int arrayLength, int maxWordLength, String[] picture) {
        StringBuilder result = new StringBuilder();

        if (index == 0 || index == (arrayLength - 1)) {
            for (int a = 0; a < maxWordLength; a++) {
                result.append("*");
            }
        } else {
            String line = picture[index - 1];
            int extra = maxWordLength - line.length() - 1;
            result.append("*" + line);
            for (int j = 0; j < extra; j++) {
                result.append("*");
            }

        }

        return result.toString();
    }

    @Test
    public void test() {
        Assertions.assertThat(new AddBorder().addBorder2(new String[] { "wzy**", "wzy**11" }))
            .isEqualTo(new String[] { "*******", "*wzy***", "*******" });

        Assertions.assertThat(new AddBorder().addBorder(new String[] { "wzy**" }))
            .isEqualTo(new String[] { "*******", "*wzy***", "*******" });

        Assertions.assertThat(new AddBorder().addBorder(new String[] { "a" })).isEqualTo(new String[] { "***", "*a*", "***" });
    }
}
