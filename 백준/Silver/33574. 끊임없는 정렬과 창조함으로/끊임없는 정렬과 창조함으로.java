import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				int b = Integer.parseInt(st.nextToken());
				if(b == 1) {
					Collections.sort(list);
				} else {
					list.sort(Collections.reverseOrder());
				}
				
			} else {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				list.add(b,  c);
			}
		}
		
		sb.append(list.size()).append("\n");
		for(int k : list) {
			sb.append(k + " ");
		}
		
		System.out.println(sb);
	}
	
}