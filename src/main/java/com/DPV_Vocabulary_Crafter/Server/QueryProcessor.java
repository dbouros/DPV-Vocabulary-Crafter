package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;

public class QueryProcessor {

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

        // Beautify each Statement according to Object's 'type'.
        if(object.isLiteral()){
            return subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((Literal)object).getLabel() + "\n";
        } else if (object.isIRI()) {
            return subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + ((IRI)object).getLocalName() + "\n";
        }else {
            return subject.getLocalName() + " -->> " + predicate.getLocalName()  + " -->> " + object + "\n";
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

    public String searchById(Model dpvModel, String term, String predicate, Integer id){

        // This method returns 5 'search' methods of type 'String' and those 5 methods all return the
        // method 'view' with the following Model variable 'searchModel' as parameter.

        Model searchModel = new LinkedHashModel();
        // Adding the namespaces from the dpvModel(Original, Personal) to the temporary "searchModel".
        // Note: This is basically the 'initializeEmptyDPV' method from class "VocabularyManipulation".
        for (Namespace ns : dpvModel.getNamespaces()){
            searchModel.setNamespace(ns.getPrefix(), ns.getName());
        }

        if (id.equals(0)){
            // Search Single Term - Subject Match-up. (term = 'subject')
            return searchSingleTerm(dpvModel, searchModel, term);
        } else if (id.equals(1)) {
            // Search All Terms - Subject Inclusion. (term = 'subject')
            return searchAllTermsSubject(dpvModel, searchModel, term);
        } else if (id.equals(2)) {
            // Search All Terms - Predicate Match-up. (term = 'predicate')
            return searchAllTermsPredicate(dpvModel, searchModel, term);
        } else if (id.equals(3)) {
            // Search All Terms - Object Match-up. (term = 'object')
            return searchAllTermsObject(dpvModel, searchModel, term);
        }else {
            // Search All Terms - Subject Inclusion and Predicate Match-up. (term = 'subject', predicate = 'predicate')
            return searchAllTermsSubjectPredicate(dpvModel, searchModel, term, predicate);
        }

    }

    public String searchSingleTerm(Model dpvModel, Model searchModel, String term){

        if (isDPVSubject(dpvModel, term)){
            for (Statement st : dpvModel){
                IRI subject = (IRI) st.getSubject();

                if (subject.getLocalName().equals(term)){
                    searchModel.add(st);
                }
            }

            return view(searchModel);
        }else {
            return "Error: No subject '" + term + "' found in model!";
        }
    }

    public String searchAllTermsSubject(Model dpvModel, Model searchModel, String term){
        boolean subjectContainsTerm = false;

        for (Statement st : dpvModel){
            IRI subject = (IRI) st.getSubject();

            if (subject.getLocalName().contains(term)){
                subjectContainsTerm = true;
                searchModel.add(st);
            }
        }

        if (!subjectContainsTerm || searchModel.isEmpty()){
            return "Error: No subject that contains '" + term + "' found in model!";
        }else {
            return view(searchModel);
        }

    }

    public String searchAllTermsPredicate(Model dpvModel, Model searchModel, String term){

        if (isDPVPredicate(dpvModel, term)){
            for (Statement st : dpvModel){
                IRI predicate = st.getPredicate();

                if (predicate.getLocalName().equals(term)){
                    searchModel.add(st);
                }
            }

            return view(searchModel);
        }else {
            return "Error: No predicate '" + term + "' found in model!";
        }

    }

    public String searchAllTermsObject(Model dpvModel, Model searchModel, String term){

        if (isDPVObject(dpvModel, term)){
            for (Statement st : dpvModel){
                Value object = st.getObject();

                if ((object.isLiteral() && ((Literal)object).getLabel().equals(term)) ||
                        (object.isIRI() && ((IRI)object).getLocalName().equals(term)) ||
                        object.stringValue().equals(term)){

                    searchModel.add(st);
                }
            }

            return view(searchModel);
        }else {
            return "Error: No object '" + term + "' found in model!";
        }
    }

    public String searchAllTermsSubjectPredicate(Model dpvModel, Model searchModel, String term, String predicate){

        if (isDPVPredicate(dpvModel, predicate)){

            boolean subjectContainsTerm = false;

            for (Statement st : dpvModel){
                IRI subjectIRI = (IRI) st.getSubject();
                IRI predicateIRI = st.getPredicate();

                if (subjectIRI.getLocalName().contains(term) && predicateIRI.getLocalName().equals(predicate)){
                    subjectContainsTerm = true;
                    searchModel.add(st);
                }
            }

            if (!subjectContainsTerm || searchModel.isEmpty()){
                return "Error: No subject that contains '" + term + "' found in model!";
            }else {
                return view(searchModel);
            }

        }else {
            return "Error: No predicate '" + predicate + "' found in model!";
        }

    }

    public boolean isDPVSubject(Model dpvModel, String subject){

        boolean isTerm = false;
        for (Statement st : dpvModel){
            IRI subjectIRI = (IRI) st.getSubject();
            if (subjectIRI.getLocalName().equals(subject)){
                isTerm = true;
                break;
            }
        }
        return isTerm;
    }

    public boolean isDPVPredicate(Model dpvModel, String predicate){

        boolean isPredicate = false;
        for (Statement st : dpvModel){
            IRI predicateIRI = st.getPredicate();
            if (predicateIRI.getLocalName().equals(predicate)){
                isPredicate = true;
                break;
            }
        }
        return isPredicate;
    }

    public boolean isDPVObject(Model dpvModel, String object){

        boolean isObject = false;
        for (Statement st : dpvModel){
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
