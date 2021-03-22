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

        Set<String> keySet = new NullableNode<Map,Map>(v-> (Map) v.get("map"))
                .add(v-> (Map) v.get("map"))
                .add(v-> (Map) v.get("map"))
                .add(v-> (Map) v.get("map"))
                .add(v-> (Map) v.get("map"))
                .add(v-> v.keySet())
                .build(map);
        System.out.println(Arrays.toString(keySet.toArray()));
    }
}

class NullableNode<T,V> {
    Function<? super T,? extends V> mapper;

    public NullableNode(Function<? super T,? extends V> mapper){
        this.mapper = mapper;
    }

    public <R> NullableNode<T,R> add(Function<? super V,? extends R> mapper){
        return new NullableNode<>(this.mapper.andThen(mapper));
    }

    public V build(T t){
        return this.mapper.apply(t);
    }

}
