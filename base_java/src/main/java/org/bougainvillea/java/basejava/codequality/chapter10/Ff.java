package org.bougainvillea.java.basejava.codequality.chapter10;

/**
 * 135.必须定义性能衡量标准
 *
 * 性能衡量标准必须在一定的环境下，
 * 比如网络、操作系统、硬件设备等确定的情况下才会有意义，
 * 并且还需要限定并发数、资源数（如10万数据和1000万的数据响应时间肯定不同）等，
 * 当然很多时候我们并没有必要白纸黑字地签署一份协约，
 * 我们编写性能衡量标准更多地是为了确定一个目标，并尽快达到业务要求而已
 *
 * 出现性能问题不可怕，可怕的是没有目标，
 * 用户只是说“我希望它非常快”，或者说“和以前一样快”，
 * 在这种情况下，我们就需要把制定性能衡量标准放在首位了，原因有两个
 * 1)性能衡量标准是技术与业务之间的契约
 *      “非常快”是一个直观性的描述，它不具有衡量的可能性，
 *      对技术人员来说，一个请求在2秒钟之内响应就可以认为是“非常快”了，
 *      但对业务人员来说，“非常快”指的是在0.5秒内看到结果—看，
 *      出现偏差了。
 *      如果我们不解决这种偏差，就有可能出现当技术人员认为优化结束的时候，
 *      而业务人员还认为系统很慢，仍然需要提高继续性能，于是拒不签收验收文档，这就产生商务麻烦了
 * 2)性能衡量标志是技术优化的目标
 *      性能优化是无底线的，性能优化得越厉害带来的副作用也就明显，例如代码的可读性差，可扩展性降低等
 *      为了让我们的代码保持优雅，减少“坏味道”的产生，就需要定义一个优化目标：优化到什么地步才算结束
 * 一个好的性能衡量标准应该包括以下KPI（Key Performance Indicators）:
 *  1)核心业务的响应时间。
 *      一个新闻网站的核心业务就是新闻浏览，它的衡量标准就是打开一个新闻的时间；
 *      一个邮件系统的核心业务就是邮件发送和接收速度；
 *      一个管理型系统的核心就是流程提交，这也就是它的衡量标准
 *  2)重要业务的响应时间。
 *      重要业务是指在系统中占据前沿地位的业务，但是不会涉及业务数据的功能，
 *      例如一个业务系统需要登录后才能操作核心业务，
 *      这个登录交易就是它的重要交易，比如邮件系统的登录
 */
public class Ff {

    /**
     * 移位运算性能确实提高了，但是也带来了副作用，
     * 比如代码的可读性降低了很多，
     * 要想让其他人员看明白这个左移是何意，就需要加上注释说“把100扩大16倍”，
     * 这在项目开发中是非常不合适的
     */
    public static void main(String[] args) {

        int i=100*16;
        //把100扩大16倍
        int iPlus=100<<4;
        //移位运算
        //左移n，相当于乘2的n次方
        // 右边空出的位用0填补高位左移溢出则舍弃该高位。计算机中常用补码表示数据，注，用补码计算
        System.err.println(100<<1);//200
        System.err.println(100<<2);//400
        System.err.println(100<<3);//800
        System.err.println(100<<4);//1600
        System.err.println(111<<1);//222
        System.err.println(111<<2);//444
        System.err.println(111<<3);//888
        System.err.println(111<<4);//111*16
        System.err.println(-111<<2);//-111*16
        //右移，左边空出的位用0或者1填补。正数用0填补，负数用1填补。
        // 注：不同的环境填补方式可能不同；低位右移溢出则舍弃该位
        System.err.println(100>>1);//50
        System.err.println(50>>1);//25
        System.err.println(25>>1);//12
        System.err.println(12>>1);//6
        System.err.println(100>>2);//25
        System.err.println(100>>3);//12
        System.err.println(100>>4);//6
        System.err.println(-100>>2);//-25
        System.err.println(-100>>4);//-7
        System.err.println(-100/16);//-6
        System.err.println(-100%16);//-4 余
        //无符号右移
        //正数与右移规则一样，负数的无符号右移，就是相应的补码移位所得，在高位补0即可
        System.err.println(100>>>1);
        System.err.println(-100>>>1);
    }
}
