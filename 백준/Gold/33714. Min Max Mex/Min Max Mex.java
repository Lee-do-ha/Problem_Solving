import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	// 최솟값 찾기 위해 값과 해당 값이 나온 횟수 저장할 class
	static class node implements Comparable<node>{
		int idx, number;

		public node(int idx, int number) {
			super();
			this.idx = idx;
			this.number = number;
		}

		@Override
		public int compareTo(node o) {
			return this.idx - o.idx;
		}
		
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 숫자와 갯수 저장할 Map
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
						
			if(!hashMap.keySet().contains(cur)) {
				hashMap.put(cur, 0);
			}
			hashMap.replace(cur, hashMap.get(cur) + 1);
		}
		
		// 최종적으로 출력할 값들 초기화
		int min = 200000;
		int max = 0;
		
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		for(int k : hashMap.keySet()) {
			pq.add(new node(k, hashMap.get(k)));
		}
		
		// 이전에 나온 수 
		int pre = -1;
		while(!pq.isEmpty()) {
			node cur = pq.poll();
						
			// 만약 이전에 나온 수의 바로 다음 수가 아니라면 최솟값은 이전 수의 바로 다음 값
			if(cur.idx != pre + 1) {
				min = pre + 1;
				break;
			}
			
			// 연속된 수라면 이전 수는 현재 수로 변환
			pre++;
			// 현재 수를 만약 지울 수 있다면 최솟값은 현재 수
			if(M >= cur.number) {
				min = cur.idx;
				break;
			// 현재 수를 지울 수 없지만 Queue가 비어있을땐 다음 수
			} else {
				if(pq.isEmpty()) {
					min = cur.idx + 1;
				}
			}
		}
		
		sb.append(min).append("\n");
		
		pq.clear();
		// 입력값중의 최댓값
		int MAX = 0;
		for(int k : hashMap.keySet()) {
			pq.add(new node(k, hashMap.get(k)));
			MAX = Math.max(MAX, k);
		}
		
		pre = -1;
		// 큐를 탐색하면서 조건에 맞는 수를 찾았는지 체크
		boolean flag = false;
		while(!pq.isEmpty()) {
			node cur = pq.poll();
						
			// 현재 수가 이전 수의 다음 수가 아니라면
			if(cur.idx != pre + 1) {
				// 비어있는 거리를 이동할 수 없는 경우
				if(M < cur.idx - pre - 1) {
					// 남아있는 M만큼 이동하고 다음 수가 정답
					max = pre + M + 1;
					// 수 찾았는지 체크
					flag = true;
					break;
				// 이동할 수 있는 경우에는 해당하는 거리만큼 M에서 빼기
				} else {
					M -= cur.idx - pre - 1;
				}
			}
			pre = cur.idx;
		}
		
		// 만약 해당하는 수를 못찾았다면 최댓값에서 남아있는 M만큼 이동 후 다음 수가 정답
		if(!flag) {
			max = MAX + M + 1;
		}
		
		sb.append(max).append("\n");
		
		System.out.println(sb);
	}

}