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

## heap area 细分 现代垃圾收集器大部分都基于分代收集理论设计
- 新生区--新生代--年轻代
- 养老区--老年区--老年代
- 永久区--永久代
### java7及之前 堆内存 逻辑上 分为三部分

1. Yong Generation Space   新生区 Young/New
    1. Eden区
    2. Survivor区
        - 两个区，但是只有一个存放数据，当jvm计算容量时只会考虑一个，所以jvm计算出的值会少一个survivor区的大小
2. Tenure Generation Space 养老区 Old/Tenure
3. Permanent Space         永久区 Perm

### java8及之后 堆内存 逻辑上 分为三部分

1. Yong Generation Space   新生区 Young/New
    1. Eden区
    2. Survivor区
2. Tenure Generation Space 养老区 Old/Tenure
3. Meta Space              元空间 Meta

# 工具
## 查看JVM进程内存
1. C:\Program Files\Java\jdk1.8.0_191\bin\jvisualvm.exe
    - 当配置Path java环境变量可以直接输入jvisualvm启动
    - 工具-插件-Virtual GC
2. 
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
3. 加命令行参数-XX:+PrintGCDetails 运行时打印内存详细信息
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
# JVM参数
## 设置heap area size
1. -Xms10M等价于-XX:InitialHeapSize 初始heap size 只影响新生区和养老区 默认情况初始内存大小：物理电脑内存大小 /64
2. -Xmx10M等价于-XX:MaxHeapSize     最大heap size 只影响新生区和养老区 默认情况最大内存大小：物理电脑内存带下 /4
3. 通常将-Xms -Xmx设置为相同的值，目的是为了能够在Java垃圾回收机制清理完堆区后不需要重新分割计算堆区的大小从而提高性能
4. 一旦堆区中的内存大小超过-Xmx所指定的最大内存时，将会抛出OutOfMemoryError异常

   