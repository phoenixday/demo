package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;

import java.util.List;
import java.util.Optional;

//TODO: store model somewhere?
/**
 * A common class for demonstration queries for generated ontologies.
 */
@Getter
@Setter
public abstract class Queries {

    /**
     * An Apache Jena model, that stores all triples
     */
    Model model;

    /**
     * The constructor in itself transforms XML to RDF and loads RDF into a model.
     * @param XMLFormat a format of XML (either ESE or AXMPR)
     * @param inputXML a path to XML
     * @param outputRDF a path for output RDF
     */
    public Queries(String XMLFormat, String inputXML, String outputRDF) {
        Optional<Transformer> transformer = TransformerFactory.createTransformer(XMLFormat, inputXML, outputRDF);
        transformer.ifPresent(Transformer::transformXMLToRDF);
        setModel(RDFDataMgr.loadModel(outputRDF));
    }

    /**
     * By default, list of queries should contain at least selectAll().
     */
    abstract public List<QueryResults> getQueries();

    /**
     * A method that returns all triples.
     * @return all triples stored in TDB
     */
    abstract QueryResults selectAll();
}
