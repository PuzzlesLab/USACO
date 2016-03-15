/*
ID:king8791
LANG:JAVA 
TASK:combo
*/

import java.io.*;
import java.util.*;

public class combo {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("combo.in"));
        StringTokenizer st;
        int N=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        int [] johnLock={Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        st=new StringTokenizer(br.readLine());
        int [] masterLock={Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        int count=0;
        if (N<=5) {
            count=N*N*N;
        } else {
            count=(int)Math.pow(5,3)*2;
            int overlaps=1;
            for (int i=0;i<3;i++) {
                int diff=Math.abs((((johnLock[i]+2)%N)-(masterLock[i]+2)%N)); //In case of the max value > N, set the max value to start from 0 again.
                if (diff>4) {
                    overlaps=0;
                    break;
                } else {
                    overlaps=overlaps*(5-diff);
                }
            }
            count-=overlaps;
        }
        PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        pw.println(count);
        pw.close();
    }
}