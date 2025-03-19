import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] schedule = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			schedule[i] = Integer.parseInt(st.nextToken());
		}
		
		HashSet<Integer> love = new HashSet<>();
		HashSet<Integer> hate = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < A ; i++) {
			love.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < B ; i++) {
			hate.add(Integer.parseInt(st.nextToken()));
		}
		
		int score = 0;
		int count = 0;
		int pre = 0;
		
		for(int i = 0 ; i < N ; i++) {
			int cur = schedule[i];
			int curState = 0;
			if(love.contains(cur)) {
				curState = 1;
			} else if(hate.contains(cur)) {
				curState = -1;
			}
			
			if(curState == 1) {
				if(pre == 1) {
					count++;
					if(count == 3) {
						score += 3;
					} else if(count > 3) {
						score++;
					}
				} else {
					pre = 1;
					count = 1;
				}
			} else if(curState == -1) {
				if(pre == -1) {
					count++;
					if(count == 3) {
						score -= 3;
					} else if(count > 3) {
						score--;
					}
				} else {
					pre = -1;
					count = 1;
				}
			} else {
				pre = 0;
				count = 0;
			}
			
		}
		
		System.out.println(score);
	}
	
}

