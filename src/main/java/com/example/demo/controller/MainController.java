package com.example.demo.controller;

import com.example.demo.model.queries.Queries;
import com.example.demo.model.queries.QueriesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class MainController {

    String inputXML = "src/main/java/com/example/demo/controller/putMeSomewhereElse/input.xml";
    String outputRDF = "src/main/java/com/example/demo/controller/putMeSomewhereElse/output.rdf";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-xml")
    public String uploadXML(@RequestParam("format") String format,
                            @RequestParam("file") MultipartFile file,
                            Model model) throws IOException {
        File inputFile = new File(inputXML);
        file.transferTo(inputFile.getAbsoluteFile());
        Optional<Queries> queries = QueriesFactory.retrieveQueries(format, inputXML, outputRDF);
        if (queries.isEmpty()) return "redirect:/error";
        model.addAttribute("format", format);
        model.addAttribute("queries", queries.get().computeQueries());
        return "show-queries";
    }
}
