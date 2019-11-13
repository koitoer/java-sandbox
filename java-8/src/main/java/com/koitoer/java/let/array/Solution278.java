package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 278. First Bad Version
 * We need to use binary search to make this not fail due to timeout.
 */
public class Solution278 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution278().firstBadVersion(2048)).isEqualTo(1024);
        Assertions.assertThat(new Solution278().firstBadVersion(2000)).isEqualTo(1024);
    }

    boolean isBadVersion(int version) {
        if (version >= 1024) {
            return true;
        }
        return false;
    }

    public int firstBadVersion(int n) {
        return lookForBad(0, n);
    }

    public int lookForBad(int start, int end) {
        int middle = start + (end - start) / 2;
        if (isBadVersion(middle)) {
            if (middleIsCorrect(middle)) {
                return middle;
            }
            return lookForBad(start, middle - 1);
        } else {
            if (middleIsCorrect(middle)) {
                return middle + 1;
            }
            return lookForBad(middle + 1, end);
        }
    }

    public boolean middleIsCorrect(int val) {
        if (isBadVersion(val - 1) != isBadVersion(val)) {
            return true;
        } else if (isBadVersion(val) != isBadVersion(val + 1)) {
            return true;
        }
        return false;
    }
}

