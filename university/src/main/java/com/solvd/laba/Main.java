package com.solvd.laba;

import com.solvd.laba.administrative.sections.University;
import com.solvd.laba.custom.linked.list.CustomLinkedList;
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
            teachers.add(new Teacher("Teacher " + i, i, i));
        }
    }


    public static void main(String[] args) throws NoSpecialtiesFoundException {
        final String UNIVERSITY_NAME = "Ohio U";
        final int QUANTITY_OF_TEACHERS = 5;
        final int QUANTITY_OF_SUBJECTS = 60;
        final int MIN_U_COST = 50;
        final int MAX_U_COST = 1200;
        final String[] ARR_COLLEGES = {"Fine Arts", "Engineering and Technology", "Health Sciences"};
        final String[] ARR_SPECIALITIES = {"Anthropology", "Applied Plant Biology", "Astrophysics",
                "Art History", "Applied Nutrition",
                "Communication Sciences and Disorders"};
        ArrayList<Teacher> teachers = new ArrayList<>();
        Main.createTeachers(teachers, QUANTITY_OF_TEACHERS);
        UniversityCreator universityCreator = new UniversityCreator();
        ArrayList<String> response = null;

        Student student = new Student("Lionel Messi", 1);

        int userRequest;
        BufferedReader readRequest = new BufferedReader(new InputStreamReader(System.in));
        do {
            LOGGER.info("Start of the program");


            CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();
            customLinkedList.insertFirst(44);
            customLinkedList.insertFirst(33);
            customLinkedList.insertFirst(12);
            customLinkedList.insertFirst(8);
            customLinkedList.insertFirst(4);
            customLinkedList.display();
            customLinkedList.displayReverse();






            //Create a university
            University ohioU = null;
            try {
                ohioU = universityCreator.create(UNIVERSITY_NAME, Main.getRandomInt(MIN_U_COST, MAX_U_COST),
                        ARR_COLLEGES, ARR_SPECIALITIES, teachers, QUANTITY_OF_SUBJECTS, student);
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

            teachers.get(0).ExamStudents();
            //response = teachers.get(0).giveResults();
            //LOGGER.info(response.toString());
            teachers.get(0).giveBackupResults();//Using custom linked list



            do {
                try {//Ask user if he wants info about any speciality
                    LOGGER.info("\nIf you want to know the cost of any of ours specialities, " +
                            "please enter its " + "(id) number. " + "Any other character to exit");
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
                }

            } while (userRequest != 0);

        } while (userRequest != 0);
        LOGGER.info("END MAIN");
    }
}