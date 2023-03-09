import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
    // 도착 정점과 가중치 저장
	static class node{
		int department, weight;
		public node(int department, int weight) {
			super();
			this.department = department;
			this.weight = weight;
		}
	}
	
    // 도착 정점 거리
	static class departmentNode{
		int distance;
		public departmentNode(int distance) {
			super();
			this.distance = distance;
		}
	}
	
    // 출발 정점과 도착 정점 경로 저장
	static class path{
		int start, end;
		public path(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			if(!(obj instanceof path)) return false;
			
			path p = (path)obj;
			
			return this.start == p.start && this.end == p.end;
		}
		
		@Override
		public int hashCode() {
			
			return Objects.hash(start, end);
		}
	}

	static int N, M, result, cityNum;
	static Queue<Integer> queue;
	static boolean[] visited;
	static ArrayList<node>[] List, backList;
	static int answer, answerNum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
        // 방문체크 배열
		visited = new boolean[N+1];
        // 연결된 정점 저장할 연결리스트
		List = new ArrayList[N+1];
        // 역추적하기위한 연결리스트
		backList = new ArrayList[N+1];
        // 도착 정점 저장할 배열
		int[] targetNode = new int[N+1];
		departmentNode[] maxNode = new departmentNode[N+1];
		
        // 연결리스트들 초기화
		for(int i = 1 ; i < N+1 ; i++){
			List[i] = new ArrayList<>();
			backList[i] = new ArrayList<>();
		}
		
        
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			List[a].add(new node(b, c));
			backList[b].add(new node(a, c));
			targetNode[b]++;
		}
		
        // 시작 정점과 도착 정점 입력 받기
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
        // 시작 정점은 거리 0, 아닌 정점들은 최소값으로 초기화
		for(int i = 1 ; i < N+1 ; i++) {
			if(i == start) {
				maxNode[i] = new departmentNode(0);
			}else {
				maxNode[i] = new departmentNode(Integer.MIN_VALUE);
			}
		}
		
        // 큐에 시작 정점 인덱스 넣고 시작
		queue = new LinkedList<Integer>();
		queue.add(start);
				
		while(!queue.isEmpty()) {
			
			int curNode = queue.poll();
			
            // 큐에 연결된 정점이 있는 경우 연결된 정점으로 가는 길이 더 가중치가 큰 경로가 되는 경우에만 
            // 해당 정점 큐에 넣고 다시 탐색하면서 최장 경로 배열 갱신
			if(!List[curNode].isEmpty()) {
				for(int i = 0 ; i < List[curNode].size() ; i++) {
					if(maxNode[curNode].distance + List[curNode].get(i).weight > maxNode[List[curNode].get(i).department].distance) {
						maxNode[List[curNode].get(i).department].distance = maxNode[curNode].distance + List[curNode].get(i).weight;
						queue.add(List[curNode].get(i).department);
					}else if(maxNode[curNode].distance + List[curNode].get(i).weight == maxNode[List[curNode].get(i).department].distance) {
						
					}
				}
			}
		}
		
        // 도착 정점의 최장 경로
		System.out.println(maxNode[end].distance);
		
		int num = 0;
		
        // 역추적하기위해 도착 정점 큐에 넣고 시작
		queue.add(end);
		
        // 이미 지나온 길 체크하기 위해 boolean 배열 사용
		boolean[] visited = new boolean[N+1];
		
        // 도착 정점 넣고 역으로 올라가면서 연결된 정점까지의 최장 거리가 현재 정점에서의 가중치 뺀 값과 같으면
        // 해당 정점이 최장 경로로 오는 경로이므로 큐에 넣고 갯수 추가
		while(!queue.isEmpty()) {
			
			int curNode = queue.poll();
			
			if(visited[curNode] == false) {
				if(!backList[curNode].isEmpty()) {
					for(int i = 0 ; i < backList[curNode].size() ; i++) {
						if(maxNode[curNode].distance - backList[curNode].get(i).weight == maxNode[backList[curNode].get(i).department].distance ) {
							queue.add(backList[curNode].get(i).department);
							num++;
						}
					}
				}
				visited[curNode] = true;
			}
		}
		System.out.println(num);
	}
}
