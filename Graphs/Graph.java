import java.util.*;

class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e2){// for kruskal
        return this.weight-e2.weight;
    }
}

class Pair implements Comparable<Pair> {
    int node;
    int currShortestdest;
    Pair(int node,int currShortestdest){
        this.node = node;
        this.currShortestdest = currShortestdest;
    }
    // sort based on Ascending currShortestdest
    @Override
    public int compareTo(Pair p2){
    return this.currShortestdest-p2.currShortestdest;
    }
}

class FlightPair{
    int node;
    int currShortestdest;
    int stops;
    FlightPair(int node,int currShortestdest,int stops){
        this.node = node;
        this.currShortestdest = currShortestdest;
        this.stops = stops;
    }
}

class DisjointSet {
    int n;
    int parent[];
    int rank[];
    DisjointSet(int n){
        this.n = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int Find(int x){
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = Find(parent[x]);// path compression
    }

    void union(int a,int b){
        int parA = Find(a);
        int parB = Find(b);

        if (rank[parA] == rank[parB]) {
         parent[parB] = parA;
         rank[parA]++;   
        }else if (rank[parA] > rank[parB]) {
            parent[parB] = parA;
        }else {
            parent[parA] = parB;
        } 
    }
}

public class Graph {

    // Breadth first search (BFS)
    public static void Bfs(ArrayList<Edge> graph[]) {
        java.util.Queue<Integer> q1 = new java.util.LinkedList<>();
        // graphs can have cycles,thats why we have visited array
        boolean visited[] = new boolean[graph.length];
        q1.add(0);// source

        while (!q1.isEmpty()) {
            int node = q1.remove();// dequeue a node
            if (!visited[node]) {
                System.out.print(node + " ");// print
                visited[node] = true;// then visit
                // push all its neighbours in the queue
                for (int i = 0; i < graph[node].size(); i++) {
                    Edge e = graph[node].get(i);
                    q1.add(e.dest);
                }
            }
        }
    }

    // Depth first search (DFS)
    public static void Dfs(ArrayList<Edge> graph[], int source, boolean visited[]) {
        System.out.print(source + " ");
        visited[source] = true;
        // for all neighbours of source
        for (int i = 0; i < graph[source].size(); i++) {
            Edge e = graph[source].get(i);
            if (!visited[e.dest]) {
                Dfs(graph, e.dest, visited);// go for neighbours
            }
        }
    }

    // Has path (using dfs)
    public static boolean Haspath(ArrayList<Edge> graph[], int src, int dest, boolean visited[]) {
        if (src == dest) {// base case
            return true;
        }
        visited[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            // if src have a non visited neighbour who has a path to dest
            if (!visited[e.dest] && Haspath(graph, e.dest, dest, visited)) {
                return true;
            }
        }
        return false;
    }

