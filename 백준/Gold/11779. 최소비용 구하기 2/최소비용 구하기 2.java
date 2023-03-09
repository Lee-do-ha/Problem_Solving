import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] distnace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        PriorityQueue<int[]> PQ = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        distnace = new int[N+1];
        ArrayList<int[]>[] List = new ArrayList[N+1];
        int[] preNode = new int[N+1];


        for(int i = 1 ; i < N+1 ; i++){
            distnace[i] = Integer.MAX_VALUE;
            List[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List[a].add(new int[] {b,c});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        distnace[start] = 0;
        preNode[start] = 0;
        PQ.add(new int[] {start, distnace[start]});

        while(!PQ.isEmpty()){
            int[] A = PQ.poll();
            
            if(A[0] == end) break;

            if(!List[A[0]].isEmpty()){
                for(int i = 0 ; i < List[A[0]].size() ; i++){
                    if(distnace[A[0]] + List[A[0]].get(i)[1] < distnace[List[A[0]].get(i)[0]]){
                        distnace[List[A[0]].get(i)[0]] = distnace[A[0]] + List[A[0]].get(i)[1];
                        PQ.add(new int[] {List[A[0]].get(i)[0], distnace[List[A[0]].get(i)[0]]});
                        preNode[List[A[0]].get(i)[0]] = A[0];
                    }
                }
            }
        }

        int result = distnace[end];

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(end);

        while(end != 0){
            arr.add(preNode[end]);
            end = preNode[end];
        }

        System.out.println(result);
        System.out.println(arr.size()-1);
        for(int i = arr.size()-2 ; i >= 0 ; i--){
            System.out.print(arr.get(i) + " ");
        }
    }
}