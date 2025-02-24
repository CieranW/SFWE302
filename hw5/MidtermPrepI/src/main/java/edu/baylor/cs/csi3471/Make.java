package edu.baylor.cs.csi3471;

import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class Make {
	private static int idCounter = 1;
	private final int makeID;
	private final String makeName;
	private Set<ModelSettings> modelSettingSet;

	public Make(String[] line) {
		// TODO generate using eclipse/intellij source code autogenerator
		this.makeID = idCounter++;
		this.makeName = line[6];
		this.modelSettingSet = new HashSet<>();
		this.modelSettingSet.add(new ModelSettings(line));
	}

	public int getMakeID() {
		return makeID;
	}

	public String getMakeName() {
		return makeName;
	}

	public Set<ModelSettings> getModelSettingSet() {
		return modelSettingSet;
	}

	public void setModelSettingSet(Set<ModelSettings> modelSettingSet) {
		this.modelSettingSet = modelSettingSet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(modelSettingSet);
	}

	@Override
	public String toString() {
		// TODO generate using eclipse/intellij source code autogenerator
		List<ModelSettings> sortedModels = new ArrayList<>(modelSettingSet);
		sortedModels.sort(Comparator.comparing(ModelSettings::getModelName).thenComparing(ModelSettings::getYear));

		StringBuilder sb = new StringBuilder();
		sb.append(makeName).append(":\n");
		for (ModelSettings model : sortedModels) {
			sb.append("\t").append(model).append("\n");
		}

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO generate using eclipse/intellij source code autogenerator
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Make other = (Make) obj;
		return Objects.equals(makeName, other.makeName);
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
