import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.scene.DepthTest;

public class ListePatient {
	
	private static ArrayList<Individu> listePatients = new ArrayList<Individu>();
	
	public static ArrayList<Individu> getListePatients(){
		return listePatients;
	}
	
	public static void getPatientsFromFile() {
		File file = new File("data/bdd.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/bdd.xml"));     
			if (br.readLine() == null) {
			    System.out.println("La fichier de base de données est vide.");
			    return;
			}
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document;
			try {
				
				document = documentBuilder.parse(file);
				NodeList NodelistePatients = document.getElementsByTagName("patients");
				
				for(int i = 0; i < NodelistePatients.getLength(); i++){
					String prenom = document.getElementsByTagName("_prenom").item(i).getTextContent();
					String nom = document.getElementsByTagName("_nom").item(i).getTextContent();
					String date = document.getElementsByTagName("_ddn").item(i).getTextContent();
					String ss = document.getElementsByTagName("_numSS").item(i).getTextContent();
					Individu monIndiv = new Individu(nom,prenom,date,ss);
					if(document.getElementsByTagName("_possedeTest").item(i).getTextContent().equals("true")){
						int age = Integer.parseInt(document.getElementsByTagName("DTest_age").item(i).getTextContent());
						String temp_sexe = document.getElementsByTagName("DTest_sexe").item(i).getTextContent();
						DTest.Sexe sexe;
						if(temp_sexe.equals("Homme")){
							sexe = DTest.Sexe.Homme;
						} else {
							sexe = DTest.Sexe.Femme;
						}
						int tourtaille = Integer.parseInt(document.getElementsByTagName("DTest_tourtaille").item(i).getTextContent());
						boolean actPhysique = document.getElementsByTagName("DTest_actPhysique").item(i).getTextContent().equals("true");
						boolean traitementHTA = document.getElementsByTagName("DTest_HTA").item(i).getTextContent().equals("true");
						boolean atcdDiabete = document.getElementsByTagName("DTest_atcdDiabete").item(i).getTextContent().equals("true");
						double taille = Double.parseDouble(document.getElementsByTagName("DTest_taille").item(i).getTextContent());
						int poids = Integer.parseInt(document.getElementsByTagName("DTest_poids").item(i).getTextContent());
						boolean atcdGlycemie = document.getElementsByTagName("DTest_atcdGlycemie").item(i).getTextContent().equals("true");
						String temp_freq = document.getElementsByTagName("DTest_leg").item(i).getTextContent();
						DTest.Frequence freq = DTest.Frequence.Jamais;
						if(temp_freq.equals("Jamais")){
							freq = DTest.Frequence.Jamais;
						}
						if(temp_freq.equals("Tt_Jours")){
							freq = DTest.Frequence.Tt_Jours;
						}
						if(temp_freq.equals("Pas_tt_Jours")){
							freq = DTest.Frequence.Pas_tt_Jours;
						}
						
						DTest indivTest = new DTest(age, sexe, tourtaille, actPhysique, traitementHTA, atcdDiabete, taille, poids, atcdGlycemie, freq);
						monIndiv.set_testDiabete(indivTest);
						System.out.println(indivTest.toString());
					} else {
						System.out.println("Aucun test recensé pour l'individu : " + ss);
					}
					
					addPatient(monIndiv);
				}
				
			System.out.println(listePatients.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addPatient(Individu i) {
		listePatients.add(i);
	}
	
	public static void removePatient(Individu i) {
		listePatients.remove(i);
	}

	public static String listToString(){
		return listePatients.toString();
	}
	
	public static boolean containsPatient(String numSS){
		try {
			for (Individu individu : listePatients) {
				if(individu.get_numSS().equals(numSS)){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void writeXmlFile() {

	    try {

	        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
	        DocumentBuilder build = dFact.newDocumentBuilder();
	        Document doc = build.newDocument();

	        Element root = doc.createElement("List");
	        doc.appendChild(root);

	        


	        for (Individu indiv : ListePatient.listePatients) {

	        	Element pat = doc.createElement("patients");
		        root.appendChild(pat);
	        	
	            Element name = doc.createElement("_nom");
	            name.appendChild(doc.createTextNode(String.valueOf(indiv.get_nom())));
	            pat.appendChild(name);

	            Element pre = doc.createElement("_prenom");
	            pre.appendChild(doc.createTextNode(String.valueOf(indiv.get_prenom())));
	            pat.appendChild(pre);

	            Element ddn = doc.createElement("_ddn");
	            ddn.appendChild(doc.createTextNode(String.valueOf(indiv.get_ddn())));
	            pat.appendChild(ddn);
	            
	            Element ss = doc.createElement("_numSS");
	            ss.appendChild(doc.createTextNode(String.valueOf(indiv.get_numSS())));
	            pat.appendChild(ss);
	            
	            if(indiv.get_testDiabete() != null){
	            	Element test = doc.createElement("_possedeTest");
		            test.appendChild(doc.createTextNode("true"));
		            pat.appendChild(test);
		            
		            DTest infos = indiv.get_testDiabete();
		            
		            Element age = doc.createElement("DTest_age");
		            age.appendChild(doc.createTextNode(String.valueOf(infos.get_age())));
		            pat.appendChild(age);
		            
		            Element sexe = doc.createElement("DTest_sexe");
		            sexe.appendChild(doc.createTextNode(String.valueOf(infos.get_sexe())));
		            pat.appendChild(sexe);
		            
		            Element tourtaille = doc.createElement("DTest_tourtaille");
		            tourtaille.appendChild(doc.createTextNode(String.valueOf(infos.get_tourTaille())));
		            pat.appendChild(tourtaille);
		            
		            Element actPhysique = doc.createElement("DTest_actPhysique");
		            actPhysique.appendChild(doc.createTextNode(String.valueOf(infos.is_actPhysique())));
		            pat.appendChild(actPhysique);
		            
		            Element traitHTA = doc.createElement("DTest_HTA");
		            traitHTA.appendChild(doc.createTextNode(String.valueOf(infos.is_traitementHTA())));
		            pat.appendChild(traitHTA);
		            
		            Element atcdDiabete = doc.createElement("DTest_atcdDiabete");
		            atcdDiabete.appendChild(doc.createTextNode(String.valueOf(infos.is_ATCDDiabete())));
		            pat.appendChild(atcdDiabete);
	
		            Element taille = doc.createElement("DTest_taille");
		            taille.appendChild(doc.createTextNode(String.valueOf(infos.get_taille())));
		            pat.appendChild(taille);
		            
		            Element poids = doc.createElement("DTest_poids");
		            poids.appendChild(doc.createTextNode(String.valueOf(infos.get_poids())));
		            pat.appendChild(poids);
		            
		            Element atcdGlycemie = doc.createElement("DTest_atcdGlycemie");
		            atcdGlycemie.appendChild(doc.createTextNode(String.valueOf(infos.is_ATCDglycemie())));
		            pat.appendChild(atcdGlycemie);
		            
		            Element leg = doc.createElement("DTest_leg");
		            leg.appendChild(doc.createTextNode(String.valueOf(infos.get_frequenceLegumes())));
		            pat.appendChild(leg);
		            
	            } else {
	            	Element test = doc.createElement("_possedeTest");
		            test.appendChild(doc.createTextNode("false"));
		            pat.appendChild(test);
	            }

	        }

	        // Save the document to the disk file
	        TransformerFactory tranFactory = TransformerFactory.newInstance();
	        Transformer aTransformer = tranFactory.newTransformer();

	        DOMSource source = new DOMSource(doc);
	        try {
	            // location and name of XML file you can change as per need
	            FileWriter fos = new FileWriter("data/bdd.xml");
	            StreamResult result = new StreamResult(fos);
	            aTransformer.transform(source, result);

	        } catch (IOException e) {

	            e.printStackTrace();
	        }

	    } catch (TransformerException ex) {
	        System.out.println("Error outputting document");

	    } catch (ParserConfigurationException ex) {
	        System.out.println("Error building document");
	    }
	}
	
	public static Individu getIndividuFromID(String numSS){
		try {
			for (Individu individu : listePatients) {
				if(individu.get_numSS().equals(numSS)){
					return individu;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void supprPatientFromList(String ID){
		try {
			for (Individu individu : listePatients) {
				if(individu.get_numSS().equals(ID)){
					listePatients.remove(individu);
					return;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void attribuerTestIndividu(String ID, DTest test){
		try {
			for (Individu individu : listePatients) {
				if(individu.get_numSS().equals(ID)){
					individu.set_testDiabete(test);
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur D'attribution");
		}
	}
	
	public static double calculerMoyenne(){
		try {
			if(listePatients.isEmpty()){
				return 0;
			}
			double somme = 0;
			double cpt = 0;
			for (Individu individu : listePatients) {
				if(individu.individuDepiste()){
					DTest test_temp = individu.get_testDiabete();
					somme += test_temp.realiserDTest();
					cpt += 1;
				}
			}
			if(cpt == 0){
				return 0;
			}
			return somme/cpt;
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int calculerMoyenneAge(){
		try {
			if(listePatients.isEmpty()){
				return 0;
			}
			int somme = 0;
			double cpt = 0;
			for (Individu individu : listePatients) {
				if(individu.individuDepiste()){
					DTest test_temp = individu.get_testDiabete();
					somme += test_temp.get_age();
					cpt += 1;
				}
			}
			if(cpt == 0){
				return 0;
			}
			return (int) (somme/cpt);
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String calculerResultatTrancheAge() {
		
		String res = "";
		
		try {
			if(listePatients.isEmpty()){
				return "";
			}
			
			/*for (Individu individu : listePatients) {
				if(individu.individuDepiste()){
					DTest test_temp = individu.get_testDiabete();
					somme += test_temp.get_age();
					cpt += 1;
				}
			}*/
			
			return res;
			
		} catch (Exception e) {
			return "";
		}
		
	}

	public static class Population {
		int nbHomme;
		int nbFemme;

		public Population(){
		    this.nbHomme = 0;
		    this.nbFemme = 0;
        }

        public void increaseH(){
		    this.nbHomme ++;
        }

        public void increaseF(){
		    this.nbFemme++;
        }
	}

	public static Population calculerPopulation(){
	    try{
            Population mapop = new Population();
            for (Individu pat : listePatients) {
                if(pat.get_testDiabete().get_sexe() == DTest.Sexe.Homme){
                    mapop.increaseH();
                } else {
                    mapop.increaseF();
                }
            }
            return mapop;
        } catch (Exception e){
	        return new Population();
        }
	}

}


