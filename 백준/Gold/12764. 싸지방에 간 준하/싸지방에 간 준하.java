import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    static class user implements Comparable<user>{
        int start, end;

        public user(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(user o) {
            return this.start - o.start;
        }
    }

    static int[] use;
    static int[] using;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<user> pq = new PriorityQueue<>();
        use = new int[1000001];
        using = new int[1000001];

        int a = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < a ; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(new user(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int max = 0;

        while(!pq.isEmpty()){
            user cur = pq.poll();

            for(int i = 1 ; i < 1000001 ; i++){
                if(using[i] <= cur.start){
                    use[i] += 1;
                    using[i] = cur.end;
                    max = Math.max(max, i);
                    break;
                }
            }

        }

        System.out.println(max);
        for(int i = 1 ; i <= max ; i++){
            System.out.print(use[i] + " ");
        }

    }
}