package com.practice.ds;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(){};
    TreeNode(int v)
    {
        val =v;
    }
    TreeNode(int val, TreeNode left, TreeNode right)
    {
        this.val = val;
        this.left =left;
        this.right = right;
    }
}

public class BST {
    static TreeNode root = null;
    public static void main(String [] args)
    {
         int [] vals = {50,100,70,150,20,10,30};
         for(int val :vals)
         {
             root =insert(root, val);
         }
         List<Integer> list = new ArrayList<>();
         traverseRec(root, list );
         System.out.println("Inorder Recursion: "+ Arrays.toString(list.toArray()));

         list = traverse(root);
         System.out.println("Inorder stack: "+Arrays.toString(list.toArray()));

         list = preOrder(root);
        System.out.println("Preorder stack: "+Arrays.toString(list.toArray()));

        list = postOrder(root);
        System.out.println("PostOrder stack: "+Arrays.toString(list.toArray()));

        list = postOrder2(root);
        System.out.println("PostOrder single stack: "+Arrays.toString(list.toArray()));
    }

    public static TreeNode insert(TreeNode node, int v){
        if(node==null)
            return new TreeNode(v);
        if(v< node.val)
        {
            node.left = insert(node.left, v);
        }else{
            node.right = insert(node.right, v);
        }
        return node;
    }

    public static void traverseRec(TreeNode node, List<Integer> list){
        if(node!=null){
            traverseRec(node.left, list);
            list.add(node.val);
            traverseRec(node.right, list);
        }
    }

    public static List<Integer> traverse(TreeNode node)
    {
       List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || node!=null) {
            while(node!=null)
            {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            if(node.right!=null)
            node = node.right;
        }
        return list;
    }

    public static List<Integer> preOrder(TreeNode node)
    {
        if(node==null)
            return null;
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            list.add(node.val);
            if(node.right!=null)
                stack.push((node.right));
            if(node.left!=null)
                stack.push(node.left);
        }
        return list;
    }

    public static List<Integer> postOrder(TreeNode node)
    {
        if(node==null)
            return null;
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        Stack<Integer> out = new Stack<>();


        while(!stack.isEmpty())
        {
             node = stack.pop();
             out.push(node.val);

             if(node.left!=null)
                 stack.push(node.left);
             if(node.right!=null)
                 stack.push(node.right);
        }
        while(!out.isEmpty())
             list.add(out.pop());
        return list;
    }

    public static List<Integer> postOrder2(TreeNode node)
    {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        TreeNode prev = null;
        TreeNode cur = null;
        while(!stack.isEmpty())
        {
            cur = stack.peek();
            //going down
            if(prev==null || prev.left==cur || prev.right==cur){
               if(cur.left!=null)
                    stack.push(cur.left);
               else if (cur.right!=null)
                   stack.push(cur.right);
               //leaf node
               else
                   list.add(stack.pop().val);
            }

            //going up from left sub tree
            else if(cur.left ==prev){
                if(cur.right!=null)
                    stack.push(cur.right);
                else
                    list.add(stack.pop().val);
            }
            //going up from right sub tree
            else if(cur.right==prev){
                list.add(stack.pop().val);
            }

          prev = cur;
        }


        return list;
    }

}
