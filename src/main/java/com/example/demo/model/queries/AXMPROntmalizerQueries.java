package com.example.demo.model.queries;

import com.example.demo.model.transformers.Transformer;
import com.example.demo.model.transformers.OntmalizerTransformer;
import org.apache.jena.query.*;
import org.apache.jena.riot.RDFDataMgr;

import java.util.List;

public class AXMPROntmalizerQueries extends Queries{

    /**
     * The constructor in itself transforms XML to RDF and loads RDF into a model.
     *
     * @param inputXML  a path to XML
     * @param outputRDF a path for output RDF
     */
    public AXMPROntmalizerQueries(String inputXML, String outputRDF) {
        super();
        Transformer transformer = new OntmalizerTransformer(inputXML, outputRDF);
        transformer.transformXMLToRDF();
        //setModel(ModelFactory.createRDFSModel(RDFDataMgr.loadModel(outputRDF)));
        setModel(RDFDataMgr.loadModel(outputRDF));
    }

    @Override
    public List<QueryResults> computeQueries() {
        return List.of(selectAll()
                , selectAllDefinedClasses()
                , selectAllClasses()
                , selectAllOfOaiId()
        );
    }

    /**
     * Select all defined classes
     * @return triples
     */
    QueryResults selectAllDefinedClasses() {
        String queryString = "" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "PREFIX oai: <http://www.openarchives.org/OAI/2.0/#>" +
                "PREFIX srdc: <http://www.srdc.com.tr/ontmalizer>" +
                "SELECT * WHERE {" +
                "   ?subject ?predicate rdfs:Class" +
                "}" +
                "ORDER BY ?subject ?predicate";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select defined classes", qexec.execSelect());
        }
    }

    /**
     * Select all classes
     * @return triples
     */
    QueryResults selectAllClasses() {
        String queryString = "" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "PREFIX oai: <http://www.openarchives.org/OAI/2.0/#>" +
                "PREFIX srdc: <http://www.srdc.com.tr/ontmalizer>" +
                "SELECT * WHERE {" +
                "   ?subject rdf:type ?object" +
                "}" +
                "ORDER BY ?subject ?object";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all classes", qexec.execSelect());
        }
    }

    /**
     * Select all OaiId
     * @return triples
     */
    QueryResults selectAllOfOaiId() {
        String queryString = "" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "PREFIX oai: <http://www.openarchives.org/OAI/2.0/#>" +
                "PREFIX srdc: <http://www.srdc.com.tr/ontmalizer/instance#>" +
                "PREFIX axmpr: <https://api.museion.cz/schema/axmpr#>" +
                "SELECT * WHERE {" +
                "   ?subject ?predicate axmpr:OaiId ." +
                //"   axmpr:OaiId ?predicate ?object" +
                "}" +
                "ORDER BY ?predicate";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all OaiId", qexec.execSelect());
        }
    }
}
