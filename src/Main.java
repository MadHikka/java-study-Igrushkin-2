import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.logging.*;

public class Main {
	
	public static void method(String text, ArrayList<String> WordsArgs) throws IOException{
        ArrayList<String> WordsList = new ArrayList<String>();
		Collections.addAll(WordsList, text.split(" "));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");
		FileWriter writer = new FileWriter("output_"+(String)dateFormat.format(new Date())+".txt", false);
		//FileWriter writer = new FileWriter("output_"+(System.currentTimeMillis())+".txt", false);
		String answer = "Количество встречающихся слов '%s' в тексте равно %d.%n";
		for(String word: WordsArgs)
			writer.write(String.format(answer, word, Collections.frequency(WordsList, word)));
        writer.flush();
        writer.close();
	}
	
	private static String readFileAllLines(String path, String fileName) throws IOException, FileNotFoundException {
		File inputFile = new File(path, fileName);
		Scanner sc = new Scanner(inputFile);
		
		String text = null;
		while (sc.hasNextLine()) {
			text += sc.nextLine() + System.getProperty("line.separator");
		}
		sc.close();
		
		return text;
    }
	
	public static void main(String[] args) {
		ArrayList <String> WordsArgs = new ArrayList<String>();
		String text = null;
		
		try {
			text = readFileAllLines(".\\", "input.txt");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("Input arguments: ");
			Collections.addAll(WordsArgs, sc.nextLine().split(" "));
			
			method(text, WordsArgs);

		} catch (FileNotFoundException e) {
			Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.INFO, "Файл не найден!")); 
		} catch (IOException e) {
			Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.WARNING, "Ошибка ввода-вывода!"));
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.ALL, "Ошибка!")); 
		} finally {
			System.out.println("End program.");
		}

	}

}
