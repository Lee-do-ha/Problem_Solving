import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class cow implements Comparable<cow>{
		int arrive, check;

		public cow(int arrive, int check) {
			super();
			this.arrive = arrive;
			this.check = check;
		}

		@Override
		public int compareTo(cow o) {
			return this.arrive - o.arrive;
		}

		@Override
		public String toString() {
			return "cow [arrive=" + arrive + ", check=" + check + "]";
		}
		
		
		
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<cow> pq = new PriorityQueue<>();
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			cow input = new cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			pq.add(input);
		}
				
		int finish = 0;
		
		while(!pq.isEmpty()) {
			cow cur = pq.poll();
						
			if(finish <= cur.arrive) {
				finish = cur.arrive + cur.check;
			} else {
				finish += cur.check;
			}
			
		}
		
		System.out.println(finish);
		
	}
	
}