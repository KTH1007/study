package chap02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExampleP1 {
	// 문자열 배열을 텍스트 파일에 저장
	public static void writeToFile(String[] arr, String filePath) throws IOException {
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)))) {
			for (int i = 0; i < arr.length; i++) {
				out.write(arr[i]);
			}
		}
	}

	// 텍스트 파일의 내용을 읽어서 화면에 출력
	public static String readFile(String filePath) throws IOException {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath)))) {
			StringBuilder builder = new StringBuilder();
			char[] a = new char[1024];
			while (true) {
				int count = reader.read(a);
				if (count == -1)
					break;
				builder.append(a, 0, count);
			}
			return builder.toString();
		}
	}
	//Person 객체를 파일에 저장하기	
	public static void writeToFile(Person person, String filePath) throws IOException, FileNotFoundException{
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))){
			out.writeObject(person);
		}
	}
	//파일에서 person 객체를 읽어서 화면에 출력하기
	public static Person readFromFile(String filepath) throws FileNotFoundException, IOException, ClassNotFoundException{
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath))){
			return (Person)in.readObject();
		}
	}
	
	//파일 복사하기
	public static void fileCopy(String sourceFile, String targetFile) throws IOException{
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)))){
			while(true) {
				int b = in.read();
				if(b == -1) break;
				out.write(b);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException {
		String[] arr = { "123", "abc", "qwe" };
		writeToFile(arr, "c:/temp/p.txt");
		
		System.out.println(readFile("c:/temp/p.txt"));
		
		Date birthday = new GregorianCalendar(1998, 8 - 1, 15).getTime();
		Person p1 = new Person(3, "홍길동", birthday);
		writeToFile(p1, "c:/temp/a.dat");
		
		Person p2 = readFromFile("c:/temp/a.dat");
		System.out.println(p1);
		System.out.println(p2);
		
		fileCopy("c:/temp/a.txt", "c:/temp/aa.txt");
	}
}
