import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
		

		parents = new int[N + 1];
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i < N + 1 ; i++) {
			parents[i] = i;
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			unionSet(Math.min(a, b), Math.max(a, b));
			
		}
		
		HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();
		
		for(int i = 1 ; i < N + 1 ; i++) {
			int fI = findSet(i);
			
			if(!hashMap.containsKey(fI)) {
				hashMap.put(fI, new HashSet<Integer>());
			}
			
			hashMap.get(fI).add(i);
		}
		
		int[] dp = new int[K];
		
		for(int k : hashMap.keySet()) {
			int number = hashMap.get(k).size();
			int candy = 0;
			
			for(int a : hashMap.get(k)) {
				candy += arr[a];
			}
			
			for(int a = K - 1 ; a > 0 ; a--) {
				if(a < number) {
					dp[a] = Math.max(dp[a-1], dp[a]);
				} else {
					dp[a] = Math.max(dp[a-number] + candy, dp[a]);
				}
			}
						
		}
		System.out.println(dp[K- 1]);
		
//		for(int i = 1 ; i < hashMap.keySet().size() + 1 ; i++) {
//			for(int j = 0 ; j < K ; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	
	private static int findSet(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	private static void unionSet(int a, int b) {
		int pA = findSet(a);
		int pB = findSet(b);
		
		if(pA == pB) return ;
		
		parents[pB] = pA;
	}
	
}

