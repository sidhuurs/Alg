package com.practice.ds;

import java.util.*;

public class CollectonCompare {
    public static  void main(String [] args){
      compareLists();
      compareSets();
    }

    private static void compareLists(){
        List<Integer> list1 = Arrays.asList(1,2);
        List<Integer> list2 = Arrays.asList(1,2);

        if(list1.equals(list2))
            System.out.println("list1 equals list2");
        else
            System.out.println("list1 not equals list2");


        list2 = Arrays.asList(2,1);

        if(list1.equals(list2))
            System.out.println("list1 equals list2");
        else
            System.out.println("list1 not equals list2");
    }

    private static void compareSets(){
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1,2));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(1,2));

        if(set1.equals(set2))
            System.out.println("set1 equals set2");
        else
            System.out.println("set1 not equals set2");


        set2 = new HashSet<>(Arrays.asList(2,1));

        if(set1.equals(set2))
            System.out.println("set1 equals set2");
        else
            System.out.println("set1 not equals set2");
    }
}
