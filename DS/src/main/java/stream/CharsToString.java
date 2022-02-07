package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharsToString {
    public static String toString(char[] chars) {
        return new String(chars);
    }

    public static String toString(List<Character> chars) {
        return chars.stream().map(character -> character.toString()).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        char[] chars = new char[]{'A', 'B', 'C', 'D'};
        System.out.println(toString(chars));

        List<Character> characters = Arrays.asList('A', 'B', 'C');
        System.out.println(toString(characters));

    }

}
