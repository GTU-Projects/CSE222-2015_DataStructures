package com.hasanmen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Bu class dosya yazma islemlerini kolaylastirmak icin kullanilacak.
 * Dosya acma kapama yazma islemleri 1-2 satirda halledilir.
 * Created by hmenn on 3/20/16.
 */
public class HMFileWriter {
	private File file; // dosyamiz
	private FileWriter fw;
	private BufferedWriter bw;

  	// default constructor
	public HMFileWriter(){

	}

	/**
	 * Yazmak icin dosyayi acar.
	 * @param fileName acilacak dosya
	 * @throws IOException dosya olmamasi yada acilamama durumunda exception firlatir.
	 */
	public void openFile(String fileName) throws IOException{
		file = new File(fileName);

		if(!file.exists())
			file.createNewFile();

		fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
	}

	/**
	 * Acilan dosyayı kapatacak metod.
	 * @throws IOException dosya acilmamam durumlari
	 */
	public void closeFile() throws IOException{
		bw.close();
	}

	/**
	 * Bu metod kendisine gelen stringi dosyaya basar.
	 * @param line dosyay basilacak string
	 * @throws IOException acik dosya olmama durumunda exception atar
	 */
	public void writeFile(String line) throws IOException{
		bw.write(line);
	}
}
