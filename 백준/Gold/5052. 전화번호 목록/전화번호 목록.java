import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int test = 0 ; test < T ; test++){
            int K = Integer.parseInt(br.readLine());
            String[] str = new String[K];

            for(int i = 0 ; i < K ; i++){
                str[i] = br.readLine();
            }

            Arrays.sort(str);
            if(Stringcheck(K,str) == false){
                sb.append("NO").append("\n");
            }else{
                sb.append("YES").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean Stringcheck(int n, String[] s){
        for(int i = 0 ; i < s.length-1 ; i++){
            if(s[i+1].startsWith(s[i])){
                return false;
            }
        }
        return true;
    }
}