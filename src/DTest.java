import java.util.Date;


/**
 * Un DTest permet de realiser un dépistage du diabète pour un patient donné.
 */
public class DTest {

	/**
	 * Enumeration et typage des sexes.
	 */
	public enum Sexe {
		Homme,
		Femme
	}

	/**
	 * Enumeration et typage des differentes frequences.
	 */
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

	/**
	 * @param _age Age du patient
	 * @param _sexe Sexe du patient
	 * @param _tourTaille Tour de taille du patient
	 * @param _actPhysique Pratique d une activite physique
	 * @param _traitementHTA Traitement Hyper Tension Arterielle
	 * @param _ATCDDiabete Antecedent de Diabete
	 * @param _taille Taille du patient
	 * @param _poids Poids du patient
	 * @param _ATCDglycemie Antecedent de Glycemie
	 * @param _frequenceLegumes Frequence de legumes
	 */
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


	/**
	 * Constructeur pour calcul d'IMC
	 * @param taille taille pour test
	 * @param poids poids pour test
	 */
	public DTest(double taille, int poids){
		this._taille = taille;
		this._poids = poids;
	}


	/**
	 * Fonction de calcul d'IMC
	 * @return L'imc du patient du Test.
	 */
	public double calculIMC(){
		return (this._poids)/(this._taille*this._taille);
	}


	/**
	 * Realise le test pour l’evaluation du risque de developper un diabete de type
	 * @return Le resultat du test de depistage.
	 */
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

	/**
	 * @return L'age du patient du test
	 */
	public int get_age() {
		return _age;
	}

	/**
	 * @return Le sexe du patient du test
	 */
	public Sexe get_sexe() {
		return _sexe;
	}

	/**
	 * @return Le tour de taille du patient du test
	 */
	public int get_tourTaille() {
		return _tourTaille;
	}

	/**
	 * @return La pratique d'activite du patient du test
	 */
	public boolean is_actPhysique() {
		return _actPhysique;
	}

	/**
	 * @return Si le patient du test est sous traitement HTA
	 */
	public boolean is_traitementHTA() {
		return _traitementHTA;
	}

	/**
	 * @return Si le patient du test a des antecedents de diabete
	 */
	public boolean is_ATCDDiabete() {
		return _ATCDDiabete;
	}

	/**
	 * @return La taille du patient du test
	 */
	public double get_taille() {
		return _taille;
	}
	/**
	 * @return Le poids du patient du test
	 */
	public int get_poids() {
		return _poids;
	}

	/**
	 * @return Si le patient du test a des antecedents de glycemie
	 */
	public boolean is_ATCDglycemie() {
		return _ATCDglycemie;
	}

	/**
	 * @return La frequence de consommation de legumes patient du test
	 */
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


	/**
	 * @return Le resultat du test du patient
	 */
	public String toString(){
		return "Individu Testé le " + this._date.toGMTString() +" - " +"[ Score : " + this.realiserDTest() + " ]\n";
	}
	
}
