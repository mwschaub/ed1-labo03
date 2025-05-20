package ed.lab;
import java.util.PriorityQueue;
import java.util.HashMap;
public class E01TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {

        // Contar la frecuencia de cada número
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Cola mínima para mantener los k elementos más frecuentes
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> frequencyMap.get(a) - frequencyMap.get(b)
        );

        for (int num : frequencyMap.keySet()) {
            minHeap.offer(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Extraer los elementos del heap al arreglo resultado
        int[] result = new int[k];
        int index = 0;

        while (!minHeap.isEmpty()) {
            result[index++] = minHeap.poll();
        }

        return result;
    }
}
