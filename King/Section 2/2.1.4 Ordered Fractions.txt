/*
ID: king8791
LANG: JAVA
TASK: frac1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

class frac1 {

    public static class Fraction {
        int numerator,denominator;
        double value;
        
        public int compareTo (Fraction f) {
            if (this.value>f.value) {
                return 1;
            } else if (this.value==f.value) {
                return 0;
            } else {
                return -1;
            }
        }
    }
    
    public static class Tree {
        public static class Node {
            public Node left;
            public Node right;
            public Fraction data;
        }
        
        public Node root;
        public int count=0;
        
        public void addNode(Fraction f) {
            this.root=addNodeHelper(f,this.root);
        }
        
        private Node addNodeHelper (Fraction f, Node rt) {
            if (rt==null) {
                rt=new Node();
                rt.data=f;
                this.count++;
            } else {
                int diff=rt.data.compareTo(f);
                if (diff<0) {
                    rt.right=addNodeHelper(f,rt.right);
                } else if (diff>0) {
                    rt.left=addNodeHelper(f,rt.left);
                }   
            }
            return rt;
        }
        
        public String inorder() {
            return this.inorderHelper(this.root);
        }
        
        public static String inorderHelper(Node rt) {
            if (rt==null) {
                return "";
            } else {
                return inorderHelper(rt.left)+rt.data.numerator+"/"+rt.data.denominator+"\n"+inorderHelper(rt.right);
            }
        }
    }
    
    public static Tree FractionTree;
    public static int FractionCount=0;
    
    public static boolean hasSimplerForm(int num, int dem) {
        int remainder=1;
        while (remainder!=0) {
            dem=num;
            num=remainder;
            remainder=dem%num;
        }
        return (num!=1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("frac1.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        FractionTree=new Tree();
        Fraction f;
        f=new Fraction(); f.numerator=0; f.denominator=1; f.value=0;
        FractionTree.addNode(f);
        f=new Fraction(); f.numerator=1; f.denominator=1; f.value=1.0;
        FractionTree.addNode(f);
        for (int dem=2;dem<=n;dem++) {
            for (int num=1;num<dem;num++) {
                if (!hasSimplerForm(num,dem)) {
                    f=new Fraction();
                    f.numerator=num;
                    f.denominator=dem;
                    f.value=(double)num/(double)dem;
                    FractionTree.addNode(f);
                }
            }
        }
        String s=FractionTree.inorder();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
        pw.print(s);
        pw.close();
        System.exit(0);
    }
    
}