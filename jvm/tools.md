# 常用调优工具
## JDK命令
- jps
- javap
    - javap -v -p MetaspaceOOMTest.class > test.txt 将内容写入到当前目录下的test.txt文件内 -p表示输出private权限修饰符的属性
- jstat
- jinfo
- jmap

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