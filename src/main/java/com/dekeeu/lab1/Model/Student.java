package com.dekeeu.lab1.Model;

/**
 * Created by dekeeu on 13/03/2018.
 */
public class Student implements Comparable {
    private String regNumber;
    private String name;
    private int group;

    public Student() {

    }

    public Student(String regNumber, String name, int group) {
        this.regNumber = regNumber;
        this.name = name;
        this.group = group;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return regNumber + " " + name + " " + group;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((regNumber == null) ? 0 : regNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (regNumber == null) {
            if (other.regNumber != null)
                return false;
        } else if (!regNumber.equals(other.regNumber))
            return false;
        return true;
    }


    @Override
    public int compareTo(Object o) {
        Student s = (Student)o;
        if(this.group <= s.getGroup() || this.group == s.getGroup()){
            return -1;
        }
        return 1;
    }
}
