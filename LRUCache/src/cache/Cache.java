package cache;

public interface Cache <K, V>{

    public boolean insert(K key, V value) throws InterruptedException;

    public V get(K key) throws InterruptedException;

}
