/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {

    private Point[] points;
    private LineSegment[] segments;
    private int segmentCount;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        this.points = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();

            for (int k = i + 1; k < points.length; k++) {
                if (points[k] == null) throw new IllegalArgumentException();
                if (points[i].compareTo(points[k]) == 0) throw new IllegalArgumentException();
            }

            this.points[i] = points[i];
        }

        this.points = points.clone();
        this.segments = new LineSegment[2];
        this.segmentCount = 0;
        LinkedList<Point> collinearPoints = new LinkedList<Point>();

        // Arrays.sort(this.points);
        // check to see if argument matches constraints
        for (Point point : this.points) {
            Arrays.sort(this.points, point.slopeOrder());
            double prevSlope = 0.0;

            for (int j = 0; j < this.points.length; j++) {
                double currentSlope = point.slopeTo(this.points[j]);
                if (j == 0 || currentSlope != prevSlope) {

                    if (collinearPoints.size() >= 3) {
                        // Collections.sort(collinearPoints);
                        this.enqueue(new LineSegment(collinearPoints.getFirst(),
                                                     collinearPoints.getLast()));
                        collinearPoints.getFirst().drawTo(collinearPoints.getLast());
                    }

                    collinearPoints.clear();
                }

                collinearPoints.add(this.points[j]);
                prevSlope = currentSlope;
            }
        }
    }

    private void resize(int capacity) {
        // textbook implementation
        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(this.segments, 0, temp, 0, this.segmentCount);
        this.segments = temp;
    }

    /**
     * @description add the item
     */
    private void enqueue(LineSegment item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (this.segmentCount == this.segments.length) {
            resize(2 * this.segments.length);
        }

        this.segments[this.segmentCount++] = item;
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segmentCount;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, this.segmentCount);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
