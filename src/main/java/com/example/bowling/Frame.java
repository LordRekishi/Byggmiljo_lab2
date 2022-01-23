package com.example.bowling;

public class Frame {
    private int firstRoll;
    private int secondRoll;
    private int extraRoll;
    private boolean isStrike;
    private boolean isSpare;
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

    public int getExtraRoll() {
        return extraRoll;
    }

    public Frame setExtraRoll(int extraRoll) {
        this.extraRoll = extraRoll;
        return this;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public Frame setStrike(boolean strike) {
        isStrike = strike;
        return this;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public Frame setSpare(boolean spare) {
        isSpare = spare;
        return this;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public Frame addFrameScore(int frameScore) {
        this.frameScore += frameScore;
        return this;
    }
}
