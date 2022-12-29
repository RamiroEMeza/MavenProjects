package com.solvd.laba.enums;

public enum SubjectsNames {
    ENGLISH("English", 40), ENGLISH_I("English I", 40),
    ENGLISH_II("English II", 40), REGIONAL_LANGUAGE("Regional language", 55),
    MATHS("Maths", 60), SCIENCE("Science", 60),
    SOCIAL_SCIENCES("Social sciences", 35), PHYSICAL_EDUCATION("Physical education", 40),
    COMPUTER_BASICS("Computer basics", 50), ARTS("Arts", 15),
    HISTORY("History", 40), HISTORY_I("History I", 40),
    HISTORY_II("History III", 40), LINGUISTICS("Linguistics", 40),
    LITERATURE("Literature", 40), PERFORMING("Performing", 15),
    PHILOSOPHY("Philosophy", 40), RELIGION("Religion", 35),
    VISUALS("Visuals", 35), ANTHROPOLOGY("Anthropology", 40),
    ARCHAEOLOGY("Archaeology", 35), CULTURE("Culture", 15),
    ECONOMICS("Economics", 40), GEOGRAPHY("Geography", 40),
    POLITICS("Politics", 40), PSYCHOLOGY("Psychology", 40),
    SOCIOLOGY("Sociology", 40), CHEMISTRY("Chemistry", 50),
    LIFE("Life", 35), PHYSICS("Physics", 45),
    SPACE("Space", 35), LOGIC("Logic", 40),
    MATHEMATICS_II("Mathematics III", 50),
    STATISTICS("Statistics", 40), STATISTICS_II("Statistics II", 4),
    SYSTEMS("Systems", 40);

    private final String printableName;
    private final int hours;

    SubjectsNames(String printableName, int hours) {
        this.printableName = printableName;
        this.hours = hours;
    }

    public String getPrintableName() {
        return printableName;
    }

    public int getHours() {
        return hours;
    }
}
