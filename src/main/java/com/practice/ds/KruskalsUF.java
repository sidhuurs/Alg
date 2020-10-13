package com.practice.ds;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Sudarshan
 */
public class KruskalsUF {

    public static void main(String args[]){
        WeightedGraph graph = new WeightedGraph(false);
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 8);
        graph.addEdge("C", "E", 2);
        graph.addEdge("E", "B", 6);
        graph.addEdge("E", "D", 2);

      graph.addEdge("F", "G", 10);

        WeightedGraph mst =  kruskalsUF(graph);

        //System.out.println("minimum spanning tree  = "+mst.dfs("A"));

        for(Edge edge :mst.edges)
            System.out.println(edge.source + " --> "+edge.destination);
    }

    private static WeightedGraph kruskalsUF(WeightedGraph graph) {
        UnionFind uf = new UnionFind(graph.adjMap.keySet());

        WeightedGraph mst = new WeightedGraph(false);
        int len = (graph.adjMap.size()-1) *2 ;
        List<Edge> edges = graph.edges;
        Collections.sort(edges); int i=0;
        for(Edge edge : edges){
            //skip the edge if that's connected means causing cycle
             if(!uf.connected(edge.source, edge.destination)){
                 //mst.edges.add(edge);
                 //use the above commented out code if only one edge needs needs added like A->B, instead of A->B and B->A
                 mst.addEdge(edge.source, edge.destination, edge.weight);
                 uf.union(edge.source, edge.destination);
                 i++;
                 if(i==len){
                     break;
                 }
             }
        }


        return mst;
    }

}
