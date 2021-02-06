# 常用调优工具
## JDK命令
- jps
- javap
    - javap -v -p MetaspaceOOMTest.class > test.txt 将内容写入到当前目录下的test.txt文件内 -p表示输出private权限修饰符的属性
- jstat
- jinfo
- jmap
### 示例
#### 查看GC
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

#### -XX:+PrintGCDetails

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

#### jps & jinfo -flag NewRatio / jinfo -flag SurvivorRatio

- 查看NewRatio/SurvivorRatio

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
## Eclipse: Memory Analyzer Tool

## Jconsole

## jvisualvm
- C:\Program Files\Java\jdk1.8.0_191\bin\jvisualvm.exe
    - 当配置Path java环境变量可以直接输入jvisualvm启动
    - 工具-插件-Virtual GC

## Jprofiler

## Java Flight Recorder（JMC下的工具）

## GCViewer

## GC Easy
## jhsdb(JDK9之后添加的工具)