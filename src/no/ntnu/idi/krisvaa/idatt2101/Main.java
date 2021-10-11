package no.ntnu.idi.krisvaa.idatt2101;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

        System.out.println("\nDownloading map-data. Please wait...");

        //Load graph-data from file
        Graph<String> g = null;
        Graph<String> map = null;
        try {
            g = Graph.readFromStream(new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g5").openStream());
            map = Graph.readFromStream(new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7Skandinavia").openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //BFS-Traversing
        int startNode = 2;
        System.out.printf("\nBFS-Traversing with root-node %s: \n", startNode);

        g.bfs(g.nodes[startNode]);
        System.out.printf("%4s  %12s  %8s \n", "Node", "predecessor", "distance");
        for(Node n : g.nodes) {
            Predecessor nodeData = (Predecessor) n.nodeData;

            int nodeID = n.id;

            String predecessor = "";
            if(nodeData.findPredecessor() != null) {
                predecessor = "" + nodeData.findPredecessor().id;
            }

            int distance = ((Predecessor) n.nodeData).getDistance();
            System.out.printf("%4s  %12s  %8s \n", nodeID, predecessor, distance);
        }

        //Topological sorted
        System.out.print("\nTopologically sorted: \n[");
        Node n = g.topologicalSort();
        while (((Topo_lst)n.nodeData).next != null) {
            System.out.print(n.id + ", ");
            n = ((Topo_lst)n.nodeData).next;
        }
        System.out.print(n.id + "]\n");

        System.out.println("\nCalculating distances on map using BFS. Please wait...");
        boolean isRunning = true;
        Scanner s = new Scanner(System.in);

        while(isRunning) {
            System.out.println("Enter id of starting-point: ");
            int from = Integer.parseInt(s.nextLine());

            System.out.println("Enter id of end-point: ");
            int  to = Integer.parseInt(s.nextLine());

            map.bfs(map.nodes[from]);
            int distance = ((Predecessor) map.nodes[to].nodeData).getDistance();
            System.out.println(distance + " number of road-segments between " + from + " and " + to + "\n");
        }

    }
}