    // Cycle detection(undirected graph)
    public static boolean cycledec(ArrayList<Edge> graph[]) {// for components
        boolean visited[] = new boolean[graph.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (cycleutil(graph, i, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean cycleutil(ArrayList<Edge> graph[], int curr, int parent, boolean visited[]) {
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            //case 1
            if (visited[i] && e.dest != parent) {
                return true;
            }
            //case 3
            if (!visited[i]) {
                if (cycleutil(graph, e.dest, i, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

// check if graph is bipartite or not
    public static boolean isBipartite(ArrayList<Edge> graph[]) {
        // two color 0 or 1
        int color[] = new int[graph.length];
        for (int i = 0; i < color.length; i++) {
            color[i] = -1;// initalize with no color
        }
        java.util.Queue<Integer> q1 = new java.util.LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1) {// bfs continues
                q1.add(i);
                color[i] = 0;
                while (!q1.isEmpty()) {
                    int node = q1.remove();
                    for (int j = 0; j < graph[node].size(); j++) {
                        Edge e = graph[node].get(j);
                        // case1
                        if (color[node] == color[e.dest]) {
                            return false;
                        }
                        // case3
                        else if (color[e.dest] == -1) {
                            int nextcol = color[node] == 0 ? 1 : 0;
                            color[e.dest] = nextcol;
                            q1.add(e.dest);
                        }
                    }
                }
                
            }
        }
        return true;
    }    

// cycle detection (directed graph)
    public static boolean directedCycle(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                if (directedCycleutil(graph,visited,stack,i)) {
                    return true;
                }
            }
        }
        return false;
    }    
    public static boolean directedCycleutil(ArrayList<Edge> graph[],boolean visited[],boolean stack[],int curr) {
        visited[curr] = true;
        stack[curr] = true;// not an actual stack
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            // if my next neighbour already exists in stack
            if (stack[e.dest] == true) {// condt of cycle
                return true;
            }
            else if (!visited[e.dest] && directedCycleutil(graph, visited, stack, e.dest)) {
                return true;
            }
        }
        stack[curr] = false;// backtrack
        return false;
    }

// topological sort   
    public static void topologicalSort(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        java.util.Stack<Integer> s = new java.util.Stack<>();
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                topologicalSortutil(graph,visited,s,i);
            }
        }
        // prints in our desired priority
        while (!s.isEmpty()) {
            System.out.print(s.pop()+" ");
        }
    }    
    public static void topologicalSortutil(ArrayList<Edge> graph[],boolean visited[],java.util.Stack<Integer> s,int curr) {
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {
                topologicalSortutil(graph, visited, s, e.dest);
            }
        }
        s.add(curr);// main step
    } 

// topological sort using bfs(kahn's algo)
    public static void Calindeg(ArrayList<Edge> graph[],int indeg[]) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                indeg[e.dest]++;
            }
        }
    }    
    public static void bfsTopo(ArrayList<Edge> graph[]) {
        int indeg[] = new int[graph.length];
        Calindeg(graph, indeg);
        // queue will only consist of nodes with indegree 0
        java.util.Queue<Integer> q = new java.util.LinkedList<>();
        // initialize the queue for bfs
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }
        // BFS
        while (!q.isEmpty()) {
            int node = q.remove();
            System.out.print(node+" ");
            // now check for all neighbours
            for (int i = 0; i < graph[node].size(); i++) {
                Edge e = graph[node].get(i);
                // decrease their indegree
                indeg[e.dest]--;
                // if it becomes 0 for any
                if (indeg[e.dest] == 0) {
                    q.add(e.dest); // add in the queue
                }
            }
        }
    }

// all paths from source to target(for directed graph)
    public static void StoD(ArrayList<Edge> graph[],int source,int dest,String path) {
        if (source == dest) {// base case
            System.out.println(path+dest);
            return;
        }
        for (int i = 0; i < graph[source].size(); i++) {
            Edge e = graph[source].get(i);
            StoD(graph, e.dest, dest, path+source);
        }
    }    

// Dijksra's Algorithm
    public static void Dijksra(ArrayList<Edge> graph[],int src) {
        boolean visited[] = new boolean[graph.length];
        int distance[] = new int[graph.length];
        java.util.PriorityQueue<Pair> pq = new java.util.PriorityQueue<>();
        // initialization of distances
        for (int i = 0; i < distance.length; i++) {
            if (src != i) {
                distance[i] = Integer.MAX_VALUE;
            }
        }
        // insert the source
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.remove();
           if (!visited[p.node]) {
             visited[p.node] = true;
             // for all neighbours
             for (int i = 0; i < graph[p.node].size(); i++) {
              Edge e = graph[p.node].get(i);
              int u = e.src;
              int v = e.dest;
              int wt = e.weight;
              if (distance[u]+wt < distance[v]) {
                  distance[v] = distance[u]+wt;
                  pq.add(new Pair(v, distance[v]));
              }
             }
           }

        }

        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i]+" ");
        }
    }    

