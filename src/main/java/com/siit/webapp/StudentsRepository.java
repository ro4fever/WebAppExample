package com.siit.webapp;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentsRepository {

    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();

        Student student1 = new Student("Mihai", "Pop");
        Student student2 = new Student("Roxana", "Dobre");
        Student student3 = new Student("Vali", "Topescu");
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        return studentList;
    }

    public HashMap<Student, List<Integer>> addGradesToStudents(List<Student> studentList) {
        HashMap<Student, List<Integer>> studentWithGradesMap = new HashMap<>();

        for (Student St : studentList) {
            if (St.getFirstName().equals("Mihai") && St.getLastName().equals("Pop")) {
                studentWithGradesMap.put(St, List.of(6, 7, 8, 4, 6, 10, 10));
            } else if (St.getFirstName().equals("Roxana") && St.getLastName().equals("Dobre")) {
                studentWithGradesMap.put(St, List.of(9, 5, 7, 3, 10, 9, 10));
            } else if (St.getFirstName().equals("Vali") && St.getLastName().equals("Topescu")) {
                studentWithGradesMap.put(St, List.of(5, 8, 9, 10, 6, 7, 9));
            }
        }
        return studentWithGradesMap;
    }
}
