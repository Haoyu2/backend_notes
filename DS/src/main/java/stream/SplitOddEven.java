package stream;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;

public class SplitOddEven {
    /**
     * split a list into odd index items and even index items
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> split(List<T> list) {
        return list.stream()
                .collect(
                        () -> {
                            List<List<T>> res = new ArrayList<>();
                            res.add(new ArrayList<>());
                            res.add(new ArrayList<>());
                            return res;
                        },
                        (res, x) -> {
                            if ((list.indexOf(x) & 1) == 1) res.get(0).add(x);
                            else res.get(1).add(x);
                        },
                        (l1, l2) -> {
                            l2.add(l1.get(0));
                            l2.add(l1.get(1));
                        }
                );
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0,1,2,3,4,5,6);
        List<Character> list1 = Arrays.asList('A', 'B', 'C', 'D');
        System.out.println(split(list));
        System.out.println(split(list1));
    }
}
