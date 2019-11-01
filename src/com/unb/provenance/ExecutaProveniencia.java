package com.unb.provenance;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unb.pojo.CKAN;
import com.unb.pojo.CSV;
import com.unb.pojo.DW;
import com.unb.pojo.DataPublication;
import com.unb.pojo.DataRequest;
import com.unb.pojo.ETL;
import com.unb.pojo.ExtractionSoftware;
import com.unb.pojo.Proveniencia;
import com.unb.pojo.InformationSystems;
import com.unb.pojo.Proveniencia;
import com.unb.pojo.RDF;
import com.unb.pojo.SemanticIndexing;
import com.unb.pojo.UnBGOLD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class ExecutaProveniencia {
	 
	public enum NodeType implements Label {
		
		Collection, Activity, Agent;
		
	}
	
	public enum RelationType implements RelationshipType {
		
		Used, WasAssociatedWith, WasGeneratedBy, WasDerivedFrom;
		
	}
	

	
	public List<Proveniencia> gerarProveniencia() {
		
		List<Proveniencia> proveniencias = new ArrayList<Proveniencia>();
		
		for(int i = 0; i < 5; i++) {
			Proveniencia proveniencia = new Proveniencia();
			
			ExtractionSoftware extractionSoftware = new ExtractionSoftware();
			extractionSoftware.setName("Extraction Software "+i);
			extractionSoftware.setOrganization("UnB");
			extractionSoftware.setDescription("Data extraction from Graduation Systems");
			extractionSoftware.setOperador("Luiz Martins");
			extractionSoftware.setNotes("Not applicable");
			proveniencia.setExtractionSoftware(extractionSoftware);
			
			
			UnBGOLD unBGOLD = new UnBGOLD();
			unBGOLD.setName("UnBGOLD "+i);
			unBGOLD.setOrganization("UnB");
			unBGOLD.setDescription("Perform semantic indexing of data");
			unBGOLD.setOperator("Luiz Martins");
			unBGOLD.setNotes("Not applicable");
			proveniencia.setUnbGOLD(unBGOLD);
			
			ETL etl = new ETL();
			etl.setName("Enviroment ETL "+i);
			etl.setProgram("Pentaho PDI");
			etl.setVersion("7.1");
			etl.setDescription("Extract, Clean of DB SIGRA");
			etl.setStartAtTime("2018-03-10 20:21:27");
			etl.setEndAtTime("2018-03-10 20:24:29");
			etl.setSoftware("MS SQL Server");
			etl.setNotes("Not applicable");
			proveniencia.setEtl(etl);
			
			
			DataRequest dataRequest = new DataRequest();
			dataRequest.setName("Data Request "+i);
			dataRequest.setProgram("UnBGOLD");
			dataRequest.setVersion("0.9 Beta");
			dataRequest.setDescription("CSV request for indexing");
			dataRequest.setStartAtTime("2018-03-10 20:30:23");
			dataRequest.setEndAtTime("2018-03-10 20:30:38");
			dataRequest.setSoftware("Java");
		
			List<String> requests = new ArrayList<String>();
			requests.add("http://servicos.unb.br/dadosabertos/departamentos");
			requests.add("http://servicos.unb.br/dadosabertos/cursos");
			requests.add("http://servicos.unb.br/dadosabertos/professores");
			requests.add("http://servicos.unb.br/dadosabertos/disciplinas");
			requests.add("http://servicos.unb.br/dadosabertos/oferta");
			requests.add("http://servicos.unb.br/dadosabertos/fluxo");
			dataRequest.setRequest(requests);
			
			proveniencia.setDataRequest(dataRequest);
			
			SemanticIndexing semanticIndexing = new SemanticIndexing();
			semanticIndexing.setName("Semantic Indexing "+i);
			semanticIndexing.setProgram("UnBGOLD");
			semanticIndexing.setVersion("0.9 Beta");
			semanticIndexing.setDescription("Uses CSV for indexing and generates RDF");
			semanticIndexing.setStartAtTime("2018-03-10 20:32:23");
			semanticIndexing.setEndAtTime("2018-03-10 20:32:54");
			semanticIndexing.setSoftware("Application Server");
			
			List<String> controledVocabulary = new ArrayList<String>();
			requests.add("FOAF");
			requests.add("DC");
			requests.add("GCIEO");
			requests.add("LUBM");
			requests.add("AIISO");
			semanticIndexing.setControledVocabulary(controledVocabulary);
			
			proveniencia.setSemanticIndexing(semanticIndexing);
			
			
			DataPublication dataPublication = new DataPublication();
			dataPublication.setName("Data Publication "+i);
			dataPublication.setProgram("CKAN");
			dataPublication.setVersion("0.9 Beta");
			dataPublication.setDescription("Publication in the CKAN instance");
			dataPublication.setStartAtTime("2018-03-10 20:35:20");
			dataPublication.setEndAtTime("2018-03-10 20:36:20");
			dataPublication.setSoftware("CKAN");
			dataPublication.setCkanInstance("Dados Abertos da UnB");
			
			proveniencia.setDataRequest(dataRequest);
			
			InformationSystems informationSystems = new InformationSystems();
			informationSystems.setName("Information Systems "+i);
			informationSystems.setSize("15 GB");
			informationSystems.setDescription("DB of graduation Systems");
			informationSystems.setLocation("SIGRA");
			informationSystems.setNotes("Not Applicable");
			
			proveniencia.setInformationSystems(informationSystems);
			
			
			DW dw = new DW();
			dw.setName("DW "+i);
			dw.setSize("4,2 GB");
			dw.setDescription("DB generated from the ETL process on SIGRA");
			dw.setLocation("MS SQL Server");
			dw.setNotes("Not Applicable");
			
			proveniencia.setDw(dw);
			
			CSV csv = new CSV(); 
			csv.setName("CSV/JSON "+i);
			csv.setSize("112 k");
			csv.setDescription("CSV file extracted");
			csv.setLocation("Application Server");
			csv.setNotes("Not Applicable");
			
			proveniencia.setCsv(csv);

			RDF rdf = new RDF();
			rdf.setName("RDF "+i);
			rdf.setSize("140 k");
			rdf.setDescription("RDF file generated");
			rdf.setLocation("Application Server");
			rdf.setNotes("Not Applicable");
			
			proveniencia.setRdf(rdf);
			
			CKAN ckan = new CKAN();
			ckan.setName("CKAN "+i);
			ckan.setSize("Not Applicable");
			ckan.setDescription("Data published on the plataform");
			ckan.setLocation("CKAN");
			ckan.setNotes("Not Applicable");
	
			proveniencia.setCkan(ckan);

			proveniencias.add(proveniencia);
			
		}
		
		return proveniencias;
	}

	
	public static List<Proveniencia> recuperaDados() {
		
		List<Proveniencia> dados = new ArrayList<Proveniencia>();

		try {
	            String url = "http://localhost:8080/unbgold/rest/publicacao/proveniencia";
	
	            String output = "";
	            
		//########################## COMEÇO DO SCRIPT PARA PEGAR DADOS LOCALMENTE ##############################################
		FileReader arq = new FileReader("/Users/cleytonreis/eclipse-workspace/neo4jDemo/proveniencia.json");
		BufferedReader lerArq = new BufferedReader(arq);
		String linha = lerArq.readLine(); // lê a primeira linha
		// a variável "linha" recebe o valor "null" quando o processo
		// de repetição atingir o final do arquivo texto
		while (linha != null) {
		   output += linha;
		   linha = lerArq.readLine(); // lê da segunda até a última linha
		}
		
		arq.close();
		//########################## FIM DO SCRIPT PARA PEGAR DADOS LOCALMENTE ##############################################
		
		
		Gson gson = new Gson();
		Type provenienciaListType = new TypeToken<ArrayList<Proveniencia>>(){}.getType(); 
		dados = gson.fromJson(new String(output.getBytes()), provenienciaListType);
		
		
		} catch (IOException ex) {   
		   
		}
		
		return dados;
		 
	}	
	
	public static void main(String[] args) { 
		
		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService graphDb = dbFactory.newEmbeddedDatabase(new File("/Users/cleytonreis/neo4j-community-3.5.8/data/graph.db"));		

		List<Proveniencia> dados = recuperaDados();
		
		 try (Transaction tx = graphDb.beginTx()) {

 	        for(Proveniencia pro : dados) {
     			Node extractionSoftware = graphDb.createNode(NodeType.Agent);
     			extractionSoftware.setProperty("Name", pro.getExtractionSoftware().getName());
     			extractionSoftware.setProperty("Organization", pro.getExtractionSoftware().getOrganization());
     			extractionSoftware.setProperty("Description", pro.getExtractionSoftware().getDescription());
     			extractionSoftware.setProperty("Operator", pro.getExtractionSoftware().getOperador());
     			extractionSoftware.setProperty("Notes", pro.getExtractionSoftware().getNotes());	
     			
     			Node unbGOLD = graphDb.createNode(NodeType.Agent);
     			unbGOLD.setProperty("Name", pro.getUnbGOLD().getName());
     			unbGOLD.setProperty("Organization", pro.getUnbGOLD().getOrganization());
     			unbGOLD.setProperty("Description", pro.getUnbGOLD().getDescription());
     			unbGOLD.setProperty("Operator", pro.getUnbGOLD().getOperator());
     			unbGOLD.setProperty("Notes", pro.getUnbGOLD().getNotes());
     			
     			Node etl = graphDb.createNode(NodeType.Activity);
     			etl.setProperty("Name", pro.getEtl().getName());
     			etl.setProperty("Program", pro.getEtl().getProgram());
     			etl.setProperty("Version", pro.getEtl().getVersion());
     			etl.setProperty("Description", pro.getEtl().getDescription());
     			etl.setProperty("StartAtTime", pro.getEtl().getStartAtTime());
     			etl.setProperty("EndAtTime", pro.getEtl().getEndAtTime());
     			etl.setProperty("Software", pro.getEtl().getSoftware());
     			etl.setProperty("Notes", pro.getEtl().getNotes());
     			
     			List<String> request = pro.getDataRequest().getRequest();
     			
     			Node dataRequest = graphDb.createNode(NodeType.Activity);
     			dataRequest.setProperty("Name", pro.getDataRequest().getName());
     			dataRequest.setProperty("Program", pro.getDataRequest().getProgram());
     			dataRequest.setProperty("Version", pro.getDataRequest().getVersion());
     			dataRequest.setProperty("Description", pro.getDataRequest().getDescription());
     			dataRequest.setProperty("StartAtTime", pro.getDataRequest().getStartAtTime());
     			dataRequest.setProperty("EndAtTime", pro.getDataRequest().getEndAtTime());
     			dataRequest.setProperty("Software", pro.getDataRequest().getSoftware());
     			dataRequest.setProperty("Request", request);

     			List<String> controlledVocabulary = pro.getSemanticIndexing().getControledVocabulary();
     			
     			Node semanticIndexing = graphDb.createNode(NodeType.Activity);
     			semanticIndexing.setProperty("Name", pro.getSemanticIndexing().getName());
     			semanticIndexing.setProperty("Program", pro.getSemanticIndexing().getProgram());
     			semanticIndexing.setProperty("Version", pro.getSemanticIndexing().getVersion());
     			semanticIndexing.setProperty("Description", pro.getSemanticIndexing().getDescription());
     			semanticIndexing.setProperty("StartAtTime", pro.getSemanticIndexing().getStartAtTime());
     			semanticIndexing.setProperty("EndAtTime", pro.getSemanticIndexing().getEndAtTime());
     			semanticIndexing.setProperty("Software", pro.getSemanticIndexing().getSoftware());
     			semanticIndexing.setProperty("ControledVocabulary", controlledVocabulary);
     			
     			Node dataPublication = graphDb.createNode(NodeType.Activity);
     			dataPublication.setProperty("Name", pro.getDataPublication().getName());
     			dataPublication.setProperty("Program", pro.getDataPublication().getProgram());
     			dataPublication.setProperty("Version", pro.getDataPublication().getVersion());
     			dataPublication.setProperty("Description", pro.getDataPublication().getDescription());
     			dataPublication.setProperty("StartAtTime", pro.getDataPublication().getStartAtTime());
     			dataPublication.setProperty("EndAtTime", pro.getDataPublication().getEndAtTime());
     			dataPublication.setProperty("Software", pro.getDataPublication().getSoftware());
     			dataPublication.setProperty("CkANInstance", pro.getDataPublication().getCkanInstance());
     			
     			Node informationSystems = graphDb.createNode(NodeType.Collection);
     			informationSystems.setProperty("Name", pro.getInformationSystems().getName());
     			informationSystems.setProperty("Size", pro.getInformationSystems().getSize());
     			informationSystems.setProperty("Description", pro.getInformationSystems().getDescription());
     			informationSystems.setProperty("Location", pro.getInformationSystems().getLocation());
     			informationSystems.setProperty("Notes", pro.getInformationSystems().getNotes());
     			
     			Node dw = graphDb.createNode(NodeType.Collection);
     			dw.setProperty("Name", pro.getDw().getName());
     			dw.setProperty("Size", pro.getDw().getSize());
     			dw.setProperty("Description", pro.getDw().getDescription());
     			dw.setProperty("Location", pro.getDw().getLocation());
     			dw.setProperty("Notes", pro.getDw().getNotes());
     			
     			Node csv = graphDb.createNode(NodeType.Collection);
     			csv.setProperty("Name", pro.getCsv().getName());
     			csv.setProperty("Size", pro.getCsv().getSize());
     			csv.setProperty("Description", pro.getCsv().getDescription());
     			csv.setProperty("Location", pro.getCsv().getLocation());
     			csv.setProperty("Notes", pro.getCsv().getNotes());

     			Node rdf = graphDb.createNode(NodeType.Collection);
     			rdf.setProperty("Name", pro.getRdf().getName());
     			rdf.setProperty("Size", pro.getRdf().getSize());
     			rdf.setProperty("Description", pro.getRdf().getDescription());
     			rdf.setProperty("Location", pro.getRdf().getLocation());
     			rdf.setProperty("Notes", pro.getRdf().getNotes());
     			
     			Node ckan = graphDb.createNode(NodeType.Collection);
     			ckan.setProperty("Name", pro.getCkan().getName());
     			ckan.setProperty("Size", pro.getCkan().getSize());
     			ckan.setProperty("Description", pro.getCkan().getDescription());
     			ckan.setProperty("Location", pro.getCkan().getLocation());
     			ckan.setProperty("Notes", pro.getCkan().getNotes());
     			
     			etl.createRelationshipTo(extractionSoftware, RelationType.WasAssociatedWith);
     			dataRequest.createRelationshipTo(unbGOLD, RelationType.WasAssociatedWith);
     			semanticIndexing.createRelationshipTo(unbGOLD, RelationType.WasAssociatedWith);
     			dataPublication.createRelationshipTo(unbGOLD, RelationType.WasAssociatedWith);
     			
     			etl.createRelationshipTo(informationSystems, RelationType.Used);
     			dataRequest.createRelationshipTo(dw, RelationType.Used);
     			semanticIndexing.createRelationshipTo(csv, RelationType.Used);
     			dataPublication.createRelationshipTo(rdf, RelationType.Used);
     			
     			dw.createRelationshipTo(informationSystems, RelationType.WasDerivedFrom);
     			csv.createRelationshipTo(dw, RelationType.WasDerivedFrom);
     			rdf.createRelationshipTo(csv, RelationType.WasDerivedFrom);
     			ckan.createRelationshipTo(rdf, RelationType.WasDerivedFrom);
     			
     			dw.createRelationshipTo(etl, RelationType.WasGeneratedBy);
     			csv.createRelationshipTo(dataRequest, RelationType.WasGeneratedBy);
     			rdf.createRelationshipTo(semanticIndexing, RelationType.WasGeneratedBy);
     			ckan.createRelationshipTo(dataPublication, RelationType.WasGeneratedBy);

     			System.out.println("Graph created...");

     			tx.success();
     			
         	
         }

         }catch (Exception e) {
 			// TODO: handle exception
 			
 			System.out.println(e.getMessage());
 		}
 		
 		graphDb.shutdown();
 		
}
	
}
