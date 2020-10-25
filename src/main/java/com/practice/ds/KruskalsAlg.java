package com.practice.ds;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Sudarshan
 */
public class KruskalsAlg {

    public static  void main(String [] args){

        WeightedGraph graph = new WeightedGraph(true);
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 8);
        graph.addEdge("C", "E", 2);
        graph.addEdge("E", "B", 6);
        graph.addEdge("E", "D", 2);

        WeightedGraph mst =  kruskals(graph);

        System.out.println("minimum spanning tree  = "+mst.edges);


    }


    private static WeightedGraph kruskals(WeightedGraph graph) {
          PriorityQueue<Edge> queue = new PriorityQueue<>();
          WeightedGraph mst = new WeightedGraph(true);
          int size = graph.adjMap.keySet().size()-1;
          for(Edge edge : graph.edges)
              queue.add(edge);

          while(!queue.isEmpty() && mst.edges.size()<size){
              Edge e = queue.remove();

              mst.addEdge(e);
              if(hasCycle(mst,e.source, null, new HashSet<>()))
                  mst.removeEdge(e);

          }
          return mst;

    }

    static boolean hasCycle(WeightedGraph graph, String u, String parent, Set<String> visited){
        visited.add(u);
        List<Edge> edges = graph.getEdges(u);
        if(edges!=null){
            for(Edge edge : edges){
                String v = edge.destination;

                if(!visited.contains(v)){
                    if(hasCycle(graph, v, u, visited))
                        return true;
                }
                else if(parent!=null && !v.equals(parent)){
                    return true;
                }
            }
        }
        return false;
    }



}
