package edu.baylor.cs.csi3471;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Tester {

	private static final int FILE_NAME = 1;
	private static final int OPTION = 0;

	private static int readOption(String[] args) {
		Integer option = null;
		if (args.length < 2) {
			System.err.println("USAGE: java Tester <filename>");
			System.exit(1);
		} else {
			try {
				option = Integer.parseInt(args[OPTION]);
			} catch (NumberFormatException e) {
				System.err.println("call as java Tester <filename>");
				System.exit(1);
			}
		}
		return option;
	}

	/*
	 * public static Collection<Make> populateSet(Collection<Make> set, String[]
	 * line){ //check the colleciton for existing make }
	 */

	public static Collection<Make> populateSet(Collection<Make> set, String[] line) {
		String makeName = line[6];
		Make make = set.stream().filter(m -> m.getMakeName().equals(makeName)).findFirst().orElseGet(() -> {
			Make newMake = new Make(makeName);
			set.add(newMake);
			return newMake;
		});

		make.getModelSettingSet().add(new ModelSettings(make, line));

		return set;
	}

	private static Collection<Make> loadCSV(String file) throws FileNotFoundException {
		BufferedReader reader = null;
		try {
			// ok, this is much faster than scanner :)
			reader = new BufferedReader(new FileReader(new File(file)));
			Collection<Make> makes = new HashSet<>();
			String line = null;
			reader.readLine(); // skip the header

			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				// just to debug
				System.out.println(split[6] + " : " + split[7]);

				makes = populateSet(makes, split);
			}

			// TODO fix this!
			// return Collections.<Make>emptySet();
			reader.close();
			return makes;
		} catch (IOException e) {
			String hint = "";
			try {
				hint = "Current dir is: " + new File(".").getCanonicalPath();
			} catch (Exception local) {
				hint = local.getLocalizedMessage();
			}
			throw new FileNotFoundException(e.getLocalizedMessage() + "\n" + hint);

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e.getLocalizedMessage());
				}
			}
		}

	}

	public static void main(String[] args) {
		int option = readOption(args);

		Collection<Make> makes = null;
		try {
			makes = loadCSV(args[FILE_NAME]);
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

		switch (option) {
			case 1:
				System.out.println("Total Makes: " + makes.size());
				System.out.println("===============");
				makes.stream()
					.sorted(Comparator.comparing(Make::getMakeName).reversed()) // Sort Z→A
					.forEach(m -> System.out.println(m.getMakeName() + " (" + m.getModelSettingSet().size() + ")"));
				System.out.println("===============");
				makes.stream()
					.sorted(Comparator.comparing(Make::getMakeName).reversed()) // Sort Z→A
					.forEach(m -> System.out.println(m.toString()));
				break;

			case 2:
				if (args.length < 4) {
					System.err.println("Usage: java Tester 2 <filePath> <columnName> <value>");
					System.exit(1);
				}
				String value = args[3].toLowerCase(); // Search value

				makes.stream()
					.filter(m -> m.getMakeName().toLowerCase().contains(value) ||
								m.getModelSettingSet().stream().anyMatch(ms -> ms.getModelName().toLowerCase().contains(value)))
					.sorted(Comparator.comparing(Make::getMakeName)) // Sort A→Z
					.forEach(m -> System.out.println(m.toString()));
				break;

			case 3:
				Map<String, Double> vClassMPG = new HashMap<>();
				Map<String, Integer> vClassCount = new HashMap<>();

				for (Make make : makes) {
					for (ModelSettings model : make.getModelSettingSet()) {
						int combinedMPG = model.getMpg().getCombinedMPG();

						vClassMPG.merge(model.getVehicleClass(), (double) combinedMPG, Double::sum);
						vClassCount.merge(model.getVehicleClass(), 1, Integer::sum);
					}
				}

				vClassMPG.replaceAll((k, v) -> v / vClassCount.get(k)); // Compute average
				vClassMPG.entrySet().stream()
					.sorted(Map.Entry.comparingByKey()) // Sort A→Z
					.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
				break;

			case 4:
				Map<String, Map<Integer, Integer>> makeYearCount = new HashMap<>();

				for (Make make : makes) {
					makeYearCount.putIfAbsent(make.getMakeName(), new HashMap<>());
					for (ModelSettings model : make.getModelSettingSet()) {
						makeYearCount.get(make.getMakeName()).merge(model.getYear(), 1, Integer::sum);
					}
				}

				makeYearCount.entrySet().stream()
					.sorted(Map.Entry.comparingByKey()) // Sort by make name A→Z
					.forEach(entry -> {
						String makeName = entry.getKey();
						entry.getValue().entrySet().stream()
							.sorted(Map.Entry.comparingByKey()) // Sort by year
							.forEach(yearEntry ->
								System.out.println(makeName + ";" + yearEntry.getKey() + ";" + yearEntry.getValue()));
					});
				break;

			default:
				System.err.println("Invalid option. Please use 1, 2, 3, or 4.");
				System.exit(1);
		}
	}
}
