class Space {   //内存空间
    int start;
    int end;
    boolean isOccpied = false;  //是否被占用
    int processId = -1;  //占用该空间的进程号
    int threadId = -1;   //占用该空间的线程号

    /**
     * 获取内存空间大小
     * @return
     */
    public int getSize() {
        return end - start + 1;
    }

    /**
     * 初始化内存空间时使用的构造函数
     * @param start
     * @param end
     */
    Space(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 申请占用内存空间时使用的构造函数
     * @param start
     * @param end
     * @param processId
     * @param threadId
     */
    Space(int start, int end, int processId, int threadId) {
        this.start = start;
        this.end = end;
        this.isOccpied = true;
        this.processId = processId;
        this.threadId = threadId;
    }

    /**
     * 占用当前空间,并且返回被占用后的剩余内存空间
     * @param processId
     * @param threadId
     * @param size
     * @return
     */
    Space occupy(int processId, int threadId, int size) {
        int originEnd = this.end;
        int newEnd = this.start + size - 1;
        this.end = newEnd;
        this.isOccpied = true;
        this.processId = processId;
        this.threadId = threadId;
        return new Space(newEnd+1, originEnd);
    }

    Space copy() {
        Space space = new Space(start, end);
        space.isOccpied = isOccpied;
        space.processId = processId;
        space.threadId = threadId;
        return space;
    }

    @Override
    public String toString() {
        if(isOccpied)
            return "Space{" +
                    "start=" + start +
                    ", end=" + end +
                    ", size=" + getSize() +
                    ", isOccpied=" + isOccpied +
                    ", processId=" + processId +
                    ", threadId=" + threadId +
                    '}';
        return "Space{" +
                "start=" + start +
                ", end=" + end +
                ", size=" + getSize() +
                ", isOccpied=" + isOccpied +
                '}';
    }
}