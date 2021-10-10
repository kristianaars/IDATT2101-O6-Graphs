package no.ntnu.idi.krisvaa.idatt2101;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args)  {

        //Load graph-data from file
        Graph<String> g = null;
        try {
            g = Graph.readFromStream(new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g1").openStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        //BFS-Traversing
        int startNode = 5;
        System.out.printf("BFS-Traversing with root-node %s: \n", startNode);

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


    }
}
