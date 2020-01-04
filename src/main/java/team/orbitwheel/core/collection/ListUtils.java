package team.orbitwheel.core.collection;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合工具类
 * @author shiyajian
 * create: 2020-01-04
 */
public final class ListUtils {

    private ListUtils() { /* no instance */ }

    /**
     * 将 List 转成 Map 使用
     */
    public static <T, K, V> Map<K, V> toMap(List<T> list, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>(0);
        }
        return list.stream().collect(Collectors.toMap(keyFunc, valueFunc));
    }
}
