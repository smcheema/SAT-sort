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
                new int[] {96, 77, 91, 86, 53, 55, 87, 22, 37, 84, 6, 21, 50, 38, 26, 78, 83, 61, 11, 10},
                new int[] {66071, 12390, 65946, 24633, 13572, 38216, 86220, 34318, 92973, 8494, 38175, 63567, 72228,
                           14732, 71343, 38612, 82922, 63577, 52795, 7793, 52815, 66010, 93330, 46622, 22807, 99713,
                           9856, 53897, 64735, 64159, 85540, 4888, 812, 26421, 21462, 91480, 53143, 6959, 6054, 38432,
                           98699, 73537, 59300, 1151, 29983, 73543, 40789, 50563, 605, 97824}
        );
    }
}
