
/**
 * @author : Last_Maggot
 * @studentId: 20211060253
 * @date : 2023/12/30
 * @description : 内存管理实验综合题
 */

import java.util.*;

public class Solution {

    public static Scanner sc = new Scanner(System.in);
    private static FirstStorageManager firstStorageManager;
    private static BestStorageManager bestStorageManager;
    private static WorstStorageManager worstStorageManager;

    public static void main(String[] args) {
        init();
        while (true) {
            showMenu();
        }
    }

    public static void init() {
        System.out.println("请输入内存空间的起始地址和结束地址：(以空格分隔)");
        int begin = sc.nextInt();
        int end = sc.nextInt();
        Space space = new Space(begin, end);
        Space space1 = space.copy();
        Space space2 = space.copy();
        Space space3 = space.copy();
        firstStorageManager = new FirstStorageManager(space1);
        bestStorageManager = new BestStorageManager(space2);
        worstStorageManager = new WorstStorageManager(space3);

    }

    public static void showMenu() {
        System.out.println("----------------");
        System.out.println("Function Menu");
        System.out.println("1.申请内存空间");
        System.out.println("2.释放内存空间");
        System.out.println("3.显示内存使用情况");
        System.out.println("4.退出");
        System.out.println("请选择操作：");
        System.out.println("----------------");
        int code = sc.nextInt();
        try {
            switch (code) {
                case 1:
                    System.out.println("请输入进程号、线程号、申请内存大小：(以空格分隔)");
                    int processId = sc.nextInt();
                    int threadId = sc.nextInt();
                    int size = sc.nextInt();
                    System.out.println("--------------------最先适应内存管理算法--------------------");
                    firstStorageManager.testRequestForSpace(processId, threadId, size);
                    System.out.println(
                            "--------------------最优适应内存管理算法--------------------");
                    bestStorageManager.testRequestForSpace(processId, threadId, size);
                    System.out.println(
                            "--------------------最差适应内存管理算法--------------------");
                    worstStorageManager.testRequestForSpace(processId, threadId, size);
                    break;
                case 2:
                    System.out.println("请输入进程号、线程号：(以空格分隔)");
                    processId = sc.nextInt();
                    threadId = sc.nextInt();
                    System.out.println("--------------------最先适应内存管理算法--------------------");
                    firstStorageManager.testReleaseSpace(processId, threadId);
                    System.out.println(
                            "--------------------最优适应内存管理算法--------------------");
                    bestStorageManager.testReleaseSpace(processId, threadId);
                    System.out.println(
                            "--------------------最差适应内存管理算法--------------------");
                    worstStorageManager.testReleaseSpace(processId, threadId);
                    break;
                case 3:

                    System.out.println("--------------------最先适应内存管理算法--------------------");
                    firstStorageManager.showSpaceTable();
                    System.out.println(
                            "--------------------最优适应内存管理算法--------------------");
                    bestStorageManager.showSpaceTable();
                    System.out.println(
                            "--------------------最差适应内存管理算法--------------------");
                    worstStorageManager.showSpaceTable();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    throw new Exception("输入错误，请重新输入！");
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
            // 清空输入缓冲
            sc.nextLine();
            showMenu();
        }
    }
}
