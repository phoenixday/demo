package com.example.demo.model;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;

import java.util.List;

public class ESEQueries extends Queries{

    /**
     * The constructor in itself transforms XML to RDF and loads RDF into a model.
     *
     * @param inputXML  a path to XML
     * @param outputRDF a path for output RDF
     */
    public ESEQueries(String inputXML, String outputRDF) {
        super("ESE", inputXML, outputRDF);
    }

    @Override
    public List<QueryResults> getQueries() {
        return List.of(selectAll());
    }

    @Override
    QueryResults selectAll() {
        String queryString = "" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>" +
                "PREFIX dcterms: <http://purl.org/dc/terms/>" +
                "SELECT * WHERE {" +
                "   ?subject ?object ?predicate" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all triples", qexec.execSelect());
        }
    }
}
