import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int k = Integer.parseInt(st.nextToken());

            if(hashMap.containsKey(k)){
                hashMap.put(k, hashMap.get(k) + 1);
            }else{
                hashMap.put(k, 1);
            }
        }

        int ans = Integer.parseInt(br.readLine());

        if(hashMap.containsKey(ans)){
            System.out.println(hashMap.get(ans));
        }else{
            System.out.println(0);
        }

    }
}