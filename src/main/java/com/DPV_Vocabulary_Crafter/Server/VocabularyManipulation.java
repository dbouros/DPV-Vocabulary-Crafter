package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;

public class VocabularyManipulation {
    public String View(Model model){

        if (model.isEmpty()){
            System.out.println("The requested model is empty!");
            return "";
        }

        String modelString = "";
        // Print RDF Model(DPV) to "Client" screen.
        // This code is for the "View" method !
        for (Statement st : model) {
            IRI subject = (IRI) st.getSubject();
            IRI predicate = st.getPredicate();
            Value object = st.getObject();
            if(object.isLiteral()){
                modelString += subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((Literal)object).getLabel() + "\n";
                //System.out.println(subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((Literal)object).getLabel());
            } else if (object.isIRI()) {
                modelString += subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((IRI)object).getLocalName() + "\n";
                //System.out.println(subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((IRI)object).getLocalName());
            }else {
                modelString += subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + object + "\n";
                //System.out.println(subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + object);
            }
            // System.out.println(st.getSubject() + " >> " + st.getPredicate() + " >> " + st.getObject());
        }

        return modelString;
    }
}
