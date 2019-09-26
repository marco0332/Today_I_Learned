class Solution {
    public int[] solution(int[] heights) {
        int[] answer = new int[heights.length];
        Stack stack = new Stack();
        stack.push(-1, 0);
        
        for(int index=0; index<heights.length; index++) {
            if(stack.top().height <= heights[index]) {
                while(stack.getSize() > 0 && stack.top().height <= heights[index]) {
                    stack.pop();
                }
                answer[index] = stack.getSize() > 0 ? stack.top().index +1 : 0;
                stack.push(index, heights[index]);
            } else {
                answer[index] = stack.top().index +1;
                stack.push(index, heights[index]);
            }
        }
        
        return answer;
    }
}

class Stack {
    private Pos[] arr;
    private int size;
    
    public Stack() {
        arr = new Pos[101];
        for(int index=0; index<101; index++) {
            arr[index] = new Pos();
        }
        size = 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public Pos top() {
        return arr[size-1];
    }
    
    public Pos pop() {
        return arr[size-- -1];
    }
    
    public void push(int index, int val) {
        arr[size++] = new Pos(index,val);
    }
}

class Pos {
    int index;
    int height;
    
    public Pos(int index, int height) {
        this.index = index;
        this.height = height;
    }
    public Pos(){}
}