package by.teachmeskills.strategy;


import static java.util.logging.Logger.getLogger;

public class Application {
    public static void main(String[] args) {
        Computer computer = new Computer(new MusicPlaying());
        computer.runTask();
        computer.setNewTask(new VideoPlaying());
        computer.runTask();
        computer.setNewTask(() -> getLogger(Application.class.getName()).info("game playing"));
        computer.runTask();
    }

}
