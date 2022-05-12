package com.example.demo.model;

import java.util.Optional;

public class QueriesFactory {

    public static Optional<Queries> chooseQueriesByFormat(String XMLFormat, String inputXML, String outputRDF) {
        //TODO: throw an exception if XMLFormat is null?
        if (XMLFormat == null) {
            return Optional.empty();
        }
        if (XMLFormat.equalsIgnoreCase("AXMPR")) {
            return Optional.of(new AXMPRQueries(inputXML, outputRDF));
        }
        if (XMLFormat.equalsIgnoreCase("ESE")) {
            return Optional.of(new ESEQueries(inputXML, outputRDF));
        }
        //TODO: throw an exception if XMLFormat is not a valid format?
        return Optional.empty();
    }
}
