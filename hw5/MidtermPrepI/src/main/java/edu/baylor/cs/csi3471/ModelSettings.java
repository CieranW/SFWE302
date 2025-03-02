package edu.baylor.cs.csi3471;

import java.util.Objects;

public class ModelSettings {

	public static class MPG {
		// TODO
		private final int cityMPG, highwayMPG, combinedMPG;

		public MPG(String city, String highway, String combined) {
			this.cityMPG = Integer.parseInt(city);
			this.highwayMPG = Integer.parseInt(highway);
			this.combinedMPG = Integer.parseInt(combined);
		}

		public int getCityMPG() { return cityMPG; }

		public int getHighwayMPG() { return highwayMPG; }

		public int getCombinedMPG() { return combinedMPG; }

		@Override
		public String toString() {
			// TODO generate by eclipse/idea following your choice and add the brand name!
			return cityMPG + "/" + combinedMPG + "/" + highwayMPG;
		}
	}

	private static int idCounter = 1;
	private final int modelID;
	private final String modelName, fuelType, transmission, vehicleClass;
	private final int year, cylinders;
	private final double displacement;
	private final MPG mpg;
	private final Make make;

	public int getModelID() { return modelID; }

	public String getModelName() { return modelName; }

	public String getFuelType() { return fuelType; }

	public String getTransmission() { return transmission; }

	public String getVehicleClass() { return vehicleClass; }

	public int getYear() { return year; }

	public int getCylinders() { return cylinders; }

	public double getDisplacement() { return displacement; }

	public MPG getMpg() { return mpg; }

	@Override
	public int hashCode() {
		// TODO generate by eclipse/idea following your choice!
		return Objects.hash(modelName, year, transmission, displacement);
	}

	@Override
	public String toString() {
		// TODO generate by eclipse/idea following your choice and add the brand name!
		return modelName + ", " + year + ", " + cylinders + ", " + transmission + ", " + displacement + "," + vehicleClass + ", " + fuelType + ", " + mpg;
	}

	public ModelSettings(Make make, String[] line) {
		this.modelID = idCounter++;
		this.make = make;
		this.modelName = line[7];
		this.year = Integer.parseInt(line[10]);
		this.cylinders = Integer.parseInt(line[2]);
		this.displacement = Double.parseDouble(line[3]);
		this.fuelType = line[4];
		this.transmission = line[8];
		this.vehicleClass = line[9];
		this.mpg = new MPG(line[0], line[5], line[1]);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO generate by eclipse/idea following your choice!
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		ModelSettings other = (ModelSettings) obj;
		return year == other.year &&  
			   cylinders == other.cylinders &&
			   Objects.equals(modelName, other.modelName) &&
			   Objects.equals(transmission, other.transmission) &&
			   Objects.equals(displacement, other.displacement) &&
			   Objects.equals(vehicleClass, other.vehicleClass) &&
			   Objects.equals(fuelType, other.fuelType);
	}

	// TODO

}
