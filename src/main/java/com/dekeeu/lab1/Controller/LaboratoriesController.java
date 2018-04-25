package com.dekeeu.lab1.Controller;

import com.dekeeu.lab1.Exception.RepositoryException;
import com.dekeeu.lab1.Model.Laboratory;
import com.dekeeu.lab1.Model.Student;
import com.dekeeu.lab1.Repository.FileDataPersistence;
import com.dekeeu.lab1.Validator.Validator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by dekeeu on 13/03/2018.
 */

// Inconsistent Name
public class LaboratoriesController {
    private FileDataPersistence studentPersistence = new FileDataPersistence("students.txt");
    private FileDataPersistence laboratoryPersistence = new FileDataPersistence("laboratories.txt");

    public LaboratoriesController(String studentFile, String laboratoryFile) {
        this.studentPersistence = new FileDataPersistence(studentFile);
        this.laboratoryPersistence = new FileDataPersistence(laboratoryFile);
    }

    // Exception should be thrown ?
    // Here should be checked if 2 students have the same Identificatio Number
    public boolean saveStudent(Student student) throws RepositoryException {
        if(Validator.validateStudent(student)){
            try{
                this.studentPersistence.saveStudent(student);
            }catch (RepositoryException re){
                return false;
            }
            return true;
        }else{
            return false;
        }
    }

    // Exception should be thrown ?
    public boolean saveLaboratory(Laboratory laboratory) throws RepositoryException {
        if (Validator.validateLaboratory(laboratory)) {
            try {
                this.laboratoryPersistence.saveLaboratory(laboratory);
            }catch (RepositoryException re){
                return false;
            }catch(ParseException pe){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    // Ambigous "student" parameter
    public boolean addGrade(String studentIdentificationNumber, String labNumber, float grade) throws NumberFormatException, IOException, ParseException{
        if(Validator.validateGrade(grade)){
            this.laboratoryPersistence.addGrade(studentIdentificationNumber, labNumber, grade);
            return true;
        }else{
            return false;
        }
    }

    public List<Student> passedStudents() throws NumberFormatException, IOException, ParseException{
        Map<String, List<Laboratory>> laboratoryMap = this.laboratoryPersistence.getLaboratoryMap();
        List<Student> studentsList = studentPersistence.getStudentsList();

        List<Student> passedStudents = new ArrayList<>();
        Map.Entry<String, List<Laboratory>> entry;

        Set<Map.Entry<String, List<Laboratory>>> entrySet = laboratoryMap.entrySet();
        Iterator<Map.Entry<String, List<Laboratory>>> iterator = entrySet.iterator();

        while (iterator.hasNext()){
            entry = iterator.next();
            float midGrade = entry.getValue().get(0).getGrade();
            for(Laboratory laboratory : entry.getValue()){
                midGrade = (midGrade + laboratory.getGrade()) / 2;
            }

            //System.out.println(midGrade); // should not be here

            if(midGrade >= 5){
                Student student = new Student();

                student.setRegNumber(entry.getKey());
                int indexOf = studentsList.indexOf(student);
                passedStudents.add(studentsList.get(indexOf));
            }
        }

        Collections.sort(passedStudents);

        return passedStudents;

    }

}
