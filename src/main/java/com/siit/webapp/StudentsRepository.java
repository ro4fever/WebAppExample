package com.siit.webapp;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class StudentsRepository {

    public List<Student> getStudentList(List<String[]> inputCSVList) {
        List<Student> studentList = new ArrayList<>();
        for (String[] strings : inputCSVList) {
            studentList.add(new Student(strings[0], strings[1]));
        }
        return studentList;
    }

    public Map<Student, List<Integer>> getStudentsWithGrades(List<String[]> inputCSVList) {
        HashMap<Student, List<Integer>> studentWithGradesMap = new HashMap<>();
        for (String[] strings : inputCSVList) {
            List<Integer> gradesStudentList = new LinkedList<>();
            for (int i = 2; i < strings.length; i++) {
                gradesStudentList.add(Integer.valueOf(strings[i]));
            }
            studentWithGradesMap.put((new Student(strings[0], strings[1])), gradesStudentList);
        }
        return studentWithGradesMap;
    }

    public List<String[]> readCSVFile() {
        List<String[]> inputStringArrayCSV = new ArrayList<>();
        try (Stream<String> linesStream = Files.lines(Paths.get("src\\main\\resources\\StudentGrades.csv"))) {
            inputStringArrayCSV = linesStream
                            .map(line -> line.split(", "))
                            .collect(Collectors.toList());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return inputStringArrayCSV;
    }
}
