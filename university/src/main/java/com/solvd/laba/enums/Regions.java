package com.solvd.laba.enums;

import com.solvd.laba.exeptions.NoValidEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Regions {

    SOUTH_AMERICA(434254119, -5, -2),
    NORTH_AMERICA(24709000, -10, 0),
    EUROPE(10180000, -1, 5),
    AFRICA(30370000, -1, 4),
    OCEANIA(8600000, 8, 10),
    ANTARCTICA(2500, -6, 12),
    ASIA(44579000, 2, 12);
    private static final Logger LOGGER = LogManager.getLogger();
    private final int LEFT_OFFSET;
    private final int RIGHT_OFFSET;
    private final int area;

    Regions(int area, int leftOffset, int rightOffset) {
        if (area < 0) {
            area = 1;
        }
        this.area = area;
        this.LEFT_OFFSET = leftOffset;
        this.RIGHT_OFFSET = rightOffset;
    }

    public int getLeftOffset() {
        return LEFT_OFFSET;
    }

    public int getRightOffset() {
        return RIGHT_OFFSET;
    }

    public int getArea() {
        return area;
    }

    public void developmentIncentiveScholarship(Regions region) {
        switch (region) {
            case SOUTH_AMERICA, AFRICA, OCEANIA, ANTARCTICA:
                LOGGER.info("True");
                break;

            case NORTH_AMERICA, EUROPE, ASIA:
                LOGGER.info("False");
                break;

            default:
                LOGGER.error(new NoValidEnum("Enum doesn't exist").toString());
                break;
        }
    }


}
