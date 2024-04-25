package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;

public class VocabularyManipulation {

    public void initializeEmptyDPV(Model origModel, Model tempModel){
        // The xml declaration "<?xml version="1.0" encoding="UTF-8"?>" is added automatically.

        // Adding only the namespaces from the "original DPV" to the "temporary DPV".
        for (Namespace ns : origModel.getNamespaces()){
            tempModel.setNamespace(ns.getPrefix(), ns.getName());
        }
    }

    public void addOntologyAndSchemes(Model origModel, Model tempModel){

        String dpvOntology = "dpv";
        String conceptScheme = "ConceptScheme";

        for (Statement st : origModel){

            IRI subject = (IRI) st.getSubject();
            Value object = st.getObject();

            if (subject.getLocalName().equals(dpvOntology)){
                tempModel.add(st);
            }
            if (object.isIRI() && ((IRI)object).getLocalName().equals(conceptScheme)){
                tempModel.add(st);
            }
        }
    }

// This code is meant for the "add Term" method.
/*
        for (Statement st : origModel){
        IRI subject = (IRI) st.getSubject();
        IRI predicate = st.getPredicate();
        Value object = st.getObject();
        if (subject.getLocalName().equals("dpv")){
            tempModel.add(st);
        }
    }
*/

}
