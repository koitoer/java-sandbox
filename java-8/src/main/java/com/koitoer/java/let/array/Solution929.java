package com.koitoer.java.let.array;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 929. Unique Email Addresses
 * Using split to modify the first part but not the other, but also take care of regular expression \\."
 */
public class Solution929 {

    @Test
    public void test() {
        String[] emails = new String[] { "test.email+alex@leetcode.com", "test.email@leetcode.com" };
        Assertions.assertThat(new Solution929().numUniqueEmails(emails)).isEqualTo(1);


        int i;
        for (i = 0; i < 10; i++) {}
        int x = i;
        System.out.println(x);
    }

    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet();
        for (String email : emails) {
            StringBuffer sb = new StringBuffer();
            char[] ar = email.toCharArray();
            boolean second = false;
            for (int i = 0; i < email.length(); i++) {
                if (second) {
                    sb.append(ar[i]);
                } else {
                    if (ar[i] == '.') {
                        continue;
                    } else if (ar[i] == '+') {
                        while (ar[i] != '@') {
                            i++;
                            second = true;
                        }
                    } else if (ar[i] == '@') {
                        second = true;
                    }
                    sb.append(ar[i]);
                }
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    public int numUniqueEmails2(String[] emails) {
        Set<String> set = new HashSet();
        for (String email : emails) {
            String[] splits = email.split("@");
            String sanitizeEmail = sanitize(splits[0]);
            set.add(sanitizeEmail + splits[1]);
        }
        return set.size();
    }

    private String sanitize(String email) {
        String email2 = email.replaceAll("\\.", "");
        String emailWithPlus = email2.split("\\+")[0];
        if (emailWithPlus != null) {
            return emailWithPlus + "@";
        }
        return email2 + "@";
    }
}

