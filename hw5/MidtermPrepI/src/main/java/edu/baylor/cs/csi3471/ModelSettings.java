package edu.baylor.cs.csi3471;

public class ModelSettings {

	public static class MPG {
		// TODO
		@Override
		public String toString() {
			// TODO generate by eclipse/idea following your choice and add the brand name!
			return "MPG []";
		}
	}

	private MPG mpg = null;
	private String foo = null;
	// TODO

	public MPG getMpg() {
		return mpg;
	}

	public void setMpg(MPG mpg) {
		this.mpg = mpg;
	}

	// TODO

	@Override
	public int hashCode() {
		// TODO generate by eclipse/idea following your choice!
		return -1;
	}

	@Override
	public String toString() {
		// TODO generate by eclipse/idea following your choice and add the brand name!
		return "ModelSettings []";
	}

	public ModelSettings(String[] line) {
		// TODO generate by eclipse/idea following your choice!
		// add the logic, this will not work!
		super();
		this.foo = line[100];
		// this.mpg = new MPG(line); do it yourself siilar to what we have here!
	}

	@Override
	public boolean equals(Object obj) {
		// TODO generate by eclipse/idea following your choice!
		return false;
	}

	// TODO

}
