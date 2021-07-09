import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.IntVar;
import com.google.ortools.util.Domain;

import java.util.Arrays;

final public class SATSort {

    protected int[] sortIt(int[] arrayToSort) {
        // load native Java jars, OR-Tools have a set-up process to link the Java abstraction
        // and the underlying C++
        Loader.loadNativeLibraries();
        final CpModel model = new CpModel();
        final long[] domain = Arrays.stream(arrayToSort)
                .mapToLong(i -> i)
                .toArray();
        final IntVar[] slots = new IntVar[arrayToSort.length];
        for (int index = 0; index < arrayToSort.length; index++) {
            // Constrain our sorted slots to contain integers present in the input array.
            slots[index] = model.newIntVarFromDomain(Domain.fromValues(domain), "Slot: " + index);
            // Define the ordering constraint. Surprisingly easy to make this happen using OR-tools, the
            // manual clauses I wrote following the Sudoku guide weren't environment friendly to say the least :P
            if (index > 0) model.addGreaterOrEqual(slots[index], slots[index - 1]);
        }
        // Constrain each slot to hold a different integer. This is really not what we want, but I can't figure out
        // how else to go about this. Biggest shortcoming of this approach is that we can't sort arrays with duplicates
        // using this. Have a look at :
        // shorturl.at/nptJ3
        // Hopefully once I build more comfort with this sort of problem solving I'll see the error in my ways.
        model.addAllDifferent(slots);
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);
        if (status == CpSolverStatus.FEASIBLE || status == CpSolverStatus.OPTIMAL) {
            int[] retVal = new int[arrayToSort.length];
            for (int index = 0; index < arrayToSort.length; index++) {
                // Read SATisfiable values from the solver.
                retVal[index] = (int) solver.value(slots[index]);
            }
            return retVal;
        } else {
            return new int[] {};
        }
    }
}
