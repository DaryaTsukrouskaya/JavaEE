package by.teachmeskills.strategy;

import java.util.logging.Logger;

public class MusicPlaying implements ComputerStrategy {
    private final static Logger LOGGER = Logger.getLogger(MusicPlaying.class.getName());

    @Override
    public void execute() {
        LOGGER.info("music playing");
    }
}
