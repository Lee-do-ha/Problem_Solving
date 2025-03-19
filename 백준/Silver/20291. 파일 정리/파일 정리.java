import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
        // 문자열 자르기
		int N = Integer.parseInt(br.readLine());
		
        // 문자열 순으로 갯수 저장하기 위해 TreeMap 사용
		TreeMap<String, Integer> treeMap = new TreeMap<>();
		
		for(int i = 0 ; i < N ; i++) {
			String input = br.readLine().split("\\.")[1];
			
            // 문자열 저장
			if(treeMap.containsKey(input)) {
				treeMap.replace(input, treeMap.get(input) + 1); 
			} else {
				treeMap.put(input, 1);
			}
			
		}
		
        // 문자열과 갯수 출력
		for(String s : treeMap.keySet()) {
			sb.append(s + " " + treeMap.get(s)).append("\n");
		}
		
		System.out.println(sb);
	}
	
}