package com.practice.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Edge{
    String destination;
    int weight;

    Edge() { }

    Edge (String dest, int w)
    {
        destination = dest;
        weight =w;
    }
}
public class WeightedGraph {
    Map<String, List<Edge>> adjMap = new HashMap<>();
    boolean isDirected;

    WeightedGraph(){}
    WeightedGraph(boolean isDirected){
        this.isDirected = isDirected;
    }

    public void addVertex(String s)
    {
        if(!adjMap.containsKey(s))
            adjMap.put(s, new ArrayList<>());
    }
    public void addEdge(String s, String t, int weight)
    {
        addVertex(s);
        addVertex(t);
        Edge edge = new Edge(t, weight);
        adjMap.get(s).add(edge);
        if(!isDirected){
            Edge edge2 = new Edge(s, weight);
            adjMap.get(t).add(edge2);
        }
    }

    public List<Edge> getEdges(String s)
    {
        return adjMap.get(s);
    }



}
