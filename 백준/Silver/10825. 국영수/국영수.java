import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class student implements Comparable<student>{
        String name;
        int a, b, c;

        public student(String name, int a, int b, int c) {
            this.name = name;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(student o) {
            if (this.a == o.a && this.b == o.b && this.c == o.c) {
                return this.name.compareTo(o.name);
            } else if (this.a == o.a && this.b == o.b) {
                return o.c - this.c;
            } else if (this.a == o.a) {
                return this.b - o.b;
            } else {
                return o.a - this.a;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<student> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            priorityQueue.add(new student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while (!priorityQueue.isEmpty()) {
            student cur = priorityQueue.poll();
            sb.append(cur.name).append("\n");
        }

        System.out.println(sb);

    }

}