package indi.ikun.spring.basejava.io.chapter01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author renqiankun
 */
public class Test7 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("tom", 188));
        users.add(new User("jim", 288));
        users.add(new User("mike", 688));
        users.add(new User("mike2", 688));
        users.add(new User("jimi", 888));
        activity(users);
    }

    public static void activity(List<User> users) {
        Scanner sc = new Scanner(System.in);
        System.err.println("输入1显示用户，输入2开始抽奖，输入0结束活动");
        boolean bool=true;
        while (bool) {
            switch (sc.nextInt()) {
                case 1: {
                    display(users);
                    System.err.println("输入1显示用户，输入2开始抽奖，输入0结束活动");
                    break;
                }
                case 2: {
                    System.err.println("请输入幸运积分数");
                    int luckyNum = sc.nextInt();
                    getLucky(users, luckyNum);
                    System.err.println("输入1显示用户，输入2开始抽奖，输入0结束活动");
                    break;
                }
                case 0:{
                    bool=false;
                    break;
                }
                default:
                    System.err.println("输入1显示用户，输入2开始抽奖，输入0结束活动");
                    break;
            }
        }
        sc.close();
    }

    public static void display(List<User> users) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < users.size(); i++) {
            sb.append(users.get(i).toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.err.println(sb.toString());
    }

    public static void getLucky(List<User> users, Integer luckyScore) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < users.size(); i++) {
            if (luckyScore.equals(users.get(i).score)) {
                sb.append(users.get(i).name);
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append("NONE");
        }
        System.err.println(sb.toString());
    }
}

class User {
    String name;
    Integer score;

    public User(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ":" + score;
    }
}
