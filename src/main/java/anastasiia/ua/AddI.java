package anastasiia.ua;

import java.util.Map;

public interface AddI<K, V> {
    Map<K, V> add(Map<K, V> num1, Map<K, V> num2);
}