import java.util.LinkedList;
import java.util.Scanner;

abstract class StorageManager {
    
    LinkedList<Space> spaces;  //可用内存空间

    public void testRequestForSpace(int processId, int threadId, int size) {
        Space space = requestForSpaceProcess(processId, threadId, size);
        requestForSpacePostProcess(space);
    }

    public void testReleaseSpace(int processId, int threadId) {
        Space space = releaseSpaceProcess(processId, threadId);
        releaseSpacePostProcess(space);
    }

    public void requestForSpace() {
        requestForSpacePreProcess();
    }


    /**
     * 申请内存空间回调函数,预处理输入数据
     */
    private void requestForSpacePreProcess() {
        Scanner sc = Solution.sc;
        System.out.println("请输入进程号、线程号、申请内存大小：(以空格分隔)");
        int processId = sc.nextInt();
        int threadId = sc.nextInt();
        int size = sc.nextInt();
        Space space = requestForSpaceProcess(processId, threadId, size);
        requestForSpacePostProcess(space);
    }

    /**
     * 申请内存空间处理函数,由子类实现
     * @param processId
     * @param threadId
     * @param size
     * @return
     */
    protected abstract Space requestForSpaceProcess(int processId, int threadId, int size);  //申请内存空间的处理函数

    /**
     *  申请内存空间回调函数,根据分配是否成功进行后续处理
     */
    private void requestForSpacePostProcess(Space space) {
        boolean success = space != null;
        if(success) {   //如果分配成功,提示本次分配的内存空间的起始地址和结束地址和大小
            System.out.println("申请成功！本次分配的内存空间的起始地址和结束地址和大小为：");
            System.out.println("Start=" + space.start + "\nEnd=" + space.end + "\nSize= " + space.getSize());
        } else {
            System.out.println("申请失败！");
        }
        System.out.println("------------------");
    }

    /**
     * 申请释放内存空间
     */
    public void releaseSpace() {
        releaseSpacePreProcess();
    }

    /**
     * 释放内存空间预处理,获取程序输入
     */
    private void releaseSpacePreProcess() {
        Scanner sc = Solution.sc;
        System.out.println("请输入进程号、线程号：(以空格分隔)");
        int processId = sc.nextInt();
        int threadId = sc.nextInt();
        Space space = releaseSpaceProcess(processId, threadId);
        releaseSpacePostProcess(space);
    }

    /**
     * 释放内存空间处理函数,由子类实现
     * @param processId 进程号
     * @param threadId  线程号
     */
    protected abstract Space releaseSpaceProcess(int processId, int threadId);
    /**
     * 释放内存空间后续处理
     */
    private void releaseSpacePostProcess(Space space) {
        System.out.println("释放成功！");
        System.out.println("释放并尝试重新组合后的内存空间的起始地址和结束地址和大小为：");
        System.out.println("Start=" + space.start + "\nEnd=" + space.end + "\nSize= " + space.getSize());
        System.out.println("------------------");
    }

    /**
     * 显示可用内存空间
     */
    protected void showUseable() {
        System.out.println("------------------");
        System.out.println("可用内存空间：");
        for(Space space : spaces) {
            if(!space.isOccpied)
            System.out.println(space.toString());
        }
        System.out.println("------------------");
    }

    protected void showSpaces() {
        System.out.println("------------------");
        System.out.println("内存空间：");
        for(Space space : spaces) {
            System.out.println(space.toString());
        }
        System.out.println("------------------");
    }

    protected void showOccupied() {
        System.out.println("------------------");
        System.out.println("已占用内存空间：");
        for(Space space : spaces) {
            if(space.isOccpied)
            System.out.println(space.toString());
        }
        System.out.println("------------------");
    }

    //显示内存空间表格
    protected void showSpaceTable() {
        String lineLabel = "+----------+----------+----------+----------+----------+----------+";
        System.out.println("+-------------------内存空间表格----------------------------------+");
        System.out.println(lineLabel);
        System.out.printf("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|\n", "Start", "End", "Size", "Occupied", "ProcessId", "ThreadId");
        System.out.println(lineLabel);
        for (Space space : spaces) {
            System.out.printf("|%-10d|%-10d|%-10d|%-10s|%-10d|%-10d|\n", space.start, space.end, space.getSize(), space.isOccpied, space.processId, space.threadId);
            System.out.println(lineLabel);
        }
        System.out.println();
    }
    
}