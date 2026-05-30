import java.util.*;

public class MetroMST {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x)
            return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    static void kruskal(List<Edge> edges, int V) {

        parent = new int[V];

        for (int i = 0; i < V; i++)
            parent[i] = i;

        Collections.sort(edges,
                (a, b) -> a.weight - b.weight);

        int totalCost = 0;

        System.out.println("===== KRUSKAL MST =====");

        for (Edge e : edges) {

            if (find(e.src) != find(e.dest)) {

                union(e.src, e.dest);

                totalCost += e.weight;

                System.out.println(
                        e.src + " - " +
                        e.dest + " : " +
                        e.weight);
            }
        }

        System.out.println(
                "Total Cost = " +
                        totalCost);
    }

    static void prim(int[][] graph, int V) {

        boolean[] selected =
                new boolean[V];

        selected[0] = true;

        int totalCost = 0;

        System.out.println(
                "\n===== PRIM MST =====");

        for (int edge = 0;
             edge < V - 1;
             edge++) {

            int min =
                    Integer.MAX_VALUE;

            int x = 0;
            int y = 0;

            for (int i = 0;
                 i < V;
                 i++) {

                if (selected[i]) {

                    for (int j = 0;
                         j < V;
                         j++) {

                        if (!selected[j]
                                && graph[i][j] != 0
                                && graph[i][j] < min) {

                            min = graph[i][j];

                            x = i;
                            y = j;
                        }
                    }
                }
            }

            System.out.println(
                    x + " - " +
                            y + " : " +
                            min);

            totalCost += min;

            selected[y] = true;
        }

        System.out.println(
                "Total Cost = " +
                        totalCost);
    }

    public static void main(String[] args) {

        System.out.println(
                "==================================");
        System.out.println(
                " Metro Rail Network Optimization");
        System.out.println(
                "==================================");

        String[] stations = {
                "Central",
                "Airport",
                "IT Park",
                "Railway Station",
                "Bus Stand"
        };

        List<Edge> edges =
                new ArrayList<>();

        edges.add(new Edge(0,1,4));
        edges.add(new Edge(0,2,2));
        edges.add(new Edge(1,2,1));
        edges.add(new Edge(1,3,5));
        edges.add(new Edge(2,3,8));
        edges.add(new Edge(2,4,10));
        edges.add(new Edge(3,4,2));

        kruskal(edges,5);

        int[][] graph = {

                {0,4,2,0,0},
                {4,0,1,5,0},
                {2,1,0,8,10},
                {0,5,8,0,2},
                {0,0,10,2,0}

        };

        prim(graph,5);
    }
}
