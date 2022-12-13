package com.solvd.laba.cost;

public class FixedCost implements ICalculateCost {
    private final int fixed;

    public FixedCost(int fixed) {
        this.fixed = fixed;
    }

    @Override
    public int getCost() {
        return this.fixed;
    }
}
