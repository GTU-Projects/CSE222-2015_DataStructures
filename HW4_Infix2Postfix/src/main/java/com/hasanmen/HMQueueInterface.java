package com.hasanmen;
/**##############################################*/
/* DERS KITABINDAN ALINMISTIR */
/*##########################################*/
/**
 * Bu interface standart queue klasinin sahip olmasi metodlari icerir.
 * Tum methodlar asbtract olarak yer alir.
 * Created by hmenn on 3/20/16.
 */
public interface HMQueueInterface<E> {
	boolean offer(E item);

	/**
	 * Queue den bir eleman cekerek queue den silinir ve return eder.
	 * @return queunin ilk elemani
	 */
	E poll();

	/**
	 * Queue den eleman cekilir. Silme islemi yapilmaz.
	 * @return queue da ilk eleman return edilir.
	 */
	E peek();

	/**
	 * Queuden eleman cekilir. Queue bos olasi durumunda exception firlatir.
	 * @return ilk elemani return eder
	 */
	E remove();

	/**
	 *	Queue nin ilk elemanina bakilir. Olmamasi durumunda exception firlatir.
	 * @return ilk elemani return eder
	 */
	E element();
}
