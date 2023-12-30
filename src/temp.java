import java.util.TreeSet;

public class temp {

    static BestStorageManager bestStorageManager =
            new BestStorageManager(new Space(0,639));

    public static void main(String[] args) {
        TreeSet<Space> freeSet = bestStorageManager.freeSet;
        bestStorageManager.testRequestForSpace(1001,1,32);
        bestStorageManager.testRequestForSpace(1001,2,64);
        bestStorageManager.testRequestForSpace(1001,3,128);
        bestStorageManager.testReleaseSpace(1001,2);
        bestStorageManager.testRequestForSpace(1002,1,16);
        bestStorageManager.testReleaseSpace(1001,3);
        for(Space space : freeSet) {
            System.out.println(space);
        }
//        bestStorageManager.testRequestForSpace(1002,2,15);
//        bestStorageManager.testReleaseSpace(1002,1);
        bestStorageManager.showSpaceTable();
    }
}
