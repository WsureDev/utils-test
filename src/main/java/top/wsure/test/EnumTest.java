package top.wsure.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.wsure.common.annotation.EnumGetBy;
import top.wsure.common.utils.EnumUtils;

import java.util.Arrays;
import java.util.Set;

public class EnumTest {

    public static void main(String[] args) {
        String desc = EnumUtils.get(TestEnum.class, e -> e.getDesc().equals("A1"), TestEnum::getDesc);
        System.out.println(desc);

        TestEnum t1 = EnumUtils.eq(TestEnum.class,TestEnum::getDesc,"A1");
        TestEnum t2 = EnumUtils.eq(TestEnum.class,TestEnum::getVal,1);
        TestEnum t3 = EnumUtils.rangeOf(TestEnum.class,TestEnum::getVal,3,5);
        TestEnum t5 = EnumUtils.range(TestEnum.class,TestEnum::getVal,TestEnum::getMax,5);
        Set<TestEnum> t4 = EnumUtils.rangeOfSet(TestEnum.class,TestEnum::getVal,1,2);

        System.out.println("t1:"+t1+" t2:"+t2+" t3:"+t3+" t4:"+ Arrays.toString(t4.toArray()));

    }
}

@Getter
@AllArgsConstructor
enum TestEnum {
    T1(1,3,"A1"),
    T2(4,7,"A2"),
    ;

    private final Integer val;

    private final Integer max;
    @EnumGetBy
    private final String desc;

    public TestEnum getByDesc(String str){
        return EnumUtils.query(TestEnum.class,e-> e.getDesc().equals(str));
    }

}