package com.example.demo.model;

import org.apache.jena.query.*;

import java.util.List;

public class AXMPRQueries extends Queries{

    /**
     * The constructor in itself transforms XML to RDF and loads RDF into a model.
     *
     * @param inputXML  a path to XML
     * @param outputRDF a path for output RDF
     */
    public AXMPRQueries(String inputXML, String outputRDF) {
        super("AXMPR", inputXML, outputRDF);
    }

    @Override
    public List<QueryResults> getQueries() {
        return List.of(selectAll(),
                selectAllNames(),
                selectAllLocalities());
    }

    @Override
    QueryResults selectAll() {
        String queryString = "" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX : <http://www.openarchives.org/OAI/2.0/#>" +
                "SELECT * WHERE {" +
                "   ?subject ?object ?predicate" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all triples", qexec.execSelect());
        }
    }

    /**
     * Select all predmet.nazev
     * @return triples
     */
    QueryResults selectAllNames() {
        String queryString = "" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX : <http://www.openarchives.org/OAI/2.0/#>" +
                "SELECT * WHERE {" +
                "   ?predmet :nazev ?nazev" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all names", qexec.execSelect());
        }
    }

    /**
     * Select all predmet.lokalita
     * @return triples
     */
    QueryResults selectAllLocalities() {
        String queryString = "" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX : <http://www.openarchives.org/OAI/2.0/#>" +
                "SELECT * WHERE {" +
                "   ?predmet :lokalitaPublic ?lokalita" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all localities", qexec.execSelect());
        }
    }
}
