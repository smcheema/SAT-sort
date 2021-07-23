import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class SATSortTest {

    private static SATSort SAT_SORT;

    @BeforeAll
    static void setup() {
        SAT_SORT = new SATSort();
    }

    @ParameterizedTest
    @MethodSource("integerArrayStream")
    public void aTest(final int[] tbSorted) {
        SATSort SATSort = new SATSort();
        int[] sortedArr = SATSort.sortIt(tbSorted);
        Arrays.sort(tbSorted);
        Assertions.assertArrayEquals(sortedArr, tbSorted);
    }

    private static Stream<int[]> integerArrayStream() {
        return Stream.of(
                new int[] {0, 1, 2},
                new int[] {1, 2, -1, 18, 4, 3, 10, 12, 99, 10001, 122, 8},
                new int[] {-12, -33, -88, 4, 21, 3091823, 22, 33, -18, 2, 3, 9},
                new int[] {0, 0, 0, 1, 1, 2, 3, 3, 4, 9, 6, 4, 4},
                new int[] {-1, 12, -1, 1, 14, 31, 1, 2, 9, 11, 22, 3},
                new int[] {-1, -2, 0, 1, 2, -3, 2, 3, 2, 1, 3, 1},
                new int[] {66071, 12390, 65946, 24633, 13572, 38216, 86220, 34318, 92973, 8494, 38175, 63567, 72228}
        );
    }
}
