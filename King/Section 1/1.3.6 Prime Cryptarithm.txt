/*
ID: king8791
LANG: JAVA
TASK: crypt1
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class crypt1 {
    
    public static boolean isNumberAllowed (int toCheck, boolean [] flag, int length) {
        int quotient;
        int divisor;
        if ((int)Math.log10(toCheck)+1>length) {
            return false;
       }
        for (length--;length>=0;length--) {
            divisor=(int)Math.pow(10, length);
            quotient=toCheck/divisor;
            if (!flag[quotient]) {
                return false;
            }
            toCheck=toCheck-(quotient*divisor);
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("crypt1.in"));
        int numbers=Integer.parseInt(br.readLine());
        boolean [] allowedNumbersFlag=new boolean[10];
        int [] allowedNumbersList=new int[numbers];
        StringTokenizer st=new StringTokenizer(br.readLine());
        int temp;
        for (int i=0;i<numbers;i++) {
            temp=Integer.parseInt(st.nextToken());
            allowedNumbersFlag[temp]=true;
            allowedNumbersList[i]=temp;
        }
        int upperPossibilitiesCount=0;
        int lowerPossibilitiesCount=0;
        int [] upperPossibilities=new int [numbers*numbers*numbers];
        int [] lowerPossibilities=new int [numbers*numbers];
        int [] list;
        int firstNum;
        int secondNum;
        int thirdNum;
        for (int firstNumCount=0;firstNumCount<numbers;firstNumCount++) {
            firstNum=allowedNumbersList[firstNumCount];
            for (int secondNumCount=0;secondNumCount<numbers;secondNumCount++) {
                secondNum=allowedNumbersList[secondNumCount]*10;
                for (int thirdNumCount=0;thirdNumCount<numbers;thirdNumCount++) {
                    thirdNum=allowedNumbersList[thirdNumCount]*100;
                    upperPossibilities[upperPossibilitiesCount]=firstNum+secondNum+thirdNum;
                    upperPossibilitiesCount++;
                }
            }
        }
        for (int firstNumCount=0;firstNumCount<numbers;firstNumCount++) {
            firstNum=allowedNumbersList[firstNumCount];
            for (int secondNumCount=0;secondNumCount<numbers;secondNumCount++) {
                secondNum=allowedNumbersList[secondNumCount]*10;
                lowerPossibilities[lowerPossibilitiesCount]=firstNum+secondNum;
                lowerPossibilitiesCount++;
            }
        }
        int partialProduct1;
        int partialProduct2;
        int result;
        int totalSolutions=0;
        for (int i=0;i<upperPossibilitiesCount;i++) {
            for (int i2=0;i2<lowerPossibilitiesCount;i2++) {
                temp=lowerPossibilities[i2]-(lowerPossibilities[i2]/10)*10;
                partialProduct1=upperPossibilities[i]*temp;
                temp=lowerPossibilities[i2]/10;
                partialProduct2=upperPossibilities[i]*temp;
                result=partialProduct2*10+partialProduct1;
                if (isNumberAllowed(partialProduct1,allowedNumbersFlag,3) && isNumberAllowed(partialProduct2,allowedNumbersFlag,3) && isNumberAllowed(result,allowedNumbersFlag,4)) {
                    totalSolutions++;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
        pw.println(totalSolutions);
        pw.close();
        System.exit(0);
    }
 
}