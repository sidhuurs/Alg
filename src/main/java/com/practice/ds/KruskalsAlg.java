package com.practice.ds;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author Sudarshan
 */
public class KruskalsAlg {

    public static  void main(String [] args){

        WeightedGraph graph = new WeightedGraph(false);
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 8);
        graph.addEdge("C", "E", 2);
        graph.addEdge("E", "B", 6);
        graph.addEdge("E", "D", 2);

        WeightedGraph mst =  kruskals(graph);

        System.out.println("minimum spanning tree  = "+mst.dfs("A"));
    }

    private static WeightedGraph kruskals(WeightedGraph graph) {
        WeightedGraph mst = new WeightedGraph(false);
        int  size = (graph.adjMap.size() -1)*2;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for(Edge edge : graph.edges)
            queue.add(edge);

        while(!queue.isEmpty() && mst.edges.size()<size){
            Edge edge = queue.remove();
            mst.addEdge(edge);
            if(mst.hasCycle(edge.source, null, new HashSet<String>())){
                mst.removeEdge(edge);
            }
        }
        if(mst.edges.size()<size){
            System.out.println("no MST");
        }

        return mst;
    }
}
