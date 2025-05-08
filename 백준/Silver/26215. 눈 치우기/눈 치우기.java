import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());

        // 눈 많이 쌓인 순서대로 내림차순 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}

        // 총 소요되는 시간
		int time = 0;

        // 모든 눈을 치울때까지
		while (!pq.isEmpty()) {
            // 현재 청소하려는 집
			int f = pq.poll();

            // 하나더 같이 청소할 수 있는 경우
			if (!pq.isEmpty()) {
				int s = pq.poll();

                // 작은 집 먼저 청소
				time += s;

                // 큰 집은 작은 집 눈만큼 청소 후 다시 넣기
				pq.add(f - s);

            // 한 집만 청소할 수 있다면 바로 청소
			} else {
				time += f;
			}
		}
        
        // 시간이 1440 초과인 경우엔 청소 불가능
		if (time > 1440) {
			System.out.println(-1);
		} else {
			System.out.println(time);
		}
	}

}