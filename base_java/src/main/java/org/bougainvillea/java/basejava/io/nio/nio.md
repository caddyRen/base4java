# NIO
## Selector
- 当客户端连接时，会通过ServerSocketChannel得到SocketChannel
- selector通过select方法监听，返回事件发生的通道个数
- 将SocketChannel注册到Selector上，SelectionKey register(Selector sel, int ops),selector可以注册多个SocketChannel
- 注册后返回一个SelectionKey会和该Selector关联（set集合）
- 进而得到各个SelectionKey（有事件发生）
- 再通过SelectionKey反向获取SocketChannel,channel()方法
- 通过获取的SocketChannel进行业务处理
## Channel
## Buffer
- 每个Channel对应一个或多个Buffer（分散读取和聚集写入）
- Selector 对应一个线程，一个Selector对应多个Channel，进而一个线程对应多个Channel
- 程序决定切换到具体Channel是由具体事件决定，Event是一个很重要的概念
- Selector会根据不同时间，在各个channel上切换
- Buffer就是一个内存块，底层是一个数组
- 数据的读取写入是用过buffer,通过flip方法切换读取模式和写入模式，BIO中输入流输出流只能单向
- channel是双向的，可以返回底层操作系统的情况，比如linux底层的操作系统通道就是双向的