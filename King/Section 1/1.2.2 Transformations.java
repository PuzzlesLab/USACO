/*
ID: king8791
LANG: JAVA
TASK: transform
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class transform {
    
    public static char [][] rotate270Degree (char [][] c) {
        char [][] toReturn=new char[c.length][c.length];
        int newx;
        int newy;
        for (int i=0;i<c.length;i++) {
            for (int i2=0;i2<c[i].length;i2++) {
                newx=c[i].length-1-i2;
                newy=i;
                toReturn[newx][newy]=c[i][i2];
            }
        }
        return toReturn;
    }
    
    public static char [][]  rotate180Degree (char [][] c) {
        char [][] toReturn=new char[c.length][c.length];
        int newx;
        int newy;
        for (int i=0;i<c.length;i++) {
            for (int i2=0;i2<c[i].length;i2++) {
                newx=c[i].length-1-i;
                newy=c[i].length-1-i2;
                toReturn[newx][newy]=c[i][i2];
            }
        }
        return toReturn;
    }

    public static char[][] rotate90Degree (char [][] c) {
        char [][] toReturn=new char[c.length][c.length];
        int newx;
        int newy;
        for (int i=0;i<c.length;i++) {
            for (int i2=0;i2<c[i].length;i2++) {
                newx=i2;
                newy=c[i].length-1-i;
                toReturn[newx][newy]=c[i][i2];
            }
        }
        return toReturn;
    }
    
    public static char[][] reflection (char [][] c) {
        char [][] toReturn=new char[c.length][c.length];
        for (int i=0;i<c.length;i++) {
            for (int i2=0;i2<c[i].length;i2++) {
                toReturn[i][i2]=c[i][i2];
            }
        }
        for (int i=0;i<c.length;i++) {
            for (int i2=0;i2<c[i].length/2;i2++) {
                char temp=toReturn[i][i2];
                toReturn[i][i2]=toReturn[i][c.length-1-i2];
                toReturn[i][c.length-1-i2]=temp;
            }
        }
        return toReturn;
    }
    
    public static boolean compare (char [][] ori, char[][] target) {
        for (int i=0;i<ori.length;i++) {
            for (int i2=0;i2<ori[i].length;i2++) {
                if (ori[i][i2]!=target[i][i2]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void display (char[][] c) {
        for (int i=0;i<c.length;i++) {
            for (int i2=0;i2<c[i].length;i2++) {
                System.out.print(c[i][i2]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("transform.in"));
        int count=Integer.parseInt(br.readLine());
        char [][] ori = new char[count][count];
        String s;
        for (int i=0;i<count;i++) {
            s=br.readLine();
            for (int i2=0;i2<count;i2++) {
                ori[i][i2]=s.charAt(i2);
            }
        }
        char [][] target = new char[count][count];
        for (int i=0;i<count;i++) {
            s=br.readLine();
            for (int i2=0;i2<count;i2++) {
                target[i][i2]=s.charAt(i2);
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
        char [][] t90degree=rotate90Degree(ori);
        if (compare(t90degree,target)) {
            pw.println(1);
            pw.close();
            System.exit(0);
        }
        char [][] t180degree=rotate180Degree(ori);
        if (compare(t180degree,target)) {
            pw.println(2);
            pw.close();
            System.exit(0);
        }
        char [][] t270degree=rotate270Degree(ori);
        if (compare(t270degree,target)) {
            pw.println(3);
            pw.close();
            System.exit(0);
        }
        char [][] tReflex=reflection(ori);
        if (compare(reflection(ori),target)) {
            pw.println(4);
            pw.close();
            System.exit(0);
        }
        t90degree=rotate90Degree(tReflex);
        if (compare(t90degree,target)) {
            pw.println(5);
            pw.close();
            System.exit(0);
        }
        t180degree=rotate180Degree(tReflex);
        if (compare(t180degree,target)) {
            pw.println(5);
            pw.close();
            System.exit(0);
        }
        t270degree=rotate270Degree(tReflex);
        if (compare(t270degree,target)) {
            pw.println(5);
            pw.close();
            System.exit(0);
        }
        if (compare(ori,target)) {
            pw.println(6);
            pw.close();
            System.exit(0);
        }
        pw.println(7);
        pw.close();
        System.exit(0);
    }
    
}