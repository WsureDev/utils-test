package top.wsure.test;

import top.wsure.common.utils.NullableUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class NullableTest {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("map", map);
        map.put("map1", map);
        map.put("map2", map);
        map.put("map3", map);
        map.put("map4", null);

        Function<Map,Map> getMap = m -> NullableUtils.get(m,v-> (Map) v.get("map"));
        Function<Map,Set<String>> getKeys = m -> NullableUtils.get(m, Map::keySet);
        Set<String> set2 = getMap.andThen(getMap)
                .andThen(getMap)
                .andThen(getMap)
                .andThen(getMap)
                .andThen(getKeys)
                .apply(map);
        System.out.println(Arrays.toString(set2.toArray()));

        Set<String> set1 = NullableUtils.build(map)
                .next(m -> (Map<String, Object>) m.get("map"))
                .next(m -> (Map<String, Object>) m.get("map1"))
                .next(m -> (Map<String, Object>) m.get("map2"))
                .next(m -> (Map<String, Object>) m.get("map3"))
                .next(m -> m.keySet())
                .get();
        System.out.println(Arrays.toString(set1.toArray()));

//        NullableUtils.getOrThrow(map,m->m.get("HAHA"),new Exception("result is null"));
        NullableUtils.getOrThrow(map,m->m.get("HAHA"),new RuntimeException("result is null"));
    }
}

