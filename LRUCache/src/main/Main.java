package main;

import cache.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = new LRUCache<>(3);
        cache.insert("abc", "1");
        cache.insert("def", "2");
        cache.get("abc");
        cache.insert("abc", "3");
        cache.insert("ghi", "4");
        cache.insert("lmn", "5");
    }

}
