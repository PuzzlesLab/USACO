/*
ID: king8791
LANG: JAVA
TASK: calfflac
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

class calfflac {
    
    public static boolean isPalindrome (String s) {
        char [] c=s.toCharArray();
        int max=c.length-1;
        for (int i=0;i<c.length/2;i++) {
            if (c[i]!=c[max-i]) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isAlphabet (char c) {
        return ((c>='A' && c<='Z') || (c>='a') && (c<='z'));
    }
    
    public static int countAlphabets (String s) {
        int count=0;
        for (int i=0;i<s.length();i++) {
            if (isAlphabet(s.charAt(i))) {
                count++;
            }
        }
        return count;
    }
    
    public static String getPalindrome (String s) {
        int longestStrMin=0;
        int longestStrMax=1;
        int longestStrLength=1;
        int min;
        int max;
        int currAlphabetCounts;
        int currStrMin;
        int currStrMax;
        String result="";
        char [] sChar=s.toUpperCase().toCharArray();
        for (int currPos=1;currPos<s.length()-1;currPos++) {
            //ABA
            currStrMin=currPos;
            currStrMax=currPos;
            min=currPos;
            max=currPos;
            currAlphabetCounts=1;
            while (true) {
                do {
                    min--;
                } while (min>0 && !isAlphabet(sChar[min]));
                do {
                    max++;
                } while (max<s.length() && !isAlphabet(sChar[max]));
                if ((min<0 || max>=s.length())) {
                    break;
                }
                if (sChar[min]!=sChar[max]) {
                    break;
                } else {
                    currAlphabetCounts+=2;
                    currStrMin=min;
                    currStrMax=max+1;
                }
            }
            if (currAlphabetCounts>longestStrLength) {
                longestStrMin=currStrMin;
                longestStrMax=currStrMax;
                longestStrLength=currAlphabetCounts;
            }
            //ABBA
            min=currPos-1;
            max=currPos;
            currStrMin=min;
            currStrMax=max;
            currAlphabetCounts=0;
            if (sChar[min]==sChar[max]) {
                currAlphabetCounts=2;
                while (true) {
                    do {
                        min--;
                    } while (min>0 && !isAlphabet(sChar[min]));
                    do {
                        max++;
                    } while (max<s.length() && !isAlphabet(sChar[max]));
                    if ((min<0 || max>=s.length())) {
                        break;
                    }
                    if (sChar[min]!=sChar[max]) {
                        break;
                    } else {
                        currAlphabetCounts+=2;
                        currStrMin=min;
                        currStrMax=max+1;
                    }
                }
                if (currAlphabetCounts>longestStrLength) {
                    longestStrMin=currStrMin;
                    longestStrMax=currStrMax;
                    longestStrLength=currAlphabetCounts;
                }
            }
        }
        return s.substring(longestStrMin,longestStrMax);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("calfflac.in"));
        int lines=0;
        while (br.readLine()!=null) {
            lines++;
        }
        br=new BufferedReader(new FileReader("calfflac.in"));
        String originalText="";
        for (;lines>0;lines--) {
            originalText=originalText+"@"+br.readLine();
            //@ must be something that the text doesn't use.
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
        String s=getPalindrome(originalText);
        String [] toPrint=s.split("@");
        pw.println(countAlphabets(s));
        for (int i=0;i<toPrint.length;i++) {
            pw.println(toPrint[i]);
        }
        pw.close();
        System.exit(0);
    }
    
}