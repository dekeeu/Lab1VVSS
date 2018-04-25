package com.dekeeu.lab1.Validator;

import com.dekeeu.lab1.Model.Laboratory;
import com.dekeeu.lab1.Model.Student;

import java.util.Date;

/**
 * Created by dekeeu on 13/03/2018.
 */

// Only 1 validator for both Classes; Inconsistent With Diagram
public class Validator {
    // incorrect if statement
    public static boolean validateStudent(Student student) {
        /*
        if(!student.getRegNumber().matches("[a-zA-Z]{4}[\\d]{4}")){
            return false;
        }
        */
        if(!student.getRegNumber().matches("^\\d+$")){
            return false;
        }

        if(Integer.valueOf(student.getRegNumber()) <= 0){
            return false;
        }

        if (!student.getName().matches("^[a-zA-Z]+[\\s]?[a-zA-Z]+$")) {
            return false;
        }
        /*
        if(student.getGroup() >= 1000 || student.getGroup() <= 0){
            return false;
        }
        */

        if(student.getGroup() > 900 || student.getGroup() < 100){
            return false;
        }
        return true;
    }

    public static boolean validateLaboratory(Laboratory laboratory) {
        if(laboratory.getNumber() < 1) {
            return false;
        }
        if(laboratory.getProblemNumber() > 10 || laboratory.getProblemNumber() < 1) {
            return false;
        }
        Date date = new Date();
        if(date.after(laboratory.getDate())) {
            return false;
        }
        return true;
    }

    // incorrect if, grade can't be 1 or 10
    public static boolean validateGrade(float grade) {
        if(grade > 0 && grade < 11) {
            return true;
        }
        return false;
    }
}
