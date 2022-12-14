package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.exeptions.InvalidIDException;
import com.solvd.laba.exeptions.NoCollegesException;
import com.solvd.laba.exeptions.NoSpecialtiesFoundException;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class University extends AdministrativeSection {
    private ArrayList<College> colleges;

    public University(String name, ICalculateCost cost) {
        super(name, cost);
        this.colleges = new ArrayList<>();
        this.subDependencies = this.colleges;//doing this because I need two pointers, subDependencies is needed for
        // getStudentsArrayList() in AdministrativeSection, colleges is needed for the streams in some methods of university
    }

    @Override
    public ArrayList<? extends AdministrativeSection> getSubSections() {
        return new ArrayList<College>(this.colleges);
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

    public void addSpeciality(int collegeIndex, Speciality speciality) throws NoCollegesException {
        if (collegeIndex > this.colleges.size()) {
            collegeIndex = this.colleges.size();
        } else if (collegeIndex < 0) {
            throw new NoCollegesException("No Colleges in " + this.getName());
        }
        this.colleges.get(collegeIndex).addSpeciality(speciality);
    }

    public void deleteSpeciality(int specialityId) {
        this.colleges.stream().filter(c -> c.haveSpecialityById(specialityId)).findFirst().ifPresent(c -> c.removeSpeciality(specialityId));
//        for (College college : this.colleges) {
//            if (college.haveSpecialityById(specialityId)) {
//                college.removeSpeciality(specialityId);
//            }
//        }
    }

    public void addSubjectToSpeciality(int specialityId, Subject subject) throws InvalidIDException {
        AtomicBoolean added = new AtomicBoolean(false);

        this.colleges.stream().filter(college -> college.haveSpecialityById(specialityId)).findFirst().ifPresent(college -> {
            college.addSubjectToSpeciality(specialityId, subject);
            added.set(true);
        });

        if (!added.get()) {
            throw new InvalidIDException("Id of speciality not found");
        }
    }

    public ArrayList<String> getColleges() throws NoCollegesException {
        if (colleges.size() < 1) {
            throw new NoCollegesException("No Colleges in " + this.getName());
        }
        return this.colleges.stream().map(College::getName).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> getSpecialities() throws NoSpecialtiesFoundException {
        ArrayList<String> response = new ArrayList<>();
        this.colleges.stream().map(College::getSpecialities).forEach(response::addAll);

        if (response.isEmpty()) {
            throw new NoSpecialtiesFoundException(this.getName() + " has no specialities");
        }
        return response;
    }

    public String getSpecialityById(int specialityId) {
        Optional<College> f = this.colleges.stream().filter(c -> c.haveSpecialityById(specialityId)).
                findFirst();
        return f.map(college -> college.getSpecialityById(specialityId)).orElse(null);
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
        ArrayList<String> response;
        try {
            response = new ArrayList<>(this.colleges.stream().filter(c -> c.haveSpecialityById(specialityId))
                    .findFirst().map(c -> c.getSpecialityDetails(specialityId)).get());
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public void deleteSubject(String subject, int specialityId) {
        this.colleges.stream().filter(c -> c.haveSpecialityById(specialityId)).forEach(c -> c.deleteSubject(subject));
    }
}
