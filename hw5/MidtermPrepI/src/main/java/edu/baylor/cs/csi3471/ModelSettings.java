package edu.baylor.cs.csi3471;

public class ModelSettings {

	public static class MPG {
		// TODO
		private int city;
		private int highway;
		private int combined;

		public MPG(String[] line) {
			this.city = Integer.parseInt(line[0]);
			this.highway = Integer.parseInt(line[5]);
			this.combined = Integer.parseInt(line[1]);
		}

		public int getCity() {
			return city;
		}

		public void setCity(int city) {
			this.city = city;
		}

		public int getHighway() {
			return highway;
		}

		public void setHighway(int highway) {
			this.highway = highway;
		}

		public int getCombined() {
			return combined;
		}

		public void setCombined(int combined) {
			this.combined = combined;
		}

		@Override
		public String toString() {
			// TODO generate by eclipse/idea following your choice and add the brand name!
			return "MPG [Combined=" + combined + ", City=" + city + ", Highway=" + highway + "]";
		}
	}

	private MPG mpg = null;
	// private String foo = null;
	// TODO
	private int cylinders;
	private double displacement;
	private String fuelType;
	private String make;
	private String modelName;
	private String transmission;
	private String vehicleClass;
	private int year;

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
		return Objects.hash(mpg, cylinders, displacement, fuelType, make, modelName, transmission, vehicleClass, year);
	}

	@Override
	public String toString() {
		// TODO generate by eclipse/idea following your choice and add the brand name!
		return "ModelSettings [" +
				"Make=" + make +
				", ModelName=" + modelName +
				", Year=" + year +
				", MPG=" + mpg.toString() +
				", Cylinders=" + cylinders +
				", Displacement=" + displacement +
				", FuelType=" + fuelType +
				", Transmission=" + transmission +
				", VehicleClass=" + vehicleClass +
				"]";
	}

	public ModelSettings(String[] line) {
		// TODO generate by eclipse/idea following your choice!
		// add the logic, this will not work!
		if (line.length < 11) {
			throw new IllegalArgumentException("Invalid line length");
		}
		super();
		// this.foo = line[100];
		// this.mpg = new MPG(line); do it yourself siilar to what we have here!
		this.mpg = new MPG(line);
		this.cylinders = Integer.parseInt(line[2]);
		this.displacement = Double.parseDouble(line[3]);
		this.fuelType = line[4];
		this.make = line[6];
		this.modelName = line[7];
		this.transmission = line[8];
		this.vehicleClass = line[9];
		this.year = Integer.parseInt(line[10]);
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

	public double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(double displacement) {
		this.displacement = displacement;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO generate by eclipse/idea following your choice!
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		ModelSettings other = (ModelSettings) obj;
		return year == other.year &&  cylinders == other.cylinders && Double.compare(other.displacement, displacement) == 0 && Objects.equals(fuelType, other.fuelType) && Objects.equals(make, other.make) && Objects.equals(modelName, other.modelName) && Objects.equals(transmission, other.transmission) && Objects.equals(vehicleClass, other.vehicleClass) && Objects.equals(mpg, other.mpg);
	}

	// TODO

}
