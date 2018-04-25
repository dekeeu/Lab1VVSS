package Controller;

import com.dekeeu.lab1.Controller.LaboratoriesController;
import com.dekeeu.lab1.Exception.RepositoryException;
import com.dekeeu.lab1.Model.Laboratory;
import com.dekeeu.lab1.Model.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

/**
 * Created by dekeeu on 03/04/2018.
 */
public class LaboratoriesControllerTest {

    @Before
    public void setUp() throws FileNotFoundException {
        PrintWriter labsWriter = new PrintWriter("laboratories_test");
        PrintWriter studentsWriter = new PrintWriter("students_test");

        labsWriter.write("");
        studentsWriter.write("");

        labsWriter.close();
        studentsWriter.close();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void addStudentTest() throws RepositoryException {
        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertTrue(ctrl.saveStudent(new Student("1888", "Coltuneac Alexandru", 733)));
    }

    @Test
    public void assignLaboratoryTest() throws ParseException, RepositoryException, IOException {
        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        ctrl.saveStudent(new Student("1888", "Coltuneac Alexandru", 733));
        Assert.assertTrue(ctrl.saveLaboratory(new Laboratory(1, "1/12/2019", 7, "1888")));

    }

    @Test
    public void gradeLaboratoryTest() throws RepositoryException, ParseException, IOException {
        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        ctrl.saveStudent(new Student("1888", "Coltuneac Alexandru", 733));
        ctrl.saveLaboratory(new Laboratory(1, "1/12/2019", 7, "1888"));

        Assert.assertTrue(ctrl.addGrade("1888", "1", 9.5f));
    }

    @Test
    public void addStudentInvalidRegNumber() throws RepositoryException {
        Student s = new Student("caie1888", "Coltuneac Alexandru", 733);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentInvalidName() throws RepositoryException {
        Student s = new Student("1888", "Coltune32131 Alexand111!@#$%^&*()ru", 733);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentInvalidGroup() throws RepositoryException {
        Student s = new Student("1888", "Coltuneac Alexandru", -500);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentBlankRegNumber() throws RepositoryException {
        Student s = new Student("", "Coltuneac Alexandru", 733);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentBlankName() throws RepositoryException {
        Student s = new Student("1888", "", 733);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    /*
    BVA for regNumber
     */

    @Test
    public void addStudentRegNumberLowBVA() throws RepositoryException {
        Student s = new Student("0", "Coltuneac Alexandru", 733);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentRegNumberLowBVA_2() throws RepositoryException {
        Student s = new Student("1", "Coltuneac Alexandru", 733);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertTrue(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentRegNumberLowBVA_3() throws RepositoryException {
        Student s = new Student("2", "Coltuneac Alexandru", 733);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertTrue(ctrl.saveStudent(s));
    }

    /*
    BVA for group
     */

    @Test
    public void addStudentGroupLowBVA() throws RepositoryException {
        Student s = new Student("1888", "Coltuneac Alexandru", 99);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentGroupLowBVA_2() throws RepositoryException {
        Student s = new Student("1888", "Coltuneac Alexandru", 100);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertTrue(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentGroupLowBVA_3() throws RepositoryException {
        Student s = new Student("1888", "Coltuneac Alexandru", 101);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertTrue(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentGroupHighBVA() throws RepositoryException {
        Student s = new Student("1888", "Coltuneac Alexandru", 899);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertTrue(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentGroupHighBVA_2() throws RepositoryException {
        Student s = new Student("1888", "Coltuneac Alexandru", 900);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertTrue(ctrl.saveStudent(s));
    }

    @Test
    public void addStudentGroupHighBVA_3() throws RepositoryException {
        Student s = new Student("1888", "Coltuneac Alexandru", 901);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    /*
    WBT
     */

    @Test
    public void addStudent_WBT_validRegNumber() throws RepositoryException{
        Student s = new Student("dqwndqw()(*&^%$#@!<>'", "Coltuneac Alexandru", 899);
        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudent_WBT_validName() throws RepositoryException{
        Student s = new Student("1888", "Coltuneac Alexandru !@#$%^&*()[];''?<>", 899);
        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudent_WBT_ValidGroup() throws RepositoryException{
        Student s = new Student("1888", "Coltuneac Alexandru !@#$%^&*()[];''?<>", 899);
        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addStudent_WBT_ExistingID() throws RepositoryException{
        Student s = new Student("1888", "Coltuneac Alexandru", 899);
        Student s2 = new Student("1888", "Coltuneac AlexandruCopy", 899);

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        ctrl.saveStudent(s2);

        Assert.assertFalse(ctrl.saveStudent(s));
    }

    @Test
    public void addLaboratory_WBT_ExistingID() throws RepositoryException, ParseException {
        Student s = new Student("1888", "Coltuneac Alexandru", 899);
        Laboratory l = new Laboratory(1, "12/02/2019", 1, "1888");
        Laboratory l2 = new Laboratory(1, "12/02/2019", 5, "1888");

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");

        ctrl.saveLaboratory(l);

        Assert.assertFalse(ctrl.saveLaboratory(l2));
    }


    @Test
    public void addStudent_WBT_ValidLabNumber() throws RepositoryException, ParseException {
        Student s = new Student("1888", "Coltuneac Alexandru", 899);
        Laboratory l = new Laboratory(15, "12/02/2019", 1, "1888");

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.saveLaboratory(l));
    }

    @Test
    public void addStudent_WBT_ValidGrade() throws RepositoryException, ParseException, IOException {
        Student s = new Student("1888", "Coltuneac Alexandru", 899);
        Laboratory l = new Laboratory(15, "12/02/2019", 1, "1888");

        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        Assert.assertFalse(ctrl.addGrade("1888", "1", 15));
    }









}
