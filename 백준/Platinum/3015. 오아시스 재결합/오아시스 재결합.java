import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static class human {
		long height;
		int number;

		public human(int number, long height) {
			super();
			this.number = number;
			this.height = height;
		}
		
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		// 키 저장할 배열
		human[] arr = new human[N];
		for (int i = 0; i < N; i++) {
			long height = Long.parseLong(br.readLine());
			
			arr[i] = new human(1, height);
		}
		
		// 최종 출력값
		long ans = 0;
		
		// Stack 사용
		Stack<human> stack = new Stack<>();
		
		// 순서대로 비교
		for(int i = 0 ; i < N ; i++) {
			human cur = arr[i];
			
			// Stack이 비어있다면 stack에 저장
			if(stack.isEmpty()) {
				stack.add(cur);
			// 비교대상이 있는 경우
			} else {
				
				// stack이 빌 때 까지
				while(!stack.isEmpty()) {
					
					// 현재 스택상 가장 낮은 사람
					human pre = stack.peek();
					
					// 키가 같은 경우에는 같은 사람 전부 다 보고 더 큰 사람 1명 더 볼 수 있음
					if(pre.height == cur.height) {
						
						// 더 큰 사람 있는지 보기위해서 pop()
						pre = stack.pop();
						
						// 키 같은 사람인원만큼 추가
						ans += pre.number;
						
						// 더 큰 사람 있다면 1명 추가
						if(!stack.isEmpty()) {
							ans++;
						}
						
						// 같은 키에 인원 추가하고 다시 스택에 추가
						pre.number++;
						stack.add(pre);
						break;
						
					// 키가 더 작은 경우
					} else if (pre.height > cur.height) {
						
						// 끝의 1명 볼 수 있기때문에 1명 추가하고 스택에 추가
						ans++;
						stack.add(cur);
						break;
						
					// 키가 더 큰 경우
					} else if (pre.height < cur.height){
						
						// 스택에서 끝의 키 인원들 추가
						ans += pre.number;
						
						// 스택에서 제거
						stack.pop();
						
						// 현재 함수에서는 스택이 비었을때만 종료
						if(stack.isEmpty()) {
							stack.add(cur);
							break;
						}
						
					}
				}
				
			}
			
		}

		System.out.println(ans);
		
	}

}
