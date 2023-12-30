import java.util.*;


/**
 * 最坏适应内存分配算法管理类
 */
class WorstStorageManager extends StorageManager {

    // 用于存储空闲空间的优先队列
    TreeSet<Space> freeSet = new TreeSet<>(new Comparator<Space>() {
        @Override
        public int compare(Space o1, Space o2) {
            return o2.getSize() - o1.getSize();
        }
    });

    WorstStorageManager(Space space) {
        spaces = new LinkedList<>();
        spaces.add(space);
        for(Space temp : spaces) {
            freeSet.add(temp);
        }
    }

    @Override
    protected Space requestForSpaceProcess(
            int processId, int threadId, int size
    ) {

        for(Space space : freeSet) {
            // 找到大小满足的最大的空闲空间
            if(space.getSize() >= size) {
                // 将空闲空间分割成两部分
                freeSet.remove(space);
                int idx = spaces.indexOf(space);
                Space newSpace = space.occupy(processId, threadId, size);
                if(newSpace.getSize() > 0) {
                    spaces.add(idx+1, newSpace);
                    freeSet.add(newSpace);
                }
                return space;
            }
        }
        throw new RuntimeException("no enough space");
    }

    @Override
    protected Space releaseSpaceProcess(int processId, int threadId) {
        int length = spaces.size();
        for (int i = 0; i < length; i++) {
            Space space = spaces.get(i);
            if (space.processId == processId && space.threadId == threadId) {
                if (!space.isOccpied) {
                    throw new RuntimeException("space is not occupied");
                }
                space.isOccpied = false;
                space.processId = -1;
                space.threadId = -1;
                if (i > 0) {
                    Space preSpace = spaces.get(i - 1);
                    if (!preSpace.isOccpied) {
                        spaces.remove(i - 1);
                        space.start = preSpace.start;
                    }
                }
                if (i < length - 1) {
                    Space nextSpace = spaces.get(i + 1);
                    if (!nextSpace.isOccpied) {
                        spaces.remove(i + 1);
                        space.end = nextSpace.end;
                    }
                }
                freeSet.add(space);
                return space;
            }
        }
        throw new RuntimeException("space not found");
    }
}
