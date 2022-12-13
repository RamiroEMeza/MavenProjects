package com.solvd.laba.university.administrative.sections;

import com.solvd.laba.university.members.Teacher;
import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.exeptions.InvalidIDException;
import com.solvd.laba.exeptions.NoCollegesException;
import com.solvd.laba.exeptions.NoSpecialtiesFoundException;
import com.solvd.laba.exeptions.NoTeacherException;

import java.util.ArrayList;
import java.util.Objects;

public class University extends AdmnistrativeSection {
    private ArrayList<College> colleges;
    private ArrayList<Teacher> teachers;

    public University(String name, ICalculateCost cost) {
        super(name, cost);
        this.colleges = new ArrayList<>();
        this.teachers = new ArrayList<>();
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
        return this.getName() + " has " + this.colleges + " hires " + this.teachers + " teachers"
                + " and cost " + this.getBaseCost();
    }

    public int calculateCost(int specialityId) {
        int result = 0;
        for (College college : this.colleges) {
            if (college.haveSpecialityById(specialityId)) {
                result = college.calculateCost(specialityId);
            }
        }
        return result + this.getBaseCost();
    }

    public void addTeacher(Teacher teacher) {
        if (!this.teachers.contains(teacher)) {
            this.teachers.add(teacher);
        }
    }

    public void deleteTeacher(int index) throws NoTeacherException {
        if (index >= 0 && index < this.teachers.size()) {
            this.teachers.remove(index);
        } else {
            throw new NoTeacherException("Index didn't match with any teacher");
        }
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
        boolean added = false;
        for (College college : this.colleges) {
            if (college.haveSpecialityById(specialityId)) {
                college.addSubjectToSpeciality(specialityId, subject);
                added = true;
            }
        }

        if (!added) {
            throw new InvalidIDException("No se encontro el id de la especialidad");
        }
    }

    public Teacher getTeacher(int index) throws NoTeacherException {
        if (index >= 1 && index <= this.teachers.size()) {
            return this.teachers.get(index - 1);
        } else {
            throw new NoTeacherException("No teacher founded");
        }
    }

    public ArrayList<String> getTeachers() throws NoTeacherException {
        ArrayList<String> response = new ArrayList<>();
        for (Teacher c : this.teachers) {
            response.add(c.getName());
        }

        if (response.size() < 1) {
            throw new NoTeacherException("No teachers founded");
        }

        return response;
    }

    public int getTeachersQuantity() {
        return this.teachers.size();
    }

    @Override
    public int getQuantityOfStudents() {
        int result = 0;
        for (College college : this.colleges) {
            result += college.getQuantityOfStudents();
        }
        return result;
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

        if (response.isEmpty() || response.size() < 1) {
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
        int response = 0;
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
}
