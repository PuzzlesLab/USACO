/*
ID: king8791
LANG: JAVA
TASK: pprime
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class pprime {
    
    public static int [] result;
    public static int resultCount=0;
    public static int [] knownPrimePalindromes={3,5,7,11};
    
    public static boolean isPrimeNumber (int value) {
        int max=(int)Math.sqrt(value)+1;
        if (value%2==0) {
            return false;
        }
        for (int i=3;i<=max;i+=2) {
            if (value%i==0) {
                return false;
            }
        }
        return true;
    }
    
    public static void generatePrimePalindromes (int length, String s) {
        if (length%2==0) {
            if (length/2==s.length()) {
                int mid=s.length()/2;
                for (int i=0;i<=mid;i++) {
                    s=s+s.charAt(mid-i);
                }
                if (isPrimeNumber(Integer.parseInt(s))) {
                    result[resultCount++]=Integer.parseInt(s);
                }
            } else {
                for (int i=0;i<=9;i++) {
                    generatePrimePalindromes(length,s+i);
                }
            }
        } else {
            if (length/2==s.length()-1) {
                int mid=s.length()-1;
                for (int i=0;i<mid;i++) {
                    s=s+s.charAt(mid-i-1);
                }
                if (isPrimeNumber(Integer.parseInt(s))) {
                    result[resultCount++]=Integer.parseInt(s);
                }
            } else {
                for (int i=0;i<=9;i++) {
                    generatePrimePalindromes(length,s+i);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("pprime.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int min=Integer.parseInt(st.nextToken());
        int max=Integer.parseInt(st.nextToken());
        int minDigits=(int)Math.log10(min)+1;
        int maxDigits=(int)Math.log10(max)+1;
        int [] palindromeDigits={1,3,7,9};
        int maxFirstDigit=(int)(max/Math.pow(10,maxDigits-1));
        result=new int[10000];
        for (int i=0;i<knownPrimePalindromes.length;i++) {
            if (knownPrimePalindromes[i]>=min && knownPrimePalindromes[i]<=max) {
                result[resultCount++]=knownPrimePalindromes[i];
            }
        }
        if (maxDigits>=3) {
            for (int i=3;i<=maxDigits;i+=2) {
                for (int i2=0;i2<palindromeDigits.length;i2++) {
                    if (i!=maxDigits || (i==maxDigits && maxFirstDigit>=palindromeDigits[i2])) {
                        generatePrimePalindromes(i,String.valueOf(palindromeDigits[i2]));
                    } else {
                        break;
                    }
                }
            }
        }
        String toPrint="";
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        for (int i=0;i<resultCount;i++) {
            if (result[i]>=min && result[i]<=max) {
                toPrint=toPrint+result[i]+"\n";
            }
            if (result[i]>max) {
                break;
            }
        }
        System.out.println(toPrint);
        pw.print(toPrint);
        pw.close();
        System.exit(0);
    }
}