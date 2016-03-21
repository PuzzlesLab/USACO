/*
ID: king8791
LANG: JAVA
TASK: milk2
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class milk2 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("milk2.in"));
        int count=Integer.parseInt(br.readLine())*2;
        int [] times=new int[count];
        boolean [] checkIn=new boolean[count];
        StringTokenizer st;
        for (int i=0;i<count;i+=2) {
            st=new StringTokenizer(br.readLine());
            times[i]=Integer.parseInt(st.nextToken());
            checkIn[i]=true;
            times[i+1]=Integer.parseInt(st.nextToken());
            checkIn[i+1]=false;
        }
        int tempInt;
        boolean tempBool;
        for (int i=0;i<count-1;i++) {
            int shortestIndex=i;
            for (int i2=i+1;i2<count;i2++) {
                if (times[i2]<times[shortestIndex]) {
                    shortestIndex=i2;
                } else if (times[i2]==times[shortestIndex]) {
                    if (checkIn[i2]) {
                        shortestIndex=i2; //If check in and check out occurs at the same time, check in first.
                    }
                }
            }
            if (shortestIndex!=i) {
                tempInt=times[i];
                tempBool=checkIn[i];
                times[i]=times[shortestIndex];
                checkIn[i]=checkIn[shortestIndex];
                times[shortestIndex]=tempInt;
                checkIn[shortestIndex]=tempBool;
            }
        }
        int farmerCount=0;
        int checkInTime=0;
        int checkOutTime=times[0];
        int idleInterval=0;
        int busyInterval=times[1]-times[0];
        for (int i=0;i<count;i++) {
            if (checkIn[i]) {
                farmerCount++;
                if (farmerCount==1) {
                    checkInTime=times[i];
                    int lastIdle=times[i]-checkOutTime;
                    if (lastIdle>idleInterval) {
                        idleInterval=lastIdle;
                    }
                }
            } else {
                farmerCount--;
                if (farmerCount==0) {
                    checkOutTime=times[i];
                    int lastBusy=times[i]-checkInTime;
                    if (lastBusy>busyInterval) {
                        busyInterval=lastBusy;
                    }
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        pw.println(busyInterval+" "+idleInterval);
        pw.close();
        System.exit(0);
    }
    
}
