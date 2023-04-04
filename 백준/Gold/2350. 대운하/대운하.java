import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        // 각 지점별 이동할 터널의 폭
        int[][] distance = new int[N][N];
        
        for(int i = 0 ; i < M ; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	distance[a-1][b-1] = c;
        	distance[b-1][a-1] = c;
        }

        // 플로이드로 터널의 최대의 폭으로 저장
        for(int i = 0 ; i < N ; i++) {
        	for(int j = 0 ; j < N ; j++) {
        		if(i == j) continue;
        		for(int k = 0 ; k < N ; k++) {
        			if(j == k || i == k) continue;
        			
        			if(distance[j][k] < Math.min(distance[j][i], distance[i][k])) {
        				distance[j][k] = Math.min(distance[j][i], distance[i][k]);
        				distance[k][j] = distance[j][k];
        			}
        		}
        	}
        }
        
        for(int i = 0 ; i < L ; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	sb.append(distance[a-1][b-1]).append("\n");
        }
        System.out.println(sb);
    }
}
