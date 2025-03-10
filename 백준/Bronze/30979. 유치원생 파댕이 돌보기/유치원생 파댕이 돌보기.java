import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			sum += Integer.parseInt(st.nextToken());
		}
		
		if(sum >= N) {
			System.out.println("Padaeng_i Happy");
		} else {
			System.out.println("Padaeng_i Cry");
		}
	}
	
}