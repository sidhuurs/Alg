package com.practice.ds;

import java.util.*;

class Graph{
    Map<String, Set<String>> adjMap = new HashMap<>();
    boolean isDirected;


    Graph(){};
    Graph(boolean isDirected){
        this.isDirected = isDirected;
    }

    public void addVertex(String s)
    {
       if(!adjMap.containsKey(s))
           adjMap.put(s, new HashSet<>());
    }

    public void addEdge(String s1, String s2)
    {
        addVertex(s1);
        addVertex(s2);
        adjMap.get(s1).add(s2);
        if(!isDirected)
        {
            adjMap.get(s2).add(s1);
        }
    }

    public Set<String> getAdjNodes(String s)
    {
        return adjMap.get(s);
    }

    public int inDegree(String s)
    {
        int inDegree =0;
        for(Set<String> nodes : adjMap.values())
        {
            if(nodes.contains(s))
                ++inDegree;
        }
        return inDegree;
    }

    public int getNodesSize()
    {
        return adjMap.size();
    }

    public int getEdgesSize()
    {
        int size =0;
        for(Set<String> nodes : adjMap.values())
            size += nodes.size();
        return size;
    }
    public Set<String> dfs(String s)
    {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(s);
        while(!stack.isEmpty()){
            s = stack.pop();
            visited.add(s);
            Set<String> neighbors = adjMap.get(s);
            if(neighbors!=null) {
                for (String n : neighbors) {
                    if (!visited.contains(n))
                        stack.push(n);
                }
            }
        }
        return  visited;
    }

    public void dfsRec(String s, Set<String> visited)
    {
        if(visited.contains(s))
            return;
        visited.add(s);
        Set<String> neighbors = adjMap.get(s);
        if(neighbors!=null){
            for(String n :neighbors)
                dfsRec(n, visited);
        }
    }

    public Set<String> bfs(String s){
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        while (!queue.isEmpty()){
            s = queue.remove();
            visited.add(s);
            Set<String> neighbors = adjMap.get(s);
            if(neighbors!=null){
                for(String n : neighbors){
                    if(!visited.contains(n))
                    {
                       queue.offer(n);
                    }
                }
            }
        }
        return visited;
    }
}

public class GraphAlg {
    public static void main(String [] args){
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");

        graph.addEdge("B", "E");
        graph.addEdge("B", "F");
        graph.addEdge("C", "E");
        graph.addEdge("C", "G");
        graph.addEdge("D", "G");

        Set<String> set = graph.dfs("A");
        System.out.println(set);
        System.out.println(graph.bfs("A"));

        Set<String> visited = new HashSet<>();
        graph.dfsRec("A", visited);
        System.out.println(visited);

    }

}
