import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    static class po{
        int x, y;

        public po(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            po p = (po) obj;
            return p.x == this.x && p.y == this.y;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(x) + Objects.hashCode(y);
        }

        @Override
        public String toString() {
            return "po{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<po, Integer> hash = new HashMap<>();

        int N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int k = pas(a,b);
            k = k>=0 ? k : -k;
            po cur = new po(a/k, b/k);

            if(hash.containsKey(cur)){
                hash.replace(cur, hash.get(cur)+1);
            }else{
                hash.put(cur, 1);
            }
        }
        int max = 0;


        for(po a: hash.keySet()){
//            System.out.println(a + " " + hash.get(a));
            if(hash.get(a) > max){
                max = hash.get(a);
            }
        }
        System.out.println(max);
    }

    public static int pas(int a, int b){
        while(b != 0){
            int temp = a%b;
            a = b;
            b = temp;
        }
        return a;
    }
}