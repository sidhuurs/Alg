package com.practice.ds;

import java.util.*;

public class TopologicalSort {

    /****************
     *
     *    A-------->     B-------->D
     *    |             ^    /\
     *    |             |  /
     *    \/            | /
     *    C ----------->E
     ******************/



    public static void main(String [] args){
        Graph graph = new Graph(true);
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("E", "B");
        graph.addEdge("E", "D");
        graph.addEdge("E", "A");
        List<String> topSort = toplogicalSort(graph);

        if(topSort.equals(Arrays.asList("A", "C", "E", "B", "D")))
            System.out.println("passed");
        else
            System.out.println("faild");
    }

    public static List<String> toplogicalSort(Graph graph)
    {
        List<String> list = new ArrayList<>();
        Map<String, Integer> inDegree = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        for(String s: graph.adjMap.keySet())
        {
            int inDeg =  graph.inDegree(s);
            inDegree.put(s,inDeg);
            if(inDeg==0)
            {
                queue.add(s);
            }
        }

        while(!queue.isEmpty())
        {
            String node = queue.poll();
            list.add(node);
            Set<String> neighbors = graph.getAdjNodes(node);
            if(neighbors!=null){
                for(String s: neighbors)
                {
                    Integer inDeg = inDegree.get(s);
                    if(inDeg>0) {
                        --inDeg;
                        inDegree.put(s, inDeg);
                        if (inDeg == 0)
                            queue.add(s);
                    }
                }
            }

        }
        if(list.size()!=graph.getNodesSize())
        {
            System.err.println("cycle detected");
        }
        return list;
    }
}
