package com.practice.ds;

import java.util.*;

/**
 * @author Sudarshan
 */
public class PrimsAlg {


    public static  void main(String [] args){

        WeightedGraph graph = new WeightedGraph(false);
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 8);
        graph.addEdge("C", "E", 2);
        graph.addEdge("E", "B", 6);
        graph.addEdge("E", "D", 2);

        WeightedGraph mst =  prims(graph, "A");

        System.out.println("minimum spanning tree  = "+mst.dfs("A"));
    }


    public static WeightedGraph prims(WeightedGraph graph, String s){
        Map<String, DistNode> distMap = new HashMap<>();
        PriorityQueue<HeapNode> queue = new PriorityQueue<>((n1, n2)-> n1.distance-n2.distance);
        Set<String> visited = new HashSet<>();

        distMap.put(s, new DistNode(s, 0));
        queue.add(new HeapNode(s, 0));

        while(!queue.isEmpty()){
            HeapNode sourceNode = queue.remove();
            visited.add(sourceNode.vertex);

            List<Edge> edges = graph.getEdges(sourceNode.vertex);
            if(edges!=null){
                for(Edge edge : edges){
                    DistNode distNode = distMap.get(edge.destination);
                    if(distNode==null || edge.weight < distNode.distance){
                        distMap.put(edge.destination, new DistNode(sourceNode.vertex, edge.weight));
                        queue.add(new HeapNode(edge.destination, edge.weight));
                    }
                }
            }

        }


        return buildMST(distMap);


    }

    public static  WeightedGraph buildMST(Map<String, DistNode> distMap)
    {
        WeightedGraph mst = new WeightedGraph(false);

        for(Map.Entry<String, DistNode> entry : distMap.entrySet()){
            if(entry.getKey().equals(entry.getValue().prevVertex))
                continue;
            mst.addEdge(entry.getKey(), entry.getValue().prevVertex, entry.getValue().distance);
        }
        return mst;
    }
}
