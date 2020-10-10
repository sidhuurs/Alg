package com.practice.ds;

import java.util.*;

/**
 * Trie with Map implementation
 * @author Sudarshan
 */
class Trie {
    Map<Character, Trie> links = new HashMap<>();
    boolean end;
    List<String> words = new ArrayList<>();

    void addWords(String s)
    {
        this.words.add(s);
        Collections.sort(this.words);
    }

    public void add(String s)
    {
        Trie t = this;
        for(char c : s.toCharArray())
        {
            t.addWords(s);
            Trie child = t.links.get(c);
            if(child==null) {
                child = new Trie();
                t.links.put(c, child);
            }
            t = child;
        }
        t.end = true;
    }

    public Trie search(String s)
    {
        Trie t = this;
        for(char c : s.toCharArray())
        {
            t = t.links.get(c);
            if(t==null)
                return null;
        }
        return t;
    }

    public boolean startsWith(String s)
    {
        return search(s)!=null;

    }
    public boolean isPresent(String s)
    {
        Trie trie = search(s);
        return trie!=null && trie.end;
    }
    public List<String> getPrefixList(String s)
    {
        Trie trie = search(s);
        if(trie==null)
            return null;
        return trie.words;
    }

}

public class TrieAlg
{
    public static  void main(String [] args)
    {
        Trie trie = new Trie();

        trie.add("sid");
        trie.add("sidhu");
        trie.add("sidhardh");
        trie.add("saket");
        trie.add("subharajitha");
        trie.add("app");
        trie.add("apple");
        trie.add("apples");

        if(!trie.startsWith("sid"))
             System.err.println("not sating with sid");
        if(trie.startsWith("sudarshan"))
            System.err.println("not sating with sudarshan");
        if(!trie.startsWith("a"))
            System.err.println("not sating with a");

        if(!trie.isPresent("sid"))
            System.err.println("sid not present");
        if(trie.isPresent("s"))
            System.err.println("s is present");

        System.out.println(Arrays.toString(trie.getPrefixList("sid").toArray()));


    }
}
