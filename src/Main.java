import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.logging.*;

public class Main {
	/**
	 * проверь кодировку, все файлы должны быть в UTF-8
	 * Исключения лучше ловить и обрабатывать в тех методах, где они могут возникнуть, нет никакой необходимости пробрасывать их выше
	 * Попробуй переделать используя конструкцию try-with-resources
	 */
	public static void method(String text, ArrayList<String> WordsArgs) throws IOException{
        ArrayList<String> WordsList = new ArrayList<String>();
		Collections.addAll(WordsList, text.split(" "));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");
		FileWriter writer = new FileWriter("output_"+(String)dateFormat.format(new Date())+".txt", false);// нет небходимости кастовать к String
		//FileWriter writer = new FileWriter("output_"+(System.currentTimeMillis())+".txt", false);
		String answer = "Êîëè÷åñòâî âñòðå÷àþùèõñÿ ñëîâ '%s' â òåêñòå ðàâíî %d.%n";// что с кодировкой?
		for(String word: WordsArgs)
			writer.write(String.format(answer, word, Collections.frequency(WordsList, word)));
        writer.flush();
        writer.close();
	}
	
	//если пробрасывается IOException, то нет необходимости пробрасывать и FileNotFoundException, лучше ловить исключения внутри этого метода
	private static String readFileAllLines(String path, String fileName) throws IOException, FileNotFoundException {
		File inputFile = new File(path, fileName);
		Scanner sc = new Scanner(inputFile);
		
		String text = null;
		while (sc.hasNextLine()) {
			text += sc.nextLine() + System.getProperty("line.separator");// переделай на StringBuilder
		}
		sc.close();
		
		return text;
    }

    //куда делась возможность указывать case sensitivity?
	public static void main(String[] args) {
		ArrayList <String> WordsArgs = new ArrayList<String>();
		String text = null;
		
		try {
			text = readFileAllLines(".\\", "input.txt");
			
			Scanner sc = new Scanner(System.in);//необходимо добавить в вывод консоли процесс ввода параметров, через пробел и т.д.
			System.out.print("Input arguments: ");
			Collections.addAll(WordsArgs, sc.nextLine().split(" "));
			
			method(text, WordsArgs);

		} catch (FileNotFoundException e) {//нет необходимости оборачивать в LogRecord
			Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.INFO, "Ôàéë íå íàéäåí!")); //best practise - инициализация логгера один раз на весь класс как константу, надо переделать
		} catch (IOException e) {
			Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.WARNING, "Îøèáêà ââîäà-âûâîäà!"));
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.ALL, "Îøèáêà!")); 
		} finally {
			System.out.println("End program.");
		}

	}

}
