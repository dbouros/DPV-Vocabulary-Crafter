package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;

public class VocabularyManipulation {

    private final QueryProcessor queryProcessor;

    public VocabularyManipulation(){
        this.queryProcessor = new QueryProcessor();
    }

    public void initializeEmptyDPV(Model origModel, Model tempModel){
        // The xml declaration "<?xml version="1.0" encoding="UTF-8"?>" is added automatically.

        // Adding only the namespaces from the "original DPV" to the "temporary DPV".
        for (Namespace ns : origModel.getNamespaces()){
            tempModel.setNamespace(ns.getPrefix(), ns.getName());
        }
    }

    public String personalModelIsEmpty(Model origModel, Model tempModel, String response){
        response += "Your personal DPV model is empty!\n";

        initializeEmptyDPV(origModel, tempModel);
        response += "Auto-Created new model: New empty personal DPV.\n";

        addOntologyAndSchemes(origModel, tempModel);
        response += "Added Ontology Term: 'dpv' + all 'ConceptSchemes'. \n";

        return response;
    }

    public String edit(Model origModel, Model tempModel, String term, Integer id, String response){

        if (tempModel.isEmpty()){
            response += personalModelIsEmpty(origModel, tempModel, response);
        }

        if (id.equals(0)){
            if (queryProcessor.isDPVSubject(origModel, term)){
                if (!queryProcessor.subjectExistsInModel(tempModel, term)){
                    // Launch "add" method here.
                    add(origModel, tempModel, term);
                    response += "Added Term: '" + term + "'\n";
                }else {
                    response += "Error: Term '" + term + "' already exists in model!\n";
                }
            }else {
                response += "Error: Term '" + term + "' is not a DPV term!\n";
            }
        }
        else {
            if (queryProcessor.isDPVSubject(origModel, term)){
                if (queryProcessor.subjectExistsInModel(tempModel, term)){
                    // Launch "remove" method here.
                    remove(origModel, tempModel, term);
                    response += "Removed Term: '" + term + "'\n";
                }else {
                    response += "Error: Term '" + term + "' is not in model!\n";
                }
            }else {
                response += "Error: Term '" + term + "' is not a DPV term!\n";
            }
        }

        return response;
    }

    public void add(Model origModel, Model tempModel, String term){

        for (Statement st : origModel){
            IRI subject = (IRI) st.getSubject();

            if (subject.getLocalName().equals(term)){
                tempModel.add(st);
            }
        }
    }

    public void remove(Model origModel, Model tempModel, String term){

        for (Statement st : origModel){
            IRI subject = (IRI) st.getSubject();

            if (subject.getLocalName().equals(term)){
                tempModel.remove(st);
            }
        }
    }

    public void addOntologyAndSchemes(Model origModel, Model tempModel){

        for (Statement st : origModel){

            IRI subject = (IRI) st.getSubject();
            Value object = st.getObject();

            if (subject.getLocalName().equals("dpv")){
                tempModel.add(st);
            }
            if (object.isIRI() && ((IRI)object).getLocalName().equals("ConceptScheme")){
                tempModel.add(st);
            }
        }
    }

}
