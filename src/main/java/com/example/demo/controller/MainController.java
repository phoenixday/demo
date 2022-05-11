package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class MainController {

    String outputRDF = "src/main/java/com/example/demo/model/assets/outputRDF.rdf";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/import/ese")
    public String submitXMLForESE(Model model) throws IOException {
//        Queries queries = new ESEQueries(multipartFile.getOriginalFilename(), outputRDF);
//        return queries.getQueries();
        return "import-ese";
    }

    @GetMapping("/import/axmpr")
    public String submitXMLForAXMPR() throws IOException {
//        Queries queries = new AXMPRQueries(multipartFile.getOriginalFilename(), outputRDF);
//        return queries.getQueries();
        return "import-axmpr";
    }
}