// bellman ford algorithm
    public static void Bellman(ArrayList<Edge> graph[],int src) {
        int distance[] = new int[graph.length];
        for (int i = 0; i < distance.length; i++) {
            if (src!=i) {
                distance[i] = Integer.MAX_VALUE;
            }
        }

        int V = graph.length;
        // together O(VE)
        //this loop traverses over all vertices[O(V)]
        for (int i = 0; i < V-1; i++) {// V-1 times
            // this two loops together traverses all edges[O(E)]
            for (int j = 0; j < graph.length; j++) {
                for (int j2 = 0; j2 < graph[j].size(); j2++) {
                    Edge e = graph[j].get(j2);
                    int u = e.src;
                    int v = e.dest;
                    int weight = e.weight;
                    // distance of u should not be infinity
                    if (distance[u]!=Integer.MAX_VALUE && distance[u]+weight < distance[v]) {
                        distance[v] = distance[u]+weight;
                    }
                }
            }
        }
        // print the distances
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i]+" ");
        }
    }    

// Prims algorithm
    public static void Prim(ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        java.util.PriorityQueue<Pair> pq = new java.util.PriorityQueue<>();
        int minTotalEdge = 0;
        // initial node
        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!visited[curr.node]) {
                visited[curr.node] = true;
                minTotalEdge += curr.currShortestdest;
                // for all neighbours
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    Edge e = graph[curr.node].get(i);
                    pq.add(new Pair(e.dest, e.weight));
                }
            }
        }
        System.out.println(minTotalEdge);
    }    

// cheapest fights within k stops
    public static void creategraph(int flights[][],ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < flights.length; i++) {
                int src = flights[i][0];
                int dest = flights[i][1];
                int wt = flights[i][2];

                Edge e = new Edge(src, dest, wt);
                graph[src].add(e);
        }
    }
    public static void cheapestFlights(int flights[][],int V,int src,int dst,int k) {
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[V];
        creategraph(flights,graph);
        // initialization
        int distance[] = new int[graph.length];
        for (int i = 0; i < distance.length; i++) {
            if (src != i) {
                distance[i] = Integer.MAX_VALUE;
            }
        }
        java.util.Queue<FlightPair> q = new java.util.LinkedList<>();

        q.add(new FlightPair(src,0,0));
        while (!q.isEmpty()) {
            FlightPair curr = q.remove();
            // When you encounter the first state with stops > k, 
            // all remaining states in the queue also have stops > k 
            // (because you're processing by levels). So breaking is 
            // correct—you've already added all reachable nodes with stops ≤ k
            //  to the distance array.
            if (curr.stops > k) {// stops should be <= k
                break;
            }

            for (int i = 0; i < graph[curr.node].size(); i++) {
                Edge e = graph[curr.node].get(i);
                int v = e.dest;
                int weight = e.weight;
                //not distance[u] because here we are not relying on overall shortest path
                if (curr.currShortestdest+weight < distance[v]) {
                    distance[v] = curr.currShortestdest+weight;
                    q.add(new FlightPair(v,distance[v],curr.stops+1));
                }
            }
        }
        // print the ans
        if (distance[dst] == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else {
            System.out.println(distance[dst]);
        }

    }    

// connecting cities
    public static void connectingCities(int cities[][]) {
        boolean visited[] = new boolean[cities.length];
        java.util.PriorityQueue<Pair> pq = new java.util.PriorityQueue<>();
        int Finalcost = 0;

        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!visited[curr.node]) {
                visited[curr.node] = true;
                Finalcost += curr.currShortestdest;

                for (int i = 0; i < cities[curr.node].length; i++) {
                    if (cities[curr.node][i] != 0) {
                        pq.add(new Pair(i, cities[curr.node][i]));
                    }
                }
            }
        }
        System.out.println(Finalcost);

    }    

// kruskals algorithm
    public static void kruskal(ArrayList<Edge> graph,int V) {
        // stored only edges to sort them
        Collections.sort(graph);
        DisjointSet ds = new DisjointSet(V);
        int mincost = 0;
        int countEdge = 0;
        // for V nodes we only need V-1 edges to connect them all(optimization)
        for (int i = 0; countEdge < V-1; i++) {
            Edge e = graph.get(i);
            int parA = ds.Find(e.src);
            int parB = ds.Find(e.dest);
            if (parA != parB) {// if no cycle
                mincost += e.weight;
                ds.union(parA, parB);
                countEdge++;
            }
        }
        System.out.println(mincost);
    }    

