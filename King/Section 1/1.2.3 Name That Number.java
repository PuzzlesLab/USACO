/*
ID: king8791
LANG: JAVA
TASK: namenum
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class namenum {
	
    public static long nameToDigit(String s) {
        long value=0;
        for (int i=0;i<s.length();i++) {
        	char c=s.charAt(i);
        	if (c<='P') {
        		value=value*10+(((s.charAt(i)-'A')/3)+2);
        	} else {
        		value=value*10+(((s.charAt(i)-'A'-1)/3)+2);
        	}
        }
        return value;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("namenum.in"));
        long digits=Long.parseLong(br.readLine());
        br=new BufferedReader(new FileReader("dict.txt"));
        HashMap<Long,ArrayList<String>> map=new HashMap<Long,ArrayList<String>>();
        String s;
        while ((s=br.readLine())!=null) {
        	long value=nameToDigit(s);
        	ArrayList<String> list=null;
        	if (!map.containsKey(value)) {
        		list=new ArrayList<String>();
        		map.put(value, list);
        	} else {
        		list=map.get(value);
        	}
        	list.add(s);
        }
        if (map.containsKey(digits)) {
        	StringBuilder sb=new StringBuilder();
        	Iterator<String> it=map.get(digits).iterator();
        	while (it.hasNext()) {
        		sb.append(it.next());
        		sb.append("\n");
        	}
        	s=sb.toString();
        } else {
            s="NONE\n";
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        pw.print(s);
        pw.close();
        System.exit(0);
    }
}