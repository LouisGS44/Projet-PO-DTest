import java.util.Date;

public class DTest {

	// Liste des sexes
	public enum Sexe {
		Homme,
		Femme
	}
	
	// Liste des fréquences pour les légumes
	public enum Frequence {
		Jamais,
		Tt_Jours,
		Pas_tt_Jours		
	}
	
	private int _age;
	private Sexe _sexe;
	private int _tourTaille;
	private boolean _actPhysique;
	private boolean _traitementHTA;
	private boolean _ATCDDiabete;
	private double _taille;
	private int _poids;
	private boolean _ATCDglycemie;
	private Frequence _frequenceLegumes;
	private Date _date;

	// Constructeur
	public DTest(int _age, Sexe _sexe, int _tourTaille, boolean _actPhysique, boolean _traitementHTA,
			boolean _ATCDDiabete, double _taille, int _poids, boolean _ATCDglycemie, Frequence _frequenceLegumes) {
		
		this._age = _age;
		this._sexe = _sexe;
		this._tourTaille = _tourTaille;
		this._actPhysique = _actPhysique;
		this._traitementHTA = _traitementHTA;
		this._ATCDDiabete = _ATCDDiabete;
		this._taille = _taille;
		this._poids = _poids;
		this._ATCDglycemie = _ATCDglycemie;
		this._frequenceLegumes = _frequenceLegumes;
		this._date = new Date();
	}
	
	// Constructeur pour calcul d'IMC
	public DTest(double taille, int poids){
		this._taille = taille;
		this._poids = poids;
	}
	
	// Fonction de calcul d'IMC
	public double calculIMC(){
		return (this._poids)/(this._taille*this._taille);
	}
	
	// Réalise le test pour l’évaluation du risque de développer un diabète de type
	public int realiserDTest(){
		int somme = 0;
		
		
		if(this._age >= 45 && this._age <= 54){
			somme += 2;
		}
		if(this._age >= 55 && this._age <= 64){
			somme += 3;
		}
		if(this._age > 64){
			somme += 4;
		}
		
		
		if(this._sexe == Sexe.Homme){
			if(this._tourTaille >= 94 && this._tourTaille <= 102){
				somme += 3;
			}
			if(this._tourTaille > 102){
				somme += 4;
			}
		} else {
			if(this._tourTaille >= 80 && this._tourTaille <= 88){
				somme += 3;
			}
			if(this._tourTaille > 88){
				somme += 4;
			}
		}
			
		
		if(!this._actPhysique){
			somme += 2;
		}
		
		if(this._traitementHTA){
			somme += 2;
		}
		
		if(this._ATCDDiabete){
			somme += 3;
		}
		
		if(this._ATCDglycemie){
			somme += 5;
		}
			
		if(this._frequenceLegumes == Frequence.Jamais){
			somme += 2;
		}
		if(this._frequenceLegumes == Frequence.Pas_tt_Jours){
			somme += 1;
		}
		
		double monIMC = calculIMC();
		
		if(monIMC >= 25 && monIMC <= 30){
			somme += 1;
		} else {
			if(monIMC > 30){
				somme += 3;
			}
		}
		
		return somme;
	}
	
	public int get_age() {
		return _age;
	}

	public Sexe get_sexe() {
		return _sexe;
	}

	public int get_tourTaille() {
		return _tourTaille;
	}

	public boolean is_actPhysique() {
		return _actPhysique;
	}

	public boolean is_traitementHTA() {
		return _traitementHTA;
	}

	public boolean is_ATCDDiabete() {
		return _ATCDDiabete;
	}

	public double get_taille() {
		return _taille;
	}

	public int get_poids() {
		return _poids;
	}

	public boolean is_ATCDglycemie() {
		return _ATCDglycemie;
	}

	public Frequence get_frequenceLegumes() {
		return _frequenceLegumes;
	}

	public void set_age(int _age) {
		this._age = _age;
	}

	public void set_sexe(Sexe _sexe) {
		this._sexe = _sexe;
	}

	public void set_tourTaille(int _tourTaille) {
		this._tourTaille = _tourTaille;
	}

	public void set_actPhysique(boolean _actPhysique) {
		this._actPhysique = _actPhysique;
	}

	public void set_traitementHTA(boolean _traitementHTA) {
		this._traitementHTA = _traitementHTA;
	}

	public void set_ATCDDiabete(boolean _ATCDDiabete) {
		this._ATCDDiabete = _ATCDDiabete;
	}

	public void set_taille(double _taille) {
		this._taille = _taille;
	}

	public void set_poids(int _poids) {
		this._poids = _poids;
	}

	public void set_ATCDglycemie(boolean _ATCDglycemie) {
		this._ATCDglycemie = _ATCDglycemie;
	}

	public void set_frequenceLegumes(Frequence _frequenceLegumes) {
		this._frequenceLegumes = _frequenceLegumes;
	}
	
	public String toString(){
		return "Individu Testé le " + this._date.toGMTString() +" - " +"[ Score : " + this.realiserDTest() + " ]\n";
	}
	
}
