package com.practice.ds;


import java.util.*;

public class Dijskstras2 {

    static class Edge implements  Comparable<Edge>{
        String src;
        String dest;
        int distance;

        Edge(String s, String dest, int dist)
        {
            src = s;
            this.dest = dest;
            distance = dist;
        }
        public int compareTo(Edge e){
            return this.distance-e.distance;
        }
        public String toString(){
            return src + " --> " + dest;
        }
    }
  static  class WtGraph{
        Map<String, List<Edge>> adjMap = new HashMap<>();
        List<Edge> edges = new ArrayList<>();

        public void addVertex(String s){
            if(!adjMap.containsKey(s)){
                adjMap.put(s, new ArrayList<>());
            }
        }

        public void addEdge(Edge edge){
            addVertex(edge.src);
            addVertex(edge.dest);
            edges.add(edge);
            List<Edge> edgeList =adjMap.getOrDefault(edge.src, new ArrayList<>());
            edgeList.add(edge);
            adjMap.put(edge.src, edgeList);
        }

    }


    public static void main(String [] args){
        WtGraph graph = new WtGraph();
        graph.addEdge(new Edge("A", "B",3));
        graph.addEdge(new Edge("A", "C",2));
        graph.addEdge(new Edge("C", "E",10));
        graph.addEdge(new Edge("B", "D",2));
        graph.addEdge(new Edge("D", "F",2));
        graph.addEdge(new Edge("F", "G",2));
        graph.addEdge(new Edge("G", "E",3));

        List<String> path = dijkstrasAlg(graph, "A", "E");
        System.out.println(("dijkstras shorted path = "+path));

    }

    static List<String> dijkstrasAlg(WtGraph graph, String source, String destination){
        Map<String, String> prevMap = new HashMap<>();
        Map<String, Integer> distMap = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue((s1, s2)-> distMap.get(s1)-distMap.get(s2));
        queue.add(source);
        distMap.put(source, 0);

        while(!queue.isEmpty()){
            String u = queue.poll();
            int distance = distMap.get(u);
            List<Edge> edges = graph.adjMap.get(u);
            if(edges!=null){
                for(Edge edge : edges){
                    String  v = edge.dest;
                    int wt = edge.distance;
                    if(!prevMap.containsKey(v) || distMap.get(v)> distance+wt){
                        prevMap.put(v, u);
                        distMap.put(v, distance+wt);
                        queue.add(v);
                    }
                }
            }
        }
        System.out.println("### distance = "+distMap.get(destination));
        return backTrack(prevMap, source, destination);

    }

    static List<String> backTrack(Map<String, String> prevMap, String s, String d){
        List<String> path = new ArrayList<>(0);
        String n = d;
        while(!n.equals(s)){
            path.add(n);
            n = prevMap.get(n);
        }
        path.add(s);
        Collections.reverse(path);
        return path;
    }
}
