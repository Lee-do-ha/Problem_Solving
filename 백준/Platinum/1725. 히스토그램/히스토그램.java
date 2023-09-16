import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+2];
        for(int i =1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        int ans = 0;

        for(int i = 1 ; i < N+2 ; i++){

            while(!stack.isEmpty()){
                int top = stack.peek();
                if(arr[top] <= arr[i]) break;

                stack.pop();
                ans = Math.max(ans, arr[top] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        System.out.println(ans);

    }
}