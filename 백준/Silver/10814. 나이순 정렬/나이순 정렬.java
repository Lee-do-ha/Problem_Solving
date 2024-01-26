import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class human{
        int age, index;
        String name;

        public human(int age, String name, int index) {
            this.age = age;
            this.name = name;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<human> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.age == o2.age) {
                return o1.index - o2.index;
            }
            return o1.age - o2.age;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new human(Integer.parseInt(st.nextToken()), st.nextToken(), i));
        }

        while (!pq.isEmpty()) {
            human cur = pq.poll();
            System.out.println(cur.age + " " + cur.name);
        }

    }

}