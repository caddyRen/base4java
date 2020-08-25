package indi.ikun.spring.basejava.codequality.chapter03;

/**
 * 36.使用构造代码块精炼程序
 * 代码块（Code Block），用大括号把多行代码封装在一起，形成一个独立的数据体，实现特定算法的代码集合即为代码块，
 * 一般来说代码块是不能单独运行的，必须要有运行主体。在Java中一共有四种类型的代码块
 * 1. 普通代码块
 *      就是在方法后面使用“{}”括起来的代码片段，它不能单独执行，必须通过方法名调用执行。
 * 2. 静态代码块
 *      在类中使用static修饰，并使用“{}”括起来的代码片段，用于静态变量的初始化或对象创建前的环境初始化
 * 3. 同步代码块
 *      使用synchronized关键字修饰，并使用“{}”括起来的代码片段，它表示同一时间只能有一个线程进入到该方法块中，是一种多线程保护机制
 * 4. 构造代码块
 *      在类中没有任何的前缀或后缀，并使用“{}”括起来的代码片段
 *
 * 一个类至少有一个构造函数（如果没有，编译器会无私地为其创建一个无参构造函数），
 * 构造函数是在对象生成时调用的，
 * 构造代码块不具有独立执行的能力，编译器会把构造代码库插入到每个构造函数的最前端 Bj和BjA相当
 * 构造代码块会在每个构造函数内首先执行（需要注意的是：构造代码块不是在构造函数之前运行的，它依托于构造函数的执行）
 *
 * 应用：
 * 1.初始化实例变量（Instance Variable）
 *      如果每个构造函数都要初始化变量，可以通过构造代码块来实现。
 *      当然也可以通过定义一个方法，然后在每个构造函数中调用该方法来实现，没错，可以解决，但是要在每个构造函数中都调用该方法，而这就是其缺点，
 *      若采用构造代码块的方式则不用定义和调用，会直接由编译器写入到每个构造函数中，这才是解决此类问题的绝佳方式
 * 2.初始化实例环境
 *      一个对象必须在适当的场景下才能存在，如果没有适当的场景，则就需要在创建对象时创建此场景，
 *      例如在JEE开发中，要产生HTTP Request必须首先建立HTTP Session，
 *      在创建HTTP Request时就可以通过构造代码块来检查HTTP Session是否已经存在，不存在则创建之
 * 构造代码块的两个特性：
 *      1.在每个构造函数中都运行
 *      2.在构造函数中它会首先运行
 * 很好地利用构造代码块的这两个特性不仅可以减少代码量，还可以让程序更容易阅读，
 * 特别是当所有的构造函数都要实现逻辑，而且这部分逻辑又很复杂时，这时就可以通过编写多个构造代码块来实现。
 * 每个代码块完成不同的业务逻辑（当然了，构造函数尽量简单，这是基本原则），按照业务顺序依次存放，
 * 这样在创建实例对象时JVM也就会按照顺序依次执行，实现复杂对象的模块化创建
 */
public class Bj {
    {
        //构造代码块3
        System.err.println("执行构造代码块3");
    }
    {
        //构造代码块
        System.err.println("执行构造代码块");
    }

    {
        //构造代码块2
        System.err.println("执行构造代码块2");
    }

    
    public Bj(){
        System.err.println("执行无参构造");
    }
    public Bj(String _str){
        System.err.println("执行有参构造");
    }
}

class BjA {

    public BjA(){
        //构造代码块3
        System.err.println("执行构造代码块3");
        //构造代码块
        System.err.println("执行构造代码块");
        //构造代码块2
        System.err.println("执行构造代码块2");
        System.err.println("执行无参构造");
    }
    public BjA(String _str){
        //构造代码块3
        System.err.println("执行构造代码块3");
        //构造代码块
        System.err.println("执行构造代码块");
        //构造代码块2
        System.err.println("执行构造代码块2");
        System.err.println("执行有参构造");
    }
}
class BjAClient{
    public static void main(String[] args) {
        Bj bj = new Bj();
    }
}