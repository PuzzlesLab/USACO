/*
ID: king8791
LANG: JAVA
TASK: wormhole
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class wormhole {
	
	public static Node [] nodes;
	public static Setting [] settings;
	public static int settingsCount=0;
	public static SettingNode [] settingNodes;
	
	public static class Node {
		public int id;
		public int x;
		public int y;
		public Node plusXNode;
		public Node [] pairNode;
	}
	
	public static class Setting {
		public int id;
		public SettingNode [] nodeSet;
	}
	
	public static class SettingNode {
		public Node n1;
		public Node n2;
	}
	
	public static void generateSettings () {
		boolean [] flag=new boolean [nodes.length];
		SettingNode [] sNodeTemp=new SettingNode[nodes.length/2];
		for (int i=0;i<nodes.length-1;i++) {
			sNodeTemp[0]=settingNodes[i];
			flag[sNodeTemp[0].n1.id]=true;
			flag[sNodeTemp[0].n2.id]=true;
			generateSettingsHelper(1,flag,sNodeTemp);
			flag[sNodeTemp[0].n1.id]=false;
			flag[sNodeTemp[0].n2.id]=false;
		}
	}
	
	public static void generateSettingNodes() {
		int sNodeN=0;
		for (int i=0;i<nodes.length-1;i++) {
			for (int i2=i+1;i2<nodes.length;i2++) {
				settingNodes[sNodeN]=new SettingNode();
				settingNodes[sNodeN].n1=nodes[i];
				settingNodes[sNodeN].n2=nodes[i2];
				sNodeN++;
			}
		}
	}
	
	public static void generateSettingsHelper (int level, boolean [] flag, SettingNode [] sNodeTemp) {
		if (level==sNodeTemp.length) {
			Setting s=new Setting();
			s.nodeSet=new SettingNode[sNodeTemp.length];
			for (int i=0;i<sNodeTemp.length;i++) {
				s.nodeSet[i]=sNodeTemp[i];
				s.nodeSet[i].n1.pairNode[settingsCount]=s.nodeSet[i].n2;
				s.nodeSet[i].n2.pairNode[settingsCount]=s.nodeSet[i].n1;
			}
			s.id=settingsCount;
			settings[settingsCount++]=s;
		} else {
			for (int i=0;i<nodes.length;i++) {
				if (!flag[i]) {
					for (int i2=0;i2<settingNodes.length;i2++) {
						if (settingNodes[i2].n1.id>sNodeTemp[level-1].n1.id && settingNodes[i2].n1.id==i && !flag[settingNodes[i2].n1.id] && !flag[settingNodes[i2].n2.id]) {
							flag[settingNodes[i2].n1.id]=true;
							flag[settingNodes[i2].n2.id]=true;
							sNodeTemp[level]=settingNodes[i2];
							generateSettingsHelper(level+1,flag,sNodeTemp);
							flag[settingNodes[i2].n1.id]=false;
							flag[settingNodes[i2].n2.id]=false;
						}
					}
				}
			}
		}
	}
	
	public static boolean isSettingLoop (int sID) {
		Setting s=settings[sID];
		for (int i=0;i<nodes.length;i++) {
			Node n=nodes[i];
			do {
				n=n.plusXNode;
				if (n!=null) {
					n=n.pairNode[sID];
				}
			} while (n!=null && n!=nodes[i]);
			if (n!=null) {
				return true;
			}
		}
		return false;
	}
	
	public static void main (String [] zzzz) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("wormhole.in"));
		int count=Integer.parseInt(br.readLine());
		nodes=new Node[count];
		for (int i=0;i<count;i++) {
			String [] s=br.readLine().split(" ");
			Node n=new Node();
			n.id=i;
			n.x=Integer.parseInt(s[0]);
			n.y=Integer.parseInt(s[1]);
			n.pairNode=new Node[100000];
			nodes[i]=n;
		}
		for (int i=0;i<count;i++) {
			int closestX=Integer.MAX_VALUE;
			Node closestN=null;
			for (int i2=0;i2<count;i2++) {
				if (i2!=i && nodes[i].y==nodes[i2].y && nodes[i2].x>nodes[i].x && nodes[i2].x<closestX) {
					closestX=nodes[i2].x;
					closestN=nodes[i2];
				}
			}
			nodes[i].plusXNode=closestN;
		}
		settings=new Setting[100000];
		// let's say for 4 coordinates, the number would be 3+2+1...
		// n(2a+(n-1)d)/2
		// n = count-1, a=1, d=1
		// (count-1)(2+count-2)/2
		// (count-1)(count)/2
		int sNode=count*(count-1)/2;
		settingNodes=new SettingNode[sNode];
		generateSettingNodes();
		generateSettings();
		int zzz=0;
		for (int i=0;i<settingsCount;i++) {
			if (isSettingLoop(i)) {
				zzz++;
			}
		}
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		pw.println(zzz);
		pw.close();
	}
}