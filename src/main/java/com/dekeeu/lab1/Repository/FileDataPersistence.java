package com.dekeeu.lab1.Repository;

import com.dekeeu.lab1.Exception.RepositoryException;
import com.dekeeu.lab1.Model.Laboratory;
import com.dekeeu.lab1.Model.Student;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dekeeu on 13/03/2018.
 */

// Name of the Class is Inconsistent; is not the same from Diagram
    // Repository should be an interface
public class FileDataPersistence {
    private String file;

    public FileDataPersistence(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    // no check if student is unique

    public void saveStudent(Student student) throws RepositoryException {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<Student> students = new ArrayList<>();
            String line;

            while((line = reader.readLine()) != null){
                String[] studentDetails = line.split(" ");
                // mvmk2412 ion vasile 935
                String studentID = studentDetails[0];
                String studentFirstname = studentDetails[1];
                String studentLastname = studentDetails[2];
                String studentGroup = studentDetails[3];

                Student s = new Student(studentID, studentFirstname + " " + studentLastname, Integer.parseInt(studentGroup));
                students.add(s);
            }

            if(students.contains(student)){
                throw new RepositoryException("Duplicate Student ");
            }
        }catch (IOException e){
            System.out.println(e.toString());
        }

        try{
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(student.toString() + "\n");
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString()); // not good, should be reported to UI
        }
    }

    // No check if laboratory is unique

    public void saveLaboratory(Laboratory laboratory) throws RepositoryException {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<Laboratory> laboratories = new ArrayList<>();
            String line;

            while((line = reader.readLine()) != null){

            }

            if(laboratories.contains(laboratory)){
                throw new RepositoryException("Duplicate Laboratory ");
            }
        }catch (IOException e){
            System.out.println(e.toString());
        }

        try{
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(laboratory.toString() + "\n");
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString()); // not good, should be reported to UI
        }

    }

    // Ambigous "student" parameter of type String
    // Ambiguous what field of "Student" should be passed
    public void addGrade(String student, String labNumber, float grade) throws IOException, NumberFormatException, ParseException{
        File fileA = new File(file);
        File fileB = new File("temp");

        BufferedReader reader = new BufferedReader(new FileReader(fileA));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileB));

        String line;

        while((line = reader.readLine()) != null){
            String[] temp = line.split(" ");
            String fileLabNumber = temp[0];
            String fileStudentNumber = temp[4];
            if(fileLabNumber.equals(labNumber) && fileStudentNumber.equals(student)){
                Laboratory laboratory = new Laboratory(Integer.valueOf(temp[0]), temp[1], Integer.valueOf(temp[2]), temp[4]);
                laboratory.setGrade(grade);
                writer.write(laboratory.toString() + "\n");
            }else{
                writer.write(line + "\n");
            }

        }

        writer.close();
        reader.close();
        fileA.delete();
        fileB.renameTo(fileA);
    }


    // Ambiguous what field of "Laboratory" should be passed
    public Map<String, List<Laboratory>> getLaboratoryMap() throws NumberFormatException, IOException, ParseException{
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Map<String, List<Laboratory>> laboratoryMap = new HashMap<>();
        String line;

        while((line = reader.readLine()) != null){
            String[] temp = line.split(" ");
            Laboratory laboratory = new Laboratory(
                    Integer.valueOf(temp[0]),
                    temp[1],
                    Integer.valueOf(temp[2]),
                    Float.valueOf(temp[3]),
                    temp[4]
            );

            if(laboratoryMap.get(laboratory.getStudentRegNumber()) == null){
                List<Laboratory> laboratoryList = new ArrayList<>();
                laboratoryList.add(laboratory);
                laboratoryMap.put(laboratory.getStudentRegNumber(), laboratoryList);
            }else{
                laboratoryMap.get(laboratory.getStudentRegNumber()).add(laboratory);
            }

        }

        return laboratoryMap;
    }


    // Ambiguous what field of "Student" should be passed
    public List<Student> getStudentsList() throws NumberFormatException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<Student> allStudentsList = new ArrayList<>();
        String line;

        while((line = reader.readLine()) != null){
            String[] temp = line.split(" ");
            Student student = new Student(
                    temp[0],
                    temp[1] + temp[2],
                    Integer.valueOf(temp[3])
            );

            allStudentsList.add(student);

        }

        return allStudentsList;



    }
}
