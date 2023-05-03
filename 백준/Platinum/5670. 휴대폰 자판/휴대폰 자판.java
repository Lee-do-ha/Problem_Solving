import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        String input = "";


        while((input = br.readLine()) != null && !input.isEmpty()){

            Trie trie = new Trie();
            int N = Integer.parseInt(input);

            String[] arr = new String[N];

            for(int i = 0 ; i < arr.length ; i++){
                arr[i] = br.readLine();
                trie.push(arr[i]);
            }

            double find = 0;
            for(int i = 0 ; i < N ; i++){
                int num = trie.find(arr[i]);
                find += num;
            }

            double ans = 0;
            ans = find / (double)arr.length;
            sb.append(String.format("%.2f", ans)).append("\n");
        }
        System.out.println(sb);
    }

    static class Trie{

        private boolean isLeaf;
        private Map<Character, Trie> children;

        public Trie(){
            isLeaf = false;
            children = new HashMap<>();
        }

        private void push(String key){

            Trie cur = this;

            for(Character o : key.toCharArray()){
                cur.children.putIfAbsent(o, new Trie());
                cur = cur.children.get(o);
            }

            cur.isLeaf = true;
        }

        private int find(String key){

            int ans = 0;

            Trie cur = this;

            cur = cur.children.get(key.toCharArray()[0]);
            ans++;

            for(int i = 1 ; i < key.toCharArray().length ; i++){
                if(cur.children.size() == 1 && !cur.isLeaf){
                    cur = cur.children.get(key.toCharArray()[i]);
                }else{
                    ans++;
                    cur = cur.children.get(key.toCharArray()[i]);
                }
            }
            return ans;
        }

    }
}