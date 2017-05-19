package com.hasanmen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.StringTokenizer;

/**
 * Bu class postfix ifadeleri asm ciktisina cevirmek icin kullanilacak.
 * Clasimizda convert islemleri icin $t0 - $t8 arasinda registerlerimiz var.
 *
 * Created by hmenn on 3/20/16.
 */
public class Assembler {
	private HMQueue<String> postFixExpressions; // postfixler satir satir depolanacak
	private HMStack<Register> availableRegisters; // kullanilabilecek registerler
	private ArrayList<Register> usedRegisters; // kullanilan registerler
	private HMStack<Operand> operand; // operand stacki
	private StringBuilder asmExpression; // asm stringimiz

	public static class NotEnoughResources
			extends Exception {
		NotEnoughResources(String message) {
			super(message);
		}
	}


	/**
	 * Assembler no parameter constructor
	 * Default olarak $t0-$t8 arasinda register olusturulur.
	 */
	public Assembler() {
		postFixExpressions = new HMQueue<>();
		availableRegisters = new HMStack<>();
		usedRegisters = new ArrayList<>();
		asmExpression = new StringBuilder();
		operand = new HMStack<>();

		for (int i = 8; i >= 0; --i) {
			availableRegisters.push(new Register(i));
		}

	}

	/**
	 * String olarak gelen postfix expressionlarini
	 * @param expression postfix expression
	 */
	public void addExpression(String expression) {
		postFixExpressions.offer(expression);
	}

