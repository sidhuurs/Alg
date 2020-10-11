package com.practice.ds;

import java.util.*;

class Graph{
    Map<String, Set<String>> graph = new HashMap<>();

    public void addVertex(String s)
    {
       if(!graph.containsKey(s))
           graph.put(s, new HashSet<>());
    }

    public void addEdge(String s1, String s2)
    {
        addVertex(s1);
        graph.get(s1).add(s2);
    }

    public Set<String> dfs(String s)
    {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(s);
        while(!stack.isEmpty()){
            s = stack.pop();
            visited.add(s);
            Set<String> neighbors = graph.get(s);
            if(neighbors!=null) {
                for (String n : neighbors) {
                    if (!visited.contains(n))
                        stack.push(n);
                }
            }
        }
        return  visited;
    }

    public Set<String> bfs(String s){
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        while (!queue.isEmpty()){
            s = queue.remove();
            visited.add(s);
            Set<String> neighbors = graph.get(s);
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

    }

}
