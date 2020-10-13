package com.practice.ds;

import java.util.*;

public class DijkstrasAlg {


    public static void main(String [] args)
    {
        WeightedGraph graph = new WeightedGraph(true);
       graph.addEdge("A", "B", 5);
       graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 8);
       graph.addEdge("C", "E", 2);
        graph.addEdge("E", "B", 6);
       graph.addEdge("E", "D", 2);

       List<String> path =  dijkstra(graph, "A", "D");
       System.out.println("shortest path = "+path);
    }

    public static List<String> dijkstra(WeightedGraph graph, String source, String destination)
    {
        Map<String, DistNode>  distMap = new HashMap<>();
        PriorityQueue<HeapNode> queue = new PriorityQueue<>((n1,n2)->n1.distance- n2.distance);
        queue.add(new HeapNode(source, 0));

        while(!queue.isEmpty()){
            HeapNode node = queue.remove();
            List<Edge> edges = graph.getEdges(node.vertex);
            if(edges!=null){
                for(Edge edge : edges){
                    String key = edge.destination;
                    int newDist = node.distance + edge.weight;
                    DistNode distNode = distMap.get(key);
                    if(distNode==null || newDist<distNode.distance){
                       DistNode newNode= new DistNode(node.vertex, newDist);
                       distMap.put(key, newNode);
                       queue.add(new HeapNode(key, newDist));
                    }
                }
            }

        }


        return backTrack(distMap, source, destination);
    }

    public static List<String> backTrack( Map<String, DistNode>  distMap, String source, String destination){
        List<String> path = new ArrayList<>();
        String temp = destination;
        while(!temp.equals(source)){
            path.add(temp);
            DistNode node = distMap.get(temp);
            temp = node.prevVertex;
        }
        path.add(source);

        System.out.println("distance ="+distMap.get(destination).distance);
        Collections.reverse(path);
        return path;
    }

}
