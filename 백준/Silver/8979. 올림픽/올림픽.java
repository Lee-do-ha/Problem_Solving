import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class medal implements Comparable<medal>{
		int number, gold, silver, copper;

		public medal(int number, int gold, int silver, int copper) {
			super();
			this.number = number;
			this.gold = gold;
			this.silver = silver;
			this.copper = copper;
		}

		@Override
		public int compareTo(medal o) {
			if(this.gold == o.gold) {
				if(this.silver == o.silver) {
					return o.copper - this.copper;
				}
				return o.silver - this.silver;
			}
			return o.gold - this.gold;
		}
		
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		medal find = null;
		
		ArrayList<medal> arrayList = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int copper = Integer.parseInt(st.nextToken());
			
			if(number == M) {
				find = new medal(number, gold, silver, copper);
			}
			
			arrayList.add(new medal(number, gold, silver, copper));
		}
		
		Collections.sort(arrayList);
		
		int ans = 0;
		
		for(int i = 0 ; i < N ; i++) {
			medal cur = arrayList.get(i);
						
			if(cur.gold != find.gold || cur.silver != find.silver || cur.copper != find.copper) {
				continue;
			}
			System.out.println(i + 1);
			break;
		}
		
	}
	
}