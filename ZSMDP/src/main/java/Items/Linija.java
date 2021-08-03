package Items;

public class Linija {
	
	private Integer brojLinijeInteger;
	private String pocetnaStanicaString;
	private String zadnjaStanicaString;
	private String stankeString;
	
	
	public Linija(Integer brojLinijeInteger, String pocetnaStanicaString, String zadnjaStanicaString,
			String stankeString) {
		super();
		this.brojLinijeInteger = brojLinijeInteger;
		this.pocetnaStanicaString = pocetnaStanicaString;
		this.zadnjaStanicaString = zadnjaStanicaString;
		this.stankeString = stankeString;
	}


	public Integer getBrojLinijeInteger() {
		return brojLinijeInteger;
	}


	public void setBrojLinijeInteger(Integer brojLinijeInteger) {
		this.brojLinijeInteger = brojLinijeInteger;
	}


	public String getPocetnaStanicaString() {
		return pocetnaStanicaString;
	}


	public void setPocetnaStanicaString(String pocetnaStanicaString) {
		this.pocetnaStanicaString = pocetnaStanicaString;
	}


	public String getZadnjaStanicaString() {
		return zadnjaStanicaString;
	}


	public void setZadnjaStanicaString(String zadnjaStanicaString) {
		this.zadnjaStanicaString = zadnjaStanicaString;
	}


	public String getStankeString() {
		return stankeString;
	}


	public void setStankeString(String stankeString) {
		this.stankeString = stankeString;
	}
}
