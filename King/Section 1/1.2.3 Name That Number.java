/*
ID: king8791
LANG: JAVA
TASK: namenum
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class namenum {
    
    public static String [] charMap={"2","2","2","3","3","3","4","4","4","5","5","5","6","6","6","7","7","7","7","8","8","8","9","9","9","9"};
    
    public static String nameToDigit(String s) {
        String toReturn="";
        for (int i=0;i<s.length();i++) {
            toReturn=toReturn+charMap[s.charAt(i)-'A'];
        }
        return toReturn;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("namenum.in"));
        String digits=br.readLine();
        br=new BufferedReader(new FileReader("dict.txt"));
        String [] names=new String[10000];
        String [] digitedNames=new String[10000];
        int namesCount=0;
        String s="";
        while (true) {
            s=br.readLine();
            if (s==null) {
                break;
            }
            names[namesCount]=s;
            digitedNames[namesCount]=nameToDigit(s);
            namesCount++;
        }
        s="";
        for (int i=0;i<namesCount;i++) {
            if (digitedNames[i].equals(digits)) {
                s=s+names[i]+"\n";
            }
        }
        if (s.equals("")) {
            s="NONE\n";
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        pw.print(s);
        pw.close();
        System.exit(0);
    }
    
}