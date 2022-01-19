package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Frame> frames = new ArrayList<>();
    private final int MAX_PINS = 10;
    private final int MAX_FRAMES = 10;
    private int score;
    private int rollCounter;

    public void roll(int pins) {
        Frame frame;

        if (pins <= MAX_PINS) {
            if (rollCounter == 0 && frames.size() < MAX_FRAMES) {
                frame = new Frame();
                frame.setFirstRoll(pins);

                if (pins == MAX_PINS) {
                    if (frames.size() == 9) {
                        frame.setStrike(true);
                        rollCounter++;
                    } else {
                        frame.setStrike(true);
                        rollCounter = 0;
                    }
                } else {
                    rollCounter++;
                }

                if (frames.size() > 0) {
                    if (getLastFrame().isStrike()) {
                        getLastFrame().addFrameScore(frame.getFirstRoll());
                    }
                    if (getLastFrame().isSpare()) {
                        getLastFrame().addFrameScore(frame.getFirstRoll());
                    }
                }
                if (frames.size() > 1) {
                    if (getSecondLastFrame().isStrike() && getLastFrame().isStrike()) {
                        getSecondLastFrame().addFrameScore(frame.getFirstRoll());
                    }
                }

                frame.addFrameScore(pins);
                frames.add(frame);

            } else if (rollCounter == 1 && frames.size() <= MAX_FRAMES) {
                frame = getLastFrame();
                frame.setSecondRoll(pins);

                frame.addFrameScore(pins);

                if (frame.getFirstRoll() + frame.getSecondRoll() == MAX_PINS) {
                    frame.setSpare(true);
                }
                rollCounter = 0;

                if (frames.size() > 1) {
                    if (getSecondLastFrame().isStrike()) {
                        getSecondLastFrame().addFrameScore(frame.getSecondRoll());
                    }
                }

                if (frame.isStrike() || frame.isSpare() && frames.size() == 10) {
                    rollCounter = 2;
                }
            } else if (rollCounter == 2 && frames.size() <= MAX_FRAMES) {
                frame = getLastFrame();
                frame.setExtraRoll(pins);
                frame.addFrameScore(pins);
            }
        }

    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    private Frame getSecondLastFrame() {
        return frames.get(frames.size() - 2);
    }

    public int score() {
        for (Frame frame : frames) {
            score += frame.getFrameScore();
        }
        return score;
    }
}
