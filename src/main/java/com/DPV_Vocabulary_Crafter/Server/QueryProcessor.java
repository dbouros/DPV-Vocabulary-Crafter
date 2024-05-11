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

        // TODO: Validate input for the variables {term(subject, predicate, object), predicate}.

        // TODO: This method will return 5 'search' methods of type 'String' and those 5 methods will
        //  all return the method 'view' with the following Model variable(searchModel) as parameter.

        Model searchModel = new LinkedHashModel();
        // Adding the namespaces from the dpvModel(Original, Personal) to the temporary "searchModel".
        // Note: This is basically the 'initializeEmptyDPV' method from class "VocabularyManipulation".
        for (Namespace ns : dpvModel.getNamespaces()){
            searchModel.setNamespace(ns.getPrefix(), ns.getName());
        }

        if (id.equals(0)){
            // Single Term - Subject Match-up.
            return searchSingleTerm(dpvModel, searchModel, term);
        } else if (id.equals(1)) {
            // All Terms - Subject Inclusion.
            return searchAllTermsSubject(dpvModel, searchModel, term);
        } else if (id.equals(2)) {
            // All Terms - Predicate Match-up.
            return searchAllTermsPredicate(dpvModel, searchModel, term);
        } else if (id.equals(3)) {
            // All Terms - Object Match-up.
            return searchAllTermsObject(dpvModel, searchModel, term);
        }else {
            // TODO: Validate respective inputs and then launch the respective method.
            System.out.println("All Terms - Both Subject Inclusion and Predicate Match-up search here!");
            return "";
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
        boolean result = false;

        for (Statement st : dpvModel){
            IRI subject = (IRI) st.getSubject();

            if (subject.getLocalName().contains(term)){
                searchModel.add(st);
                result = true;
            }
        }

        if (!result || searchModel.isEmpty()){
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
        // TODO: Requires Testing!!
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
