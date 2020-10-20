package com.practice.ds;

import java.util.*;

class Node {
    Object value;
    List<Node> neigbors;

}



public class CloneGraph {



        static Map<Node, Node> map = new HashMap<>();
        public static Node clone(Node node){
            //check if node is already cloned
            if(map.containsKey(node)){
                return map.get(node);
            }

            Node clonedNode = new Node();

            //cloning node
            clonedNode.value = node.value;
            List<Node> neigbors = node.neigbors;
            List<Node> clonedNeigbors = null;

            //saving cloned node to cache
            map.put(node, clonedNode);

            //cloning beighbors
            if(neigbors!=null){
                clonedNeigbors = new ArrayList<Node>();
                for(Node n : neigbors){
                    clonedNeigbors.add(clone(n));
                }
            }

            clonedNode.neigbors = clonedNeigbors;
            return clonedNode;
        }


      static   Set<Object> print(Node node){
            Set<Object> set = new LinkedHashSet<>();
            Queue<Node> queue = new LinkedList<>();
            queue.offer(node);
            set.add(node.value);


            while(!queue.isEmpty()){
                Node n = queue.poll();
                System.out.print("\n"+n.value +" -> ");
                if (n.neigbors != null) {

                    for(Node n1 : n.neigbors){
                        System.out.print(n1.value+", ");
                        if(!set.contains(n1.value)){
                            set.add(n1.value);
                            queue.add(n1);
                        }
                    }
                }

            }

            return set;

        }


        public static void main(String[] args) {
            Node a = new Node();
            a.value = "a";

            Node b = new Node();
            b.value = "b";

            Node c= new Node();
            c.value = "c";

            Node d= new Node();
            d.value = "d";

            a.neigbors  = Arrays.asList(b,c);
            b.neigbors = Arrays.asList(c,d);
            c.neigbors = Arrays.asList(d);


            Node clonedA = clone(a);
            System.out.println(("--------------Input graph-----------------"));
             Set<Object> set = print(a);
            System.out.println(("\n------------cloned graph-----------------"));
            print(clonedA);


            /* for(Object o : set){
                 System.out.println(o);
             }*/



        }

}
