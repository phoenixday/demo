package com.example.demo.model.transformers;

import lombok.AllArgsConstructor;
import tr.com.srdc.ontmalizer.XML2OWLMapper;
import tr.com.srdc.ontmalizer.XSD2OWLMapper;

import java.io.File;
import java.io.FileOutputStream;

//TODO: add reasoning
@AllArgsConstructor
public class OntmalizerTransformer implements Transformer{
    String inputXML;
    String outputRDF;

    /**
     * A method to convert XML to RDF/XML. Uses third-party project ontmalizer.
     * <a href="https://github.com/srdc/ontmalizer">https://github.com/srdc/ontmalizer</a>
     */
    @Override
    public void transformXMLToRDF() {
        String schema = "src/main/java/com/example/demo/model/assets/axmpr/axmpr_complete.xsd";
        // This part converts XML schema to OWL ontology.
        XSD2OWLMapper mapping = new XSD2OWLMapper(new File(schema));
        mapping.setObjectPropPrefix("");
        mapping.setDataTypePropPrefix("");
        mapping.convertXSD2OWL();

        // This part converts XML instance to RDF data model.
        XML2OWLMapper generator = new XML2OWLMapper(
                new File(inputXML), mapping);
        generator.convertXML2OWL();

        // This part prints the RDF data model to the specified file.
        try{
            File f = new File(outputRDF);
            f.getParentFile().mkdirs();
            FileOutputStream fout = new FileOutputStream(f);
            generator.writeModel(fout, "RDF/XML");
            fout.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
