import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// XOR 값 저장할 Trie
	static class Trie {
		Trie[] child = new Trie[2];

		// 숫자 넣기
		void insert(int cur) {	
			Trie trie = this;

			// 32비트 정수이므로 31비트부터 검사
			for(int i = 31 ; i >= 0 ; i--) {
				// i번째 비트
				int bit = (cur >> i) & 1;
				
				// 해당 trie에 자식노드 없으면 새로 생성
				if(trie.child[bit] == null) {
					trie.child[bit] = new Trie();
				}
				
				// trie 이동
				trie = trie.child[bit];
			}
		}
		
		// 최대 XOR값 찾기
		int search(int cur) {
			Trie trie = this;
			
			// return 할 값
			int result = 0;
			
			// 32비트 정수이므로 31비트부터 검사
			for(int i = 31 ; i >= 0 ; i--) {
				// i번째 비트
				int bit = (cur >> i) & 1;
				
				// XOR은 반대 비트여야 최댓값이 만들어지므로 현재 비트의 반대 비트 찾기
				// 해당 trie에 자식노드 없으면 새로 생성
				if(trie.child[1 - bit] != null) {
					trie = trie.child[1 - bit];
					result |= (1 << i);
				} else {
					trie = trie.child[bit];
				}
			}
			
			return result;
		}

	}

	static int[] arr;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		
		// 입력값 저장할 배열
		arr = new int[100001];
		
		for(int t = 0 ; t < T ; t++) {
			int N = Integer.parseInt(br.readLine());
			
			Trie trie = new Trie();
			
			// 초기 XOR부터 모두 저장
			trie.insert(0);
			
			// 최댓값
			int max = 0;
			// 누적 XOR 값
			int XOR = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				
				// 누적 XOR 갱신
				XOR ^= arr[i];
				// trie에 삽입
				trie.insert(XOR);
				// 현재까지의 max값과 현재까지의 XOR 최댓값 비교
				max = Math.max(max, trie.search(XOR));
			}
			sb.append(max).append("\n");
		}
		System.out.println(sb);
	}

}
