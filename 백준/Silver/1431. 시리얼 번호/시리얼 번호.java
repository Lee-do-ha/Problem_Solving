import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class word implements Comparable<word>{
        int length, sum;
        String word;

        public word(int length, int sum, String word) {
            this.length = length;
            this.sum = sum;
            this.word = word;
        }

        @Override
        public String toString() {
            return "word{" +
                    "length=" + length +
                    ", sum=" + sum +
                    ", word='" + word + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Main.word o) {
            if (this.length != o.length) {
                return this.length - o.length;
            } else {
                if (this.sum != o.sum) {
                    return this.sum - o.sum;
                } else {
                    return this.word.compareTo(o.word);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<word> words = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            int sum = 0;
            for (int k = 0; k < str.length(); k++) {
                char cur = str.charAt(k);
                if(cur >= '1' && cur <= '9') sum += Integer.parseInt(String.valueOf(cur));
            }
            words.add(new word(str.length(), sum, str));
        }

        while (!words.isEmpty()) {
            sb.append(words.poll().word).append("\n");
        }
        System.out.println(sb);
    }

}