package indi.ikun.spring.basejava.io.chapter01;
import java.util.Scanner;
/**
 * @author renqiankun
 */
public class Test8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] customers = str.split(",");
        String[] name = new String[customers.length];
        int[] score = new int[customers.length];
        for (int i = 0; i < customers.length; i++) {
            String[] user = customers[i].split(":");
            name[i] = user[0];
            score[i] = Integer.valueOf(user[1]);
        }
        StringBuffer sb = new StringBuffer();
        int luckyNum = sc.nextInt();
        sc.close();
        for (int i = 0; i < score.length; i++) {
            if (luckyNum == score[i]) {
                sb.append(name[i]);
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            System.err.println(sb.toString());
        }else System.err.println("NONE");

    }
}