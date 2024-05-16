import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class word implements Comparable<word>{
        String word;
        int count;

        public word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(Main.word o) {
            if (this.count == o.count) {
                if (this.word.length() == o.word.length()) {
                    return this.word.compareTo(o.word);
                }
                return o.word.length() - this.word.length();
            }
            return o.count - this.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<word> priorityQueue = new PriorityQueue<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            if(str.length() < M) continue;

            hashMap.putIfAbsent(str, 0);
            hashMap.put(str, hashMap.get(str) + 1);
        }

        for (String s : hashMap.keySet()) {
            priorityQueue.add(new word(s, hashMap.get(s)));
        }

        while (!priorityQueue.isEmpty()) {
            word cur = priorityQueue.poll();
            bw.write(cur.word + "\n");
        }
        bw.flush();
    }

}