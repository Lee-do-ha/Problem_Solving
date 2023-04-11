import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] weight, parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0){
                break;
            }

            // 상대적 무게 저장할 배열
            weight = new int[N+1];
            // 조상 저장할 배열
            parents = new int[N+1];

            for(int i = 1 ; i < N+1 ; i++){
                parents[i] = i;
            }

            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());

                String k = st.nextToken();

                if(k.equals("!")){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    // a와 b 결합
                    unionSet(a, b, c);
                }else if(k.equals("?")){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    
                    // 같은 집합이 아닌경우
                    if(findSet(a) != findSet(b)){
                        sb.append("UNKNOWN").append("\n");
                        // 같은 집합에 있는 경우
                    }else{
                        sb.append(weight[b] - weight[a]).append("\n");
                    }
                }
            }
        }
        System.out.println(sb);
    }

    // 조상 찾기 메소드
    private static int findSet(int a){
        // 이미 같으면 바로 리턴
        if(a == parents[a]) return a;

        // 계속 조상 찾아가서 상대적 무게 차이 계속 합산해주기
        int pa = findSet(parents[a]);
        weight[a] += weight[parents[a]];
        return parents[a] = pa;
    }

    // 조상 결합 메소드
    private static void unionSet(int a, int b, int c){
        int pa = findSet(a);
        int pb = findSet(b);

        if(pa == pb) return;

        parents[pb] = pa;
        // 조상결합하는 경우 현재 가지고있는 무게와 상대적 무게 비교해서 
        weight[pb] += (c - weight[b] + weight[a]);
    }
}
