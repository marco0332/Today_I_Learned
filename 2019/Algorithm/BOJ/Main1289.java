//1. 해결 전략
//- 시간 제한이 2초이며, N의 최대 크기가 10^5 이므로 O(N^2)의 방식으로는 해결할 수 없다.
//  O(N)의 방식으로 해결해야 하는데, DFS를 이용해 풀이하였다.
//
//(1) DFS(int root, int parent)에는 root node를 반드시 경유하는 경로만을 저장한다.
//    - 여기에서 DFS는 root를 경로의 마지막 정점으로 하는 root 이하의 모든 sub-tree에 대한  
//      경로를 저장한다.
//    - 이러한 과정은 곱셈의 분배법칙을 생각하면 충분히 O(N)의 방식으로 해결할 수 있다.
//    - 즉, root에서 자식 노드까지의 가중치 * 자식노드의 DFS 값을 누적하여 더해나가면 된다.
//   
//(2) 전역 변수인 sum에는 자식-root-자식 경로를 포함하여 저장한다.
//    - 마찬가지로, root에서의 각 분기점이 2이상인 경우는 자식-root-자식의 경로를 생각해봐 
//      야 하는데, 이러한 경우에는 (1)에서 구한 각각의 경로 값에 대해 앞선 자식의 경로의 값들의 
//      합을 곱해줌으로써 그 누적 값을 sum에 더한다.

public class Main1289 {

}