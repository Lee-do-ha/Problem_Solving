import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    // 선 시작점과 끝점 저장할 클래스
    static class line implements Comparable<line>{
        int start, end;

        public line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 그리디 사고로 해결하기 위해 시작점을 기준으로 정렬
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
        // 총 그을수 있는 선의 길이
        result = 0;
        
        // 모든 선 사용하기위해 flag 선언하고 true동안 반복
        flag = true;

        while(flag){
            Liner();
        }

        System.out.println(result);
    }
    
    // 큐가 비었다면 다 그은것이므로 flag를 false로 선언하고 return
    private static void Liner(){
        if(PQ.isEmpty()){
            flag = false;
            return;
        }
        
        // 초기시작점과 끝점은 첫 큐에서 나온 점의 시작점과 끝점
        int start = 0;
        int end = 0;

        line A = PQ.poll();
        start = A.start;
        end = A.end;
        
        // 초기 길이 = 첫 점의 끝점 - 첫 점의 시작점
        int sum = end - start;

        // 현재 선의 길이에서 이어지지 않는 선이 나올떄까지 반복
        while(true){
            // 큐가 비었으면 flag를 false로 선언하고 현재 선의 길이 결과에 더해주기
            if(PQ.isEmpty()){
                flag = false;
                result += sum;
                break;
            }else{
                // 다음 선이 현재의 선에 이어지는 경우
                if(PQ.peek().start >= start && PQ.peek().start <= end){
                    // 다음 선의 끝점이 현재까지 이은 선의 끝점 보다 길때 끝점 갱신
                    if(PQ.peek().end > end){
                        end = PQ.peek().end;
                    }
                    // 현재까지 그은 선의 길이
                    sum = end - start;
                    // 길이 다 구했으므로 큐에서 빼기
                    PQ.poll();
                    // 다음 선이 현재의 선에 이어지지않는경우 현재까지 그은 선의 길이 결과에 더해주고 다시 
                }else{
                    result += sum;
                    return;
                }
            }
        }
    }
}
