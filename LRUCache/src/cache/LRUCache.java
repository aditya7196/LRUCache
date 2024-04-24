package cache;

import java.util.*;
import java.util.concurrent.*;

public class LRUCache <String, Object> implements Cache<String, Object>{

    private BlockingDeque<String> usageQueue;

    private LinkedHashMap<String, Object> cache;

    public LRUCache(int maxCapacity){
        usageQueue = new LinkedBlockingDeque<>(maxCapacity);
        cache = new LinkedHashMap<>(maxCapacity);
    }

    @Override
    public boolean insert(String key, Object value) throws InterruptedException {
        System.out.println("Inserting " + key +  " : " + value);
        if(usageQueue.remainingCapacity()==0){
            System.out.println("Purging the cache");
            String keyToRemove = usageQueue.removeLast();
            cache.remove(keyToRemove);
        }
        if(cache.containsKey(key) && usageQueue.contains(key)){
            cache.put(key, value);
            System.out.println("Inserted " + key +  " : " + value + " with reposition");
            usageQueue.remove(key);
            usageQueue.put(key);
        }
        else {
            cache.put(key, value);
            System.out.println("Inserted " + key +  " : " + value);
            usageQueue.put(key);
        }
        return true;
    }

    @Override
    public Object get(String key) throws InterruptedException {
        if(usageQueue.contains(key)){
            usageQueue.remove(key);
            usageQueue.put(key);
        }
        return cache.get(key);
    }
}
