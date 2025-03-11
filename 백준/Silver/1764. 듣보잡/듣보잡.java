import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 듣도 못한 사람 저장
		HashSet<String> hashSet = new HashSet<>();
		for(int i = 0 ; i < N ; i++) {
			hashSet.add(br.readLine());
		}
		
		// 듣도 보도 못한 사람 찾기 
		ArrayList<String> arrayList = new ArrayList<>();
		for(int i = 0 ; i < M ; i++) {
			String input = br.readLine();
			
			if(hashSet.contains(input)) {
				arrayList.add(input);
			}
		}
		
		// 사전순으로 정렬
		Collections.sort(arrayList);
		
		// 정답 출력
		int size = arrayList.size();
		sb.append(size).append("\n");
		for(int i = 0 ; i < size ; i++) {
			sb.append(arrayList.get(i)).append("\n");
		}
		
		System.out.println(sb);
	}
	
}