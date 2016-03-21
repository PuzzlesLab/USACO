/*
ID: king8791
LANG: JAVA
TASK: milk3
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class milk3 {
    private static class Node {
        int [] milk=new int [3];
        
        public Node(Node n) {
            this.milk[0]=n.milk[0];
            this.milk[1]=n.milk[1];
            this.milk[2]=n.milk[2];
        }
        
        public Node (int i0, int i1, int i2) {
            this.milk[0]=i0;
            this.milk[1]=i1;
            this.milk[2]=i2;
        }
    }
    
    public static Node pour (Node n, int from, int to, int toCap) {
        Node newN=new Node(n);
        int toPour=toCap-newN.milk[to];
        if (toPour>newN.milk[from]) {
            toPour=newN.milk[from];
        }
        newN.milk[to]=newN.milk[to]+toPour;
        newN.milk[from]=newN.milk[from]-toPour;
        return newN;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("milk3.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int [] cap=new int [3];
        cap[0]=Integer.parseInt(st.nextToken());
        cap[1]=Integer.parseInt(st.nextToken());
        cap[2]=Integer.parseInt(st.nextToken());
        ArrayList<Integer> list=new ArrayList<Integer>();
        Stack<Node> stack=new Stack<Node>();
        boolean [][][] flag=new boolean [21][21][21];
        stack.add(new Node(0,0,cap[2]));
        int [][] comb={{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
        while (!stack.empty()) {
            Node n=stack.pop();
            if (n.milk[0]==0 && !list.contains(n.milk[2])) {
                list.add(n.milk[2]);
            }
            flag[n.milk[0]][n.milk[1]][n.milk[2]]=true;
            Node newN;
            for (int i=0;i<comb.length;i++) {
                newN=pour(n,comb[i][0],comb[i][1],cap[comb[i][1]]);
                if (!flag[newN.milk[0]][newN.milk[1]][newN.milk[2]]) {
                    stack.push(newN);
                }
            }
        }
        Collections.sort(list);
        String s="";
        for (int i=0;i<list.size();i++) {
            s=s+list.get(i)+" ";
        }
        s=s.substring(0,s.length()-1);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        pw.println(s);
        pw.close();
        System.exit(0);
    }
}