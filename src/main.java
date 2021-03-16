import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String p = scan.nextLine();
        if(isCorrect(p)) {
            TestString(p);
        }
        else System.out.println("String is not correct");
    }

    public static void TestString(String m) {
        System.out.println(Convert(m.toCharArray()));
    }

    public static String Convert(char[] c){
        int z = 0;
        String s = "";
        int num = 0;
        int indx = 0;
        for(int i = 0; i < c.length; i++) {
            if(Character.isLetter(c[i]) && num ==0){
                s+=c[i];
            }
            if (Character.isDigit(c[i]) && num == 0) {
                z = Integer.parseInt(String.valueOf(c[i]));
            }
            if (c[i] == '['){
                num++;
                if (num == 1)
                    indx = i;
            }
            if (c[i] == ']'){
                num--;
                if (num == 0){
                    s += Convert(Arrays.copyOfRange(c,indx+1,i)).repeat(z);
                    indx = 0;
                    z = 0;
                }
            }
        }
        return  s;
    }

    public static boolean isCorrect(String str) {
        int num = 0;
        for (int i = 0; i<str.length();i++){
            if (Character.isDigit(str.toCharArray()[i]))
                if (str.toCharArray()[i+1]!='[') {
                    return false;
                }

            if (str.toCharArray()[i] == '['){
                num++;
            }
            if (str.toCharArray()[i] == ']') {
                num--;
                if (num<0) {
                    return false;
                }
            }
        }
        if (num!=0) {
            return false;
        }
        return (Pattern.matches("[a-zA-Z\\[\\]0-9]+",str));
    }


}
