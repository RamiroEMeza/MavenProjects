package com.solvd.laba.exam;

import java.util.ArrayList;

public interface IGiveResults {
    public abstract ArrayList<String> giveResults();

    public void giveBackupResults();
}
