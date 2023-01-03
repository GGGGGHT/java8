import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Allocator {
    // 总大小
    int totalSize;
    // 空闲大小
    int freeSize;
    int allocSize;
    // 表示mid及其对应的大小
    Map<Integer, AllocObject> allocBuf;
    // 空闲列表 记录起始的位置及大小
    Map<Integer, Integer> freeBuf;

    public Allocator(int n) {
        totalSize = n;
        freeSize = n;
        allocBuf = new HashMap<>();
        freeBuf = new TreeMap<>();
        freeBuf.put(0, n);
    }


    public int allocate(int size, int mID) {
        if (size > freeSize) {
            return -1;
        }

        if (allocBuf.containsKey(mID)) {
            var allocObj = allocBuf.get(mID);
            int startPosition = getNextAllocPosition(size);
            if(startPosition == -1) return -1;
            allocObj.alloc(startPosition, size);
            allocSize += size;
            freeSize -= size;

            return startPosition;
        } else {
            int position = getNextAllocPosition(size);
            if (position == -1) {
                return -1;
            }
            AllocObject object = new AllocObject();
            object.alloc(position, size);
            allocSize += size;
            allocBuf.put(mID, object);
            freeSize -= size;

            return position;
        }
    }

    public int getNextAllocPosition(int allocSize) {
        for (Map.Entry<Integer, Integer> entry : freeBuf.entrySet()) {
            if (entry.getValue() > allocSize) {
                Integer returnRes = entry.getKey();
                var size = freeBuf.remove(entry.getKey());
                freeBuf.put(returnRes + allocSize, size - allocSize);
                return returnRes;
            } else if (entry.getValue() == allocSize) {
                freeBuf.remove(entry.getKey());
                return entry.getKey();
            }
        }

        return -1;
    }

    public int free(int mID) {
        if (allocBuf.containsKey(mID)) {
            var res = allocBuf.remove(mID);
            freeSize += res.totalAllocSize;
            allocSize -= res.totalAllocSize;

            freeBuf.putAll(res.startPositionAndSize);
            List<Map.Entry> entries = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : freeBuf.entrySet()) {
                entries.add(entry);
            }
            for (int i = entries.size() - 2; i >= 0; i--) {
                Map.Entry<Integer, Integer> cur = entries.get(i);
                Map.Entry<Integer, Integer> next = entries.get(i + 1);
                if (cur.getKey() + cur.getValue() - 1 == next.getKey() - 1) {
                    Integer remove = freeBuf.remove(next.getKey());
                    Integer integer = freeBuf.get(cur.getKey());
                    freeBuf.put(cur.getKey(), remove + integer);
                }
            }
            return res.totalAllocSize;
        } else {
            return 0;
        }
    }
}

class AllocObject {
    public Map<Integer, Integer> startPositionAndSize;
    // 总共分配的次数
    public int totalAllocCount;
    public int totalAllocSize;

    public AllocObject() {
    }

    public void alloc(int startPos, int size) {
        if (startPositionAndSize == null) {
            startPositionAndSize = new HashMap<>();
        }

        totalAllocCount++;
        startPositionAndSize.put(startPos, size);
        totalAllocSize += size;
    }
}
