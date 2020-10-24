package com.practice.ds;

import java.util.Arrays;

public class MergeSortedLists {

    public static void main(String [] args){
        int  [] a = {-5,-1,0,1,2,3,15,23};
        int [] b = {1,5,10,21};

        int [] m = merge(a,b);

        System.out.println(Arrays.toString(m));
    }

   static int [] merge(int [] a, int [] b){

        int l1 = a.length;
        int l2 = b.length;

        int [] result = new int [l1+l2];
        int i =0, j =0, k=0;

        while(i<l1 && j<l2){

            if(a[i]<b[j]){
                result[k++] = a[i++];
            }else{
                result[k++] = b[j++];
            }


        }

        if(i<l1)
        {
            copy(a, result, i, k);
        }
        else if(j<l2){
            copy(b, result, j, k);
        }

        return result;
    }

  static   void copy(int [] source, int [] target, int sourceStart, int targetStart)
    {
        for(int i=sourceStart;i<source.length;i++){
            target[targetStart++] = source[i];
        }
    }
}
