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

        List<String> path = shortestPathBFS(graph, "A", "D");
        System.out.println("Shortest Path from A to D is ==> "+ path);

            dfsRec(graph, "A", "D", new ArrayList<>());
          System.out.println("Shortest Path from A to D (DFS Rec) is ==> "+ result);




    }

  static List<String> shortestPathBFS(Graph graph, String source, String destination)
    {
        String [] path = null;
        Map<String, String> preMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(source); boolean found =false;
        int level =0;
        while(!queue.isEmpty() && !found){
            int size = queue.size();
            for(int i=0;i<size && !found;i++){
                String node = queue.poll();

                Set<String> nodes = graph.getAdjNodes(node);
                if(nodes!=null){
                    for(String n : nodes){
                        if(!preMap.containsKey(n)){
                            preMap.put(n, node);
                            if(n.equals(destination)){
                                found = true;
                                break;
                            }
                            queue.add(n);
                        }
                    }
                }
            }
            level++;
        }
        System.out.println("level ="+level);
        return backTrack(preMap, source, destination);


    }


   static List<String> backTrack(Map<String, String> prevMap, String source, String dest){
       List<String> path = new ArrayList<>();
       String n = dest;
       while(!n.equals(source)){
           path.add(n);
           n = prevMap.get(n);
       }
       path.add(source);
       Collections.reverse(path);
       return path;
   }


    static List<String> result = null;
    static void dfsRec(Graph graph, String source, String destination, List<String> path){
        path.add(source);
        if(result!=null && path.size()>=result.size()){
            path.remove(path.size()-1);
            return;
        }
       if(source.equals(destination)){
           result = new ArrayList<>(path);
           path.remove(path.size()-1);
           return;
       }

       Set<String> neighbors = graph.getAdjNodes(source);
       if(neighbors!=null){
           for(String n : neighbors){
                dfsRec(graph,n, destination, path);
           }
       }
       path.remove(path.size()-1);
    }



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
