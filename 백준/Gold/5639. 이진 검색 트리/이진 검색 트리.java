import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class node {
		node left, right;
		int cur;
		boolean visited;

		public node(node left, node right, int cur) {
			super();
			this.left = left;
			this.right = right;
			this.cur = cur;
			this.visited = false;
		}

		@Override
		public String toString() {
			return "node [left=" + left + ", right=" + right + ", cur=" + cur + "]";
		}

	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		node node = new node(null, null, 0);
		
		String s = "";
		boolean start = false;

		while((s = br.readLine()) != null && !s.isEmpty()) {

			node cur = node;

			int input = Integer.parseInt(s);
			
			if(!start) {
				node.cur = input;
				start = true;
				continue;
			}

			while (true) {
				if (input > cur.cur) {
					if (cur.right == null) {
						cur.right = new node(null, null, input);
						break;
					} else {
						cur = cur.right;
					}

				} else {
					if (cur.left == null) {
						cur.left = new node(null, null, input);
						break;
					} else {
						cur = cur.left;
					}
				}
			}

		}

		search(node, sb);

		System.out.println(sb);

	}

	private static void search(node cur, StringBuilder sb) {
		if (cur.left != null && !cur.left.visited) {
			search(cur.left, sb);
		}

		if (cur.right != null && !cur.right.visited) {
			search(cur.right, sb);
		}

		if ((cur.left == null || cur.left.visited) && (cur.right == null || cur.right.visited)) {
			cur.visited = true;
			sb.append(cur.cur).append("\n");
		}

	}

}