package com.file.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import com.file.download.*;

public class FileProcess {

	public static void main(String args[]) throws IOException {
		String fileName = FileDownloadProcess.downloadFile();
		BufferedReader br = new BufferedReader(new FileReader(fileName
				+ "/DownloadedFile.txt"));
		StringTokenizer parser = null;
		Map<String, String> header = new HashMap<String, String>();
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		header.put("word", "count");
		String currentLine;
		Integer lineCount = 0;
		while ((currentLine = br.readLine()) != null) {
			lineCount = lineCount + 1;
			// currentLine.replaceAll(".*)(", "");
			parser = new StringTokenizer(currentLine, " \t\n\r\f.,;:)(*-!?'\"");
			while (parser.hasMoreTokens()) {
				String currentWord = parser.nextToken();
				if (wordCount.containsKey(currentWord)) {
					int count = wordCount.get(currentWord).intValue();
					wordCount.put(currentWord, new Integer(count + 1));
				} else {
					wordCount.put(currentWord, new Integer(1));
				}

			}

		}
		File csvOutputFile = new File(fileName + "/ProcessedFile.csv");
		String DELIMITER = ",";
		FileWriter fw = new FileWriter(csvOutputFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		for (Entry<String, String> headerRow : header.entrySet()) {
			if (!csvOutputFile.exists()) {
				csvOutputFile.createNewFile();
			}

			bw.write(headerRow.getKey() + DELIMITER + headerRow.getValue());
			bw.newLine();

		}
		for (Entry<String, Integer> entry : wordCount.entrySet()) {
			if (!csvOutputFile.exists()) {
				csvOutputFile.createNewFile();
			}

			bw.write(entry.getKey() + DELIMITER + entry.getValue());
			bw.newLine();

		}
		bw.close();
		System.out.println("File processing complete!");
		System.out.println("Total Number of lines in the text file downloaded : " + lineCount);
		System.out.println("CSV file generated at : " + csvOutputFile);

	}

}
