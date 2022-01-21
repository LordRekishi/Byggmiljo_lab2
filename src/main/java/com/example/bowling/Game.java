package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Frame> frames = new ArrayList<>();
    private final int MAX_PINS = 10;
    private int score;
    private int rollCounter;

    public void roll(int pins) {
        if (pins <= MAX_PINS) {
            executeFrameDependingOnRoll(pins);
        } else {
            System.out.println("Invalid number of Pins, cancelling game...");
        }
    }

    private void executeFrameDependingOnRoll(int pins) {
        int MAX_FRAMES = 10;
        Frame frame;

        if (rollCounter == 0 && frames.size() < MAX_FRAMES) {
            frame = new Frame();
            frame.setFirstRoll(pins);

            checkIfCurrentFrameIsStrike(pins, frame);
            addScoreForStrikesAndSparesInEarlierFrames(frame);

            frame.addFrameScore(pins);
            frames.add(frame);

        } else if (rollCounter == 1 && frames.size() <= MAX_FRAMES) {
            frame = getLastFrame();
            frame.setSecondRoll(pins);
            frame.addFrameScore(pins);

            checkIfSpare(frame);

            rollCounter = 0;

            addScoreForStrikeInEarlierFrame(frame);
            changeRollCounterForExtraRollForStrikeOrSpareInFrameTen(frame);

        } else if (rollCounter == 2 && frames.size() <= MAX_FRAMES) {
            extraRollOnFrameTen(pins);
        }
    }

    private void checkIfCurrentFrameIsStrike(int pins, Frame frame) {
        if (isStrike(pins)) {
            setStrike(frame);
        } else {
            rollCounter++;
        }
    }

    private boolean isStrike(int pins) {
        return pins == MAX_PINS;
    }

    private void setStrike(Frame frame) {
        if (frames.size() == 9) {
            frame.setStrike(true);
            rollCounter++;
        } else {
            frame.setStrike(true);
            rollCounter = 0;
        }
    }

    private void addScoreForStrikesAndSparesInEarlierFrames(Frame frame) {
        if (frames.size() > 0) {
            addScoreInLastFrameForStrikeOrSpare(frame);
        }
        if (frames.size() > 1) {
            addScoreInSecondLastFrameForStrikeIfLastFrameWasAlsoStrike(frame);
        }
    }

    private void addScoreInLastFrameForStrikeOrSpare(Frame frame) {
        if (getLastFrame().isStrike()) {
            getLastFrame().addFrameScore(frame.getFirstRoll());
        }
        if (getLastFrame().isSpare()) {
            getLastFrame().addFrameScore(frame.getFirstRoll());
        }
    }

    private void addScoreInSecondLastFrameForStrikeIfLastFrameWasAlsoStrike(Frame frame) {
        if (getSecondLastFrame().isStrike() && getLastFrame().isStrike()) {
            getSecondLastFrame().addFrameScore(frame.getFirstRoll());
        }
    }

    private void checkIfSpare(Frame frame) {
        if (frame.getFirstRoll() + frame.getSecondRoll() == MAX_PINS) {
            frame.setSpare(true);
        }
    }

    private void addScoreForStrikeInEarlierFrame(Frame frame) {
        if (frames.size() > 1) {
            if (getSecondLastFrame().isStrike()) {
                getSecondLastFrame().addFrameScore(frame.getSecondRoll());
            }
        }
    }

    private void changeRollCounterForExtraRollForStrikeOrSpareInFrameTen(Frame frame) {
        if (frame.isStrike() || frame.isSpare() && frames.size() == 10) {
            rollCounter = 2;
        }
    }

    private void extraRollOnFrameTen(int pins) {
        getLastFrame().setExtraRoll(pins).addFrameScore(pins);
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
