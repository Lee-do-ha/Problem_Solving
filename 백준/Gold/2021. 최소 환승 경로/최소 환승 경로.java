import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    // LineConnected = 현재 역에서 탈 수 있는 노선리스트
    // StationConnected = 현재 노선에서 갈 수 있는 역
    static ArrayList<Integer>[] LineConnected, StationConnected;
    static int n, m, start, end;
    
    static class Node{
        int station, line, cnt;

        public Node(int station, int line, int cnt) {
            this.station = station;
            this.line = line;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        LineConnected = new ArrayList[n+1];
        StationConnected = new ArrayList[m+1];
        for(int i = 1 ; i < n+1 ; i++){
            LineConnected[i] = new ArrayList<>();
        }
        for(int i = 1 ; i < m+1 ; i++){
            StationConnected[i] = new ArrayList<>();
        }

        for(int i = 1 ; i < m+1 ; i++){
            st = new StringTokenizer(br.readLine());
            while(true){
                int a = Integer.parseInt(st.nextToken());

                if(a==-1){
                    break;
                }else{
                    LineConnected[a].add(i);
                    StationConnected[i].add(a);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(BFS());
    }

    private static int BFS(){
        Queue<Node> queue = new LinkedList<>();
        boolean[] visitedStation = new boolean[n+1];
        boolean[] visitedLine = new boolean[m+1];
        visitedStation[start] = true;
        
        // 시작역에서 탈 수 있는 노선들 큐에 넣고 방문 처리
        for(int i : LineConnected[start]){
            queue.add(new Node(start, i, 0));
            visitedLine[i] = true;
        }

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            // 현재 역이 도착 역이라면 return
            if(cur.station == end){
                return cur.cnt;
            }
            
            // 현재 노선에서 갈 수 있는 역들 체크
            for(int nextStation : StationConnected[cur.line]){
                // 역 방문안했다면 방문처리하고 큐에 해당 역 넣기
                if(!visitedStation[nextStation]){
                    visitedStation[nextStation] = true;
                    queue.add(new Node(nextStation, cur.line, cur.cnt));
                    
                    // 다음 역에서 갈 수 있는 노선들 체크
                    for(int nextLine : LineConnected[nextStation]){
                        // 노선 방문안했다면 방문처리하고 큐에 노선과 시간+1 해주고 큐에 넣기
                        if(!visitedLine[nextLine]){
                            visitedLine[nextLine] = true;
                            queue.add(new Node(nextStation,nextLine, cur.cnt+1));
                        }
                    }
                }
            }
        }
        
        // 큐가 빌때까지 방문 못했다면 -1
        return -1;
    }
}
