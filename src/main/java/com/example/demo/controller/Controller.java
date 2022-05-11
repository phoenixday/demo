package com.example.demo.controller;

import com.example.demo.model.AXMPRQueries;
import com.example.demo.model.ESEQueries;
import com.example.demo.model.Queries;
import com.example.demo.model.QueryResults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {
    String outputRDF = "src/main/java/com/example/demo/model/assets/outputRDF.rdf";

    @GetMapping("/import/ese")
    public List<QueryResults> getResultsForESE(@RequestParam("myFile") MultipartFile multipartFile) throws IOException {
//        Queries queries = new ESEQueries(multipartFile.getOriginalFilename(), outputRDF);
//        return queries.getQueries();
        return List.of();
    }

    @GetMapping("/import/axmpr")
    public List<QueryResults> getResultsForAXMPR(@RequestParam("myFile") MultipartFile multipartFile) throws IOException {
        Queries queries = new AXMPRQueries(multipartFile.getOriginalFilename(), outputRDF);
        return queries.getQueries();
    }
}
