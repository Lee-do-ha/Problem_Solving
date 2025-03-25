import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	static class problem implements Comparable<problem>{
		int number, level;

		public problem(int number, int level) {
			super();
			this.number = number;
			this.level = level;
		}

		@Override
		public int compareTo(problem o) {
			
			if(this.level == o.level) {
				return o.number - this.number;
			} else {
				return o.level - this.level;
			}
			
		}

		@Override
		public String toString() {
			return "problem [number=" + number + ", level=" + level + "]";
		}
		
		
		
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		TreeSet<problem> treeSet = new TreeSet<>();
		HashMap<Integer, problem> hashMap = new HashMap<>();
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			problem cur = new problem(x, y);
			
			treeSet.add(cur);
			hashMap.put(x, cur);
		}
				
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			if(command.equals("recommend")) {
				
				int x = Integer.parseInt(st.nextToken());
				
				if(x == 1) {
					while (!treeSet.isEmpty()) {
						problem cur = treeSet.first();
						
						if(hashMap.keySet().contains(cur.number)) {
							sb.append(cur.number).append("\n");							
							break;
						} else {
							treeSet.pollFirst();
						}
					}
				} else {
					while (!treeSet.isEmpty()) {
						problem cur = treeSet.last();
						
						if(hashMap.keySet().contains(cur.number)) {
							sb.append(cur.number).append("\n");
							break;
						} else {
							treeSet.pollLast();
						}
					}
				}
				
			} else if(command.equals("add")) {
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				problem cur = new problem(x, y);
				
				treeSet.add(cur);
				hashMap.put(x, cur);
								
			} else {
				
				int x = Integer.parseInt(st.nextToken());
				treeSet.remove(hashMap.get(x));
				hashMap.remove(x);
								
			}
		}
		
		System.out.println(sb);
		
	}
	
}

