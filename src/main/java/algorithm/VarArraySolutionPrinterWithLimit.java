package algorithm;

import com.google.ortools.sat.CpSolverSolutionCallback;
import com.google.ortools.sat.IntVar;
import data.Shift;

import java.util.ArrayList;
import java.util.List;

public class VarArraySolutionPrinterWithLimit extends CpSolverSolutionCallback {

    public List<Shift> shiftList = new ArrayList<>();

    public VarArraySolutionPrinterWithLimit(
            int[] allNurses, int[] allDays, int[] allShifts, IntVar[][][] shifts, int limit) {
        solutionCount = 0;
        this.allNurses = allNurses;
        this.allDays = allDays;
        this.allShifts = allShifts;
        this.shifts = shifts;
        solutionLimit = limit;
    }

    @Override
    public void onSolutionCallback() {

        System.out.printf("Solution #%d:%n", solutionCount);
        for (int d : allDays) {
            System.out.printf("Ziua %d%n", d);
            for (int n : allNurses) {
                Shift sh = new Shift();
                sh.setWorker(n);
                sh.setDay(d);
                boolean isWorking = false;
                for (int s : allShifts) {
                    if (value(shifts[n][d][s]) == 1L) {
                        isWorking = true;
                        sh.setWorking(true);
                        sh.setShift(s);
                        shiftList.add(sh);
                        System.out.printf("  Angajatul %d lucreaza schimbul %d%n", n, s);
                    }
                }
                if (!isWorking) {
                    sh.setWorking(false);
                    sh.setShift(-1);
                    shiftList.add(sh);
                    System.out.printf("  Angajaatul %d nu lucreaza%n", n);
                }
            }
        }
        solutionCount++;
        if (solutionCount >= solutionLimit) {
            System.out.printf("Cautare oprita dupa %d solutii%n", solutionLimit);
            stopSearch();
        }

    }

    public int getSolutionCount() {
        return solutionCount;
    }

    public List<Shift> getShiftList() {
        return shiftList;
    }

    private int solutionCount;
    private final int[] allNurses;
    private final int[] allDays;
    private final int[] allShifts;
    private final IntVar[][][] shifts;
    private final int solutionLimit;
}


