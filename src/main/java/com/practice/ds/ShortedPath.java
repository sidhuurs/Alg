package com.practice.ds;

import java.util.*;

/****************
 *
 *    A-------->     B-------->D
 *    |             ^    /\
 *    |             |  /
 *    \/            | /
 *    C ----------->E
 ******************/
public class ShortedPath {

   static  class Distance {
       int dist;
       String prevNode;

       Distance() {
       }
       Distance(int d, String prev)
       {
           dist = d;
           prevNode = prev;
       }
   }

    public static void main(String [] args){
        Graph graph = new Graph(true);
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("E", "B");
        graph.addEdge("E", "D");

        String [] shortestPath = shortestPathBFS(graph, "A", "D");
        System.out.println("Shortest Path from A to D is ==> "+ Arrays.toString(shortestPath));

        //List<String> pathDFS = shortestPathDFS(graph, "A", "D");
      //  System.out.println("Shortest Path from A to D (DFS) is ==> "+ pathDFS);
    }

  static String [] shortestPathBFS(Graph graph, String source, String destination)
    {
        String [] path = null;
        Map<String, Distance> distMap = new HashMap<>();
        fillDistanceTable(graph, source, distMap);
        if(distMap.containsKey(destination)){
            int len = distMap.get(destination).dist+1;
            path = new String [len];

            String temp = destination;
            int index = len-1;
            while(index>-1){
                path[index--] = temp;
                temp = distMap.get(temp).prevNode;
            }
        }
        return path;
    }


   /*static List<String> shortestPathDFS(Graph graph, String source, String destination)
    {


        Set<String> visited = new HashSet<>();
        List<String> list = null;
        List<String> l = null;
        Deque<String> queue = new ArrayDeque<>();

        queue.push(source);

        while (!queue.isEmpty()){
            String s = queue.pop();
            visited.add(s);

            Set<String> links = graph.getAdjNodes(s);
            if(links!=null){
                if(links.contains(destination)){
                    if(list==null || queue.size()<list.size())
                    {
                        list = copy(queue);
                    }
                } else {
                    for (String n : links) {
                        if (!visited.contains(n)) {
                            queue.push(n);
                        }
                    }
                }
            }
        }

        return list;
    }*/

    static  List<String> copy(Deque<String> q)
    {
        List<String> list = new ArrayList<>();
        Iterator<String> itr = q.iterator();
        while(itr.hasNext())
        {
            list.add(itr.next());
        }

        return list;
    }
    static void fillDistanceTable(Graph graph, String source, Map<String, Distance> distMap){
        Queue<String> queue = new LinkedList<>();
        distMap.put(source, new Distance(0, source) );
        queue.add(source);
        int i =0;
        while (!queue.isEmpty()){
            String node = queue.poll();
            i++;
            Set<String> neighbors = graph.getAdjNodes(node);
            if(neighbors!=null){
                for(String n : neighbors){
                    if(!distMap.containsKey(n)){
                        distMap.put(n, new Distance(i, node));
                        queue.add(n);
                    }
                }
            }
        }
    }
}
