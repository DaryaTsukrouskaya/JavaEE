package by.teachmeskills.strategy;

public class Computer {
    private ComputerStrategy computerStrategy;

    public Computer(ComputerStrategy computerStrategy) {
        this.computerStrategy = computerStrategy;
    }

    public void setNewTask(ComputerStrategy computerStrategy) {
        this.computerStrategy = computerStrategy;
    }

    public void runTask() {
        this.computerStrategy.execute();
    }
}
