package com.hasanmen;

import java.io.*;

/**
 * BU class dosyadan islemleri uzerine odaklanacaktir. Dosya acarak icini okuyup icerigi methodlarla return eder.
 * Kolaylık olsun diye yazildi.
 * Created by hmenn on 3/20/16.
 */
public class HMFileReader  {
	private String line; // okunan satir
	private File file; // dosya
	private FileReader freader;
	private BufferedReader br;

	// default constuctor
	public HMFileReader(){
		line = new String("");
	}

	/**
	 * Bu method parametre olarak gelen dosyayi okuma modunda acar.
	 * @param fileName dosya adi
	 * @throws FileNotFoundException dosya olmadıgında exception firlatir.
	 */
	public void openFile(String fileName) throws FileNotFoundException{

			file = new File(fileName);
			freader = new FileReader(file);
			br = new BufferedReader(freader);
	}

	/**
	 * Bu method ACILMIS dosyadan okuma yapmak icin okuma yapar.
	 * 1 satir okur ve return eder.
	 * @return dosyadan okunan satiri return eder
	 * @throws IOException dosyadan okuma hatalarini firlatir. Dosya acilmamis olabilir.
	 */
	public String readLine() throws IOException{
		if(file == null)
			throw new FileNotFoundException("File didn't opened. Check file open method");
		if(null != ( line = br.readLine()))
			return line;
		return null;
	}

	/**
	 * Bu method acik olan bir dosyayi kapatir. Acilmamis dosya kapatilirsa exception firlatirilir.
	 * @throws IOException acik olmayan dosya kapatilirsa exception firlatir.
	 */
	public void closeFile() throws IOException{
		file =null;
		freader.close();
		br.close();
	}
}
