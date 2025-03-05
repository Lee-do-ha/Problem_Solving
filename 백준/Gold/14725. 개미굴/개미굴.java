import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static class Trie {
		Node root;
		
		public Trie() {
			this.root = new Node();
		}
		
		// 단어 삽입
		void insert(String word) {
			
			// root노드에서부터 시작
			Node node = this.root;
					
			// 단어열 자르고 진행
			String[] arr = word.split(" ");
			int length = arr.length;
			
			// 첫번째 단어부터 시작
			for(int i = 1 ; i < length ; i++) {
				String cur = arr[i];
				
				// 현재 노드에 다음 단어가 저장되어있는지 체크
				if(!node.child.containsKey(cur)) {
					node.child.put(cur, new Node());
				}
				
				// 만약 마지막 단어라면 단어의 끝임을 체크
				if(i == length - 1) {
					node.end = true;
				// 아니라면 다음 노드로 진행
				} else {
					node = node.child.get(cur);
				}
				
			}
		}
		
		// 단어 체크
		StringBuilder check(Node node, int depth, StringBuilder sb) {

			// 현재 저장된 다음 단어들 사전순으로 정렬
			ArrayList<String> arr = new ArrayList<>();
			arr.addAll(node.child.keySet());
			
			Collections.sort(arr);
			
			int size = arr.size();
			
			for(int i = 0 ; i < size ; i++) {
				
				// 다음 진행되는 단어
				String next = arr.get(i);
				
				// 문자열 넣기
				for(int k = 0 ; k < depth ; k++) {
					sb.append("--");
				}
				sb.append(next).append("\n");
				
				// 다음 노드로 진행
				check(node.child.get(next), depth+1, sb);
			
			}
			
			return sb;
			
		}
		
	}
	
	// Trie에 저장할 노드
	static class Node {
		// 다음 들어오는 단어 저장할 Map
		HashMap<String, Node> child;
		// 해당 단어에서 끝나는지 체크
		boolean end;
		
		public Node() {
			this.child = new HashMap<>();
			this.end = false;
		}
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		// Trie 생성자
		Trie trie = new Trie();
		
		// 문자열 입력
		for(int i = 0 ; i < N ; i++) {
			String str = br.readLine();
			
			trie.insert(str);

		}
				
		// 전체 단어 검색
		trie.check(trie.root, 0, sb);
		
		System.out.println(sb);
		
	}
	
}