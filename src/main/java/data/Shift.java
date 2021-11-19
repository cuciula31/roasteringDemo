package data;

public class Shift {

    private int worker;
    private int day;
    private boolean isWorking;
    private int shift;

    public int getDay() {
        return day;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
}