	/**
	 * Bu method genel olarak gorevi yuklenip asm kodu uretecek.
	 * Queue den postfix ifadeler cekilir. Operator gorulene kadar operand klasina ifadeleri ekler.
	 * Operator gorulunce operandan 2 tane ifade cekilip islemler yapilir. Sonuc temsili olarak operand
	 * stackine eklenir. Operator bitene kadar devam eder.
	 * @return asm kodunu string olarak return eder
	 */
	public String createASM()  {

		String token;
		Register lRegister; // left register
		Register rRegister; // rigth register
		Operand operand1; // left operand
		Operand operand2; // rigth operand
		try {
			// tum postfixleri tara
			while (!postFixExpressions.isEmpty()) {
				asmExpression.append("\n");
				StringTokenizer st = new StringTokenizer(postFixExpressions.poll());
				while (st.hasMoreTokens()) {
					// stringi parcala
					token = st.nextToken();
					// print operatoru icin cok farklı davranacak
					if (token.equals("print")) {
						operand1 = new Operand(st.nextToken());
						print(operand1);
						break;
					}
					// operator ise 2tane operand cek isleme koy sonucu operand stackıne koy
					if (Operator.isOperator(token)) {
						operand2 = operand.pop();
						operand1 = operand.pop();
						evaluate(new Operator(token), operand1, operand2);
					} else {
						Operand newOperand = new Operand(token);
						operand.push(newOperand);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return asmExpression.toString();
	}

	/**
	 * BU METHOD PRIVATE DIR. TEST YAPMAK AMACIYLA PACKAGE LEVEL YAPILDI
	 * Bu method assembly print komutunu yazmak icin kullanilir.
	 * a0 ve v0 registerlerini olsuturup uzerlerinde islem yapar.
	 * @param operand1 print edilecek deger/value
	 */
	 void print(Operand operand1) {
		Register reg = getRegister(operand1); //operanda ait registeri al
		if (reg == null) {
			reg = createRegister(operand1); // yoksa olustur yani bir tam sayi olabilir operand
		}
		ARegister a0 = new ARegister(0, new Operand("a0"));
		a0.setValue(new Operand("a0")); // a0 registeri
		asmExpression.append(a0.moveRegister(reg));
		VRegister v0 = new VRegister(0, new Operand("v0"));
		v0.setValue(new Operand("v0")); // v0 registeri
		asmExpression.append(v0.liRegister(1)); // v0 listele
		asmExpression.append("syscall"); // printi cagir
	}


	/*BU METHOD PRIVATE DIR. TEST YAPMAK AMACIYLA PACKAGE LEVEL YAPILDI*/

	/**
	 *
	 * MEHHOD NASIL CALISIR : Kendisine gelen operatorun turune gore kisimlara ayrilir.
	 * Gelen operandlardan rightop ve leftopun sayi veya degisken olma durumunu inceler
	 * OPeratorler yani o registerler daha onceden kullanilmissa bulunur ve onun uzerine
	 * islem yapilir. Eger yoksa yeni register olusturulup ona islem yapilir.
	 * Yapilan ismler neticesinde asm kodlari parca parca olusturulup izi surulur ve
	 * asm stringine yazilir.
	 *
	 * @param operator operator
	 * @param leftOp sol operand
	 * @param rightOp sag operand
	 * @throws Exception olasi hatalar
	 */
	 void evaluate(Operator operator, Operand leftOp, Operand rightOp) throws Exception {

		Register lRegister = null;
		Register rRegister = null;
		Register resultRegister = null;

		try {
			if (operator.getOperator().equals("=")) {
				// ilk operand sayi olamaz -> sayiya atama olmaz
				if (Operand.isNumber(leftOp))
					throw new Exception("Left operand of assign can not be a number!!!");
				else {
					// sayi degilse registerdir -> eger kullanılan registerlerden degilse olustur
					lRegister = getRegister(leftOp);
					if (null == lRegister)
						lRegister = createRegister(leftOp);
				}
				// 2.operand sayi ise direk atamasini yap
				if (Operand.isNumber(rightOp)) {
					int newValue = Integer.parseInt(rightOp.getOperandData());
					asmExpression.append(lRegister.liRegister(newValue));
				} else {
					// sayi degilse register OLABILIR
					rRegister = getRegister(rightOp);
					if (null == rRegister) // kullanılan registerlerde yoksa exception at hatalı girdi vardir
						throw new Exception("Can not found operand : " + rightOp);
					asmExpression.append(lRegister.moveRegister(rRegister));

					if (lRegister.getTempStatus()) { // temp register varsa hemen sil
						freeRegister(lRegister);
					}

					if (rRegister.getTempStatus()) { //temp register varsa hemen sil
						freeRegister(rRegister);
					}
				}

				// 4 islem varsa
			} else if (operator.getOperator().equals("-") || operator.getOperator().equals("+") ||
					operator.getOperator().equals("*") || operator.getOperator().equals("/")) {
				//System.out.println("LEFT :" + leftOp + " Right :" + rightOp);
				//System.out.println(usedRegisters);
				// sayi ise direk register al kullan ve gecici olarak isaretleki islemden sonra silinsin
				if (Operand.isNumber(leftOp)) {
					int newValue = Integer.parseInt(leftOp.getOperandData());
					lRegister = createRegister(leftOp);
					lRegister.setTempStatus(true);
					asmExpression.append(lRegister.liRegister(newValue));
				} else {
					// degilse registeri bul ve kullan
					lRegister = getRegister(leftOp);
					if (null == lRegister)
						throw new Exception("Left operand of operator is Illegal operand");
				}
				// sag operand sayi ise direk gecici registere at kullan
				if (Operand.isNumber(rightOp)) {
					int newValue = Integer.parseInt(rightOp.getOperandData());
					rRegister = createRegister(rightOp);
					rRegister.setTempStatus(true);
					asmExpression.append(rRegister.liRegister(newValue));
				} else {
					// sag operandin registerini bul ve kullan
					rRegister = getRegister(rightOp);
					if (null == rRegister)
						throw new Exception("Right operand of operator is Illegal operand");
				}
				// temp registerleri sil
				if (rRegister.getTempStatus()) {
					rRegister = freeRegister(rRegister);
				}
				if (lRegister.getTempStatus()) {
					lRegister = freeRegister(lRegister);
				}

				// sonucu yazmamiz, stacke tekrardan koymamiz gerekirse temp(hmenn) operandi olusturup
				// stacke eklerim. daha sonra registerinide olsuturur gerekli islemleri yaptiririm.
				resultRegister = createRegister(new Operand("hmenn")); // temp register yaptım
				resultRegister.setTempStatus(true);
				operand.push(new Operand("hmenn"));
				// sayi olan operandlara artık gerek kalmadıgından silebilirim
				if (operator.getOperator().equals("-"))
					asmExpression.append(resultRegister.subRegister(lRegister, rRegister));
				else if (operator.getOperator().equals("+"))
					asmExpression.append(resultRegister.addRegister(lRegister, rRegister));
				else if (operator.getOperator().equals("*")) {
					asmExpression.append(lRegister.multRegister(rRegister));
					asmExpression.append(resultRegister.mfloRegister());
				} else {
					if(rRegister.getValue().getOperandData().equals("0"))
						throw new Exception("Division by zero exception!!!");
					asmExpression.append(lRegister.divRegister(rRegister));
					asmExpression.append(resultRegister.mfloRegister());
				}
				// print varsa print
			}
			// 8taneden fazla register kullanimi olursa exceptin firlat
		}catch (EmptyStackException e){
			throw new NotEnoughResources("There is not enough register to continue converting.");
		}
	}

	/*BU METHOD PRIVATE DIR. TEST YAPMAK AMACIYLA PACKAGE LEVEL YAPILDI*/
	/**
	 * Bu method gecici registeri used register icinden bulup hemen free eder ki daha sonra kullanilabilsin.
	 * @param rg silinecek register
	 * @return silinen register
	 */
	 Register freeRegister(Register rg) {
		int index = usedRegisters.indexOf(rg);
		Register temp = usedRegisters.remove(index);
		availableRegisters.push(temp);
		return temp;
	}


	/*BU METHOD PRIVATE DIR. TEST YAPMAK AMACIYLA PACKAGE LEVEL YAPILDI*/
	/**
	 * Bu method kendisine verile operanda ait registeri kullanilan registerler arasinda bulup return eder
	 * rgister yok ise null return edecektir.
	 * @param operand registerin datasi
	 * @return bulunan registeri return eder
	 */
	 Register getRegister(Operand operand) {
		Register rg = new Register(operand);
		int index = usedRegisters.indexOf(rg);
		if (-1 == index) {
			return null;
		} else {
			rg = usedRegisters.get(index);
		}

		return rg;
	}

	/*BU METHOD PRIVATE DIR. TEST YAPMAK AMACIYLA PACKAGE LEVEL YAPILDI*/
	/**
	 * Bu method istenilen operanda sahip yeni bir register ayarlar. Eger kullanilacak register kalmadiysa exception
	 * firlatir.
	 * @param operand registerin yeni operandi
	 * @return ayarlanan register
	 * @throws EmptyStackException kullanilacak register kalmadigina dair hata
	 */
	 Register createRegister(Operand operand) throws EmptyStackException{
		if(availableRegisters.empty())
			throw new EmptyStackException();
		Register rg = availableRegisters.pop();
		rg.setValue(operand);
		usedRegisters.add(rg);
		return rg;
	}

	public String getASMExpression(){
		return asmExpression.toString();
	}
}
