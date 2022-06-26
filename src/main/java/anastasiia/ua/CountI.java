package anastasiia.ua;

import java.io.File;
import java.util.Map;

public interface CountI<K, V> {
    Map<K, V> count(File file);
}