package week2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

	private static final String FILE_NAME = "_32387ba40b36359a38625cbb397eee65_QuickSort.txt";
	
	
	
	public void readFile(int[] array){
		
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILE_NAME).getFile());

		try (Scanner scanner = new Scanner(file)) {

			int i = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				int num = Integer.parseInt(line);
				array[i] = num;
				i++;
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
