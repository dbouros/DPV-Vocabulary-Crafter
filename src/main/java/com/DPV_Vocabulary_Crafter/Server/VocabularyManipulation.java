package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;

public class VocabularyManipulation {
    public String View(Model model){

        if (model.isEmpty()){
            System.out.println("The requested model is empty!");
            return "";
        }else{

            String modelString = "";

            // Print RDF Model(DPV) to screen.
            for (Statement st : model) {
                modelString += modifyStatement(st);
            }

            return modelString;}
    }
    public String modifyStatement(Statement st){

        IRI subject = (IRI) st.getSubject();
        IRI predicate = st.getPredicate();
        Value object = st.getObject();

        //System.out.println(st.getSubject() + " >> " + st.getPredicate() + " >> " + st.getObject());

        if(object.isLiteral()){
            return subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((Literal)object).getLabel() + "\n";
            //System.out.println(subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((Literal)object).getLabel());
        } else if (object.isIRI()) {
            return subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((IRI)object).getLocalName() + "\n";
            //System.out.println(subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((IRI)object).getLocalName());
        }else {
            return subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + object + "\n";
            //System.out.println(subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + object);
        }

    }

    public void createNewEmptyDPV(Model origModel, Model tempModel){

        // The xml declaration "<?xml version="1.0" encoding="UTF-8"?>" is added automatically.

        // Adding only the namespaces from the "original DPV" to the "temporary DPV".
        for (Namespace ns : origModel.getNamespaces()){
            tempModel.setNamespace(ns.getPrefix(), ns.getName());
        }

    }

// This code is meant for the "add Statement" method.
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
