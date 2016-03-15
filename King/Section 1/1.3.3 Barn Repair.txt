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
import java.util.StringTokenizer;

class barn1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("barn1.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int maxBoard=Integer.parseInt(st.nextToken());
        int maxStall=Integer.parseInt(st.nextToken());
        int stallContains=Integer.parseInt(st.nextToken());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        if (stallContains>1) {
            int [] stallList=new int [stallContains];
            for (int i=0;i<stallContains;i++) {
                stallList[i]=Integer.parseInt(br.readLine());
            }
            for (int i=0;i<stallContains-1;i++) {
                int smallest=i;
                for (int i2=i+1;i2<stallContains;i2++) {
                    if (stallList[i2]<stallList[smallest]) {
                        smallest=i2;
                    }
                }
                if (smallest!=i) {
                    int temp=stallList[smallest];
                    stallList[smallest]=stallList[i];
                    stallList[i]=temp;
                }
            }
            int [] gaps=new int [stallContains-1];
            for (int i=0;i<stallContains-1;i++) {
                gaps[i]=stallList[i+1]-stallList[i];
            }
            for (int i=0;i<gaps.length-1;i++) {
                int smallest=i;
                for (int i2=i+1;i2<gaps.length;i2++) {
                    if (gaps[i2]<gaps[smallest]) {
                        smallest=i2;
                    }
                }
                if (smallest!=i) {
                    int temp=gaps[smallest];
                    gaps[smallest]=gaps[i];
                    gaps[i]=temp;
                }
            }
            int covered=stallContains;
            int currBoardUsed=stallContains;
            for (int i=0;currBoardUsed>maxBoard;i++) {
                currBoardUsed--;
                covered+=(gaps[i]-1);
            }
            pw.println(covered);
        } else {
            pw.println("1");
        }
        pw.close();
        System.exit(0);
    }
    
}