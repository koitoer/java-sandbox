package com.koitoer.java.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by koitoer on 8/14/16.
 */
public class Convex {

      static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

          @Override
          public String toString() {
              return "Point{" +
                      "x=" + x +
                      ", y=" + y +
                      '}';
          }
      }

    public static void main(String[] args) {
        Point points[] = {new Point(0, 3), new Point(2,2), new Point(1,1), new Point(2,1),
                    new Point(3,0), new Point(0,0), new Point(3,3)};
        int size = points.length;
        convexHull(points, size);
    }

    /**
     * Time complexity is  O(m*n)
     * m number of points in the hull
     * n number of total points
     * Worst case scenario is O(n^2) when all the points are in hull
     * @param points
     * @param n
     */
    private static void convexHull(Point[] points, int n) {

        //At least three points to close
        if(n<3)return;;

        List<Point> resultList = new ArrayList<>();

        //We need to start for the leftmost point, iterate and find it
        int l  = 0;
        for (int i = 1; i < n; i++) {
            if (points[i].x < points[l].x) {
                l = i;
            }
        }

        //Keep reference for leftmost
        int p = l, q;
        do
        {
            // Add current point to result, initial is leftmost then move on.
            resultList.add(points[p]);


            q = (p+1)%n;
            for (int i = 0; i < n; i++) {

                //Index p is current leftmost,
                //Index i is from 0 to N
                //Index q is the new leftmost
                if (orientation(points[p], points[i], points[q]) == 2)
                    q = i;
            }

            // Now q is the most counterclockwise with respect to p
            // Set p as q for next iteration, so that q is added to
            // result 'hull'
            p = q;

        } while (p != l);  // While we don't come to first point


        System.out.println(resultList);
    }

    private static int orientation(Point p, Point i, Point q) {
        int val = (i.y - p.y) * (q.x - i.x) -
                (i.x - p.x) * (q.y - i.y);

        if (val == 0) return 0;  // collinear
        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    //2^16 = 2^8 * 2^8 = 65536
}
