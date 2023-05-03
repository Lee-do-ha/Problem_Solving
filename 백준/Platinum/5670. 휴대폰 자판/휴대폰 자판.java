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

        // 자바 EOF 처리하기위한 문자열
        String input = "";

        // EOF 조건문
        while((input = br.readLine()) != null && !input.isEmpty()){

            // 트라이 구성
            Trie trie = new Trie();
            int N = Integer.parseInt(input);

            String[] arr = new String[N];

            // 문자열 들어오는 것 트라이에 추가해주기
            for(int i = 0 ; i < arr.length ; i++){
                arr[i] = br.readLine();
                trie.push(arr[i]);
            }

            // 찾는데 필요한 클릭 수
            double find = 0;
            
            // 총 클릭 수 구하기
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

    // 트라이
    static class Trie{

        // 현재 트라이가 문자열이 끝나는 부분인지 체크할 boolean
        private boolean isLeaf;
        
        // 다음 트라이 문자 저장할 Map
        private Map<Character, Trie> children;

        // Trie 안에 다시 새로만들 Trie 생성자
        public Trie(){
            isLeaf = false;
            children = new HashMap<>();
        }

        // 문자열 저장하는 메소드
        private void push(String key){

            // root 트라이에서부터 시작
            Trie cur = this;

            // 들어온 문자열에 대해 각 문자마다 탐색
            for(Character o : key.toCharArray()){
                
                // 현재 트라이에서 다음 문자가 없다면 새로 넣어주기
                cur.children.putIfAbsent(o, new Trie());
                
                // 다음 트라이로 이동
                cur = cur.children.get(o);
            }

            // for문이 끝났다면 문자열이 끝났으므로 현재 트라이는 true
            cur.isLeaf = true;
        }

        // 문자열 찾는데 필요한 클릭 수 찾을 메소드
        private int find(String key){

            // 총 클릭수 
            int ans = 0;

            // root 트라이에서부터 시작
            Trie cur = this;

            // 첫번째 문자로 이동
            cur = cur.children.get(key.toCharArray()[0]);
            
            // 첫번째 문자는 반드시 클릭해야하므로 값 추가
            ans++;

            // 문자열 2번째부터 자동완성 기능 사용
            for(int i = 1 ; i < key.toCharArray().length ; i++){
                // 문자열끝나는 노드가 아니고 자식 노드가 1개라면 자동완성되므로 바로 다음 
                if(cur.children.size() == 1 && !cur.isLeaf){
                    cur = cur.children.get(key.toCharArray()[i]);
                    // 위의 경우에 해당하지않는다면 클릭해야 하므로 클릭 수 추가하고 다음 문자로 이동
                }else{
                    ans++;
                    cur = cur.children.get(key.toCharArray()[i]);
                }
            }
            return ans;
        }

    }
}
