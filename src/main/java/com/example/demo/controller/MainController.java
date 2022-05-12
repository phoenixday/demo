package com.example.demo.controller;

import com.example.demo.model.Queries;
import com.example.demo.model.QueriesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class MainController {

    String outputRDF = "src/main/java/com/example/demo/model/assets/outputRDF.rdf";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/import")
    public String showQueries(@RequestParam("format") String format, @RequestParam("file") MultipartFile file, Model model) {

        Optional<Queries> queries = QueriesFactory.chooseQueriesByFormat(format, file.getOriginalFilename(), outputRDF);
        if (queries.isEmpty()) return "redirect:/error";
        model.addAttribute("queries", queries.get().computeQueries());
        return "redirect:/show-queries";
    }
}
