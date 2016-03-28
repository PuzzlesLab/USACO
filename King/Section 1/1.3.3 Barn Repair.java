/*
ID: king8791
LANG: JAVA
TASK: barn1
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class barn1 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("barn1.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int maxWood=Integer.parseInt(st.nextToken());
        int barnLength=Integer.parseInt(st.nextToken());
        int toClose=Integer.parseInt(st.nextToken());
        
        int [] data = new int [toClose];
        for (int i=0;i<toClose;i++) {
        	data[i]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(data);
        
        int [] gap = new int [toClose-1];
        for (int i=0;i<toClose-1;i++) {
        	gap[i]=data[i+1]-data[i];
        }
        Arrays.sort(gap);

        int sum=Math.min(maxWood,toClose);
        for (int i=0;i<=gap.length-maxWood;i++) {
        	sum+=gap[i];
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        pw.println(sum);
        pw.close();
        System.exit(0);
    }
}