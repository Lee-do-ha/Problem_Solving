import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class human implements Comparable<human> {
		int weight, power;

		public human(int weight, int power) {
			super();
			this.weight = weight;
			this.power = power;
		}

		@Override
		public int compareTo(human o) {
			return Integer.compare(o.weight + o.power, this.weight + this.power);
		}

	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());

		PriorityQueue<human> pq = new PriorityQueue<>();

		int sum = 0;
		
		human[] humans = new human[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			humans[i] = new human(w, p);

			sum += humans[i].weight;
			
			pq.add(humans[i]);
		}

		int ans = Integer.MIN_VALUE;
		while (!pq.isEmpty()) {
			
			human cur = pq.poll();
						
			sum -= cur.weight;
			
			ans = Math.max(ans, sum - cur.power);
			
		}
		
		System.out.println(ans);

	}

}