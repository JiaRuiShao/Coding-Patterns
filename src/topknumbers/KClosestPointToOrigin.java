/*
 * Given an array of points in a 2D plane, find ‘K’ closest points to the origin.
 */

package topknumbers;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getDistanceFromOrigin() {
        return x * x + y * y;
    }
}

public class KClosestPointToOrigin {

    /**
     * Time: O((n+k)logk)
     * Space: O(k)
     *
     * @param points the given Point arr
     * @param k k
     * @return the k points that closest to the origin
     */
    List<Point> findClosestPoints(Point[] points, int k) {
        List<Point> closestPoints = new LinkedList<>();
        if (points == null || points.length < k) return closestPoints;

        // create a maxHeap to store the closest k points to the origin
        Queue<Point> maxHeap = new PriorityQueue<>(k, (a, b) -> Integer.compare(b.getDistanceFromOrigin(), a.getDistanceFromOrigin()));
        for (Point p : points) {
            if (maxHeap.size() < k) {
                maxHeap.offer(p);
            } else if (p.getDistanceFromOrigin() < maxHeap.peek().getDistanceFromOrigin()) {
                maxHeap.poll();
                maxHeap.offer(p);
            }
        }

        while (!maxHeap.isEmpty()) {
            closestPoints.add(0, maxHeap.poll());
        }

        return closestPoints;
    }

    public static void main(String[] args) {
        Point[] points = new Point[] { new Point(1, 3), new Point(3, 4), new Point(2, -1) };
        List<Point> result = new KClosestPointToOrigin().findClosestPoints(points, 2);
        System.out.print("Here are the k points closest the origin: ");
        for (Point p : result) System.out.print("[" + p.x + " , " + p.y + "] ");
    }
}
