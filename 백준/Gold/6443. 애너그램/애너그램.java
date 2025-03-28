import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	// 정렬하기 위한 Set
	static TreeSet<String> set;
	// 알파벳 갯수
	static int[] canUse;
	// 조합에 사용할 Stack
	static Stack<Character> stack;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		canUse = new int[26];
		set = new TreeSet<String>();
		stack = new Stack<Character>();

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			// Set, canUser, stack 초기화
			set.clear();
			stack.clear();
			Arrays.fill(canUse, 0);

			// 입력받은 문자열에서 문자 갯수 추가
			char[] chars = input.toCharArray();
			for (char k : chars) {
				canUse[k - 'a']++;
			}

			comb(input.length());

			for(String k : set) {
				sb.append(k).append("\n");
			}

		}

		System.out.println(sb);

	}

	private static void comb(int length) {
		// 입력받은 문잘열의 길이가 된다면 Set에 추가하고 종료
		if (stack.size() == length) {
			String str = "";
			for (char s : stack) {
				str += s;
			}
			set.add(str);
			return;
		}

		// 조합
		for (int i = 0; i < 26; i++) {
			if (canUse[i] > 0) {
				canUse[i]--;
				stack.push((char) (i + 'a'));
				comb(length);
				stack.pop();
				canUse[i]++;
			}
		}

	}

}
