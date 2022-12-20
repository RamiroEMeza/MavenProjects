package com.solvd.laba.members;

import com.solvd.laba.enums.Regions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Member {
    private static final Logger LOGGER = LogManager.getLogger();
    private String name;
    private int idNumber;

    private Regions region;

    public Member(String name, int idNumber, Regions region) {
        this.name = name;
        this.idNumber = idNumber;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public abstract String getInfo();

    public void isScolarshipped() {
        LOGGER.info(this.name + " has scholarship?");
        this.region.developmentIncentiveScholarship(this.region);
    }

    public Regions getRegion() {
        return region;
    }
}
