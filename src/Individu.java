import java.util.Date;

public class Individu {

	private String _nom;
	private String _prenom;
	private String _ddn;
	private String _numSS;
	private DTest _testDiabete;
	
	public Individu(String nom, String prenom, String ddn, String numSS){
		this._nom = nom;
		this._prenom = prenom;
		this._ddn = ddn;
		this._numSS = numSS;
		this._testDiabete = null;
	}

	public String get_nom() {
		return _nom;
	}

	public String get_prenom() {
		return _prenom;
	}

	public String get_ddn() {
		return _ddn;
	}

	public String get_numSS() {
		return _numSS;
	}

	public void set_nom(String _nom) {
		this._nom = _nom;
	}

	public void set_prenom(String _prenom) {
		this._prenom = _prenom;
	}

	public void set_ddn(String _ddn) {
		this._ddn = _ddn;
	}

	public void set_numSS(String _numSS) {
		this._numSS = _numSS;
	}

	public DTest get_testDiabete() {
		return _testDiabete;
	}

	public void set_testDiabete(DTest _testDiabete) {
		this._testDiabete = _testDiabete;
	}
	
	public String toString(){
		return "\n" + "Individu : " + "\n" + "[Nom] - " + this._nom + "\n" + "[Prenom] - " + this._prenom + "\n" + "[SS] - " + this._numSS + "\n" + "[Date] - " + this._ddn + "\n" ;
	}
	
	public boolean individuDepiste(){
		return !(this._testDiabete == null);
	}
	
	
}
