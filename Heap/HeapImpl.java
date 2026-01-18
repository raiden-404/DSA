package Heap;
import java.util.ArrayList;
import java.util.Collections;

class MaxHeap {
    ArrayList<Integer> heap;

    MaxHeap() {
        heap = new ArrayList<Integer>();
    }

    MaxHeap(int[] nums) {
        // Create MaxHeap using Array
    }

    public int peek() {
        if(heap.size() == 0) return Integer.MIN_VALUE;
        return heap.get(0);
    }

    public void heapify(int index) {
        while(index < heap.size()) {
            int large = index;
            int leftIdx = (2 * index) + 1;
            int rightIdx = (2 * index) + 2;

            if(leftIdx < heap.size() && heap.get(leftIdx) > heap.get(large)) {
                large = leftIdx;
            }

            if(rightIdx < heap.size() && heap.get(rightIdx) > heap.get(large)) {
                large = rightIdx;
            }
            
            if(index == large) break;

            Collections.swap(heap, large, index);
            index = large;

        }
    }

    public void insert(int num) {
        heap.add(num);
        int index = heap.size() - 1;
        while(index >= 0) {
            int parentIdx = (index - 1) / 2;
            if(heap.get(parentIdx) < heap.get(index)) {
                Collections.swap(heap, parentIdx, index);
                index = parentIdx;
            }
        }
    }

    public int pop() {
        if(heap.size() == 0) return Integer.MIN_VALUE;
        int top = heap.get(0);
        int lastIdx = heap.size() - 1;
        Collections.swap(heap, 0, lastIdx);
        heap.remove(lastIdx);
        return top;
    }

    public void print() {
        for(int n : heap) {
            System.out.print(n + " -> ");
        }
    }
}
  
public class HeapImpl {
    public static void main(String[] args) {
        MaxHeap mh = new MaxHeap();
        mh.insert(4);
        mh.insert(24);
        mh.insert(3);
        mh.insert(15);
        mh.insert(22);
        mh.print(); 
    }
}