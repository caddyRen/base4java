# Method area 方法区

## stack、heap、method交互关系
```text
Person p=new Person();
Person类信息保存在Method area
new Person()实例对象保存在heap
如果此句在方法内部，则p是一个reference引用类型保存在stack
```
## method area 理解
```text
2.5.4 Method Area
The Java Virtual Machine has a method area that is shared among all Java Virtual
Machine threads. The method area is analogous[类似的] to the storage[保存] area for compiled[编译好的]
code of a conventional[传统] language or analogous to the "text" segment[文本段] in an operating
system process[进程]. It stores per-class[每个类] structures[结构] such as the run-time constant[常量] pool,
field[属性] and method data, and the code[字节码] for methods and constructors, including
the special methods (§2.9) used in class and instance[实例] initialization[初始化] and interface
initialization.
The method area is created on virtual machine start-up. Although the method area
is logically[逻辑上] part of the heap, simple implementations[实现] may choose not to either
garbage collect or compact[压缩] it. This specification[规范] does not mandate[要求] the location of
the method area or the policies used to manage compiled code. The method area
may be of a fixed[固定的] size or may be expanded[扩展的] as required by the computation[计算] and may
be contracted[收缩] if a larger method area becomes unnecessary. The memory for the
method area does not need to be contiguous[连续的].
A Java Virtual Machine implementation may provide the programmer or the user control
over the initial[初始] size of the method area, as well as[以及], in the case of[如果是] a varying-size[大小动态变化的] method area,
control over the maximum and minimum method area size.
The following exceptional[特殊] condition[情况] is associated with[有关联的] the method area:
• If memory in the method area cannot be made available to satisfy[满足] an allocation[分配]
request, the Java Virtual Machine throws an OutOfMemoryError.
```
- 方法区有一个别名叫Non-Heap非堆
- 方法区是一块独立于Java堆的内存空间
- 方法区保存类信息
- 线程共享
- 大小可以选择固定大小或者可扩展
- 方法区大小决定系统可以保存多少个类，如果系统定义了太多的类，导致方法区溢出，JVM抛出java.lang.OutOfMemoryError: PermGen space/java.lang.OutOfMemoryError: Metaspace
    - 例子：加载大量的第三方jar包
    - Tomcat部署的工程过多（30-50个）
    - 大量动态的生产反射类
- 关闭JVM就会释放这个方法区的内存
## 设置method area大小与OOM
1. -XX:PermSize=20.75M 'JDK7及以前设置PermanentGenerationSpace 初始值 默认20.75M'
2. -XX:MaxPermSize=82M 'JDK7及以前设置PermanentGenerationSpace 最大可分配空间，32位机器默认是64M，64位机器默认是82M'
3. -XX:MetaspaceSize=21M 'JDK8及以后，设置元空间初始值，平台不同默认值不同，windows下默认约为21M'
4. -XX:MaxMetaspaceSize=-1 'JDK8及以后，设置元空间最大可分配空间，-1表示没有限制'
- 如果不指定大小，默认情况下，JVM会耗尽所有的可用系统内存，如果元空间发生溢出，虚拟机一样会抛出java.lang.OutOfMemoryError: Metaspace
- 设置初始Metaspace大小，对于一个64位的服务器端JVM来说，其默认的-XX:MetaspaceSize值为21MB
- 21M是初始的高水位线，一旦触及这个水位线FullGC将会被触发并卸载没用的类（这些类对应的类加载器不再存活）
- FullGC后这个高水位线将会重置，新的高水位线的值取决于GC后释放了多少元空间
- 如果释放的空间不足，在不超过MaxMetaspaceSize时，适当提高该值
- 如果释放的空间过多，则适当降低该值
- 如果初始化的高水位线设置过低，上述高水位线调整情况会发生很多次。通过GC的日志可以观察到FullGC多次调用。
- 为了避免频繁GC，建议将-XX:MetaspaceSize设置为一个相对较高的值

