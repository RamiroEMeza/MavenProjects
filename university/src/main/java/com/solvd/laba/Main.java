package com.solvd.laba;

import com.solvd.laba.administrative.sections.University;
import com.solvd.laba.enums.Regions;
import com.solvd.laba.exeptions.InvalidIDException;
import com.solvd.laba.exeptions.NoCollegesException;
import com.solvd.laba.exeptions.NoSpecialtiesFoundException;
import com.solvd.laba.exeptions.NoUniversityInReferenceException;
import com.solvd.laba.helpers.UniversityCreator;
import com.solvd.laba.members.Student;
import com.solvd.laba.members.Teacher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void isEqualsUniversities(University u1, University u2) throws NoUniversityInReferenceException {
        if (u1 == null || u2 == null) {
            throw new NoUniversityInReferenceException("There's not U in" + u1 + " or " + u2);
        }
        LOGGER.info("\nOhio University is equals to Ohio University? " +
                u1.equals(u2) + "\n");
    }

    public static void createTeachers(ArrayList<Teacher> teachers, int quantity) {
        for (int i = 1; i < (quantity + 1); i++) {
            teachers.add(new Teacher("Teacher " + i, i, i, Regions.EUROPE));
        }
    }

    public static void main(String[] args) throws NoSpecialtiesFoundException {
        final String UNIVERSITY_NAME = "Ohio U";
        final int QUANTITY_OF_TEACHERS = 5;
        ArrayList<Teacher> teachers = new ArrayList<>();
        Main.createTeachers(teachers, QUANTITY_OF_TEACHERS);
        UniversityCreator universityCreator = new UniversityCreator();
        ArrayList<String> response = null;

        Student student = new Student("Lionel Messi", 1, Regions.EUROPE);
        //student.isScolarshipped();

        int userRequest;
        BufferedReader readRequest = new BufferedReader(new InputStreamReader(System.in));
        do {
            LOGGER.info("Start of the program");

            //Create a university
            University ohioU = null;
            try {
                ohioU = universityCreator.create(UNIVERSITY_NAME, teachers, student);
            } catch (NoCollegesException | InvalidIDException nCE) {
                LOGGER.error(nCE.getMessage());
            }

            try {
                Main.isEqualsUniversities(ohioU, ohioU);
            } catch (NoUniversityInReferenceException nCE) {
                LOGGER.error(nCE.getMessage());
            }


            //Print colleges
            try {
                LOGGER.info(ohioU.getName() + " have these colleges:");
                response = ohioU.getColleges();
                LOGGER.info(response.toString());
            } catch (NoCollegesException nCE) {
                LOGGER.error(nCE.getMessage());
            }

            //Print specialities
            try {
                LOGGER.info("\n" + ohioU.getName() + " have these specialities:");
                response = ohioU.getSpecialities();
                for (String word : response) {
                    LOGGER.info("-" + word + "\n");
                }
            } catch (NoSpecialtiesFoundException nSFException) {
                LOGGER.error(nSFException.getMessage());
            }

            do {
                try {//Ask user if he wants info about any speciality
                    LOGGER.info("\nIf you want to know the cost of any of ours specialities, " +
                            "please enter its " + "(id) number. " + "\nOr -1 to search students."
                            + "\nOr -2 to Exam Students."
                            + "Any other character to exit");
                    userRequest = Integer.parseInt(readRequest.readLine());
                } catch (Exception e) {
                    LOGGER.info("User didn't ask about any speciality and enter an non integer data, " +
                            "setting userRequest=0");
                    userRequest = 0;
                }

                if (userRequest > 0 && userRequest <= ohioU.getLastSpecialityId()) {
                    int specialityId = userRequest;
                    String speciality;
                    LOGGER.info("User ask about an speciality");
                    speciality = ohioU.getSpecialityById(specialityId);
                    LOGGER.info("\nHow much does it cost to study " + speciality + " (" + specialityId + ") ?");
                    LOGGER.info("--It will be: " + ohioU.calculateCost(specialityId));

                    try {//Ask user if he wants details about the speciality
                        LOGGER.info("\nIf you want to know the details of " + speciality + ", please enter [1]." +
                                "\nAny other character to keep asking." + "\n[0] to exit");
                        userRequest = Integer.parseInt(readRequest.readLine());
                    } catch (Exception e) {
                        LOGGER.warn("User didn't ask about any details of an speciality and enter an non integer data, " +
                                "setting userRequest=-1");
                        userRequest = -1;
                    }

                    if (userRequest == 1) {
                        LOGGER.info("User ask about the details of an speciality");
                        LOGGER.info("\nIn " + speciality + " you will have:");
                        response = ohioU.getSpecialityInfo(specialityId);
                        for (String line : response) {
                            LOGGER.info(line);
                        }
                    }
                } else if (userRequest == -1) {
                    LOGGER.info("Europe = 1");
                    LOGGER.info("South America = 2");
                    try {
                        userRequest = Integer.parseInt(readRequest.readLine());
                    } catch (Exception e) {
                        LOGGER.info("User didn't ask about any Region and enter an non integer data, " +
                                "setting userRequest=0");
                        userRequest = 0;
                    }
                    switch (userRequest) {
                        case 1:
                            for (Teacher teacher : teachers) {
                                teacher.printSearchedStudents(((p) -> p.getRegion().equals(Regions.EUROPE)));
                            }
                            break;
                        case 2:
                            for (Teacher teacher : teachers) {
                                teacher.printSearchedStudents(((p) -> p.getRegion().equals(Regions.SOUTH_AMERICA)));
                            }
                            break;
                        default:
                            LOGGER.info("User didn't ask about any Region setting userRequest=0");
                            userRequest = 0;
                            break;
                    }
                } else if (userRequest == -2) {
                    LOGGER.info("EXAM STUDENTS");
                    teachers.get(0).ExamStudents();
                    //response = teachers.get(0).giveResults();
                    //LOGGER.info(response.toString());
                    //ohioU.orderExams();
                    //ohioU.requestResultsExam(0);
                    teachers.get(0).giveBackupResults();//Using custom linked list
                }

            } while (userRequest != 0);

        } while (userRequest != 0);

        LOGGER.info("END MAIN");
    }
}