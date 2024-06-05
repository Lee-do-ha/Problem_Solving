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

        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);

            if (cur < 'A' || cur > 'Z' && cur <'a' || cur > 'z') continue;

            if (cur >= 'a' && cur <= 'z') {
                cur -= 32;
            }

            hashMap.putIfAbsent(cur, 0);
            hashMap.replace(cur, hashMap.get(cur) + 1);
        }
        
        char start = 'A';
        for (int i = 0; i < 26; i++) {
            sb.append(start + " | ");

            int count = hashMap.getOrDefault(start, 0);

            for (int k = 0; k < count; k++) {
                sb.append("*");
            }
            sb.append("\n");

            start++;
        }

        System.out.println(sb);
    }

}