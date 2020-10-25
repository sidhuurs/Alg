package com.practice.ds;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Sudarshan
 */
public class UnionFind {
    Map<String, String> parentMap ;
    Map<String, Integer> sizeMap;

    UnionFind(Set<String> elements){
        parentMap = new HashMap<>();
        sizeMap = new HashMap<>();
         for(String u : elements){
             parentMap.put(u, u);
             sizeMap.put(u,1);
         }
    }

    public void union(String u, String v){
          if(connected(u,v))
                return ;
          String pu = parent(u);
          String pv = parent(v);
          int su = sizeMap.get(u);
          int sv = sizeMap.get(v);
          if(su<sv){
              parentMap.put(pu, pv);
              sizeMap.put(pv, sv+su);
          }else{
              parentMap.put(pv,pu);
              sizeMap.put(pu, sv+su);
          }

    }

    public boolean connected(String u, String v){
          return parent(u).equals(parent(v));
    }
    private String parent(String u){
        String pu = parentMap.get(u);
        if(u.equals(pu)){
            return pu; // or return u;
        }else{
            pu = parent(pu);
            parentMap.put(u, pu);
        }
       return pu;
    }



    String parent2(String u)
    {
        String p = parentMap.get(u);
        if(p.equals(u)){
            return p;
        }
        else{
            p = parent2(p);
            parentMap.put(u, p);
        }
        return p;
    }

    void union2(String u, String v){
        String pu = parentMap.get(u);
        String pv = parentMap.get(v);

        if(pu.equals(pv)){
            int su = sizeMap.get(pu);
            int sv = sizeMap.get(pv);

            if(su<sv){
                parentMap.put(pu,pv);
                sizeMap.put(pv, sv+su);
            }else{
                parentMap.put(pv,pu);
                sizeMap.put(pu, su+sv);
            }
        }
    }


}
