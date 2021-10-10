package no.ntnu.idi.krisvaa.idatt2101;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args)  {

        Graph g = null;
        try {
            g = Graph.readFromStream(new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g1").openStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(g);
    }
}
