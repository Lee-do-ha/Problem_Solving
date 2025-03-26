import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		
		int[] cost = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			parents[i] = i;
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			unionSet(a - 1, b - 1);
		}
		
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		
		int sum = 0;
		
		for(int i = 0 ; i < N ; i++) {
			int pCur = findSet(i);
			
			if(!hashMap.keySet().contains(pCur)) {
				sum += cost[i];
				hashMap.put(pCur, cost[i]);
			} else {
				int preCost = hashMap.get(pCur);
				
				if(cost[i] < preCost) {
					hashMap.replace(pCur, cost[i]);
					
					sum -= preCost;
					sum += cost[i];
				}
			}
		}
		
		if(sum > K) {
			System.out.println("Oh no");
		} else {
			System.out.println(sum);
		}
		
	}
	
	private static int findSet(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	private static void unionSet(int a, int b) {
		int pA = findSet(a);
		int pB = findSet(b);
		
		if(pA == pB) return;
		
		parents[pB] = pA;
	}
	
}

