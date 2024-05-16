import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int cur = 0;

        for (int i = 0; i < N; i++) {
            stack.push(Integer.parseInt(br.readLine()));
        }

        int ans =0;

        while (!stack.isEmpty()) {
            if (cur == 0) {
                cur = stack.pop();
                ans++;
            } else {
                if (stack.peek() > cur) {
                    cur = stack.pop();
                    ans++;
                } else {
                    stack.pop();
                }
            }
        }
        System.out.println(ans);
    }
}