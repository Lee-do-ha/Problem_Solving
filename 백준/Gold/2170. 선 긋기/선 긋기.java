import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class line implements Comparable<line>{
        int start, end;

        public line(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public int compareTo(line o) {
            return this.start - o.start;
        }
    }

    static PriorityQueue<line> PQ;
    static int result;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PQ = new PriorityQueue<>();


        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            PQ.add(new line(a,b));
        }
        result = 0;

        flag = true;

        while(flag){
            Liner();
        }

        System.out.println(result);
    }

    private static void Liner(){
        if(PQ.isEmpty()){
            flag = false;
            return;
        }

        int start = 0;
        int end = 0;

        line A = PQ.poll();
        start = A.start;
        end = A.end;

        int sum = end - start;

        while(true){
            if(PQ.isEmpty()){
                flag = false;
                result += sum;
                break;
            }else{
                if(PQ.peek().start >= start && PQ.peek().start <= end){
                    if(PQ.peek().end > end){
                        end = PQ.peek().end;
                    }
                    sum = end - start;
                    PQ.poll();
                }else{
                    result += sum;
                    return;
                }
            }
        }
    }
}