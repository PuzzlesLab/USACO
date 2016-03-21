/*
ID:king8791
LANG:JAVA 
TASK:holstein
*/  

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class holstein {

	public static class Feed {
		public int [] amount;
		public int total=0;
		public int [] id;
		public int idCount=0;
	}
	
	public static int VitaminCount;
	public static Feed ReferenceFeed;
	public static Feed [] Feeds;
	public static Feed BestFeed;

	public static void searchBestFeed (Feed f) {
		boolean fulfillVitamin=true;
		for (int i=0;i<VitaminCount;i++) {
			if (f.amount[i]<ReferenceFeed.amount[i]) {
				fulfillVitamin=false;
				break;
			}
		}
		if (fulfillVitamin) {
			if (f.idCount<BestFeed.idCount) {
				BestFeed=f;
			} else if (f.idCount==BestFeed.idCount) {
				if (f.id[0]<BestFeed.id[0]) {
					BestFeed=f;
				}
			}
		} else {
			if (f.total<BestFeed.total && f.idCount<Feeds.length) {
				for (int i=f.id[f.idCount-1]+1;i<Feeds.length;i++) {
					Feed fnew=new Feed();
					fnew.amount=new int[VitaminCount];
					for (int i2=0;i2<VitaminCount;i2++) {
						fnew.amount[i2]=f.amount[i2]+Feeds[i].amount[i2];
						fnew.total=fnew.total+fnew.amount[i2];
						if (fnew.total>BestFeed.total) {
							break;
						}
					}
					if (fnew.total<=BestFeed.total) {
						fnew.id=new int [Feeds.length];
						for (int i2=0;i2<f.idCount;i2++) {
							fnew.id[i2]=f.id[i2];
						}
						fnew.idCount=f.idCount;
						fnew.id[fnew.idCount++]=i;
						//System.out.println(fnew.id[fnew.idCount--]);
						searchBestFeed(fnew);
					}
				}
			}
		}
	}
	
	public static void main (String [] abc) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("holstein.in"));
        VitaminCount=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        ReferenceFeed=new Feed();
        ReferenceFeed.amount=new int [VitaminCount];
        for (int i=0;i<VitaminCount;i++) {
        	ReferenceFeed.amount[i]=Integer.parseInt(st.nextToken());
        }
        int nFeed=Integer.parseInt(br.readLine());
        Feeds=new Feed[nFeed];
        for (int i=0;i<nFeed;i++) {
        	Feed f=new Feed();
        	f.amount=new int [VitaminCount];
        	st=new StringTokenizer(br.readLine());
        	for (int i2=0;i2<VitaminCount;i2++) {
        		f.amount[i2]=Integer.parseInt(st.nextToken());
        		f.total=f.total+f.amount[i2];
        	}
        	f.id=new int [nFeed];
        	f.id[f.idCount++]=i;
        	Feeds[i]=f;
        }
        BestFeed = new Feed();
        BestFeed.amount=new int [VitaminCount];
        for (int i=0;i<VitaminCount;i++) {
        	BestFeed.amount[i]=Integer.MAX_VALUE;
        }
        BestFeed.id=new int [nFeed];
        BestFeed.id[0]=Integer.MAX_VALUE;
        BestFeed.idCount=Integer.MAX_VALUE;
        BestFeed.total=Integer.MAX_VALUE;
        for (int i=0;i<nFeed;i++) {
        	searchBestFeed(Feeds[i]);
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
        String s=BestFeed.idCount+" ";
        for (int i=0;i<BestFeed.idCount;i++) {
        	s=s+(BestFeed.id[i]+1)+" ";
        }
        s=s.substring(0,s.length()-1);
        pw.println(s);
        pw.close();
        System.exit(0);
	}
	
}