package indi.ikun.spring.dsaa;

public class Test {
    public static void main(String[] args) {

        System.out.println(17-16);



//        System.out.println(15&1);
//        System.out.println(8&1);
//        System.out.println(getCandy(15,0));
        flag(13);
        flag(15);
        flag(17);
        flag(7);
        flag(5);
        flag(3);
        //16   13   6   8

        //16  10000
        //8   01000
        //4   00100
        //2   00010

        //17  10001   -1   10010  10000  00001 1
        //15  01111   +1   10000  01110  00111 7
        //13  01101   -1   01110  01100  00101 5
        //7   00111   +1   01000  00110  00011
        //5   00101   -1   00110  00100  00001
        //3   00011   -1   00100  00010  00001
        //3   00011

        //13-1 12/2 6/2 3-1 2/2

        //15/2=7
        //16 15       7 8

        //15+1 16/2 8/2 4/2 2/2

    }

    public static boolean flag(int a){
        String s = Integer.toString(a, 2);
        int length=s.length();
        double tmp_b=Math.pow(2,length-1);
        int v = a - (int)tmp_b;
        if((v&3)==3){
            return true;
        }
        return false;
    }


    public static int getCandy(int a,int count){
        //按位与运算判断是否为奇数
        if((a&1)==1){
            count++;
            a+=1;
        }
        a /= 2;
        count++;
        if(a!=1){
            count=getCandy(a,count);
        }
        return count;
    }
}
