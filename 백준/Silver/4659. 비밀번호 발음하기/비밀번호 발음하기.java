import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        HashSet<Character> hashSet = new HashSet<>();
        hashSet.add('a');hashSet.add('e');
        hashSet.add('i');hashSet.add('o');hashSet.add('u');

        while (true) {
            String input = br.readLine();

            if(input.equals("end")) break;

            boolean haveAEIOU = false;
            int a = 0;
            int b = 0;
            boolean flag1 = true;
            boolean flag2 = true;

            for (int i = 0; i < input.length(); i++) {
                char cur = input.charAt(i);

                if (hashSet.contains(cur)) {
                    haveAEIOU = true;
                    a++;
                    b = 0;
                } else {
                    a = 0;
                    b++;
                }

                if (i > 0 && cur == input.charAt(i-1) && cur != 'e' && cur != 'o') {
                    flag2 = false;
                }

                if (a >= 3 || b >= 3) {
                    flag1 = false;
                }

                if(!flag1 || !flag2) break;

            }

            if (!haveAEIOU || !flag1 || !flag2) {
                sb.append("<" + input + "> " + "is not acceptable.").append("\n");
            } else {
                sb.append("<" + input + "> " + "is acceptable.").append("\n");
            }

        }

        System.out.println(sb);
    }
}