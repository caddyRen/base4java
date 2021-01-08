# JVM

## 所需插件

### javap

- javap -c xxx.class
- javap -l xxx.class

### IDEA jclasslib 插件

## JVM架构

- Class Loader subSystem 类加载子系统
    - loading 加载
        - Bootstrap ClassLoader c和c++实现的 引导 类加载器（Java核心类库默认使用）
        - Extension ClassLoader java实现的 扩展 类加载器
        - Application ClassLoader java实现的 系统 类加载器（自定义类型默认使用）
        - 自定义类加载器
    - Linking 链接
        - Verify 验证
        - Prepare 准备
        - Resolve 解析
    - Initialization 初始化
- Runtime Data Areas 运行时数据区
    - PC Registers 程序计数器 pc寄存器 每个线程一份
    - Stack Area 虚拟机栈 每个线程一份
        - stack frame 栈帧
        - LV 局部变量表
        - OS 操作数栈
        - DL 动态链接
        - RA 方法返回地址
    - Native Method Stack 本地方法栈
    - Heap Area 堆区 对象主体存放位置 GC主要管理的区域 多线程共享
    - Method Area 方法区 类信息，常量，域信息，方法信息 多线程共享（HotSpot JVM有此区，JDK8叫元数据metadata）
- Execution Engine 执行引擎
    - Interpreter 解释器
    - JIT Compiler 即时编译器
        - Intermediate Code Generator 中间代码生成器
        - Code Optimizer 代码优化器
        - Target Code Generator 目标代码生成器
        - Profiler 分析器
    - Garbage Collection 垃圾回收器
- Native Method Interface（JNI） 本地方法接口
- Native Method Library 本地方法库

## Class Loader subSystem 类加载器子系统

- 类加载器子系统负责从文件系统或者网络中加载class文件，class文件在文件开头有特定的文件标识
- ClassLoader只负责class文件的加载，能否运行由Execution Engine决定
- 加载的类信息存放于方法区，方法区中还会存放运行时常量池信息（final），字符串字面量和数字常量（这部分常量信息是class文件中常量池部分的内存映射）

### Loading

- 通过一个类的全限定名（全类名）获取定义此类的二进制字节流
- 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
- 在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口
- 加载.class文件方式
    - 从本地文件系统直接加载
    - 通过网络获取，典型场景Web Applet
    - 从压缩包中读取，jar包，war包基础
    - 运行时计算生成，动态代理技术
    - 由其他文件生成，JSP应用
    - 从专有数据库中提前.class文件，比较少见
    - 从加密文件中获取，防止反编译的保护措施

#### Java虚拟机规范

- 所有派生于抽象类ClassLoader的类加载器都划分为自定义类加载器

#### JVM支持两种类加载器

- Bootstrap ClassLoader 引导类加载器，c和c++实现，嵌套再jvm内部
- User-Defined ClassLoader 自定义加载器，java实现，派生于抽象类java.lang.ClassLoader

#### 常见类加载器（不是上下层关系，也不是父子类的继承关系，是包含关系，a文件夹里有b和c，类似这种包含关系）

- 引导类加载器
    - Bootstrap Class Loader c和c++实现
    - 嵌套再jvm内部
    - 用来加载java核心库（JAVA_HOME/jre/lib/rt.jar、resources.jar或sun.boot.class.path路径下的内容）用于提供JVM自身需要的类
    - 并不继承自java.lang.ClassLoader，没有父加载器
    - 加载扩展类和应用程序类加载器，并指定为他们的父类加载器
    - 处于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类
- 自定义类加载器
    - Extension Class Loader 
        - java语言编写由sun.misc.Launcher的静态内部类static class ExtClassLoader extends URLClassLoader实现
        - 派生于ClassLoader
        - 父类加载器为 Bootstrap Class Loader
        - 从java.ext.dirs系统属性所指定的目录中加载类库
        - 从JDK的安装目录jre/lib/ext子目录（扩展目录）下加载类库，如果用户创建的jar放在此目录下，也会自动由扩展类加载器加载
    - System Class Loader 
        - java语言编写由sun.misc.Launcher的静态内部类static class AppClassLoader extends URLClassLoader实现
        - 派生于ClassLoader
        - 父类加载器为 Extension Class Loader
        - 负责加载环境变量classpath或系统属性java.class.path指定路径下的类库
        - 程序中的默认类加载器，一般java应用的类都是由其来完成加载
        - 通过CLassLoader.getSystemClassLoader()可以获取到该类加载器
    - User Defined Class Loader

### Linking

#### Verify

- 确保class文件的字节流中包含的信息符合当前虚拟机要求，保证被加载类的正确性，保证不会危害虚拟机自身安全
- 验证方式
    - 文件格式验证
    - 元数据验证
    - 字节码验证
    - 符号引用验证

#### Prepare

- 为类变量分配内存并且设置该类变量的默认初始值（int i=1; 此时i=0）
- 不包含用final修饰的static静态常量，因为final在编译时分配，prepare阶段会显示初始化
- 这里不会为实例变量分配初始化，类变量会分配在Method Area方法区，而实例变量会随着对象一起分配到Heap Area堆区

#### Resolve

- 将常量池内的符号引用转换为直接引用的过程
- 事实上，Resolve解析操作往往会伴随JVM在执行完初始化之后再执行
- 符号引用就是一组符号来描述所应用的目标，符号引用的字面量形式明确定义在《java虚拟机规范》的class文件格式中。 直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄
- 解析动作主要针对类或接口、字段、类方法、接口方法、方法类型等。对应常量池中
    - CONSTANT_Class_info
    - CONSTANT_Fieldref_info
    - CONSTANT_Methodref_info
    - ......

### Initialization

- 初始化阶段就是执行类构造器方法（不是类的构造器） <clinit>() 的过程（当有静态变量赋值，静态代码块赋值时才会有clinit）
- 此方法不需要定义，是javac编译器自动收集类中的所有类静态变量的赋值动作和静态代码块中的语句合并而来
- 构造器方法中指令按语句在源文件中出现的顺序执行
- <clinit>() 不同于类的构造器
    - 构造器是jvm视角下的<init>()
- 若该类具有父类，jvm会保证 父类的<clinit>() 执行完毕后 子类的 <clinit>()执行
- jvm必须保证一个类的<clinit>() 方法在多线程下被同步加锁
    - 类只会被加载一次，并放置到method area（metadata）
