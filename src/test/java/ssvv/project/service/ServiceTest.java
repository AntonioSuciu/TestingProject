package ssvv.project.service;

import org.junit.Test;
import ssvv.project.domain.Student;
import ssvv.project.repository.NotaXMLRepo;
import ssvv.project.repository.StudentXMLRepo;
import ssvv.project.repository.TemaXMLRepo;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;
import ssvv.project.validation.ValidationException;

import static org.junit.Assert.*;

public class ServiceTest {

    StudentXMLRepo studentXMLRepo = new StudentXMLRepo( "src/test/resources/fisiere/Studenti.xml");
    StudentValidator studentValidator = new StudentValidator();

    TemaXMLRepo temaXMLRepo = new TemaXMLRepo("src/test/resources/fisiere/Teme.xml");

    TemaValidator temaValidator = new TemaValidator();

    NotaXMLRepo notaXMLRepo = new NotaXMLRepo("src/test/resources/fisiere/Note.xml");
    NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);


    Service testService = new Service(studentXMLRepo, studentValidator,
            temaXMLRepo, temaValidator,  notaXMLRepo,  notaValidator);
    @Test
    public void addStudentPositiveIDs() {
        Student student1 = new Student("1", "Nume Student 1", 123,"email1@abc.com");
        Student student2 = new Student("2", "Nume Student 2", 123,"email2@abc.com");
        Student student3 = new Student("3", "Nume Student 3", 123,"email3@abc.com");
        Student student4 = new Student("4", "Nume Student 4", 123,"email4@abc.com");
        assertEquals(testService.addStudent(student1), student1);
        assertEquals(testService.addStudent(student2), student2);
        assertEquals(testService.addStudent(student3), student3);
        assertEquals(testService.addStudent(student4), student4);

    }

    @Test
    public void addStudentNegativeIDs() {
        Student studentmin1 = new Student("-1", "Nume Student -1", 123,"email1@abc.com");
        Student studentmin2 = new Student("-2", "Nume Student -2", 123,"email2@abc.com");
        assertEquals(testService.addStudent(studentmin1), studentmin1);
        assertEquals(testService.addStudent(studentmin2), studentmin2);


    }

    @Test
    public void addStudentNullIDs() {
        Student studentNull = new Student("", "Nume Student Null", 123,"email1@abc.com");
        assertThrows(ValidationException.class, () -> testService.addStudent(studentNull));


    }

    @Test
    public void addStudentBVA()
    {
        Student student1 = new Student("1", "Nume Student 1", 123,"email1@abc.com");
        Student studentNull = new Student("", "Nume Student Null", 123,"email1@abc.com");
        Student studentmin1 = new Student("-1", "Nume Student -1", 123,"email1@abc.com");

        assertEquals(testService.addStudent(student1), student1);
        assertThrows(ValidationException.class, () -> testService.addStudent(studentNull));
        assertEquals(testService.addStudent(studentmin1), studentmin1);
    }

}