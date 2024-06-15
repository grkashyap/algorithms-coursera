/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {

    private Point[] points;
    private int numSegments;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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

        List<LineSegment> lineSegmentsList = new LinkedList<>();
        Arrays.sort(this.points);
        int numPoints = this.points.length;

        for (int p = 0; p < numPoints - 3; p++) {
            for (int q = p + 1; q < numPoints - 2; q++) {
                for (int r = q + 1; r < numPoints - 1; r++) {
                    for (int s = r + 1; s < numPoints; s++) {
                        if (this.points[p].slopeTo(this.points[q]) == this.points[q].slopeTo(
                                this.points[r])
                                && this.points[q].slopeTo(this.points[r]) == this.points[r].slopeTo(
                                this.points[s])) {
                            lineSegmentsList.add(new LineSegment(this.points[p], this.points[s]));
                        }
                    }
                }
            }
        }

        numSegments = lineSegmentsList.size();
        lineSegments = lineSegmentsList.toArray(new LineSegment[numSegments]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return numSegments;
    }

    // the line segment
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, numberOfSegments());
    }

    public static void main(String[] args) {

        /* try {
            new BruteCollinearPoints(null);
        }
        catch (IllegalArgumentException e) {
            StdOut.println("Excepted exception");
        }

        Point[] points = new Point[2];
        points[0] = new Point(2, 2);
        points[1] = null;

        try {
            new BruteCollinearPoints(points);
        }
        catch (IllegalArgumentException e) {
            StdOut.println("Excepted exception");
        }

        Point[] points2 = new Point[2];
        points2[0] = new Point(2, 2);
        points2[1] = new Point(2, 2);

        try {
            new BruteCollinearPoints(points2);
        }
        catch (IllegalArgumentException e) {
            StdOut.println("Excepted exception");
        }

        Point[] points3 = new Point[2];
        points3[0] = new Point(2, 2);
        points3[1] = new Point(4, 4);

        try {
            new BruteCollinearPoints(points3);
        }
        catch (IllegalArgumentException e) {
            StdOut.println("Unexpected");
        } */

        Point[] points2 = new Point[3];
        points2[0] = new Point(2, 2);
        points2[1] = new Point(5, 5);
        points2[2] = new Point(3, 3);

        new BruteCollinearPoints(points2);

        // read the n points from a file
        /*In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            // StdOut.println(points[i].toString());
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();*/
    }
}
