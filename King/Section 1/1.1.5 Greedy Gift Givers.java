/*
ID: king8791
LANG: JAVA
TASK: gift1
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.StringTokenizer;

class gift1 {
    static String [] name;
    static int [] initialMoney;
    static int [] finalMoney;
    
    public static int nameToIndex (String n) {
        for (int i=0;i<name.length;i++) {
            if (name[i].equals(n)) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("gift1.in"));
        int size=Integer.parseInt(br.readLine());
        name=new String [size];
        initialMoney=new int [size];
        finalMoney=new int [size];
        for (int i=0;i<size;i++) {
            name[i]=br.readLine();
        }
        for (int i=0;i<size;i++) {
            int index=nameToIndex(br.readLine());
            StringTokenizer st=new StringTokenizer(br.readLine());
            initialMoney[index]=Integer.parseInt(st.nextToken());
            int toGiveNo=Integer.parseInt(st.nextToken());
            if (toGiveNo>0) {
                finalMoney[index]=finalMoney[index]+(initialMoney[index]%toGiveNo);
                int targetIndex;
                int moneyToGive=initialMoney[index]/toGiveNo;
                for (int loop=0;loop<toGiveNo;loop++) {
                    targetIndex=nameToIndex(br.readLine());
                    finalMoney[targetIndex]=finalMoney[targetIndex]+moneyToGive;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
        for (int i=0;i<size;i++) {
            pw.println(name[i]+" "+(finalMoney[i]-initialMoney[i]));
        }
        pw.close();
        System.exit(0);
    }
}
