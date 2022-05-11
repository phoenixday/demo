package com.example.demo.model;

import lombok.AllArgsConstructor;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

//TODO: add reasoning
@AllArgsConstructor
public class ESETransformer implements Transformer{

    String inputXML;
    String outputRDF;

    /**
     * A method to extract Dublin Core items from XML in ESE format. Uses XSLT for extracting.
     */
    @Override
    public void transformXMLToRDF() {
        javax.xml.transform.TransformerFactory factory = javax.xml.transform.TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("com/example/demo/model/assets/ese/transform.xslt"));
        javax.xml.transform.Transformer transformer = null;
        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }

        Source text = new StreamSource(new File(inputXML));
        try {
            transformer.transform(text, new StreamResult(new File(outputRDF)));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
