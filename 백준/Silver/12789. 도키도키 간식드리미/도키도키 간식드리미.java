import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        boolean[] visited = new boolean[N+1];
        visited[0] = true;

        boolean flag = true;

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty()) {
                if (visited[stack.peek() - 1]) {
                    visited[stack.pop()] = true;
                    continue;
                }
                break;
            }

            int input = Integer.parseInt(st.nextToken());

            if (visited[input - 1]) {
                visited[input] = true;

                while (!stack.isEmpty()) {
                    if (visited[stack.peek() - 1]) {
                        visited[stack.pop()] = true;
                        continue;
                    }
                    break;
                }

            } else {
                stack.add(input);
            }

        }



        if (stack.isEmpty()) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }

}