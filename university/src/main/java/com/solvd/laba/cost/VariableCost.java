package com.solvd.laba.cost;

import java.util.concurrent.ThreadLocalRandom;

public class VariableCost implements ICalculateCost {

    // 0 <= maxVariation <= 1
    private final double maxVariation;
    private final int maxCost;
 
    public VariableCost(double maxVariation, int maxCost) {
        this.maxVariation = setMaxVariation(maxVariation);
        this.maxCost = setMaxCost(maxCost);
    }

    private double setMaxVariation(double maxVariation) {
        if (maxVariation < 0) {
            maxVariation *= (-1);
        }
        while (maxVariation > 1) {
            maxVariation *= 0.1;
        }
        return maxVariation;
    }

    private int setMaxCost(int maxCost) {
        if (maxCost < 0) {
            maxCost *= (-1);
        }
        return maxCost;
    }

    @Override
    public int getCost() {
        return (int) (this.maxCost - (this.maxCost * ThreadLocalRandom.current().nextDouble() * this.maxVariation));
    }
}
