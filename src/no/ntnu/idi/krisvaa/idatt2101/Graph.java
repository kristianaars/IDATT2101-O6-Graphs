package no.ntnu.idi.krisvaa.idatt2101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Graph<T> {

    int nodeCount, edgeCount;
    Node<T>[] nodes;

    public Graph(int nodeCount, int edgeCount) {
        nodes = new Node[nodeCount];

        for(int i = 0; i < nodes.length; i++) { nodes[i] = new Node<T>(); }

        this.nodeCount = nodeCount;
        this.edgeCount = edgeCount;
    }

    public static Graph readFromStream(InputStream is) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodeCount = Integer.parseInt(st.nextToken());
            int edgeCount = Integer.parseInt(st.nextToken());

            System.out.println(nodeCount + " " + edgeCount);

            Graph g = new Graph<>(nodeCount, edgeCount);

            for(int i = 0; i < edgeCount; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                System.out.println(from + " " + to);

                Edge e = new Edge(g.nodes[to], g.nodes[from].edge1);
                g.nodes[from].edge1 = e;
            }

            return g;
        } catch (Exception e) {
            System.out.println("Unable to read input stream... Reason: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}

class Node<T> {
    Edge edge1;
    T data;

}

class Edge {

     Edge next;
     Node to;

     public Edge(Node n, Edge next) {
         this.to = n;
         this.next = next;
     }

}
