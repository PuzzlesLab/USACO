/*
ID: king8791
LANG: JAVA
TASK: skidesign
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

class skidesign {
	
	public static void main (String [] zzzz) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("skidesign.in"));
		int count=Integer.parseInt(br.readLine());
		int [] heights=new int [count];
		for (int i=0;i<count;i++) {
			heights[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(heights);
		int cost=Integer.MAX_VALUE;
		if (heights[count-1]-heights[0]<=17) {
			cost=0;
		} else {
			for (int min=heights[0];min<=heights[count-1] && heights[count-1]-min>17;min++) {
				int max=min+17;
				int thisCost=0;
				for (int i=0;i<count;i++) {
					if (heights[i]>max) {
						thisCost+=(max-heights[i])*(max-heights[i]);
					} else if (heights[i]<min) {
						thisCost+=(heights[i]-min)*(heights[i]-min);
					}
				}
				cost=Math.min(cost,thisCost);
			}
		}
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		pw.println(cost);
		System.out.println(cost);
		pw.close();
	}
}