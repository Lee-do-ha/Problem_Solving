import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Trie {
        private TreeMap<String, Trie> child;
        private boolean isFinal;

        Trie() {
            this.child = new TreeMap<>(Comparator.reverseOrder());
            this.isFinal = false;
        }

        private void insert(String str) {
            Trie cur = this;
            String[] arr = str.split("\\\\");

            for (int i = 0; i < arr.length; i++) {
                if (!cur.child.containsKey(arr[i])) {
                    cur.child.put(arr[i], new Trie());
                }
                cur = cur.child.get(arr[i]);
            }
            cur.isFinal = true;
        }

        public void print(StringBuilder sb) {
            Stack<node> stack = new Stack<>();

            stack.push(new node(null, this, -1));

            while (!stack.isEmpty()) {
                node cur = stack.pop();

                if (cur.str != null) {
                    for (int i = 0; i < cur.idx; i++) {
                        sb.append(" ");
                    }
                    sb.append(cur.str).append("\n");
                }

                for (String str : cur.trie.child.keySet()) {
                    stack.push(new node(str, cur.trie.child.get(str), cur.idx + 1));
                }
            }
        }
    }
    static class node{
        String str;
        Trie trie;
        int idx;
        public node(String str, Trie trie, int idx) {
            this.str = str;
            this.trie = trie;
            this.idx = idx;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        Trie trie = new Trie();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            trie.insert(str);
        }

        trie.print(sb);

        System.out.println(sb);

    }

}