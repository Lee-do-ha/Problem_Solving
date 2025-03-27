import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		// BFS 진행하면서 방문체크하기위한 해시셋
		HashSet<Long> hashSet = new HashSet<>();

		// BFS 하기 위함
		Queue<Long> queue = new LinkedList<>();
		queue.add(1L);
		hashSet.add(1L);
		int time = N - 1;

		// BFS 진행
		while (!queue.isEmpty() && time > 0) {
			int size = queue.size();
			
			hashSet.clear();
			
			for(int i = 0 ; i < size ; i++) {
				long cur = queue.poll();
				
				// 앞 수가 짞수라고 가정
				long first = cur * 2;
				// 앞 수가 홀수라고 가정
				long second = (cur - 1) / 3;
				
				// 1이 되면 안되고 짝수이면서 이미 추가된 수인지 체크
				if(first != 1 && first % 2 == 0 && !hashSet.contains(first)) {
					hashSet.add(first);
				}
				
				// 1이 되면 안되고 홀수이면서 이미 추가된 수인지 체크 + 홀수로 만들 수 있는 수인지 체크
				if(second != 1 && second % 2 == 1 && !hashSet.contains(second) && (cur - 1) % 3 == 0) {
					hashSet.add(second);
				}
			}
			
			// 전체 큐에 추가
			queue.addAll(hashSet);
			time--;
			
		}

		// 오름차순 정렬
		PriorityQueue<Long> pq = new PriorityQueue<>(hashSet);
		
		
		sb.append(pq.size()).append("\n");
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}

		System.out.println(sb);

	}

}
