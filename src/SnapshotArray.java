package src;

import java.util.HashMap;
import java.util.Map;

class SnapshotArray {

    private Map<Integer, Integer>[] arr;
    private int snapId;

    public SnapshotArray(int length) {
        this.arr = new HashMap[length];
        for (int i = 0; i < length; i++) {
            this.arr[i] = new HashMap<>();
        }
        this.snapId = 0;
    }

    public void set(int index, int val) {
        this.arr[index].put(snapId, val);
    }

    public int snap() {
        return this.snapId++;
    }

    public int get(int index, int snap_id) {
        int val = 0;
        while (val == 0 && snap_id >= 0) {
            if (this.arr[index].containsKey(snap_id)) {
                val = this.arr[index].get(snap_id);
                break;
            }
            snap_id--;
        }
        return val;
    }

}
