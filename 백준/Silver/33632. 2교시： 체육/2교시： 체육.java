import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		double x = Double.parseDouble(st.nextToken());
		double y = Double.parseDouble(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		double curX = Double.parseDouble(st.nextToken());
		double curY = Double.parseDouble(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		double x1 = Double.parseDouble(st.nextToken());
		double y1 = Double.parseDouble(st.nextToken());
		double x2 = Double.parseDouble(st.nextToken());
		double y2 = Double.parseDouble(st.nextToken());
		
		if(curY < y1) {
			System.out.println(0);
			return ;
		}
		
		double changeX1 = x1 - y1 * (curX - x1) / (curY - y1);
		double changeX2 = x2 - y2 * (curX - x2) / (curY - y2);
		
	//	System.out.println(changeX1 + " " + changeX2);

		if(changeX1 >= x || changeX2 <= 0) {
			System.out.println(0);
		} else {
			System.out.println((Math.min(changeX2, x) - Math.max(0, changeX1)) / x);
		}
		
	}
	
}
