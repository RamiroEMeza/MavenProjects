package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;

import java.util.ArrayList;
import java.util.Objects;

public class College extends AdmnistrativeSection {
    private ArrayList<Speciality> specialities;
    private int id;

    public College(String name, ICalculateCost cost, int id) {
        super(name, cost);
        this.id = id;
        this.specialities = new ArrayList<Speciality>();
    }

    public boolean haveSpecialityById(int specialityId) {
        for (Speciality speciality :
                this.specialities) {
            if (speciality.getId() == specialityId) {
                return true;
            }
        }
        return false;
    }

    public int calculateCost(int specialityId) {
        int result = 0;
        for (Speciality speciality : specialities) {
            if (speciality.getId() == specialityId) {
                result = speciality.calculateCost();
            }
        }
        return result + this.getBaseCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        College that = (College) o;
        if (this.hashCode() != that.hashCode()) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getBaseCost(), that.getBaseCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBaseCost());
    }

    @Override
    public String toString() {
        return "College{" +
                "Name=" + getName() +
                "id=" + getId() +
                '}';
    }

    public void addSpeciality(Speciality speciality) {
        if (!this.specialities.contains(speciality)) {
            this.specialities.add(speciality);
        }
    }

    public void removeSpeciality(int specialityId) {
        for (int j = 0; j < specialities.size(); j++) {
            if (specialities.get(j).getId() == specialityId) {
                specialities.remove(j);
            }
        }
    }

    public void addSubjectToSpeciality(int specialityId, Subject subject) {
        for (Speciality speciality : this.specialities) {
            if (speciality.getId() == specialityId) {
                speciality.addSubject(subject);
            }
        }
    }

    public void deleteSubject(String subject) {
        for (Speciality speciality : this.specialities) {
            if (speciality.haveSubjectByName(subject)) {
                speciality.deleteSubjectByName(subject);
            }
        }
    }

    public ArrayList<String> getSpecialities() {
        ArrayList<String> response = new ArrayList<String>();
        for (Speciality speciality : specialities) {
            response.add(speciality.getName() + " (Id: " + speciality.getId() + ")\n [Subjects: "
                    + speciality.getQuantitySubjects() + "]\n At "
                    + this.getName() + "\n\n");
        }
        return response;
    }

    public int getId() {
        return id;
    }

    public String getSpecialityById(int specialityId) {
        for (Speciality speciality : this.specialities) {
            if (speciality.getId() == specialityId) {
                return speciality.getName();
            }
        }
        return null;
    }

    @Override
    public int getQuantityOfStudents() {
        int result = 0;
        for (Speciality speciality : this.specialities) {
            result += speciality.getQuantityOfStudents();
        }
        return result;
    }

    public ArrayList<String> getSpecialityDetails(int specialityId) {
        ArrayList<String> response = new ArrayList<>();
        for (Speciality speciality : this.specialities) {
            if (speciality.getId() == specialityId) {
                response.addAll(speciality.getDetail());
            }
        }
        return response;
    }


}
