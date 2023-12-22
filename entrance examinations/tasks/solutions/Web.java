import java.util.*;

public class Main {
    static class Point {
        int id;
        double x, y;

        public Point(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public double distanceTo(Point other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return dx * dx + dy * dy;
        }
    }

    static class Edge {
        boolean isVisited = false;
        Point start, end;
        double weight;

        public Edge(Point start, Point end) {
            this.start  = start;
            this.end    = end;
            this.weight = start.distanceTo(end);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Point> computers = new ArrayList<>();
        List<Point> servers = new ArrayList<>();

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        for (int i = 1; i <= n; ++i) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            computers.add(new Point(i, x, y));
        }

        for (int i = 1; i <= m; ++i) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            servers.add(new Point(i, x, y));
        }

        scanner.close();

        List<Edge> minimumSpanningTree = findEMST(servers);
        long connectionNumber = minimumSpanningTree.size() + computers.size();
        System.out.print(connectionNumber + " ");

        Point geometricMedian = findGeometricMedian(computers);
        Point centralServer   = findClosestPoint(geometricMedian, servers);

        double distance = 0.0;
        distance += distanceSum(centralServer, computers);
        for (Edge edge : minimumSpanningTree) {
            distance += edge.weight;
        }
        System.out.println((int) distance);

        orientMST(centralServer, minimumSpanningTree);

        for (Point computer : computers) {
            System.out.println("s " + centralServer.id + " -> c " + computer.id);
        }
    }

    public static List<Edge> findEMST(List<Point> points) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.size(); ++i) {
            for (int j = i + 1; j < points.size(); ++j) {
                Edge edge = new Edge(points.get(i), points.get(j));
                edges.add(edge);
            }
        }

        Collections.sort(edges, Comparator.comparingDouble(e -> e.weight));

        int[] parent = new int[points.size()];
        for (int i = 0; i < points.size(); ++i) {
            parent[i] = i;
        }

        List<Edge> minimumSpanningTree = new ArrayList<>();

        for (Edge edge : edges) {
            int root1 = find(parent, points.indexOf(edge.start));
            int root2 = find(parent, points.indexOf(edge.end));

            if (root1 != root2) {
                minimumSpanningTree.add(edge);
                parent[root1] = root2;
            }
        }

        return minimumSpanningTree;
    }

    private static int find(int[] parent, int node) {
        while (parent[node] != node) {
            node = parent[node];
        }

        return parent[node];
    }

    public static Point findGeometricCenter(List<Point> points) {
        Point geometricCenter = new Point(-1, 0.0, 0.0);

        for (Point point : points) {
            geometricCenter.x += point.x;
            geometricCenter.y += point.y;
        }

        geometricCenter.x /= points.size();
        geometricCenter.y /= points.size();

        return geometricCenter;
    }

    public static Point findGeometricMedian(List<Point> points) {
        Point currentMedian    = findGeometricCenter(points);
        double minimalDistanse = distanceSum(currentMedian, points);

        double eps = 0.001;
        double step = 1000.0;

        boolean isDone = false;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (step > eps) {
            isDone = false;

            // shift left, up, right, down
            for (int i = 0; i < 4; ++i) {
                Point newMedian = new Point(
                    -1,
                    currentMedian.x + step * dx[i],
                    currentMedian.y + step * dy[i]
                );

                double newDistance = distanceSum(newMedian, points);

                if (newDistance < minimalDistanse) {
                    currentMedian   = newMedian;
                    minimalDistanse = newDistance;
                    isDone = true;
                    break;
                }
            }

            if (!isDone) {
                step /= 2.0;
            }
        }

        return currentMedian;
    }

    private static double distanceSum(Point center, List<Point> points) {
        double sum = 0.0;

        for (Point point : points) {
            sum += center.distanceTo(point);
        }

        return sum;
    }

    public static Point findClosestPoint(Point targetPoint, List<Point> points) {
        Point closestPoint = points.get(0);
        double closestDistance = targetPoint.distanceTo(closestPoint);

        for (Point point : points) {
            double currentDistance = targetPoint.distanceTo(point);

            if (currentDistance < closestDistance) {
                closestPoint = point;
                closestDistance = currentDistance;
            }
        }

        return closestPoint;
    }

    public static void orientMST(Point currentServer, List<Edge> minimumSpanningTree) {
        for (Edge edge : minimumSpanningTree) {
            if (edge.isVisited) {
                continue;
            }

            Point start = edge.start;
            Point end   = edge.end;

            if (end.equals(currentServer)) {
                System.out.println("s " + start.id + " -> s " + end.id);
                edge.isVisited = true;
                orientMST(start, minimumSpanningTree);
            } else if (start.equals(currentServer)) {
                System.out.println("s " + end.id + " -> s " + start.id);
                edge.isVisited = true;
                orientMST(end, minimumSpanningTree);
            }
        }
    }
}
