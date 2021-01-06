package org.bougainvillea.java.basejava.codequality.chapter12;

/**
 * 147.让接口的职责保持单一
 *
 * 接口职责一定要单一，实现类职责尽量单一
 *
 * 一个类所对应的需求功能越多，引起变化的可能性就越大，
 * 单一职责原则（Single Responsibility Principle，简称SRP）就是要求我们的接口（或类）尽可能保持单一，
 * 它的定义是说“一个类有且仅有一个变化的原因（There should never be more than one reason for a class tochange）”
 *
 * 职责是一个接口（或类）要承担的业务含义，或是接口（或类）表现出的意图，
 *      例如一个User类可以包含写入用户信息到数据库、删除用户、修改用户密码等职责，
 *      而一个密码工具类则可以包含解密职责和加密职责。
 * 明白了什么是类的职责单一，再来看看它有什么好处。单一职责有以下三个优点
 * 1）类的复杂性降低
 *      职责单一，在实现什么职责时都有清晰明确的定义，
 *      那么接口（或类）的代码量就会减少，复杂度也就会减少。
 *      当然，接口（或类）的数量会增加上去，相互间的关系也会更复杂，这就需要适当把握了
 * 2）可读性和可维护性提高
 *      职责单一，会让类中的代码量减少，我们可以一眼看穿该类的实现方式，有助于提供代码的可读性，这也间接提升了代码的可维护性
 * 3）降低变更风险
 *      变更是必不可少的，如果接口（或类）的单一职责做得好，一个接口修改只对相应的实现类有影响，对其他的接口无影响，那就会对系统的扩展性、维护性都有非常大的帮助
 *
 * 以电话通信为例子来说明如何实施单一职责原则:
 * 1）分析职责
 *      一次电话通信包含四个过程：拨号、通话、回应、挂机，
 *      我们来思考一下该如何划分职责，这四个过程包含了两个职责：
 *          一个是拨号和挂机表示的是通信协议的链接和关闭，
 *          另外一个是通话和回应所表示的数据交互
 *      问题是我们依靠什么来划分职责呢？
 *      依靠变化因素，我们可以这样考虑该问题：
 *          通信协议的变化会引起数据交换的变化吗？会的！你能在3G网络视频聊天，但你很难在2G网络上实现。
 *          数据交互的变化会引起通信协议的变化吗？会的！传输2KB的文件和20GB的文件需要的不可能是同一种网络，用56KB的“小猫”传输一个20GB的高清影视那是不可行的
 * 2）设计接口
 *      职责分析确定了两个职责，
 *      首先不要考虑实现类是如何设计的，
 *      我们首先应该通过两个接口来实现这两个职责。
 * 3）合并实现
 *      接口定义了两个职责，难道实现类就一定要分别实现这两个接口吗？这样做确实完全满足了单一职责原则的要求：
 *          每个接口和类职责分明，结构清晰，
 *      因为一个电话类要把ConnectionManager和DataTransfer的实现类组合起来才能使用。
 *      组合是一种强耦合关系，你和我都有共同的生命期，
 *      这样的强耦合关系还不如使用接口实现的方式呢！而且这还增加了类的复杂性，多出了两个类。
 *      通常的做法是一个实现类实现多个职责，也就是实现多个接口
 */
public class Fr {

}

/**
 * 通信协议
 */
interface ConnectionFr{
    //拨通电话
    void dial();
    //通话完毕，挂电话
    void huangup();
}

/**
 * 数据传输
 */
interface TransferFr{
    //通话
    void chat();
}

/**
 * 一个类实现了两个接口，把两个职责融合在一个类中。
 * 你会觉得这个Phone有两个原因引起了变化，
 * 是的，但是别忘记了我们是面向接口编程的，
 *
 * 我们对外公布的是接口而不是实现类。
 *
 * 而且，如果真要实现类的单一职责，就必须使用上面的组合模式，
 * 这会引起类间的耦合过重、类的数量增加等问题，人为地增加了设计的复杂性
 */
class PhoneFr implements ConnectionFr,TransferFr{
    @Override
    public void dial() {

    }

    @Override
    public void huangup() {

    }

    @Override
    public void chat() {

    }
}