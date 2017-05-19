import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by hmenn on 3/29/16.
 */
public class HMList<E> {
	private List<E> list1;
	private List<E> list2;


	// constructor
	public HMList(){
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
	}

	/**
	 * Bu metod listelere eleman eklemek icin yazilmistir.
	 * ... operatoru sayesinde hem istedigimiz kadar hemde generic ekleme yapabiliriz
	 * @param listNum hangi listeye eleman eklenecegi
	 * @param items eklenecek elemanlar
	 */
	public boolean addElements(int listNum,E ... items){

		List<E> ref; // liste icin referans tutalım

		if(listNum == 1)
			ref = list1;
		else if(listNum ==2)
			ref = list2;
		else throw new IllegalArgumentException("Argument ERROR : List num can not be "+listNum);

		if(items == null) // gelen elemanlar null ise false
			return false;

		// ... operatoruyle gelen elemanlari gezelim
		for(E itr : items){
			ref.add(itr);
		}

		return true;
	}

	public void printList(int listNum){

		if(listNum ==1)
			System.out.println("List1 : "+list1);
		else if(listNum == 2)
			System.out.println("List2 : "+list2);
		else throw new IllegalArgumentException("There is not "+listNum+". list");
	}

	/**
	 * Wrapper Recursive Method
	 * Bu method iki listenin kesisim kumesini liste olarak return eder.
	 * @return iki listenin kesisimi
	 */
	public List<E> intersectionOfList(){

		if(list1.isEmpty() || list2.isEmpty())
			return null;
		// size kucuk olandan baslat ki daha kisa surede tamamlansin
		return list1.size() <= list2.size() ? intersect(list1,list2,0) : intersect(list2,list1,0);


	}

	/**
	 * BU METOD NORMALDE PRIVATEDIR
	 * Bu metod 1.listin elemanlarini en sondan gelerek kontrol eder. 2.dede olanlari ve daha once
	 * yazilmayan elemanlari listeye ekler
	 * @param list1
	 * @param list2
	 * @param index
	 * @return
	 */
	 List<E> intersect(List<E> list1, List<E> list2,int index){

		// once listin sonuna kadar git sonra bos bir liste ile geri gel
		if(index == list1.size())
			return new ArrayList<>();
		List<E> temp;

		// tail recursion degil once sona kadar gider
		temp = intersect(list1,list2,index+1);

		//her geri cagirmada kontrol ederek boylece listeye ayni elemanlar eklenmemis oluyor.
		if(list2.contains(list1.get(index)) && !temp.contains(list1.get(index))){
			temp.add(list1.get(index));
		}
		return temp;
	}

	/**
	 * Wrapper metod
	 * Bu method iki tane listenin ortak olmayan elemanlarini return eder
	 * @return ortak olmayan eleman listesi
	 */
	public List<E> unionOfLists(){
		if(list1.isEmpty() || list2.isEmpty())
			return null;
		// size kucuk olandan baslat ki daha kisa surede tamamlansin
		return list1.size() <= list2.size() ? union(list1,list2,0) : union(list2,list1,0);
	}

	/**
	 * BU METOD NORMALDE PRIVATEDIR
	 * Bu metod iki listin ortak olmayan elemanlarini recurisve olarak return eder
	 * @param list1 ilk listemiz
	 * @param list2 ikinci listemiz
	 * @param index ilk listemiz icin index
	 * @return ortak olmayan elemanlarin listesi
	 */
	 List<E> union(List<E> list1,List<E> list2,int index){

		// once listin sonuna kadar git sonra bos bir liste ile geri gel
		if(index == list1.size())
			return new ArrayList<>();
		List<E> temp;

		// tail recursion degil once sona kadar gider
		temp = union(list1,list2,index+1);

		//her geri cagirmada kontrol ederek olmayan elemanalari yeni listeye ekleyip retun et
		if(!list2.contains(list1.get(index)) && !temp.contains(list1.get(index))){
			temp.add(list1.get(index));
		}
		return temp;
	}

	/**
	 * Wrapper
	 * Bu metod 2 list arasinda alt kume durumunu kontrol eder
	 * @return alt kume ise true degilse false
	 */
	public boolean isSubset(){

		if(list1.isEmpty() || list2.isEmpty())
			return false;

		return list1.size() >= list2.size() ? subset(list1,list2,0) : false;

	}

	/**
	 * BU METOD NORMALDE PRIVATEDIR
	 * Bu metod list2 nin list1in alt kumesi olup olmamasini kontrol eder.
	 * @param list1 buyuk kumemiz
	 * @param list2 kucuk kumemeiz
	 * @param index ilk kume icin index
	 * @return alt kumesi ise true, degilse false
	 */
	 boolean subset(List<E> list1,List<E> list2,int index){

		if(index == list2.size())
			return true;

		if(list1.contains(list2.get(index))){ // ilk eleman 1.listte varmi?
			return subset(list1,list2,index+1);
		}else return false; // bir eleman bile olmamasi durumunda alt kume sarti bozulur

	}
}
