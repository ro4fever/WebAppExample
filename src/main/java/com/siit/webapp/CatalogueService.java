package com.siit.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CatalogueService {

    @Autowired
    public CatalogueService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    private final StudentsRepository studentsRepository;
    public String createStudentCatalogue(){
        List<Student> studentListToBeProcessed = studentsRepository.getStudentList(studentsRepository.readCSVFile());
        String result = "";
        for (Student student: studentListToBeProcessed) {
            result = result.concat(student.getFirstName()).concat(" ").concat(student.getLastName().concat("<br></br>"));
        }
        return result;
    }

    public Map<Student, Double> calculateAverageSortGrades (Map<Student, List<Integer>> studentWithGrades) {
        HashMap<Student, Double> averageStudentGradeMap = new HashMap<>();
        for (Student St : studentWithGrades.keySet()) {
            OptionalDouble gradesAverage = studentWithGrades.get(St).stream()
                    .mapToInt(Integer::intValue)
                    .average();
            averageStudentGradeMap.put(St, gradesAverage.orElse(0));
        }
        Map<Student, Double> averageDescendingSortedStudentGradeMap = averageStudentGradeMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return averageDescendingSortedStudentGradeMap;
    }

    public String createStudentRank(){
        Map<Student, Double> studentListToBeDisplayed = calculateAverageSortGrades(studentsRepository.getStudentsWithGrades(studentsRepository.readCSVFile()));
        String result = "";
        DecimalFormat df = new DecimalFormat("0.00");
        for(Map.Entry<Student, Double> entry : studentListToBeDisplayed.entrySet()) {
            result = result.concat(entry.getKey().getFirstName()).concat(" ").concat(entry.getKey().getLastName()).concat(" ").concat(df.format(entry.getValue())).concat("<br></br>");
        }
        return result;
    }

}
