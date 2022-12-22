package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.exeptions.InvalidIDException;
import com.solvd.laba.exeptions.NoCollegesException;
import com.solvd.laba.exeptions.NoSpecialtiesFoundException;
import com.solvd.laba.members.Student;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class University extends AdministrativeSection {
    private ArrayList<College> colleges;

    public University(String name, ICalculateCost cost) {
        super(name, cost);
        this.colleges = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        if (this.hashCode() != that.hashCode()) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getBaseCost(), that.getBaseCost());
    }

    @Override
    public int hashCode() {
        //System.out.println("hashCode= "+ Objects.hash(getName(), getCost()));
        return Objects.hash(getName(), getBaseCost());
    }

    @Override
    public String toString() {
        return this.getName() + " has " + this.colleges + " hires " + " teachers"
                + " and cost " + this.getBaseCost();
    }

    public int calculateCost(int specialityId) {
        AtomicInteger result = new AtomicInteger();
        this.colleges.stream().filter(college -> college.haveSpecialityById(specialityId))
                .forEach(college -> result.set(college.calculateCost(specialityId)));

//        for (College college : this.colleges) {
//            if (college.haveSpecialityById(specialityId)) {
//                result = college.calculateCost(specialityId);
//            }
//        }
        return result.get() + this.getBaseCost();
    }

    public void addCollege(College college) {
        if (!this.colleges.contains(college)) {
            this.colleges.add(college);
        }
    }

    public void deleteCollege(int index) throws NoCollegesException {
        if (index >= 0 && index < colleges.size()) {
            this.colleges.remove(index);
        } else {
            throw new NoCollegesException("Index searched out of bounds for colleges of " + this.getName());
        }
    }

    public void addSpeciality(int collegeIndex, Speciality speciality) {
        if (collegeIndex > this.colleges.size()) {
            collegeIndex = this.colleges.size();
        }
        this.colleges.get(collegeIndex).addSpeciality(speciality);
    }

    public void deleteSpeciality(int specialityId) {
        for (College college : this.colleges) {
            if (college.haveSpecialityById(specialityId)) {
                college.removeSpeciality(specialityId);
            }
        }
    }

    public void addSubjectToSpeciality(int specialityId, Subject subject) throws InvalidIDException {
        AtomicBoolean added = new AtomicBoolean(false);

        Optional<College> objective = this.colleges.stream().filter(college -> college.haveSpecialityById(specialityId)).
                findFirst();

        objective.ifPresent(college -> {
            college.addSubjectToSpeciality(specialityId, subject);
            added.set(true);
        });

//        for (College college : this.colleges) {
//            if (college.haveSpecialityById(specialityId)) {
//                college.addSubjectToSpeciality(specialityId, subject);
//                added.set(true);
//            }
//        }

        if (!added.get()) {
            throw new InvalidIDException("Id of speciality not found");
        }
    }


    @Override
    public int getQuantityOfStudents() {
        return this.getStudentsArrayList().size();
    }

    @Override
    public ArrayList<Student> getStudentsArrayList() {
        ArrayList<Student> noRepeatsList = new ArrayList<Student>();
        ArrayList<Student> aux = new ArrayList<Student>();
        for (College college : this.colleges) {
            aux = college.getStudentsArrayList();
            aux.stream().filter(student -> !noRepeatsList.contains(student)).forEach(noRepeatsList::add);
        }
        return noRepeatsList;
    }


    public ArrayList<String> getColleges() throws NoCollegesException {
        if (colleges.size() < 1) {
            throw new NoCollegesException("No Colleges in " + this.getName());
        }
        ArrayList<String> response = new ArrayList<>();
        for (College c : this.colleges) {
            response.add(c.getName());
        }
        return response;
    }

    public ArrayList<String> getSpecialities() throws NoSpecialtiesFoundException {
        ArrayList<String> response = new ArrayList<>();
        for (College c : this.colleges) {
            response.addAll(c.getSpecialities());
        }

        if (response.isEmpty()) {
            throw new NoSpecialtiesFoundException(this.getName() + " has no specialities");
        }

        return response;
    }

    public String getSpecialityById(int specialityId) {
        for (College college : this.colleges) {
            if (college.haveSpecialityById(specialityId)) {
                return college.getSpecialityById(specialityId);
            }
        }
        return null;
    }

    public int getLastSpecialityId() throws NoSpecialtiesFoundException {
        int response;
        try {
            response = this.getSpecialities().size();
        } catch (NoSpecialtiesFoundException e) {
            throw e;
        }

        return response;
    }

    public ArrayList<String> getSpecialityInfo(int specialityId) {
        ArrayList<String> response = new ArrayList<>();
        for (College college : this.colleges) {
            if (college.haveSpecialityById(specialityId)) {
                response.addAll(college.getSpecialityDetails(specialityId));
            }
        }
        return response;
    }

    public void deleteSubject(String subject, int specialityId) {
        for (College college : this.colleges) {
            if (college.haveSpecialityById(specialityId)) {
                college.deleteSubject(subject);
            }
        }
    }

//    public void orderExams() {
//        for (Teacher t : this.teachers) {
//            t.ExamStudents();
//        }
//    }
//
//    public void requestResultsExam(int i) {
//        this.teachers.get(i).giveBackupResults();
//    }

//    public int getTeachersQuantity() {
//        return this.teachers.size();
//    }

    //    public Teacher getTeacher(int index) throws NoTeacherException {
//        if (index >= 1 && index <= this.teachers.size()) {
//            return this.teachers.get(index - 1);
//        } else {
//            throw new NoTeacherException("No teacher founded");
//        }
//    }
//    public ArrayList<String> getTeachers() throws NoTeacherException {
//        ArrayList<String> response = new ArrayList<>();
//        for (Teacher c : this.teachers) {
//            response.add(c.getName());
//        }
//
//        if (response.size() < 1) {
//            throw new NoTeacherException("No teachers founded");
//        }
//        return response;
//    }
}
