/*
ID: king8791
LANG: JAVA
TASK: sprime
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class sprime {
    
    public static int [] superprimes;
    public static int superprimesCount=0;
    public static int [] primeNumbers={2,3,5,7,9};
    public static int [] lastNumbers={1,3,7,9};
    
    public static boolean isPrimeNumber (int value) {
        int max=(int)Math.sqrt(value)+1;
        if (value<10 && (value==2 || value==3 || value==5 || value==7) ) {
            return true;
        }
        for (int i=2;i<=max;i+=1) {
            if (value%i==0) {
                return false;
            }
        }
        return true;
    }
    
    public static void generateSuperprime(int length, String str) {
        if (length==str.length()) {
            if (isPrimeNumber(Integer.parseInt(str))) {
                superprimes[superprimesCount++]=Integer.parseInt(str);
            }
        } else if (str.length()==0) {
            for (int i=0;i<primeNumbers.length;i++) {
                if (isPrimeNumber((Integer.parseInt(str+primeNumbers[i])))) {
                    generateSuperprime(length,str+primeNumbers[i]);
                }
            }
        } else {
            for (int i=0;i<lastNumbers.length;i++) {
                if (isPrimeNumber(Integer.parseInt(str))) {
                    generateSuperprime(length,str+lastNumbers[i]);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("sprime.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int length=Integer.parseInt(st.nextToken());
        superprimes=new int [10000];
        generateSuperprime(length,"");
        String toPrint="";
        for (int i=0;i<superprimesCount;i++) {
            toPrint=toPrint+superprimes[i]+"\n";
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        pw.print(toPrint);
        pw.close();
        System.exit(0);
    }
}