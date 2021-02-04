# heap area

## heap area核心概述

- 一个JVM实例只存在一个heap area，heap area是java内存管理的核心区域
- java heap area在JVM启动时即被创建，其空间大小也就确定了，是JVM管理的最大一块内存空间
    - heap area size是可以调节的
- java虚拟机规范 规定 heap可以处于物理上不连续的内存空间中，但在逻辑上它应该被视为连续的
- 所有线程共享java heap area 在这里还可以划分线程私有的缓冲区（TLAB：Thread local allocation buffer）
- JVM规范对Java Heap描述为：所有对象实例以及数组都应当在运行时分配在堆上（The heap is the run-time data area from which memory for all class instances
  and arrays is allocated）
    - 几乎所有的对象实例都在这里分配内存，almost 不是 all
- 数组和对象可能永远不会存储在stack上，因为stack frame中保存引用，这个引用指向对象或者数组在堆中的位置
- 在方法结束后，堆中对象不会马上被移除，仅仅在垃圾收集的时候才会被移除
- heap area 是Garbage Collection执行垃圾回收的重点区域

## Heap 分代思想
1. 经研究，不同对象的生命周期不同，70%~99%的对象是临时对象
    1. 新生代: 由Eden、两块大小相同的Survivor区（s0、s1或from、to）构成（to总是空的）
    2. 老年代: 存放新生代中经历多次GC仍存活的对象
2. 不分代完全可以
```text
分代的唯一理由就是优化GC性能
如果没有分代，将所有的对象都放在一块，GC时找哪些对象没有用，此时需要对所有区域进行扫描，STW（stop the work）时间相对分代策略会变长
而很对对象都是朝生夕死，
如果分代，把新创建的对象放到某一地方，GC时先把这块存储新创建对象的区域进行回收，STW时间相对不分代的扫描全部区域小很多
```

## heap area 细分 现代垃圾收集器大部分都基于分代收集理论设计

- 新生区--新生代--年轻代
- 养老区--老年区--老年代
- 永久区--永久代

### java7及之前 堆内存 逻辑上 分为三部分

1. Yong Generation Space 新生区 Young/New
    1. Eden区
    2. Survivor区（只有一个存放数据，当jvm计算容量时只会考虑一个，所以Runtime.getRuntime().totalMemory()和Runtime.getRuntime().maxMemory()
       的值会少一个survivor区的大小）
        1. survivor 0 区
        2. survivor 1 区
2. Tenure Generation Space 养老区 Old/Tenure
3. Permanent Space 永久区 Perm

### java8及之后 堆内存 逻辑上 分为三部分

1. Yong Generation Space 新生区 Young/New
    1. Eden区
    2. Survivor区（只有一个存放数据，当jvm计算容量时只会考虑一个，所以Runtime.getRuntime().totalMemory()和Runtime.getRuntime().maxMemory()
       的值会少一个survivor区的大小）
        1. survivor 0 区
        2. survivor 1 区
2. Tenure Generation Space 养老区 Old/Tenure
3. Meta Space 元空间 Meta

### Java对象

```text
存储在JVM中的java对象可以被划分为两类
1.生命周期较短的瞬时对象，这类对象的创建和消亡都非常迅速
2.生命周期非常长，在某些极端的情况下还能够与JVM的生命周期保持一致
```

### 新生代

1. 在Hotspot中Eden空间和另外两个Survivor空间缺省所占比例是8：1：1 ;通过-XX:SurvivorRatio=8(默认为8，但是实际比例是6：1：1 -XX:
   +UseAdaptiveSizePolicy默认开启了自适应内存分配策略，显式添加参数才会生效) 调整比例
2. 几乎所有的Java对象都是在Eden区被new出来
3. 绝大部分的Java对象的销毁都在新生代进行
    - IBM公司的专门研究表明，新生代中80%的对象都是朝生夕死
4. 可以使用选项-Xmn设置新生代最大内存大小
    - 这个参数一般使用默认值就可以了

### 老年代

### 对象分配过程

- 注意
    - 当Eden区满时才会触发YGC，Survivor0或1区满不会触发YGC
    - YGC回收Eden区和From区（YGC后会清空Eden区和From区）
    - Survivor 0区和Survivor 1区大小1：1，肯定会有一个为空。为了使用复制算法，目的是解决碎片化问题 
    - Survivor 0区和Survivor 1区：复制之后有交换，谁空谁时To区
    - Garbage Collection频繁在Young区收集，很少在Old区收集，几乎不在Perm/Meta收集
    - 对象可能直接分配在Old区
    - Eden区和To区满了，对象即使没达到阈值，也可能直接晋升到Old区
