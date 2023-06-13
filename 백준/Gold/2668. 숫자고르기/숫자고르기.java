import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;
    static int[] arr;
    static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        // 뽑히는 숫자 배열
        arr = new int[N+1];
        // 숫자 방문 체크할 배열
        visited = new boolean[N+1];
        // 정답들 저장할 리스트
        ans = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            arr[i+1] = Integer.parseInt(br.readLine());
        }

        for(int i = 1 ; i < N+1 ; i++){
            // 방문 체크하고 dfs진행
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        // 오름차순 정렬
        Collections.sort(ans);

        sb.append(ans.size()).append("\n");
        for(int i : ans){
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }
    private static void dfs(int start, int target){
        // 아직 방문안했다면 dfs계속 진행
        if(!visited[arr[start]]){
            visited[arr[start]] = true;
            dfs(arr[start], target);
            visited[arr[start]] = false;
        }
        // 사이클 완성되면 target값 정답 리스트에 추가
        if(arr[start] == target) {
            ans.add(target);
        }
    }
}