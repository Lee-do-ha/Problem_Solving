import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    static class Class implements Comparable<Class>{
        int p, q;

        public Class(int p, int q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public int compareTo(Class o) {
            return o.p - this.p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Class> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        boolean[] visited = new boolean[10001];

        int ans = 0;

        while(!pq.isEmpty()){
            Class cur = pq.poll();
            
            while(cur.q > 0){
                if(visited[cur.q]) {
                    cur.q--;
                    continue;
                }
                    
                visited[cur.q] = true;
                ans += cur.p;
                break;

            }

        }

        System.out.println(ans);
    }
}