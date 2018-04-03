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
        Assert.assertTrue(ctrl.saveStudent(new Student("caie1888", "Coltuneac Alexandru", 933)));
    }

    @Test
    public void assignLaboratoryTest() throws ParseException, RepositoryException, IOException {
        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        ctrl.saveStudent(new Student("caie1888", "Coltuneac Alexandru", 933));
        Assert.assertTrue(ctrl.saveLaboratory(new Laboratory(1, "1/12/2019", 7, "caie1888")));

    }

    @Test
    public void gradeLaboratoryTest() throws RepositoryException, ParseException, IOException {
        LaboratoriesController ctrl = new LaboratoriesController("students_test", "laboratories_test");
        ctrl.saveStudent(new Student("caie1888", "Coltuneac Alexandru", 933));
        ctrl.saveLaboratory(new Laboratory(1, "1/12/2019", 7, "caie1888"));

        Assert.assertTrue(ctrl.addGrade("caie1888", "1", 9.5f));
    }
}