// flood fill algorithm
    public static void helper(int image[][],int stRow,int stCol,int color,int originalCol,boolean vis[][]) {
        if (stRow < 0 || stCol < 0 || stRow >= image.length || stCol >= image[0].length ||
            vis[stRow][stCol] == true || image[stRow][stCol] != originalCol
        ) {
            return;
        }
         vis[stRow][stCol] = true;// visit
         image[stRow][stCol] = color;// and update color
        // left
        helper(image, stRow, stCol-1, color, originalCol,vis);
        // right
        helper(image, stRow, stCol+1, color, originalCol,vis);
        // up
        helper(image, stRow-1, stCol, color, originalCol,vis);
        // down
        helper(image, stRow+1, stCol, color, originalCol,vis);
    }
    public static void Floodfill(int image[][],int stRow,int stCol,int color) {
        boolean visited[][] = new boolean[image.length][image[0].length];
        helper(image,stRow,stCol,color,image[stRow][stCol],visited);
    }    

    public static void main(String[] args) {
        //      (5)
        //   0 ------ 1
        //           |  \
        //           |   \
        //         (1)    (3)
        //           |      \
        //           2 ------ 3
        //            \
        //            (2)
        //              \
        //               4
        int v = 5;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[v];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // // 0
        // graph[0].add(new Edge(0, 1, 5));

        // // 1
        // graph[1].add(new Edge(1, 3, 3));
        // graph[1].add(new Edge(1, 2, 1));
        // graph[1].add(new Edge(1, 0, 5));

        // // 2
        // graph[2].add(new Edge(2, 1, 1));
        // graph[2].add(new Edge(2, 3, 1));
        // graph[2].add(new Edge(2, 4, 2));

        // // 3
        // graph[3].add(new Edge(3, 1, 3));
        // graph[3].add(new Edge(3, 2, 1));

        // // 4
        // graph[4].add(new Edge(4, 2, 1));

        // graph[0].add(new Edge(0, 1, 1));
        // graph[0].add(new Edge(0, 2, 1));
        // graph[1].add(new Edge(1, 3, 1));
        // graph[2].add(new Edge(2, 3, 1));

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));
        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));
        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));

        // find neighbours
        // for (int i = 0; i < graph[2].size(); i++) {
        // Edge e = graph[2].get(i);
        // System.out.println(e.dest);
        // }
    //    int n = 4;
    //    int flights[][] = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
    //    int src = 0, dst = 3 , k = 1;
    //    cheapestFlights(flights, 4, 0, 3, 1);
        //  int cities[][] = {{0,1,2,3,4},
        //                    {1,0,5,0,7},
        //                    {2,5,0,6,0},
        //                    {3,0,6,0,0},
        //                    {4,7,0,0,0}};
        // connectingCities(cities);
        // DisjointSet ds = new DisjointSet(7);
        // System.out.println(ds.Find(3));
        // ds.union(1, 3);
        // System.out.println(ds.Find(3));
        // ds.union(2, 4);
        // ds.union(3, 6);
        // ds.union(1, 4);
        // System.out.println(ds.Find(3));
        // System.out.println(ds.Find(4));
        // ds.union(1, 5);

        ArrayList<Edge> graph1 = new ArrayList<>();
        int V = 4;
        graph1.add(new Edge(0, 1, 10));
        graph1.add(new Edge(0, 2, 15));
        graph1.add(new Edge(0, 3, 30));
        graph1.add(new Edge(1, 3, 40));
        graph1.add(new Edge(2, 3, 50));
        kruskal(graph1, V);
        int image[][] = {{1,1,1},{1,1,0},{1,0,1}};
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j]+" ");
            }
            System.out.println();
        }
        Floodfill(image, 1, 1, 2);
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j]+" ");
            }
            System.out.println();
        }
    }
}
