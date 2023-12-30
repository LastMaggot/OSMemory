
import java.util.LinkedList;

/**
 * 首次适应内存分配算法管理类
 */
class FirstStorageManager extends StorageManager {

    FirstStorageManager(Space space) {
        spaces = new LinkedList<>();
        spaces.add(space);
    }

    @Override
    protected Space requestForSpaceProcess(
            int processId, int threadId, int size
    ) {
        int length = spaces.size();
        for(int i = 0; i < length; i++) {
            Space space = spaces.get(i);
            if(!space.isOccpied && space.getSize() >= size) {
                Space newSpace = space.occupy(processId, threadId, size);
                if(newSpace.getSize() > 0) {
                    spaces.add(i+1, newSpace);
                }
                return space;
            }
        }
        return null;
    }

    @Override
    protected Space releaseSpaceProcess(int processId, int threadId) {
        int length = spaces.size();
        for(int i = 0; i < length ; i++) {
            Space space = spaces.get(i);
            if(space.processId == processId && space.threadId == threadId) {
                if(!space.isOccpied) {
                    throw new RuntimeException("space is not occupied");
                }
                space.isOccpied = false;
                space.processId = -1;
                space.threadId = -1;
                boolean removeLast = false;
                boolean removeNext = true;
                if(i < length - 1) {
                    Space nextSpace = spaces.get(i+1);
                    if(!nextSpace.isOccpied) {
                        spaces.remove(i+1);
                        space.end = nextSpace.end;
                    }
                }
                if(i > 0) {
                    Space preSpace = spaces.get(i - 1);
                    if (!preSpace.isOccpied) {
                        spaces.remove(i - 1);
                        space.start = preSpace.start;
                    }
                }
                return space;
            }
        }
        throw new RuntimeException("space not found");

    }
}

