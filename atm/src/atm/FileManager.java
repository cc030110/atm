package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private File file;
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	
	private FileManager(){}
	private static FileManager instance = new FileManager();
	public static FileManager getInstance() {
		return instance;
	}
}
