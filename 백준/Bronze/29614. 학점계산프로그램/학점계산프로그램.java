import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("A+", 4.5);
        hashMap.put("A", 4.0);
        hashMap.put("B+", 3.5);
        hashMap.put("B", 3.0);
        hashMap.put("C+", 2.5);
        hashMap.put("C", 2.0);
        hashMap.put("D+", 1.5);
        hashMap.put("D", 1.0);
        hashMap.put("F", 0.0);

        double score = 0;
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            String cur = String.valueOf(str.charAt(i));

            if (cur.equals("A") || cur.equals("B") || cur.equals("C") || cur.equals("D")) {
                if (i + 1 < str.length() && str.charAt(i + 1) == '+') {
                    cur += "+";
                    score += hashMap.get(cur);
                    i++;
                    count++;
                } else {
                    score += hashMap.get(cur);
                    count++;
                }
            } else {
                count++;
            }
        }
        System.out.println(score / count);
    }

}