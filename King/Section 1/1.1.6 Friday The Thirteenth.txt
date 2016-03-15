/*
ID: king8791
LANG: JAVA
TASK: friday
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.StringTokenizer;

class friday {
    
    public static void main(String[] args) throws IOException {
        int firstDayOfYearFrom1900=1; //0=sunday, 1= monday, 2= tuesday, vice versa
        int [] NonLeapYearDays={31,28,31,30,31,30,31,31,30,31,30,31};
        int [] leapYearDays={31,29,31,30,31,30,31,31,30,31,30,31};
        int [] noOf13thInLeapYear=new int [12];
        int [] noOf13thInNonLeapYear=new int [12];
        int [] flag=new int [7];
        noOf13thInLeapYear[0]=13;
        noOf13thInNonLeapYear[0]=13;
        for (int i=1;i<12;i++) {
            noOf13thInLeapYear[i]=noOf13thInLeapYear[i-1]+leapYearDays[i-1];
            noOf13thInNonLeapYear[i]=noOf13thInNonLeapYear[i-1]+NonLeapYearDays[i-1];
        }
        BufferedReader br=new BufferedReader(new FileReader("friday.in"));
        int loop=Integer.parseInt(br.readLine());
        int currYear=1900;
        for (int i=0;i<loop;i++) {
            boolean isLeapYear=false;
            if (currYear%100==0) {
                isLeapYear=(currYear%400==0);
            } else {
                isLeapYear=(currYear%4==0);
            }
            if (isLeapYear) {
                //currYear=leapYear
                for (int month=0;month<12;month++) {
                    flag[(firstDayOfYearFrom1900+noOf13thInLeapYear[month])%7]++;
                }
                firstDayOfYearFrom1900+=366;
            } else {
                for (int month=0;month<12;month++) {
                    flag[(firstDayOfYearFrom1900+noOf13thInNonLeapYear[month])%7]++;
                }
                firstDayOfYearFrom1900+=365;
            }
            currYear++;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
        pw.println(flag[0]+" "+flag[1]+" "+flag[2]+" "+flag[3]+" "+flag[4]+" "+flag[5]+" "+flag[6]);
        pw.close();
        System.exit(0);
    }
}