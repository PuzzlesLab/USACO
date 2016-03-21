/*
ID: king8791
LANG: JAVA
TASK: milk
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class milk {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("milk.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int milkNeeded=Integer.parseInt(st.nextToken());
        int farmers=Integer.parseInt(st.nextToken());
        int [] price=new int[farmers];
        int [] amount=new int[farmers];
        int totalCost=0;
        for (int i=0;i<farmers;i++) {
            st=new StringTokenizer(br.readLine());
            price[i]=Integer.parseInt(st.nextToken());
            amount[i]=Integer.parseInt(st.nextToken());
        }
        for (int i=0;i<farmers-1;i++) {
            int lowestPriceIndex=i;
            for (int i2=i+1;i2<farmers;i2++) {
                if (price[i2]<price[lowestPriceIndex]) {
                    lowestPriceIndex=i2;
                }
            }
            int pricetemp;
            int amounttemp;
            if (lowestPriceIndex!=i) {
                pricetemp=price[lowestPriceIndex];
                amounttemp=amount[lowestPriceIndex];
                price[lowestPriceIndex]=price[i];
                amount[lowestPriceIndex]=amount[i];
                price[i]=pricetemp;
                amount[i]=amounttemp;
            }
        }
        for (int i=0;milkNeeded>0;i++) {
            if (milkNeeded>=amount[i]) {
                milkNeeded-=amount[i];
                totalCost=totalCost+price[i]*amount[i];
            } else {
                totalCost=totalCost+price[i]*milkNeeded;
                milkNeeded=0;
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        pw.println(totalCost);
        pw.close();
        System.exit(0);
    }
    
}