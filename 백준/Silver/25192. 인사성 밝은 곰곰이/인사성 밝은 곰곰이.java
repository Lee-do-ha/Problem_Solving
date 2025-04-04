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
		
		int N = Integer.parseInt(br.readLine());
		
		// 입력한 아이디 저장할 HashSet
		HashSet<String> hashSet = new HashSet<>();
		
		int ans = 0;
		String enter = "ENTER";
		// 첫 입장을 한 상태인지 검증
		boolean flag = false;
		
		for(int i = 0 ; i < N ; i++) {
			String input = br.readLine();
			
			// 아직 첫 입장이 이루어지지 않았을 때
			if(!flag) {
				// ENTER라면 입장 체크 갱신
				if(input.equals(enter)) {
					flag = true;
				}
			// 첫 입장이 이루어졌을 때
			} else {
				// 새로운 입장이라면 입력한 아이디 초기화
				if(input.equals(enter)) {
					hashSet.clear();
				// 새로운 입장이 아니라면 이미 입력한 아이디인지 검증
				} else {
					if(!hashSet.contains(input)) {
						hashSet.add(input);
						ans++;
					}
				}
			}
			
		}
		
		System.out.println(ans);
		
	}

}
