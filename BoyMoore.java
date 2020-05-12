import java.util.HashMap;
import java.util.Scanner;

public class BoyMoore {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.print("Input text: ");
        String basestr = in.nextLine();
        String searched = "";
        boolean flag = true;
        String s = "";
        while (flag) {
            System.out.println("Options: Add, Search, Show, Compare, Stop");
            //String choice = in.next();
            String choice = in.nextLine();
        switch (choice.toLowerCase()) {
            case "add":
                System.out.print("Enter string: ");
                basestr = Add(basestr, in.next());
                s = in.nextLine();
                break;
            case "search":
                System.out.print("Enter string: ");
                searched = in.next();
                int ind = Search(basestr, searched);
                if (ind == -1) System.out.println("Not found");
                else System.out.println("Index of first occurence: " + ind);
                s = in.nextLine();
                break;
            case "show":
                System.out.println(basestr);
                break;
            case "stop":
                flag = false;
                break;
            case "compare":
                System.out.print("Enter string: ");
                searched = in.next();
                long start = System.currentTimeMillis();
                ind = Search(basestr, searched);
                long finish = System.currentTimeMillis();
                long time = finish-start;
                System.out.println("Boyer Moore time: " + time);
                start = System.currentTimeMillis();
                ind = basestr.indexOf(searched);
                finish = System.currentTimeMillis();
                time = finish-start;
                System.out.println("Base search time: " + time);
                s = in.nextLine();
                break;
            default: System.out.println("Unrecognized command");
        }
        }
    }

    public static int Search(String source, String template) {
        int podstrl = template.length();
        int strl = source.length();
        if (strl < podstrl) {
            return -1;
        }
        HashMap<Character, Integer> smash = new HashMap<Character, Integer>();
        for (int i = 0; i <= 255; i++) {
            smash.put((char) i, podstrl);
        }
        for (int i = 0; i < podstrl - 1; i++) {
            smash.put(template.charAt(i), podstrl - i - 1);
        }
        int count = podstrl - 1;
        int strch = count;
        int podstrch = count;
        while (count <= strl - 1 && podstrch >= 0) {
            podstrch = podstrl - 1;
            strch = count;
            while (podstrch >= 0 && source.charAt(strch) == template.charAt(podstrch)) {
                podstrch--;
                strch--;
            }
            count += smash.get(source.charAt(count));
        }
        if (strch >= strl - podstrl) {
            return -1;
        } else {
            return strch + 1;
        }
    }

    public static String Add(String base, String additive) {
        return base + " " + additive;
    }
}
