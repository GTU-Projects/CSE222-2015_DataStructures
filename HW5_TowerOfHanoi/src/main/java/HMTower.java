import java.util.Stack;

/**
 * REFERENCE  : http://www.geeksforgeeks.org/iterative-tower-of-hanoi/
 *
 *
 * BU CLASS TOWER OF HANOI PROBLEMINI SIMULIZE ETMEK ICIN YAZILMISTIR.
 * BUYUK ORANDA KAYNAK KODDAN YARARLANILMISTIR.
 *
 * Created by hmenn on 3/29/16.
 */
public class HMTower {

	private static class Disk {
		private int size;

		private Disk(int size) {
			this.size = size;
		}

		private int getSize() {
			return size;
		}

		@Override
		public String toString() {
			return "" + size;
		}
	}

	/**
	 * Bu method soruce cubugundan destination cubugune disk tasimasi yapar
	 *
	 * @param diskAmount  kulenin kac tane disk icerecegi
	 * @param source      baslama noktasi
	 * @param destination varis noktasi
	 * @param auxiliary   yardimci kulemiz
	 */
	public static void TowerOfHanoi(int diskAmount, Stack<Disk> source, Stack<Disk> destination, Stack<Disk> auxiliary) {

		int totalMoves; // toplam hamle sayisi

		String src = "Source";
		String dest = "Destination";
		String aux = "Auxiliary";

		//Once stackleri dolduralim
		for (int i = diskAmount; i >= 1; i--)
			source.push(new Disk(i));

		// cift adetli disk olma durumunda yardimci kule ile hedef kuleyi degistir
		if (diskAmount % 2 == 0) {
			String temp = dest;
			dest = aux;
			aux = temp;
		}

		// toplam hareket sayisi 2^n-1
		totalMoves = (int) Math.pow(2, diskAmount) - 1;

		for (int i = 1; i <= totalMoves; i++) {
			if (i % 3 == 0)
				moveToPeg(auxiliary, destination, aux, dest);
			else if (i % 3 == 1)
				moveToPeg(source, destination, src, dest);
			else
				moveToPeg(source, auxiliary, src, aux);
		}
		System.out.println("SOLVED IN " + totalMoves + " MOVEMENT FOR "+diskAmount+" DISKS.");

	}

	/**
	 * Bu method iki cubuk-kule arasinda disk alis verisini saglar.
	 *
	 * @param source disk alinacak cubuk
	 * @param destination diskin varis noktasi
	 * @param src disk cekilecek cubugun string degeri
	 * @param dest disk koyulacak cubugunun varis noktasi
	 */
	public static void moveToPeg(Stack<Disk> source,
								 Stack<Disk> destination, String src, String dest) {

		Disk temp;
		// eger source bos ise destinationdan cektigini yukle
		if (source.isEmpty()) {
			temp = destination.pop();
			source.push(temp);
			System.out.println("Move the disk " + temp + " from " + dest + " peg to " + src + " peg");
		}

		// eger destination bos ise direk sourceden cektigini yukle
		else if (destination.isEmpty()) {
			temp = source.pop();
			destination.push(temp);
			System.out.println("Move the disk " + temp + " from " + src + " peg to " + dest + " peg");
		}

		// sourceden cekilen daha buyuk ise destinationda soruceye aktar
		else if (source.peek().getSize() > destination.peek().getSize()) {
			temp = destination.pop();
			source.push(temp);
			System.out.println("Move the disk " + temp + " from " + dest + " peg to " + src + " peg");
		}

		// kucuk olandan buyuge aktarma yap
		else {
			temp = source.pop();
			destination.push(temp);
			System.out.println("Move the disk " + temp + " from " + src + " peg to " + dest + " peg");
		}
	}


}
