package com.koitoer.java.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 */
public class Solution93 {

    public static void main(String[] args) {
        new Solution93().restoreIpAddresses("25525511135");
        Assertions.assertThat(new Solution93().restoreIpAddresses("0000")).containsExactly("0.0.0.0");
        Assertions.assertThat(new Solution93().restoreIpAddresses("101023"))
            .containsExactly("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3");
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList();
        if (s.length() > 12) {
            return new ArrayList();
        }

        backtrack(s, 0, 0, "", result);
        return result;
    }

    private void backtrack(String original, int index, int points, String ip, List<String> result) {

        if (points == 4 && index == original.length()) {
            result.add(ip.substring(0, ip.length() - 1));
            return;
        }

        if (points > 4) {
            return;
        }

        //System.out.println("ip=" + ip + " indexp=" + index + " point=" + points);

        for (int j = index; j <= Math.min(index + 2, original.length() - 1); j++) {
            String aux = original.substring(index, j + 1);
            boolean isZeroInFront = aux.length() > 1 && aux.startsWith("0");
            if (Integer.parseInt(aux) <= 255 && !isZeroInFront) {
                backtrack(original, j + 1, points + 1, ip + aux + ".", result);
            }
        }
    }

}
