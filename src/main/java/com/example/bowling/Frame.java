package com.example.bowling;

public class Frame {
    private int firstRoll;
    private int secondRoll;
    private int frameScore;

    public int getFirstRoll() {
        return firstRoll;
    }

    public Frame setFirstRoll(int firstRoll) {
        this.firstRoll = firstRoll;
        return this;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public Frame setSecondRoll(int secondRoll) {
        this.secondRoll = secondRoll;
        return this;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public Frame setFrameScore(int frameScore) {
        this.frameScore = frameScore;
        return this;
    }
}
