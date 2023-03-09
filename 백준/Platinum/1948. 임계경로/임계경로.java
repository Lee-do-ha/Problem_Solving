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
	
	static class node{
		int department, weight;
		public node(int department, int weight) {
			super();
			this.department = department;
			this.weight = weight;
		}
	}
	
	static class departmentNode{
		int distance;
		public departmentNode(int distance) {
			super();
			this.distance = distance;
		}
	}
	
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
		
		visited = new boolean[N+1];
		List = new ArrayList[N+1];
		backList = new ArrayList[N+1];
		int[] targetNode = new int[N+1];
		departmentNode[] maxNode = new departmentNode[N+1];
		
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
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		for(int i = 1 ; i < N+1 ; i++) {
			if(i == start) {
				maxNode[i] = new departmentNode(0);
			}else {
				maxNode[i] = new departmentNode(Integer.MIN_VALUE);
			}
		}
		
		queue = new LinkedList<Integer>();
		queue.add(start);
				
		while(!queue.isEmpty()) {
			
			int curNode = queue.poll();
			
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
		
		System.out.println(maxNode[end].distance);
		
		int num = 0;
		
		queue.add(end);
		
		boolean[] visited = new boolean[N+1];
		
		while(!queue.isEmpty()) {
			
			int curNode = queue.poll();
			
			if(visited[curNode] == false) {
				if(!backList[curNode].isEmpty()) {
					for(int i = 0 ; i < backList[curNode].size() ; i++) {
						if(maxNode[curNode].distance - backList[curNode].get(i).weight == maxNode[backList[curNode].get(i).department].distance ) {
							queue.add(backList[curNode].get(i).department);
							num++;
//							System.out.println(curNode + " " + backList[curNode].get(i).department);
						}
					}
				}
				visited[curNode] = true;
			}
		}
		System.out.println(num);
	}
}
/*
5
7
1 2 1
1 3 3
2 3 2
2 4 1
2 5 3
3 5 1
4 5 1
1 5
*/