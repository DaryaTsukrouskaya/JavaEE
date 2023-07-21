package by.teachmeskills.strategy;

import java.util.logging.Logger;

public class VideoPlaying implements ComputerStrategy {
    private static final Logger LOGGER = Logger.getLogger(VideoPlaying.class.getName());

    @Override
    public void execute() {
     LOGGER.info("video playing");
    }
}
