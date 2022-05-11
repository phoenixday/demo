package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.jena.query.ResultSet;

/**
 * A class for results of a query. Contains a description and results actually.
 */
@Getter
@Setter
@AllArgsConstructor
public class QueryResults {
    String description;
    ResultSet results;
}
