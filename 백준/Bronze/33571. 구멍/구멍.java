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
		
		HashSet<Character> one  = new HashSet<Character>();
		HashSet<Character> two = new HashSet<Character>();
		
		one.add('A');
		one.add('a');
		one.add('b');
		one.add('D');
		one.add('d');
		one.add('e');
		one.add('g');
		one.add('O');
		one.add('o');
		one.add('P');
		one.add('p');
		one.add('Q');
		one.add('q');
		one.add('R');
		one.add('@');
		
		two.add('B');
		
		String str = br.readLine();
		
		int ans = 0;
		
		for(char s : str.toCharArray()) {
			if(s == ' ') continue;
			
			if(one.contains(s)) {
				ans = ans + 1;
			} else if (two.contains(s)){
				ans = ans + 2;
			}
		}
		
		System.out.println(ans);
	}
	
}