package Items;

import java.time.LocalTime;

public class Stanka {
	
	private String stanicaString;
	private LocalTime time;
	
	
	public Stanka(String stanicaString, LocalTime time) {
		super();
		this.stanicaString = stanicaString;
		this.time = time;
	}

	public String getStanicaString() {
		return stanicaString;
	}

	public void setStanicaString(String stanicaString) {
		this.stanicaString = stanicaString;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
}
