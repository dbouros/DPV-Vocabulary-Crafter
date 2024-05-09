package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;

public class QueryProcessor {

    private final VocabularyManipulation vocabularyManipulation;

    public QueryProcessor(){
        this.vocabularyManipulation = new VocabularyManipulation();
    }

    public String view(Model model){

        if (model.isEmpty()){
            // Needs to be removed at the end, so it's not printed on the "Server" console.
            System.out.println("The requested model is empty!");
            return "";
        }else{

            String modelString = "";
            String previousSubject = "";

            for (Statement st : model) {

                // Add an empty line "\n" between each new term.
                if (((IRI) st.getSubject()).getLocalName().equals(previousSubject)){
                    modelString += modifyStatement(st);
                }else{
                    modelString += "\n" + modifyStatement(st);
                }
                // Each time the previous "subject" differs from the current one, a new line "\n" is added.
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

    public String search(Model origModel, Model tempModel, Integer voc_id, String term, String predicate, Integer id){

        if (origModel.isEmpty()){
            return "Error: Original DPV model is empty";
        } else if (tempModel.isEmpty()) {
            return "Error: Personal DPV model is empty";
        }

        if (voc_id.equals(0)){
            return searchById(origModel, term, predicate, id);
        }else {
            return searchById(tempModel, term, predicate, id);
        }
    }

    public String searchById(Model model, String term, String predicate, Integer id){

        // TODO: Validate input for the variables (term, predicate, object), inside of each of the
        //  "id" case's respective methods!

        Model searchModel = new LinkedHashModel();
        vocabularyManipulation.initializeEmptyDPV(model, searchModel);

        return "";
    }

    public boolean isDPVSubject(Model origModel, String subject){
        boolean isTerm = false;
        for (Statement st : origModel){
            IRI subjectIRI = (IRI) st.getSubject();
            if (subjectIRI.getLocalName().equals(subject)){
             isTerm = true;
             break;
            }
        }
        return isTerm;
    }

    public boolean isDPVPredicate(Model origModel, String predicate){
        // TODO: Requires Testing!!
        boolean isPredicate = false;
        for (Statement st : origModel){
            IRI predicateIRI = st.getPredicate();
            if (predicateIRI.getLocalName().equals(predicate)){
                isPredicate = true;
                break;
            }
        }
        return isPredicate;
    }

    public boolean isDPVObject(Model origModel, String object){
        // TODO: Requires Testing!!
        boolean isObject = false;
        for (Statement st : origModel){
            Value objectVAL = st.getObject();
            if ((objectVAL.isLiteral() && ((Literal)objectVAL).getLabel().equals(object)) ||
                (objectVAL.isIRI() && ((IRI)objectVAL).getLocalName().equals(object)) ||
                objectVAL.stringValue().equals(object)){
                isObject = true;
            }
        }
        return isObject;
    }

    public boolean subjectExistsInModel(Model tempModel, String term){
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
