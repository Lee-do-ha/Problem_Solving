import java.io.*;
import java.util.*;

public class Main {

    static String teamName;
    static HashSet<String> nickHash;
    static final int SIZE = 26;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Trie color = new Trie();
        nickHash = new HashSet<>();

        for(int i = 0 ; i < N ; i++){
            color.push(br.readLine());
        }

        for(int i = 0 ; i < M ; i++){
            nickHash.add(br.readLine());
        }

        int K = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < K ; i++){
            teamName = br.readLine();

            if(color.find(teamName)){
                sb.append("Yes").append("\n");
            }else{
                sb.append("No").append("\n");
            }

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Trie{
        TrieNode root;

        public Trie(){
            this.root = new TrieNode();
            this.root.cur = ' ';
        }

        private void push(String key){
            TrieNode cur = this.root;

            for(Character o : key.toCharArray()){
                if(cur.child[o-'a'] == null){
                    cur.child[o-'a'] = new TrieNode();
                }
                cur = cur.child[o-'a'];
            }

            cur.isLeaf = true;
        }

        private boolean find(String key){
            TrieNode cur = this.root;

            int time = 0;

            for(Character o : key.toCharArray()){
                if(cur.child[o-'a'] != null){
                    cur = cur.child[o-'a'];
                    if(cur.isLeaf == true && nickHash.contains(key.substring(time+1))){
                        return true;
                    }
                }else{
                    break;
                }
                time++;
            }
            return false;
        }

        private static class TrieNode{
            TrieNode[] child = new TrieNode[SIZE];
            boolean isLeaf = false;
            char cur;
        }
    }

}