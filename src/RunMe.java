import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class RunMe {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		EventOrganizer eventOrganizer = new EventOrganizer();
		Scanner in = new Scanner(System.in);
		String inputFileName = null;
		System.out.print("Enter Input File Name: ");
		try {
			inputFileName = in.nextLine();
		} catch (Exception exception) {
			System.out.println("Exception Caught: " + exception.getMessage());
		}
		final long startTime = System.nanoTime();
		in.close();
		PrintWriter writer = new PrintWriter(inputFileName+"_Output.txt", "UTF-8");
		try (BufferedReader br = new BufferedReader(new FileReader("./InputFiles/" + inputFileName + ".txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] lineArray = line.split("\\{");
				String[] inputs = (lineArray[0].split(", "));
				int totalAttendes = Integer.parseInt(inputs[0]);
				int selectedAttendes = Integer.parseInt(inputs[1]);
				String[] inputs2 = lineArray[1].split("\\}");
				String[] xSetString = inputs2[0].split(", ");
				String[] inputs3 = lineArray[2].split("\\}");
				String[] ySetString = inputs3[0].split(", ");
				int[] xValues = stringToIntegerArray(xSetString);
				int[] yValues = stringToIntegerArray(ySetString);
				String result = eventOrganizer.organizeEvent(totalAttendes, selectedAttendes, xValues, yValues);
				writer.println("" + result);
				//System.out.println(result);
			}
		} catch (Exception e) {
			System.out.println("Exception Caught" + e.getMessage());
		}
		writer.close();
		final long endTime = System.nanoTime();
		System.out.println("Program Took: " + ((endTime - startTime) / 1000000) + " milliseconds");
	}

	public static int[] stringToIntegerArray(String[] stringValues) {
		int[] intarray = new int[stringValues.length];
		int i = 0;
		for (String str : stringValues) {
			intarray[i] = Integer.parseInt(str.trim());
			i++;
		}
		return intarray;

	}

}
