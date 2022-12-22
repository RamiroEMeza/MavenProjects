package com.solvd.laba.helpers;

import com.solvd.laba.cost.FixedCost;
import com.solvd.laba.enums.CollegesNames;
import com.solvd.laba.enums.Regions;
import com.solvd.laba.enums.SpecialitiesNames;
import com.solvd.laba.enums.SubjectsNames;
import com.solvd.laba.exeptions.InvalidIDException;
import com.solvd.laba.exeptions.NoCollegesException;
import com.solvd.laba.exeptions.NoSpecialtiesFoundException;
import com.solvd.laba.members.Student;
import com.solvd.laba.members.Teacher;
import com.solvd.laba.administrative.sections.College;
import com.solvd.laba.administrative.sections.Speciality;
import com.solvd.laba.administrative.sections.Subject;
import com.solvd.laba.administrative.sections.University;
import com.solvd.laba.quizes.Quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public final class UniversityCreator {
    public final int MIN_BIG_COST = 100;
    public final int MAX_BIG_COST = 2000;
    public final int MIN_SMALL_COST = 50;
    public final int MAX_SMALL_COST = 210;

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public University create(String name, ArrayList<Teacher> teachers, Student student)
            throws NoCollegesException, NoSpecialtiesFoundException, InvalidIDException {
        //Create a university
        University university = new University(name, new FixedCost(UniversityCreator.getRandomInt(MIN_BIG_COST, MAX_BIG_COST)));
        
        Arrays.stream(CollegesNames.values()).forEach(cn -> university.addCollege(new College(("College of " + cn), new FixedCost(
                UniversityCreator.getRandomInt(MIN_BIG_COST, MAX_BIG_COST)), (cn.ordinal()))));

        //add specialities to the colleges
        for (SpecialitiesNames sn : SpecialitiesNames.values()) {
            try {
                university.addSpeciality(UniversityCreator.getRandomInt(0, (university.getColleges().size() - 1)),
                        new Speciality(sn.toString(), sn.ordinal() + 1,
                                new FixedCost(UniversityCreator.getRandomInt(MIN_BIG_COST, MAX_BIG_COST))));
            } catch (NoCollegesException nCE) {
                throw new NoCollegesException("No Colleges in " + name);
            }
        }

        //add subjects to the specialities
        for (SubjectsNames sn : SubjectsNames.values()) {
            try {
                Subject s = new Subject(sn.toString(), 40,
                        teachers.get(UniversityCreator.getRandomInt(0, (teachers.size() - 1))),
                        new Quiz(4, 6, 0.4),
                        new FixedCost(UniversityCreator.getRandomInt(MIN_SMALL_COST, MAX_SMALL_COST)));

                s.addStudent(student);
                student.addHistoricEnrolledSubjects(s);

                university.addSubjectToSpeciality(UniversityCreator.getRandomInt(1, university.getSpecialities().size()),
                        s);
            } catch (NoSpecialtiesFoundException | InvalidIDException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        return university;
    }

}
