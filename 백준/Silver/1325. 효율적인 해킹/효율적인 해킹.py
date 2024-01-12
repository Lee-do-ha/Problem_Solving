from collections import deque

def bfs(data):
    q = deque([data])
    visited = [False] * (n+1)
    visited[data] = True
    cnt = 1
    
    while q:
        data = q.popleft()
        
        for i in maps[data]:
            if not visited[i]:
                visited[i] = True
                q.append(i)
                cnt += 1
    return cnt

n, m = map(int, input().split())
maps = [[] for  _ in range(n+1)]


for _ in range(m):
    x, y = map(int, input().split())
    maps[y].append(x)
    
result = []
max_value = -1

for i in range(1, n+1):
    c = bfs(i)
    if c > max_value:
        result = [i]
        max_value = c
    elif c == max_value:
        result.append(i)
        max_value = c


for a in result:
    print(a, end=' ')