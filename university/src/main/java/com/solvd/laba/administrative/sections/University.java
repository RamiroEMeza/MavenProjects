package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.exeptions.InvalidIDException;
import com.solvd.laba.exeptions.NoCollegesException;
import com.solvd.laba.exeptions.NoSpecialtiesFoundException;

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
        this.subDependencies = this.colleges;//doing this because I need two pointers, subDependencies is needed for
        // getStudentsArrayList() in AdministrativeSection, colleges is needed for the streams in some methods of university
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
        return Objects.hash(getName(), getBaseCost());
    }

    @Override
    public String toString() {
        return this.getName() + " has " + this.colleges + " hires " + " teachers"
                + " and cost " + this.getBaseCost();
    }

    public int calculateCost(int specialityId) {
        int result = 0;
        College c;
        Optional<College> collegeAux = this.colleges.stream()
                .filter(college -> college.haveSpecialityById(specialityId))
                .findFirst();
        if (collegeAux.isPresent()) {
            c = collegeAux.get();
            result = c.calculateCost(specialityId);
        }
        return result + this.getBaseCost();
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

        if (!added.get()) {
            throw new InvalidIDException("Id of speciality not found");
        }
    }


    @Override
    public int getQuantityOfStudents() {
        return this.getStudentsArrayList().size();
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
}
