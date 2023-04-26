package service;

import domain.Student;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    StudentXMLRepo studentXMLRepo = new StudentXMLRepo( "D:\\FACULTATE\\AN3SEM2\\SSVV\\Lab 1\\Lab1Maven\\lab1\\fisiere\\Studenti.xml");
    StudentValidator studentValidator = new StudentValidator();

    TemaXMLRepo temaXMLRepo = new TemaXMLRepo("D:\\FACULTATE\\AN3SEM2\\SSVV\\Lab 1\\Lab1Maven\\lab1\\fisiere\\Teme.xml");

    TemaValidator temaValidator = new TemaValidator();

    NotaXMLRepo notaXMLRepo = new NotaXMLRepo("D:\\FACULTATE\\AN3SEM2\\SSVV\\Lab 1\\Lab1Maven\\lab1\\fisiere\\Note.xml");
    NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);


    service.Service testService = new Service(studentXMLRepo, studentValidator,
            temaXMLRepo, temaValidator,  notaXMLRepo,  notaValidator);
    @org.junit.jupiter.api.Test
    void addStudentPositiveIDs() {
        Student student1 = new Student("1", "Nume Student 1", 123,"email1@abc.com");
        Student student2 = new Student("2", "Nume Student 2", 123,"email2@abc.com");
        Student student3 = new Student("3", "Nume Student 3", 123,"email3@abc.com");
        Student student4 = new Student("4", "Nume Student 4", 123,"email4@abc.com");
        assertEquals(testService.addStudent(student1), student1);
        assertEquals(testService.addStudent(student2), student2);
        assertEquals(testService.addStudent(student3), student3);
        assertEquals(testService.addStudent(student4), student4);

    }

    @org.junit.jupiter.api.Test
    void addStudentNegativeIDs() {
        Student studentmin1 = new Student("-1", "Nume Student -1", 123,"email1@abc.com");
        Student studentmin2 = new Student("-2", "Nume Student -2", 123,"email2@abc.com");
        assertEquals(testService.addStudent(studentmin1), studentmin1);
        assertEquals(testService.addStudent(studentmin2), studentmin2);


    }

    @org.junit.jupiter.api.Test
    void addStudentNullIDs() {
        Student studentNull = new Student("", "Nume Student Null", 123,"email1@abc.com");
        assertThrows(ValidationException.class, () -> testService.addStudent(studentNull));


    }

    @org.junit.jupiter.api.Test
    void addStudentBVA()
    {
        Student student1 = new Student("1", "Nume Student 1", 123,"email1@abc.com");
        Student studentNull = new Student("", "Nume Student Null", 123,"email1@abc.com");
        Student studentmin1 = new Student("-1", "Nume Student -1", 123,"email1@abc.com");

        assertEquals(testService.addStudent(student1), student1);
        assertThrows(ValidationException.class, () -> testService.addStudent(studentNull));
        assertEquals(testService.addStudent(studentmin1), studentmin1);
    }

}