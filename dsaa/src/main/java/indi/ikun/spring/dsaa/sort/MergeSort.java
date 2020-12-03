package indi.ikun.spring.dsaa.sort;

import java.util.Arrays;

/**
 * 归并排序 分治算法
 *
 * （先分的，后治）==》栈---》递归
 *
 *  1,5,7,4,3,2
 *  1,5,7  4,3,2
 *  1,5  7  4,3  2
 *  1  5  7  4  3  2
 *  治
 *  1,5  7   3,4  2
 *  1,5,7    2,3,4
 *  1,2,3,4,5,6,7
 *
 */
public class MergeSort {

    public static void main(String[] args) {
        int a[]={1,5,7,4,3,2,0};
        int temp[]=new int[a.length];
        fen(a,0,a.length-1,temp);
        System.err.println(Arrays.toString(a));

    }

    public static void fen(int a[],int left,int right,int[] temp){
        if(left<right){
            int mid=(left+right)/2;
            fen(a, left, mid, temp);
            fen(a, mid+1, right, temp);
            merge(a, left ,mid,right,temp);
        }
    }


    /**
     *
     * @param a 待治数组
     * @param left 左
     * @param mid 中
     * @param right 右
     * @param temp 临时存放有序数组
     */
    public static void merge(int[] a,int left, int mid,int right,int[] temp){
        int i=left;
        int j=mid+1;
        int t=0;
        while (i<=mid&&j<=right){
            if(a[i]<a[j]){
                temp[t]=a[i];
                i++;
            }else {
                temp[t]=a[j];
                j++;
            }
            t++;
        }
        while (j<=right){
            temp[t]=a[j];
            t++;
            j++;
        }
        while (i<=mid){
            temp[t]=a[i];
            t++;
            i++;
        }
        //转移有序数组
        t=0;
        //tempLeft = 0,0,3,3,0
        //right    = 1,2,4,5,5
        int tempLeft=left;
        while (tempLeft<=right){
            a[tempLeft]=temp[t];
            t++;
            tempLeft++;
        }
        System.err.println(Arrays.toString(a));
    }



}