- ![image](img/young_old.png)
```text
1. new的对象优先尝试放Eden区，Eden区可能已有对象
2. 如果Eden区剩余空间放得下，则直接在Eden区为对象分配内存
3. 如果Eden区剩余空间放不下，则触发Minor GC(YGC)，YGC会将Eden区和From区清空
    1. 将Eden区和From区内的不再被其他对象所引用的对象进行销毁。
    2. 将Eden区和From区内幸存的对象，移动到To区，并且标识移动次数加1次
    3. 此时Eden区为空，再次判断Eden区是否放得下
        1. 放得下则放Eden区
        2. 放不下则尝试放Old区（一般是超大对象）
            1. 放得下，直接将对象放置到Old区
            2. 放不下，则触发Major GC，回收一次Old区，再进行判断Old区是否放的下
                1. 放得下则直接将对象放置到Old区
                2. 放不下则OOM
4. Survivor 0区和Survivor 1区
    1. 当JVM进程第一次触发YGC。将Eden区内的不再被其他对象所引用的对象进行销毁。
    2. 将Eden区所有幸存对象，尝试移到Survivor 0区（会将Eden区清空）
        1. 当Survivor 0 区空间放得下则放在Survivor 0区，移动次数加1
        2. 幸存对象太大放不下，则直接晋升老年代，放到Old区，移动次数加1
    3. 当Eden区和Survivor 0或1区有数据时触发了YGC。此时JVM将Eden区和Survivor 0区（此时Survivor 0区称为From区）内不再被其他对象所引用的对象进行销毁。(此时的From区要根据实际情况来定，此处只是例子说明)
        1. 尝试将幸存的对象移动到Survivor 1区（此时也叫To区）。
            1. 当From区幸存对象阈值等于设置的值
                1. 则Promotion晋升老年代Old区，移动次数加1
            2. 当From区幸存对象阈值小于设置的值
                1. To区放得下则放到To区，移动次数加1
                2. To区放不下直接放Old区，移动次数加1（此时Old区正常设置参数肯定放的下，因为Young空间一般都比Old空间小）
    4. 之后就是重复3步 
```
### 内存分配策略
```text
对象提升Promotion规则：
    如果对象在Eden出生并经过第一次MinorGC后仍存活，并且能倍Survivor容纳的化，将被移动到Survivor空间，并将对象年龄设为1
    对象在Survivor区中每熬过一次MinorGC年龄就增加1岁，当它的年龄增加到一定程度(默认15岁，每个JVM每个GC都有所不同)时，就会晋升Promotion到老年代

对象晋升老年代的年龄阈值，可以通过-XX:MaxTenuringThreshold来设置

针对不同年龄段的对象分配原则：
    1.优先分配到Eden
    2.大对象直接分配到老年代
        1.尽量避免程序中出现过多的大对象(连续的内存空间，更高概率触发GC)
    3.长期存活的对象分配到老年代（经过多次YGC仍存活，达到阈值则晋升到老年代）
    4.动态对象年龄判断
        1.如果Survivor区中相同年龄的所有对象大小的综合大于Survivor空间的一半，年龄大于或等于该年龄的对象可以直接进入老年代
            无须等到MaxTenuringThreshold中要求的年龄
    5.空间分配担保
        1. -XX:HandlePromotionFailure
```
### 空间分配担保
```text
在发生MinorGC之前，虚拟机会检查老年代最大可用的连续空间是否大于新生代所有对象的总空间
    1.如果大于则此次MinorGC是安全的
    2.如果小于则虚拟机会查看-XX:HandlePromotionFailure设置值是否运行担保失败
        1.如果-xx:HandlePromotionFailure=true会继续检查老年代最大可用连续空间是否大于历次晋升老年代的对象的平均大小
            1.大于，则尝试进行一次MinorGC，但这次MinorGC是有风险的
            2.小于，则改为进行一次FullGC
        2.如果-xx:HandlePromotionFailure=false则改为进行一次FullGC
在JDK6 Update24之后，HandlePromotionFailure参数不会再影响到JVM的空间分配担保策略，虽然OpenJDK源码中仍定义了HandlePromotionFailure参数，但是在代码中已经不会再使用它
规则变更为
    Old区的连续空间大于新生代对象总大小进行MinorGC
    Old区的连续空间大于历次晋升的平均大小进行MinorGC
    否则进行FullGC
```


# 工具

## 查看JVM进程内存

### jvisualvm

- C:\Program Files\Java\jdk1.8.0_191\bin\jvisualvm.exe
    - 当配置Path java环境变量可以直接输入jvisualvm启动
    - 工具-插件-Virtual GC

### jps & jstat -gc

- jps查看java进程获得进程号
- jstat -gc 进程号

```text
C:\Users\renqiankun>jps
2208 GradleDaemon
1300
12856 Jps
12968 GradleDaemon
808 Main
13772 HeapSpaceInitial

C:\Users\renqiankun>jstat -gc 13772
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
25600.0 25600.0  0.0    0.0   153600.0  6144.0   409600.0     0.0     4480.0 775.8  384.0   76.4       0    0.000   0      0.000    0.000
```

### -XX:+PrintGCDetails

- 运行时加命令行参数-XX:+PrintGCDetails 运行时打印内存详细信息

```text
Heap
 PSYoungGen      total 179200K, used 6144K [0x00000000f3800000, 0x0000000100000000, 0x0000000100000000)
  eden space 153600K, 4% used [0x00000000f3800000,0x00000000f3e00188,0x00000000fce00000)
  from space 25600K, 0% used [0x00000000fe700000,0x00000000fe700000,0x0000000100000000)
  to   space 25600K, 0% used [0x00000000fce00000,0x00000000fce00000,0x00000000fe700000)
 ParOldGen       total 409600K, used 0K [0x00000000da800000, 0x00000000f3800000, 0x00000000f3800000)
  object space 409600K, 0% used [0x00000000da800000,0x00000000da800000,0x00000000f3800000)
 Metaspace       used 2637K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 281K, capacity 386K, committed 512K, reserved 1048576K
```

### jps & jinfo -flag NewRatio / jinfo -flag SurvivorRatio

- 查看NewRatio

```text
C:\>jps
4448
2164 Jps
8324 GradleDaemon
3164 EdenSurvivortest

C:\>jinfo -flag NewRatio 3164
-XX:NewRatio=2

C:\>jinfo -flag SurvivorRatio 3164
-XX:SurvivorRatio=8

```
- 查看UseTLAB

```text
C:\>jps
4448
2164 Jps
8324 GradleDaemon
3164 EdenSurvivortest

C:\>jinfo -flag UseTLAB 3164
-XX:+UseTLAB

```