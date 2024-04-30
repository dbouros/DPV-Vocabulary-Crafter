package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;

public class QueryProcessor {
    public String view(Model model){

        if (model.isEmpty()){
            // Needs to be removed at the end, so it's not printed on the "Server" console.
            System.out.println("The requested model is empty!");
            return "";
        }else{

            String modelString = "";

            String previousSubject = "";
            // Print RDF Model(DPV) to screen.
            for (Statement st : model) {

                if (((IRI) st.getSubject()).getLocalName().equals(previousSubject)){
                    modelString += modifyStatement(st);
                }else{
                    modelString += "\n" + modifyStatement(st);
                }
                previousSubject = ((IRI) st.getSubject()).getLocalName();
            }

            return modelString;
        }
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

    public boolean isDPVTerm(Model origModel, String term){
        boolean isTerm = false;
        for (Statement st : origModel){
            IRI subject = (IRI) st.getSubject();
            if (subject.getLocalName().equals(term)){
             isTerm = true;
             break;
            }
        }
        return isTerm;
    }

    public boolean existsInModel(Model tempModel, String term){
        boolean exists = false;
        for (Statement st : tempModel){
            IRI subject = (IRI) st.getSubject();
            if (subject.getLocalName().equals(term)){
                exists = true;
                break;
            }
        }
        return exists;
    }

}
