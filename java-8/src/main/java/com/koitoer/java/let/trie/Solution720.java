package com.koitoer.java.let.trie;

import java.util.HashMap;
import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution720 {

    @Test
    public void test() {
        String[] a = new String[] { "w", "wo", "wor", "worl", "world" };
        Assertions.assertThat(new Solution720().longestWord(a)).isEqualTo("world");
    }

    @Test
    public void test2() {
        String[] a = new String[] { "a", "al", "albaca", "ala", "alabama" };
        Assertions.assertThat(new Solution720().longestWord(a)).isEqualTo("ala");
    }

    @Test
    public void test3() {
        String[] a = new String[] { "a", "al", "alab", "ala", "alabama", "alaba", "alabam" };
        Assertions.assertThat(new Solution720().longestWord(a)).isEqualTo("alabama");
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for (String word : words) {
            trie.insert(word, ++index); //indexed by 1
        }
        trie.words = words;
        return trie.depthFirstSearch();
    }

}

class Trie {

    Node root;

    String[] words;

    public Trie() {
        root = new Node('0');
    }

    public void insert(String word, int index) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            cur.children.putIfAbsent(c, new Node(c));
            cur = cur.children.get(c);
        }
        cur.end = index;
    }

    public String depthFirstSearch() {

        //Initialize the variables and stack.
        //Ans will be our response.
        String ans = "";
        Stack<Node> stack = new Stack();
        stack.push(root);

        while (!stack.empty()) {
            Node node = stack.pop();

            //If this is a word or the root node let's iterate
            //In multiple character words we will have in the intermediate node the end == 0 as they are not words
            //We won't go depth in those branches
            if (node.end > 0 || node == root) {

                //Avoid the root node.
                if (node != root) {
                    //Bring the complete word.
                    String word = words[node.end - 1];

                    //If the word is larger than the previous answer OR
                    //The word is equal but lexicographically lower then
                    //Replace the ans
                    if (word.length() > ans.length() ||
                        word.length() == ans.length() && word.compareTo(ans) < 0) {
                        ans = word;
                    }
                }

                //Start DFS
                for (Node nei : node.children.values()) {
                    stack.push(nei);
                }
            }
        }

        //Just return the answer.
        return ans;
    }
}

class Node {

    char c;

    HashMap<Character, Node> children = new HashMap();

    int end;

    public Node(char c) {
        this.c = c;
    }
}

