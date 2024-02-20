import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        Stack<Character> stack = new Stack<Character>();

        while (true) {
            String str = br.readLine();
            boolean flag = true;
            if(str.equals(".")) break;

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '[' || str.charAt(i) == '(') {
                    stack.add(str.charAt(i));
                } else if (str.charAt(i) == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        flag = false;
                        break;
                    }
                } else if (str.charAt(i) == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag && stack.isEmpty()) {
                sb.append("yes").append("\n");
            } else {
                sb.append("no").append("\n");
            }

            stack.clear();

        }
        System.out.println(sb);
    }

}