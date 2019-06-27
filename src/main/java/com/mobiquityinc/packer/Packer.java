package com.mobiquityinc.packer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.models.Item;
import com.mobiquityinc.service.Solution;
import com.mobiquityinc.service.SolutionImpl;

public class Packer {
	private final static int MAX_BAGS_WEIGHT = 100;

	/**
	 * @param filePath
	 *            a valid absolute file path on the local filesystem
	 * @return a String containing the solutions (one line for each test case)
	 * @throws APIException
	 */
	public static String pack(String filePath) throws APIException {
		try {
			List<String> fileLines;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
			fileLines = br.lines().parallel().map(Object::toString).collect(Collectors.toList());

			StringBuffer sb = new StringBuffer();

			for (Object line : fileLines) {
				sb.append(Packer.process((String) line)).append("\n");
			}
			br.close();
			return sb.toString();

		} catch (Exception e) {
			throw new APIException(e.getMessage());
		}
	}

	/**
	 * @param line
	 *            a single test case in the [capacity] :
	 *            ([index],[weight],[€value])
	 * @return a comma-separated list of items indexes, or '-' if there's no
	 *         solution
	 * @throws APIException
	 */
	public static String process(String line) throws APIException {

		String[] splitedLines = line.split(" : ");

		if (splitedLines == null) {
			throw new APIException("Invalid input file");
		}
		int capacityOfBag = Integer.valueOf(splitedLines[0]);
		String[] itemsParts = splitedLines[1].split("\\s+");
		List<Item> itemsList = new ArrayList<>();

		if (itemsParts.length > 0) {
			for (String itemString : itemsParts) {
				String[] itemParts = itemString.replace("(", "").replace(")", "").split(",");
				if (itemParts.length == 3) {
					itemsList.add(new Item(Integer.valueOf(itemParts[0]).intValue(), Double.valueOf(itemParts[1]),
							Integer.valueOf(itemParts[2].replace("€", ""))));
				} else {
					itemsList.clear();
					break;
				}
			}
		}

		Solution solution = new SolutionImpl();

		String resultList = solution.solve(itemsList, capacityOfBag, MAX_BAGS_WEIGHT);

		return resultList;
	}
}
