package edu.baylor.cs.csi3471;

import java.util.Set;

public class Make {

	private Set<ModelSettings> modelSettingSet;

	public Set<ModelSettings> getModelSettingSet() {
		return modelSettingSet;
	}

	public void setModelSettingSet(Set<ModelSettings> modelSettingSet) {
		this.modelSettingSet = modelSettingSet;
	}

	@Override
	public int hashCode() {
		// TODO generate using eclipse/intellij source code autogenerator
		return -1;
	}

	@Override
	public String toString() {
		// TODO generate using eclipse/intellij source code autogenerator
		return "";
	}

	public Make(String[] line) {
		// TODO generate using eclipse/intellij source code autogenerator
		super();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO generate using eclipse/intellij source code autogenerator
		return false;
	}

	// there are 2 options, do this functionality here(its static),
	// or in your tester.java and call this method from the make object that a 
	// line is. I would suggest number 2. 
	public Collection<Make> creatorPattern(String[] line, Collection<Make> makes) {
		if (!modelSettingSet.contains(new ModelSettings(line))) {
			// if the make does not exist then create a new one
		} else {
			// if the make does exist, update its modelSettingSet
		}
		return makes;
	}
}
