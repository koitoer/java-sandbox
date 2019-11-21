package com.koitoer.java.let.minheap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Reference to the merge intervals problem.
 */
public class MeetingRooms2 {

    @Test
    public void test() {
        Interval i1 = new Interval(0, 30);
        Interval i2 = new Interval(5, 10);
        Interval i3 = new Interval(15, 20);
        Interval[] a = new Interval[] { i1, i2, i3 };

        Assertions.assertThat(new MeetingRooms2().minMeetingRooms(a)).isEqualTo(2);
    }


    @Test
    public void test2() {
        Interval i1 = new Interval(0, 17);
        Interval i2 = new Interval(5, 10);
        Interval i3 = new Interval(5, 10);
        Interval i4 = new Interval(9, 11);
        Interval i5 = new Interval(15, 16);
        Interval[] a = new Interval[] { i1, i2, i3, i4, i5};
        Assertions.assertThat(new MeetingRooms2().minMeetingRooms(a)).isEqualTo(4);
    }


    @Test
    public void test3() {
        Interval i2 = new Interval(5, 10);
        Interval i3 = new Interval(5, 10);
        Interval i4 = new Interval(0, 4);
        Interval i5 = new Interval(11, 12);
        Interval[] a = new Interval[] { i2, i3, i4, i5};
        Assertions.assertThat(new MeetingRooms2().minMeetingRooms(a)).isEqualTo(2);
    }

    public int minMeetingRooms(Interval[] intervals) {

        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(Interval::getStart));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(intervals[0].getEnd());

        for (int i = 1; i < intervals.length; i++) {
            Interval current = intervals[i];
            if (current.getStart() >= priorityQueue.peek()) {
                priorityQueue.remove();
            }
            priorityQueue.add(current.getEnd());
        }

        return priorityQueue.size();
    }

}

@Data
@RequiredArgsConstructor
class Interval {

    private final int start;

    private final int end;
}
