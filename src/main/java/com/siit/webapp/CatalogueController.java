package com.siit.webapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogueController {
   private final CatalogueService catalogueService;

    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping("/students")
    public String getAllStudents(){

        return catalogueService.createStudentCatalogue();
    }

    @GetMapping("/ranking")
    public String getStudentRanking(){

        return catalogueService.createStudentRank();
    }

}
