/*
ID: king8791
LANG: JAVA
TASK: ariprog
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ariprog {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("ariprog.in"));
        int maxSequence=Integer.parseInt(br.readLine());
        int maxPQ=Integer.parseInt(br.readLine());
        boolean [] flag=new boolean [maxPQ*maxPQ*2+1];
        int [] set=new int [maxPQ*maxPQ*2+1];
        int setCount=0;
        for (int p=0;p<=maxPQ;p++) {
            for (int q=0;q<=maxPQ;q++) {
                flag[p*p+q*q]=true; //generates bisquares and flag it for checking purposes later.
            }
        }
        for (int i=0;i<flag.length;i++) {
            if (flag[i]) {
                set[setCount++]=i; //store the bisquares in an array.
            }
        }
        String output="";
        int maxDiff=((set[setCount-1]-set[0])/2)+1;
        for (int diff=1;diff<maxDiff;diff++) {
            for (int num=0;num<setCount;num++) {
                int count=0;
                int nextNum=set[num]; //first number.. check the following sequence with d = 1,2,3,4,5.. then second number.. 1,2,3,4,5.. vice versa.
                if (nextNum+(maxSequence-1)*diff<=set[setCount-1]) { //check if the last number of the sequence exceed the largest number of the set or not. If yes, ignore it as the sequence is invalid. Reduces a lot of computational time!
                    for (int i=0;i<=maxSequence && nextNum<flag.length && flag[nextNum];i++) {
                        count++;
                        nextNum=nextNum+diff;
                    }
                    if (count>=maxSequence) {
                        output+=set[num]+" "+diff+"\n";
                    }
                }
            }
        }
        if (output.equals("")) {
            output="NONE\n";
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        pw.print(output);
        pw.close();
        System.exit(0);
    }
}