/*
ID:king8791
LANG:JAVA 
TASK:sort3 
*/  
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class sort3 {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("sort3.in"));
        int n=Integer.parseInt(br.readLine());
        int [] ints=new int [n];
        int [] count=new int [4];
        for (int i=0;i<n;i++) {
            ints[i]=Integer.parseInt(br.readLine());
            count[ints[i]]++;
        }
        br.close();
        int [] min=new int [4]; 
        int [] max=new int [4];
        for (int i=1;i<4;i++) {
        	min[i]=max[i-1];
        	max[i]=min[i]+count[i];
        }
        int totalSwap=0;
        int index=-1;
        int temp;
        
        for (int i=min[1];i<max[1];i++) {
        	if (ints[i]==2) {
        		for (int i2=min[2];i2<max[3];i2++) {
        			if (ints[i2]==1) {
        				index=i2;
        				break;
        			}
        		}
        		temp=ints[i];
        		ints[i]=ints[index];
        		ints[index]=temp;
        		totalSwap++;
        	} else if (ints[i]==3) {
        		for (int i2=max[3]-1;i2>=min[2];i2--) {
        			if (ints[i2]==1) {
        				index=i2;
        				break;
        			}
        		}
        		temp=ints[i];
        		ints[i]=ints[index];
        		ints[index]=temp;
        		totalSwap++;
        	}
        }
        for (int i=0;i<max[1];i++) {
        	System.out.print(ints[i]+" ");
        }

        for (int i=min[2];i<max[2];i++) {
        	if (ints[i]==3) {
        		for (int i2=max[3]-1;i2>i;i2--) {
        			if (ints[i2]==2) {
        				index=i2;
        				break;
        			}
        		}
        		temp=ints[i];
        		ints[i]=ints[index];
        		ints[index]=temp;
        		totalSwap++;
        	}
        }
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
        pw.println(totalSwap);
        pw.close();
        System.exit(0);
    }
    
}

