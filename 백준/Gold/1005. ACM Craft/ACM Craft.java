import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test = 0 ; test < T ; test++) {
			st = new StringTokenizer(br.readLine());
			// 건물의 갯수
			int N = Integer.parseInt(st.nextToken());
			// 단방향 그래프 저장할 인접리스트
			ArrayList<Integer>[] List = new ArrayList[N];
			// 본인 정점에 들어오는 단방향의 갯수
			int[] inNode = new int[N];
			for(int i = 0 ; i < N ; i++) {
				List[i] = new ArrayList<>();
			}
			// 규칙 갯수
			int M = Integer.parseInt(st.nextToken());
			
			// 각 건물당 건설시간
			int[] buildings = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				buildings[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// 단방향 그래프 연결
				List[a-1].add(b-1);
				// 들어오는 노드 갯수 추가
				inNode[b-1]++;
			}
			
			// 해당 정점까지 걸리는 비용
			int[] dist = new int[N];
			for(int i = 0 ; i < N ; i++) {
				dist[i] = Integer.MIN_VALUE;
			}
			
			// 도착점
			int arrive = Integer.parseInt(br.readLine());
			
			Queue<Integer> queue = new LinkedList<>();
			
			// 들어오는 노드가 0개인 정점들은 큐에 넣고 시작
			for(int i = 0 ; i < N ; i++) {
				if(inNode[i] == 0) {
					queue.add(i);
					dist[i] = buildings[i];
				}
			}

			// 위상정렬 
			while(!queue.isEmpty()) {
				int l = queue.poll();
				
				if(!List[l].isEmpty()) {
					for(int i = 0 ; i < List[l].size() ; i++) {
						// 현재가지고 있는 다음 정점까지의 비용과 현재 정점에서 다음 정점으로 가는 비용 비교
						if(dist[List[l].get(i)] < dist[l] + buildings[List[l].get(i)]) {
							dist[List[l].get(i)] = dist[l] + buildings[List[l].get(i)];
						}
						// 들어오는 노드 갯수 1개 줄이기
						inNode[List[l].get(i)]--;
						// 들어오는 노드의 갯수가 0개가 된다면 큐에 넣기
						if(inNode[List[l].get(i)] == 0) {
							queue.add(List[l].get(i));
						}
					}
				}
			}
			sb.append(dist[arrive-1]).append("\n");
		}
		System.out.println(sb);
	}

}