## 方法区内部结构
- method area标准的存储内容，后续会变化
    - 类型信息：类class、接口interface、枚举enum、注解annotation等
        1. 这个类型的完整有效名称（全名=包名.类名）
        2. 这个类型直接父类的完整有效名（对于interface或java.lang.Object都没有父类）
        3. 这个类型的修饰符（public、abstract、final的某个子集）
        4. 这个类型直接接口的一个有序列表
    - 域信息（field/属性/常量）：运行时常量池，随着JDK变化，StringTable存储位置也会变化
        1. JVM必须在方法区中保存类型信息的所有域的相关信息以及域的声明顺序
        2. 域的相关信息包括
            1. 域名称
            2. 域类型
            3. 域修饰符（public、private、protected、static、final、volatile、transient的某个子集）
    - 静态常量（non-final static变量）：随着JDK变化，存储位置会变化
        1. 静态变量和类关联在一起，随着类的加载而加载，他们称为类数据在逻辑上的一部分
        2. 类变量被类的所有实例共享，即使没有类实例也可以访问
    - 即时编译器JIT编译后的代码缓存
    - 方法信息
        1. 方法名称
        2. 方法的返回类型（void也是一种类型）
        3. 方法参数的数量和类型（按顺序）
        4. 方法的修饰符（public、private、protected、static、final、synchronized、native、abstract的一个子集）
        5. 方法的字节码（byte codes）、OperandStack、LocalVariables及大小（abstract和native方法除外）
        6. 异常表（abstract和native除外）
            ```text
            每个异常处理的开始位置、结束位置、代码处理在Program Counter Register中的偏移地址、被捕获的异常类的常量池索引
            ```
    - static final全局常量
        1. 被声明为final的类变量的处理方法不同每个全局常量在编译的时候就会被分配
    - runtime Constant pool运行时常量池
        - Constant Pool Table是Class文件的一部分，用于存放编译期生成的各种字面量与符号引用
        - Constant Pool Table在类加载后存放到Method Area的Runtime Constant Pool
        - 运行时常量池，在加载类和接口到虚拟机后，就会创建对应的Runtime Constant Pool
        - JVM为每个已加载的类型（类或接口）都维护一个常量池，池中的数据项像数组项一样，是通过索引访问的
        - 运行时常量池中包含多种不同的常量，包括编译期就已经明确的数值字面量，也包括到运行期解析后才能够获得的方法或者字段引用，此时不再是常量池中的符号地址了，这里换为真实地址
            - 运行时常量池，相对于Class文件常量池的另一重要特征是：具备动态性
            - 动态举例，String.intern()如果运行时常量池中没有该字符串，则在运行时常量池放一个字符串常量，（native方法）
        - 运行时常量池类似于传统编程语言中的符号表（symbol table），但是它所包含的数据却比符号表要更加丰富一些
        - 当创建类或接口的运行时常量池时，如果构造运行时常量池所需的内存空间超过了方法区所能提供的最大值，则JVM会抛OOM异常
### 字节码文件
- 一个有效的字节码文件中除了包含类的版本信息、字段、方法以及接口等描述信息外，还包含一项信息那就是常量池表ConstantPoolTable，包括各种字面量和对类型、域、方法的符号引用
### 字节码文件为什么需要常量池
```text
一个Java源文件中的类、接口，编译后产生一个字节码文件。
Java中的字节码需要数据支持，通常这种数据会很大，以至于不能直接存在字节码里，
换另一种方式，可以存到常量池，这个字节码包含了指向常量池的引用。
在JVM stack的DynamicLinking的时候会用到运行时常量池，将符号引用转换为调用方法的直接引用。
可以大大减小字节码文件的大小

常量池，可以看作是一张表，虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量等类型
```
### 运行时常量池vs常量池
- methad area内部包含了运行时常量池
- 字节码文件内部包含了常量池 Constant pool
- ClassLoaderSubSystem将字节码文件加载到RuntimeDataArea，其中字节码文件中的常量池ConstantPool被加载到MethodArea后，就是运行时常量池    

## 方法区使用举例
## 方法区的演进细节
- jdk7以前习惯上把方法区称为永久代 Permanent Generation space
- jdk8开始使用MetaSpace元空间取代永久代
- 本质上方法区和永久代并不等价，仅对Hotspot而言两者等价
- JVM规范对如何实现方法区不做统一要求，BEA的JRockit和IBM J9中不存在永久代的概念
- 永久代导致java程序更容易OOM，超过-XX:MaxPermSize上限
  
- JDK8完全废弃永久代的概念，改用JRockit、J9一样在本地内存中实现MetaSpace来代替
- MetaSpace的本质和Permanent Generation space类似，都是对JVM规范中method area的实现
- MeatSpace不在JVM设置的内存中，而是直接使用本地物理内存，可以设置的更大，更不容易OOM
- PermGenSpace在JVM设置的内存中，所以容易OOM
- 根据JVM规范，如果方法区无法满足新的内存分配需求时，将抛出OOM
## 方法区的垃圾回收
## 总结