package Controller;

/**
 * Created by dekeeu on 25/04/2018.
 */
import com.dekeeu.lab1.Controller.LaboratoriesController;
import com.dekeeu.lab1.Exception.RepositoryException;
import com.dekeeu.lab1.Model.Laboratory;
import com.dekeeu.lab1.Model.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;


public class LaboratoriesIntegrationTest {
    private String labsFileName = "laboratories_test";
    private String studentsFileName = "students_test";

    private File labsFile = new File(labsFileName);
    private File studentsFile = new File(studentsFileName);

    LaboratoriesController ctrl;


    @Before
    public void setUp() throws IOException {
        labsFile = new File(labsFileName);
        studentsFile = new File(studentsFileName);

        labsFile.createNewFile();
        studentsFile.createNewFile();

        ctrl = new LaboratoriesController(studentsFileName, labsFileName);
    }

    @After
    public void tearDown(){
        labsFile.delete();
        studentsFile.delete();
    }

    @Test
    public void addStudentTest() throws RepositoryException {
        Student s = new Student("1888", "Alexandru Coltuneac", 833);
        Student s2 = new Student("1999", "Gogo Lala", 844);

        Assert.assertTrue(ctrl.saveStudent(s));
        Assert.assertTrue(ctrl.saveStudent(s2));

    }

    @Test
    public void addLaboratoryTest() throws RepositoryException, ParseException {
        Laboratory l1 = new Laboratory(1, "22/12/2020", 7, "1888");
        Laboratory l2 = new Laboratory(1, "22/1/2020", 5, "1999");

        Assert.assertTrue(ctrl.saveLaboratory(l1));
        Assert.assertTrue(ctrl.saveLaboratory(l2));
    }

    @Test
    public void passingStudentsTest() throws IOException, ParseException {
        Assert.assertEquals(0, ctrl.passedStudents().size());
    }

    @Test
    public void testIntegration() throws RepositoryException, ParseException, IOException {
        Student s = new Student("1888", "Alexandru Coltuneac", 833);
        Student s2 = new Student("1999", "Gogo Lala", 844);

        Assert.assertTrue(ctrl.saveStudent(s));
        Assert.assertTrue(ctrl.saveStudent(s2));

        Laboratory l1 = new Laboratory(1, "22/12/2020", 7, "1888");
        Laboratory l2 = new Laboratory(1, "22/1/2020", 5, "1999");

        Assert.assertTrue(ctrl.saveLaboratory(l1));
        Assert.assertTrue(ctrl.saveLaboratory(l2));

        ctrl.addGrade("1888", "1", 2);
        ctrl.addGrade("1999", "1", 5);

        Assert.assertEquals(1, ctrl.passedStudents().size());


    }


}
