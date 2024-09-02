package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {

        }
        for (int j = 0; j < 1000; j++) {
            Thread thread = new Thread(() -> {
                String s = generateRoute("RLRFR", 100);
                int count = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == 'R') {
                        count++;
                    }
                }
                synchronized (sizeToFreq) {
                    sizeToFreq.merge(count, 1, Integer::sum);

                }
            });
            thread.start();
        }

        System.out.println("Самое частое количество повторений " + maxKey(sizeToFreq) + " (встретилось " + maxVal(sizeToFreq) + " раз)");
        System.out.println("Другие размеры:");
        for (Integer key : sizeToFreq.keySet()) {
            Thread thread1 = new Thread(() -> {
                Integer value = sizeToFreq.get(key);
                System.out.println("- " + key + " (" + value + " раз)");
            });
            thread1.start();

        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static <K, V extends Comparable<V>> V maxVal(Map<K, V> map) {
        Map.Entry<K, V> maxEntry = Collections.max(map.entrySet(), (Map.Entry<K, V> e1, Map.Entry<K, V> e2) -> e1.getValue().compareTo(e2.getValue()));
        return maxEntry.getValue();
    }

    public static <K, V extends Comparable<V>> K maxKey(Map<K, V> map) {
        Map.Entry<K, V> maxEntry = Collections.max(map.entrySet(), (Map.Entry<K, V> e1, Map.Entry<K, V> e2) -> e1.getValue().compareTo(e2.getValue()));
        return maxEntry.getKey();
    }

}

