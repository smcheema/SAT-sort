import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.IntVar;
import com.google.ortools.sat.LinearExpr;


final public class SATSort {

    protected int[] sortIt(int[] arrayToSort) {
        // load native Java jars.
        Loader.loadNativeLibraries();
        final CpModel model = new CpModel();
        // Different approach this time : we model indices.
        // i.e, I tell CP-SAT : each variable in this set ranges :
        // from 0..arr.len, and is unique.
        // Intuitively, for any array[], our indices represent 1-to-1 where an element at the same index ends up
        // in the sorted array. For example, for [1, 5, 4, 1, 8], a feasible (I say feasible since duplicate entries make multiple assignments possible)
        // would be [0, 3, 2, 1, 4]. i.e, arr[0] == 1 ends up at index 0 in the sorted array,
        // arr[1] == 5 ends up at index 3, and so on.
        final IntVar[] indices = new IntVar[arrayToSort.length];
        for (int index = 0; index < arrayToSort.length; index++) {
            // Constrain our sorted slots to contain integers present in the input array.
            indices[index] = model.newIntVar(0,  arrayToSort.length - 1, "Index: " + index);
        }
        model.addAllDifferent(indices);

        // this is really neat and is where the magic happens. One way of thinking about sorted arrays is :
        // I have indices, and elements. Maximize for me the scalar product of my indices and elements.
        // i.e, put my largest elements at my largest indices.
        // Small proof by contradiction : suppose the above is false, and our array is ordered, I can take any two
        // elements on indices indexOne and indexTwo, where indexTwo > indexOne and arr[indexTwo] < arr[indexOne]
        // I can swap them, and obtain a new maximal scalar product. Pretty neat way of thinking about sorting.
        model.maximize(LinearExpr.scalProd(indices, arrayToSort));
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);
        if (status == CpSolverStatus.FEASIBLE || status == CpSolverStatus.OPTIMAL) {
            int[] retVal = new int[arrayToSort.length];
            for (int index = 0; index < arrayToSort.length; index++) {
                // to explain this, consider the above example :
                // arr = [1, 5, 4, 1, 8], solution = [0, 3, 2, 1, 4]
                // so, go over the index arr, and each value tells us where the corresponding element
                // in the unsorted array should end up. So 1 ends up at index 0.
                // 5 ends up at index 3, so on.
                retVal[(int) solver.value(indices[index])] = arrayToSort[index];
            }
            return retVal;
        } else {
            return new int[] {};
        }
    }
}
