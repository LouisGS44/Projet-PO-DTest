import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import com.jgoodies.forms.layout.FormSpecs;

public class Fenetre extends JFrame implements ActionListener{
	private JTextField txtF_Nom;
	private JTextField txtF_prenom;
	private JTextField txtF_ddn;
	private JTextField txtF_SS;
	private Choice choice_Patient;
	private JButton btn_suppr_elem; 
	private JSpinner spn_age;
	
	private Choice choice_Sexe;
	private JSpinner spn_tour_taille;
	private Choice choice_actPhysique;
	private Choice choice_HTA;
	private Choice choice_ATCD_diabete;
	private JSpinner spn_taille;
	private JSpinner spn_poids;
	private Choice choice_ATCD_glycémie;
	private Choice choice_legumes;
	private JLabel lbl_resultDTest;
	
	private JLabel lbl_moyenne;
	
	public Fenetre() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ListePatient.writeXmlFile();
			}
		});
		setResizable(false);
		setSize(new Dimension(700, 609));
		setTitle("Base de données - Patients");
	    JTabbedPane tp = new JTabbedPane();
	    tp.setBounds(0, 0, 700, 587);
	    JPanel onglet_Test_Patient = new JPanel();
	    JPanel onglet_Statistiques = new JPanel();
	    onglet_Statistiques.addComponentListener(new ComponentAdapter() {
	    	@Override
	    	public void componentShown(ComponentEvent e) {
	    		setSize(new Dimension(700, 550));
	    		calculStats();
	    	}
	    });
	    onglet_Test_Patient.addComponentListener(new ComponentAdapter() {
	    	@Override
	    	public void componentShown(ComponentEvent e) {
	    		setSize(new Dimension(700, 600));
	    	}
	    });
	    
	    JPanel onglet_Add_Patient = new JPanel();
	    onglet_Add_Patient.addComponentListener(new ComponentAdapter() {
	    	@Override
	    	public void componentShown(ComponentEvent e) {
	    		setSize(new Dimension(700, 370));
	    	}
	    });
	    tp.add("Ajouter un patient", onglet_Add_Patient);
	    onglet_Add_Patient.setLayout(null);
	    
	    JLabel lbl_nom = new JLabel("Nom : ");
	    lbl_nom.setBounds(122, 54, 48, 16);
	    onglet_Add_Patient.add(lbl_nom);
	    
	    JLabel lblPrnom = new JLabel("Prénom :");
	    lblPrnom.setBounds(122, 96, 60, 16);
	    onglet_Add_Patient.add(lblPrnom);
	    
	    txtF_Nom = new JTextField();
	    txtF_Nom.setBounds(280, 49, 245, 26);
	    onglet_Add_Patient.add(txtF_Nom);
	    txtF_Nom.setColumns(10);
	    
	    txtF_prenom = new JTextField();
	    txtF_prenom.setColumns(10);
	    txtF_prenom.setBounds(280, 91, 245, 26);
	    onglet_Add_Patient.add(txtF_prenom);
	    
	    JLabel lbl_ddn = new JLabel("Date de naissance :");
	    lbl_ddn.setBounds(122, 135, 132, 16);
	    onglet_Add_Patient.add(lbl_ddn);
	    
	    txtF_ddn = new JTextField();
	    txtF_ddn.setColumns(10);
	    txtF_ddn.setBounds(280, 129, 245, 26);
	    onglet_Add_Patient.add(txtF_ddn);
	    
	    JLabel lbl_SS = new JLabel("Numéro SS :");
	    lbl_SS.setBounds(122, 173, 132, 16);
	    onglet_Add_Patient.add(lbl_SS);
	    
	    txtF_SS = new JTextField();
	    txtF_SS.setColumns(10);
	    txtF_SS.setBounds(280, 167, 245, 26);
	    onglet_Add_Patient.add(txtF_SS);
	    
	    JButton btn_Add_Patient = new JButton("Ajouter");
	    btn_Add_Patient.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ajouterPatientBDD();
	    	}
			
	    });
	    btn_Add_Patient.setBounds(280, 245, 117, 29);
	    onglet_Add_Patient.add(btn_Add_Patient);
	    tp.add("Tester un patient", onglet_Test_Patient);
	    tp.add("Statistiques", onglet_Statistiques);
	    onglet_Statistiques.setLayout(null);
	    
	    lbl_moyenne = new JLabel("Resultat moyen :");
	    lbl_moyenne.setBounds(91, 59, 433, 26);
	    onglet_Statistiques.add(lbl_moyenne);
	    onglet_Test_Patient.setLayout(null);
	    
	    choice_Patient = new Choice();
	    
	    choice_Patient.setBounds(282, 10, 344, 39);
	    refreshChoice();
	    
	    onglet_Test_Patient.add(choice_Patient);
	    
	    JLabel lbl_choice = new JLabel("Sélectionner un patient via numéro SS : ");
	    lbl_choice.setBounds(16, 20, 260, 16);
	    onglet_Test_Patient.add(lbl_choice);
	    
	    btn_suppr_elem = new JButton("X");
	    btn_suppr_elem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		supprPatientActuel();
	    	}
	    });
	    btn_suppr_elem.setBounds(632, 15, 41, 29);
	    onglet_Test_Patient.add(btn_suppr_elem);
	    
	    JLabel lbl_sexe = new JLabel("Sexe : ");
	    lbl_sexe.setBounds(210, 83, 46, 16);
	    onglet_Test_Patient.add(lbl_sexe);
	    
	    choice_Sexe = new Choice();
	    choice_Sexe.setBounds(280, 73, 146, 39);
	    choice_Sexe.add("Homme");
	    choice_Sexe.add("Femme");
	    onglet_Test_Patient.add(choice_Sexe);
	    
	    JLabel lbl_age = new JLabel("Age : ");
	    lbl_age.setBounds(210, 111, 46, 16);
	    onglet_Test_Patient.add(lbl_age);
	    
	    SpinnerModel model = new SpinnerNumberModel(30, 0, 120, 1);
	    spn_age = new JSpinner(model);
	    spn_age.setBounds(254, 106, 55, 26);
	    onglet_Test_Patient.add(spn_age);
	    
	    JLabel lbl_tour_taille = new JLabel("Tour de taille :");
	    lbl_tour_taille.setBounds(210, 139, 99, 16);
	    onglet_Test_Patient.add(lbl_tour_taille);
	    
	    SpinnerModel model_taille = new SpinnerNumberModel(30, 0, 250, 1);
	    spn_tour_taille = new JSpinner(model_taille);
	    spn_tour_taille.setBounds(308, 134, 55, 26);
	    onglet_Test_Patient.add(spn_tour_taille);
	    
	    JLabel lbl_actPhysique = new JLabel("Activité physique régulière : ");
	    lbl_actPhysique.setBounds(208, 167, 186, 16);
	    onglet_Test_Patient.add(lbl_actPhysique);
	    
	    choice_actPhysique = new Choice();
	    choice_actPhysique.setBounds(400, 155, 114, 39);
	    choice_actPhysique.add("Oui");
	    choice_actPhysique.add("Non");
	    onglet_Test_Patient.add(choice_actPhysique);
	    
	    JLabel lbl_antiHTA = new JLabel("ATCD traitement anti_HTA : ");
	    lbl_antiHTA.setBounds(210, 195, 186, 16);
	    onglet_Test_Patient.add(lbl_antiHTA);
	    
	    choice_HTA = new Choice();
	    choice_HTA.setBounds(410, 188, 104, 39);
	    choice_HTA.add("Oui");
	    choice_HTA.add("Non");
	    onglet_Test_Patient.add(choice_HTA);
	    
	    JLabel lbl_diabete = new JLabel("Antécédent Diabète : ");
	    lbl_diabete.setBounds(210, 233, 140, 16);
	    onglet_Test_Patient.add(lbl_diabete);
	    
	    choice_ATCD_diabete = new Choice();
	    choice_ATCD_diabete.setBounds(365, 231, 85, 29);
	    choice_ATCD_diabete.add("Oui");
	    choice_ATCD_diabete.add("Non");
	    onglet_Test_Patient.add(choice_ATCD_diabete);
	    
	    JLabel lbl_taille = new JLabel("Taille (m) :");
	    lbl_taille.setBounds(210, 261, 72, 16);
	    onglet_Test_Patient.add(lbl_taille);
	    
	    SpinnerModel model_taille_m = new SpinnerNumberModel(0.5, 0, 2.5, 0.01);
	    spn_taille = new JSpinner(model_taille_m);
	    spn_taille.setBounds(294, 256, 63, 26);
	    onglet_Test_Patient.add(spn_taille);
	    
	    JLabel lbl_poids = new JLabel("Poids : ");
	    lbl_poids.setBounds(210, 292, 61, 16);
	    onglet_Test_Patient.add(lbl_poids);
	    
	    SpinnerModel model_poids = new SpinnerNumberModel(50, 0, 250, 1);
	    spn_poids = new JSpinner(model_poids);
	    spn_poids.setBounds(282, 287, 55, 26);
	    onglet_Test_Patient.add(spn_poids);
	    
	    JLabel lbl_glycemie = new JLabel("Antécédent Glycémie : ");
	    lbl_glycemie.setBounds(210, 320, 153, 16);
	    onglet_Test_Patient.add(lbl_glycemie);
	    
	    choice_ATCD_glycémie = new Choice();
	    choice_ATCD_glycémie.setBounds(365, 310, 99, 39);
	    choice_ATCD_glycémie.add("Oui");
	    choice_ATCD_glycémie.add("Non");
	    onglet_Test_Patient.add(choice_ATCD_glycémie);
	    
	    JLabel lbl_legumes = new JLabel("Mange des légumes : ");
	    lbl_legumes.setBounds(210, 367, 153, 16);
	    onglet_Test_Patient.add(lbl_legumes);
	    
	    choice_legumes = new Choice();
	    choice_legumes.setBounds(365, 355, 226, 46);
	    choice_legumes.add("Jamais");
	    choice_legumes.add("Tous les jours");
	    choice_legumes.add("Pas tous les jours");
	    onglet_Test_Patient.add(choice_legumes);
	    
	    JButton btn_depister = new JButton("Evaluer le risque de diabete");
	    btn_depister.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		depister((int)spn_age.getValue(), choice_Sexe.getSelectedItem(), (int)spn_tour_taille.getValue(), choice_actPhysique.getSelectedItem(), choice_HTA.getSelectedItem(),choice_ATCD_diabete.getSelectedItem(), (double)spn_taille.getValue(), (int)spn_poids.getValue(), choice_ATCD_glycémie.getSelectedItem(), choice_legumes.getSelectedItem());
	    	}
	    });
	    
	    
	    choice_Patient.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent e) {
	    		selectIndividu();
	    	}
	    });
	    
	   
	    getContentPane().setLayout(null);
	    btn_depister.setBounds(267, 407, 197, 39);
	    onglet_Test_Patient.add(btn_depister);
	    
	    lbl_resultDTest = new JLabel("");
	    lbl_resultDTest.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_resultDTest.setBounds(131, 467, 430, 46);
	    onglet_Test_Patient.add(lbl_resultDTest);
	    
	    JButton bt_infos = new JButton("i");
	    bt_infos.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ouvrirInfo();
	    	}
	    });
	    bt_infos.setBounds(463, 407, 30, 39);
	    onglet_Test_Patient.add(bt_infos);
	    getContentPane().add(tp);
	    
	    selectIndividu();
	    
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    //this.setLocationRelativeTo(null);
	    this.setVisible(true);

	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void ajouterPatientBDD() {
		try {
			if(txtF_Nom.getText().equals("") || txtF_prenom.getText().equals("") || txtF_ddn.getText().equals("") || txtF_SS.getText().equals("")){
				//Frame_Error frmError = new Frame_Error("Veuillez remplir tous les champs !", this,"Champs vide(s)");
				msgError("Champs Invalides", "Veuillez remplir tous les champs !");
				return;
			}
			if(ListePatient.containsPatient(txtF_SS.getText())){
				msgError("Individu présent", "Cet individu est déjà présent dans la base");
				return;
			}
			ListePatient.addPatient(new Individu(txtF_Nom.getText(), txtF_prenom.getText(), txtF_ddn.getText(), txtF_SS.getText()));
			System.out.println(ListePatient.listToString());
			clearAjoutPatient();
			refreshChoice();
		} catch (Exception e) {
			Frame_Error frmError = new Frame_Error(e.getMessage(), this, "Erreur - Exception");
		}
		
	}
	
	private void clearAjoutPatient(){
		try {
			txtF_ddn.setText("");
			txtF_Nom.setText("");
			txtF_SS.setText("");
			txtF_prenom.setText("");
		} catch (Exception e) {
			msgError("clearAjoutPatient", e.getMessage());
		}
	}
	
	private void refreshChoice(){
		try {
			choice_Patient.removeAll();
			for (Individu indiv : ListePatient.getListePatients()) {
				choice_Patient.add(indiv.get_numSS() + ": " + indiv.get_nom() + " - " + indiv.get_prenom() + " ("+ indiv.get_ddn()+")");
			}
		} catch (Exception e) {
			msgError("refreshChoice", e.getMessage());
		}
	}
	
	private void selectIndividu(){
		try {
			
			String[] parts = choice_Patient.getSelectedItem().split(":");
			String monID = parts[0];
			System.out.println(parts[0]);
			Individu monindiv = ListePatient.getIndividuFromID(monID.trim());
			System.out.println(monindiv.toString());
			
			if(monindiv.individuDepiste()){
				DTest test_temp = monindiv.get_testDiabete();
				
				lbl_resultDTest.setText(test_temp.toString());
				
				// Homme - Femme
				if(test_temp.get_sexe() == DTest.Sexe.Homme){
					choice_Sexe.select(0);
				} else {
					choice_Sexe.select(1);
				}
				
				spn_age.setValue(test_temp.get_age());
				spn_tour_taille.setValue(test_temp.get_tourTaille());
			
				if(test_temp.is_actPhysique()){
					choice_actPhysique.select(0);
				} else {
					choice_actPhysique.select(1);
				}
				
				if(test_temp.is_traitementHTA()){
					choice_HTA.select(0);
				} else {
					choice_HTA.select(1);
				}
				
				if(test_temp.is_ATCDDiabete()){
					choice_ATCD_diabete.select(0);
				} else {
					choice_ATCD_diabete.select(1);
				}
				
				spn_taille.setValue(test_temp.get_taille());
				
				spn_poids.setValue(test_temp.get_poids());
				
				if(test_temp.is_ATCDglycemie()){
					choice_ATCD_glycémie.select(0);
				} else {
					choice_ATCD_glycémie.select(1);
				}
				
				if(test_temp.get_frequenceLegumes() == DTest.Frequence.Jamais){
					choice_legumes.select(0);
				}
				if(test_temp.get_frequenceLegumes() == DTest.Frequence.Tt_Jours){
					choice_legumes.select(1);
				}
				if(test_temp.get_frequenceLegumes() == DTest.Frequence.Pas_tt_Jours){
					choice_legumes.select(2);
				}
				
			} else {
				lbl_resultDTest.setText("Aucun test recensé pour cet individu.");
			}
			
			
			
		} catch (Exception ex) {
			msgError("selectIndividu", ex.getMessage());
		}
	}
	
	private void calculStats(){
		try {
			StringBuilder res = new StringBuilder();
			res.append("<html>");
			
			res.append("Resultat moyen : " + ListePatient.calculerMoyenne());
			res.append("<br>");
			res.append("Resultat moyen : " + ListePatient.calculerMoyenne());
			res.append("<br>");
			res.append("Nombre d'homme :");
			res.append("<br>");
			res.append("	- " + ListePatient.calculerPopulation().nbHomme);
			res.append("<br>");
			res.append("Nombre de femme :");
			res.append("<br>");
			res.append("	- " + ListePatient.calculerPopulation().nbFemme);
			res.append("</html>");
			lbl_moyenne.setText(res.toString());
		} catch (Exception e) {
			msgError("calculStats", e.getMessage());
		}
	}
	
	private void supprPatientActuel(){
		try {
			String[] parts = choice_Patient.getSelectedItem().split(":");
			System.out.println(parts.length);
			if(parts.length == 0){
				return;
			}
			ListePatient.supprPatientFromList(parts[0]);
			refreshChoice();
		} catch (Exception e) {
			msgError("supprPatientActuel", e.getMessage());
		}
	}
	
	private void depister(int age, String sexe, int tourtaille, String actPhysique, String HTA,String ATCDDiabete, double taille, int poids, String ATCDGlycemie, String legumes){
		try {
			DTest.Sexe i_sexe;
			if(sexe.equals("Homme")){
				i_sexe = DTest.Sexe.Homme;
			} else {
				i_sexe = DTest.Sexe.Femme;
			}
			
		
			boolean actPhys = false;
			if(actPhysique.equals("Oui")){
				actPhys = true;
			}
			
			boolean traitementHTA = false;
			if(HTA.equals("Oui")){
				traitementHTA = true;
			}
			
			boolean bool_atcdDiabete = false;
			if(ATCDDiabete.equals("Oui")){
				bool_atcdDiabete = true;
			}
			
			boolean bool_atcdGlycemie = false;
			if(ATCDGlycemie.equals("Oui")){
				bool_atcdGlycemie = true;
			}
			
			DTest.Frequence leg = null;
			if(legumes.equals("Jamais")){
				leg = DTest.Frequence.Jamais;
			}
			if(legumes.equals("Tous les jours")){
				leg = DTest.Frequence.Tt_Jours;
			}
			if(legumes.equals("Pas tous les jours")){
				leg = DTest.Frequence.Pas_tt_Jours;
			}
			
			DTest _leTest = new DTest(age, i_sexe, tourtaille, actPhys, traitementHTA, bool_atcdDiabete, taille, poids, bool_atcdGlycemie,leg);
			
			System.out.println(_leTest.realiserDTest());
			
			String[] parts = choice_Patient.getSelectedItem().split(":");
			
			if(parts.length == 0){
				return;
			}
			
			String monID = parts[0];
			
			ListePatient.attribuerTestIndividu(monID, _leTest);
			
			lbl_resultDTest.setText(_leTest.toString());
			
		} catch (Exception e) {
			msgError("depister", e.getMessage());
		}
	}
	
	private void msgError(String titre, String msg){
		try {
			JOptionPane d = new JOptionPane();
			d.showMessageDialog(this, msg, titre,JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void ouvrirInfo(){
		try {
			
			String[] parts = choice_Patient.getSelectedItem().split(":");
			if(parts.length == 0){
				Frame_Info mesInfos = new Frame_Info(0);
				return;
			}
			String monID = parts[0];
			System.out.println(parts[0]);
			Individu monindiv = ListePatient.getIndividuFromID(monID.trim());
			System.out.println(monindiv.toString());
			
			if(monindiv.individuDepiste()){
				DTest test_temp = monindiv.get_testDiabete();
				Frame_Info mesInfos = new Frame_Info(test_temp.realiserDTest());
			} else {
				Frame_Info mesInfos = new Frame_Info(0);
			}
			
			
		} catch (Exception e) {
			msgError("ouvrirInfo", e.getMessage());
		}
	}
}