package com.solvd.laba.university.helpers;

import com.solvd.laba.cost.FixedCost;
import com.solvd.laba.exeptions.InvalidIDException;
import com.solvd.laba.exeptions.NoCollegesException;
import com.solvd.laba.exeptions.NoSpecialtiesFoundException;
import com.solvd.laba.university.administrative.sections.College;
import com.solvd.laba.university.administrative.sections.Speciality;
import com.solvd.laba.university.administrative.sections.Subject;
import com.solvd.laba.university.administrative.sections.University;
import com.solvd.laba.university.members.Student;
import com.solvd.laba.university.members.Teacher;
import com.solvd.laba.university.quizes.Quiz;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class UniversityCreator {
    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public University create(String name, int cost, String[] collegesNames, String[] specialitiesNames,
                             ArrayList<Teacher> teachers, int subjectsQuantity, Student student)
            throws NoCollegesException, NoSpecialtiesFoundException, InvalidIDException {
        //Create a university
        University university = new University(name, new FixedCost(UniversityCreator.getRandomInt(100, 2000)));

        //add colleges to the university
        for (int i = 0; i < collegesNames.length; i++) {
            university.addCollege(new College(("College of " + collegesNames[i]), new FixedCost(
                    UniversityCreator.getRandomInt(100, 2000)), (i + 1)));
        }

        //add specialities to the colleges
        for (int i = 0; i < specialitiesNames.length; i++) {
            try {
                university.addSpeciality(UniversityCreator.getRandomInt(1, (university.getColleges().size() - 1)),
                        new Speciality(specialitiesNames[i], (i + 1),
                                new FixedCost(UniversityCreator.getRandomInt(100, 2000))));
            } catch (NoCollegesException nCE) {
                throw new NoCollegesException("No Colleges in " + name);
            }
        }


        //add subjects to the specialities
        for (int i = 1; i < (subjectsQuantity + 1); i++) {
            try {
                Subject s = new Subject(("Subject-" + i), 40,
                        teachers.get(UniversityCreator.getRandomInt(0, (teachers.size() - 1))),
                        new Quiz(4, 6, 0.7),
                        new FixedCost(UniversityCreator.getRandomInt(50, 210)));
                s.addStudent(student);
                university.addSubjectToSpeciality
                        (UniversityCreator.getRandomInt(1, university.getSpecialities().size()),
                                s);
            } catch (NoSpecialtiesFoundException | InvalidIDException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }

        return university;
    }

}
