package algorithm;

import com.google.ortools.Loader;
import com.google.ortools.sat.*;
import data.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Roster {


    public Roster() {
        Loader.loadNativeLibraries();
    }

    private final CpModel model = new CpModel();
//    CpSolverSolutionCallback cpSolverSolutionCallback;

    IntVar[][][] plan;
    List<Shift> ssh = new ArrayList();

    private void calculate(int employees, int wDays, int shifts) {

        plan = new IntVar[employees][wDays][shifts];

        int[] allEmployees = IntStream.range(0, employees).toArray();
        int[] allWDays = IntStream.range(0, wDays).toArray();
        int[] allShifts = IntStream.range(0, shifts).toArray();

        for (int e : allEmployees) {
            for (int w : allWDays) {
                for (int s : allShifts) {
                    plan[e][w][s] = model.newBoolVar("Angajatul " + e + " ziua " + w + " tura " + s);
                }
            }
        }

        for (int w : allWDays) {
            for (int s : allShifts) {
                IntVar[] x = new IntVar[employees];
                for (int e : allEmployees) {
                    x[e] = plan[e][w][s];
                }
                model.addEquality(LinearExpr.sum(x), 1);
            }
        }

        for (int e : allEmployees) {
            for (int w : allWDays) {
                IntVar[] x = new IntVar[shifts];
                for (int s : allShifts) {
                    x[s] = plan[e][w][s];
                }
                model.addLessOrEqual(LinearExpr.sum(x), 1);

            }
        }
        int minShift = (shifts * wDays) / employees;
        int maxShift;

        if ((shifts * wDays) % employees == 0) {
            maxShift = minShift;
        } else {
            maxShift = minShift + 1;
        }
        for (int e : allEmployees) {
            IntVar[] shiftWorked = new IntVar[wDays * shifts];
            for (int w : allWDays) {
                for (int s : allShifts) {
                    shiftWorked[w * shifts + s] = plan[e][w][s];
                }
            }
            model.addLinearConstraint(LinearExpr.sum(shiftWorked), minShift, maxShift);
        }

        CpSolver solver = new CpSolver();
        solver.getParameters().setLinearizationLevel(0);
        solver.getParameters().setEnumerateAllSolutions(true);

        final int solutionLimit = 5;

        VarArraySolutionPrinterWithLimit cb =
                new VarArraySolutionPrinterWithLimit(allEmployees, allWDays, allShifts, plan, solutionLimit);

        CpSolverStatus status = solver.solve(model, cb);
        ssh = cb.getShiftList();
        System.out.println("Status: " + status);
        System.out.println(cb.getSolutionCount() + " solutions found");


    }

    public void testCalculate(int employees, int wDays, int shifts) {
        calculate(employees, wDays, shifts);
    }


    public List<Shift> getSsh(int employees, int wDays, int shifts) {
        calculate(employees,wDays,shifts);
        return ssh;
    }
}
