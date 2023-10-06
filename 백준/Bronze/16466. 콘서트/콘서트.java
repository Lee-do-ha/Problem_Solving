import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        boolean[] sell = new boolean[1000001];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            sell[Integer.parseInt(st.nextToken())] = true;
        }
        
        int ans = 0;
        
        for(int i = 1 ; i < 1000001 ; i++){
            if(!sell[i]){
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }
}