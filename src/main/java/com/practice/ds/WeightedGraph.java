package com.practice.ds;

import java.util.*;

class Edge implements Comparable<Edge>{
    String source;
    String destination;
    int weight;

    Edge() { }

    Edge (String src, String dest, int w)
    {
        source = src;
        destination = dest;
        weight =w;
    }

    @Override
    public int compareTo(Edge e2) {
        return this.weight-e2.weight;
    }
}
class DistNode{
    String prevVertex;
    int distance;
    DistNode(){}
    DistNode(String prevVertex, int distance){
        this.prevVertex = prevVertex;
        this.distance = distance;
    }
}

class HeapNode{
    String vertex;
    int distance;
    HeapNode(){}
    HeapNode(String vertex, int distance){
        this.vertex = vertex;
        this.distance = distance;
    }
}
public class WeightedGraph {
    Map<String, List<Edge>> adjMap = new HashMap<>();
    List<Edge> edges = new ArrayList<>();
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

    public void removeVertex(String s){
        if(adjMap.containsKey(s)){
            adjMap.remove(s);
        }

        for(List<Edge> edges : adjMap.values()){
            Edge removableEdge = null;
            for(Edge edge : edges){
                if(edge.destination.equals(s)){
                    removableEdge = edge;
                    break;
                }
            }
            edges.remove(removableEdge);
        }
    }
    public void addEdge(String s, String t, int weight)
    {
        addVertex(s);
        addVertex(t);
        Edge edge = new Edge(s, t, weight);
        adjMap.get(s).add(edge);
        edges.add(edge);
        if(!isDirected){
            Edge edge2 = new Edge(t, s, weight);
            adjMap.get(t).add(edge2);
            edges.add(edge2);
        }
    }

    public void  addEdge(Edge edge)
    {
        addVertex(edge.source);
        addVertex(edge.destination);
        adjMap.get(edge.source).add(edge);
        edges.add(edge);

      /*  if(!isDirected){
            Edge edge2 = new Edge(edge.destination, edge.source, edge.weight);
            adjMap.get(edge.destination).add(edge2);
            edges.add(edge2);
        }*/

    }
    public void removeEdge(Edge edge){
        adjMap.get(edge.source).remove(edge);
        edges.remove(edge);
      /*if(!isDirected){
            Edge edge2 = new Edge(edge.destination, edge.source, edge.weight);
            adjMap.get(edge2.source).remove(edge2);
            edges.remove(edge2);
        }*/

    }

    public List<Edge> getEdges(String s)
    {
        return adjMap.get(s);
    }


   public  Set<String> dfs(String s)
    {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(s);
        while(!stack.isEmpty()){
            s = stack.pop();
            visited.add(s);
            List<Edge> edges =  this.getEdges(s);
            if(edges!=null){
                for(Edge edge : edges){
                    if(!visited.contains(edge.destination)){
                        stack.push(edge.destination);
                    }
                }
            }
        }
        return visited;
    }

    public boolean hasCycle(String u, String parent, Set<String> visited)
    {
        visited.add(u);
        List<Edge> edges =  this.getEdges(u);
        if(edges!=null){
            for(Edge edge : edges){
                String v = edge.destination;
                if(!visited.contains(v)){
                    if(hasCycle(v, u, visited))
                        return true;
                }
                else if(parent!=null && !parent.equals(v)){
                    return true;
                }
            }
        }
        return false;
    }



}
