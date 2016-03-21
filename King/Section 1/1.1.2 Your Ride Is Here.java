/*
ID: king8791
LANG: JAVA
TASK: ride
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

class ride {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("ride.in"));
        String cometName=br.readLine();
        String groupName=br.readLine();
        char [] cometChar=cometName.toCharArray();
        char [] groupChar=groupName.toCharArray();
        int comet=1;
        for (int i=0;i<cometChar.length;i++) {
            comet=comet*((int)cometChar[i]-64);
        }
        comet=comet%47;
        int group=1;
        for (int i=0;i<groupChar.length;i++) {
            group=group*((int)groupChar[i]-64);
        }
        group=group%47;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
        if (comet==group) {
            pw.println("GO");
        } else {
            pw.println("STAY");
        }
        pw.close();
        System.exit(0);
    }
}