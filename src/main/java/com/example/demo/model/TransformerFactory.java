package com.example.demo.model;

import java.util.Optional;

//TODO: make it somehow with maps, without ifs ?
/**
 * A factory that chooses either AXMPRTransformer or ESETransformer.
 */
public class TransformerFactory {

    /**
     * A method that chooses either AXMPRTransformer or ESETransformer.
     * @param XMLFormat a string, either "ESE" or "AXMPR", the case is ignored
     * @param inputXML path to XML
     * @param outputRDF path to result RDF
     * @return AXMPRTransformer or ESETransformer
     */
    public static Optional<Transformer> createTransformer(String XMLFormat, String inputXML, String outputRDF) {
        //TODO: throw an exception if XMLFormat is null?
        if (XMLFormat == null) {
            return Optional.empty();
        }
        if (XMLFormat.equalsIgnoreCase("AXMPR")) {
            return Optional.of(new AXMPRTransformer(inputXML, outputRDF));
        }
        if (XMLFormat.equalsIgnoreCase("ESE")) {
            return Optional.of(new ESETransformer(inputXML, outputRDF));
        }
        //TODO: throw an exception if XMLFormat is not a valid format?
        return Optional.empty();
    }
}
