import java.io.*;

public class Main5397_키로거 {
    public static void main(String[] args) throws IOException {
        Solver sv = new Solver();
        sv.solve();
    }

    public static class Solver {
        BufferedReader br;
        BufferedWriter bw;
        List myList;

        public Solver() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void solve() throws IOException {
            int testCase = Integer.parseInt(br.readLine());
            while (testCase-- > 0) {
                myList = new List();
                String query = br.readLine();

                for(int index=0, size=query.length(); index<size; index++){
                    exeCommand(query.charAt(index));
                }

                myList.print(bw);
            }
        }

        public void exeCommand(char input){
            if(input == '<'){
                myList.goLeft();
            }
            else if(input == '>'){
                myList.goRight();
            }
            else if(input == '-'){
                myList.delete();
            }
            else {
                myList.add(input);
            }
        }
    }

    public static class List {
        Node head;
        Node curser;
        StringBuilder ret;

        public List() {
            head = new Node(' ');
            curser = new Node(' ');
            curser.next = head;
            ret = new StringBuilder();
        }

        public void add(char val) {
            Node newNode = new Node(val);

            Node node = curser.next;
            if (node.next != null) {
                newNode.next = node.next;
                node.next.befo = newNode;
            }
            node.next = newNode;
            newNode.befo = node;

            curser.next = newNode;
        }

        public void delete(){
            Node node = curser.next;
            if(!node.equals(head)){
                node.befo.next = node.next;
                
                if(node.next != null) {
                    node.next.befo = node.befo;
                }

                curser.next = node.befo;
                node = null;
            }
        }

        public void goLeft(){
            Node node = curser.next;

            if(node.befo != null){
                curser.next = node.befo;
            }
        }

        public void goRight(){
            Node node = curser.next;

            if(node.next != null){
                curser.next = node.next;
            }
        }

        public void print(BufferedWriter bw) throws IOException {
            Node node = head.next;
            while(node != null){
                ret.append(node.val);
                node = node.next;
            }
            
            bw.write(ret.toString()+"\n");
            bw.flush();
        }
    }

    public static class Node {
        char val;
        Node befo;
        Node next;

        public Node(char val) {
            this.val = val;
            befo = null;
            next = null;
        }
    }
}