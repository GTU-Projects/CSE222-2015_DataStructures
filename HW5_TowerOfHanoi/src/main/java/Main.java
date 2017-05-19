import java.util.Stack;

/**
 * Created by hmenn on 3/29/16.
 */
public class Main {
	public static void main(String []args){

		System.out.println("\n###### TOWER OF HANOI ######");

		// 3 lu tower of hanoi 3lu test
		HMTower.TowerOfHanoi(4, new Stack<>(), new Stack<>(), new Stack<>());


		System.out.println("\n###### REMOVE TEST ######");

		LinkedListRec<Integer> l = new LinkedListRec<>();
		l.add(2);
		l.add(2);
		l.add(3);
		l.add(3);
		l.add(4);
		l.add(2);
		l.add(1);
		l.add(3);
		l.add(2);
		l.add(2);
		System.out.println("Before remove : ");
		System.out.println(l.toString());

		l.remove(5);
		System.out.println("After remove 5 :");
		System.out.println(l.toString());
		l.remove(2);
		System.out.println("After remove 2 :");
		System.out.println(l.toString());
		l.remove(4);
		System.out.println("After remove 4 :");
		System.out.println(l.toString());
		l.remove(3);
		System.out.println("After remove 3 :");
		System.out.println(l.toString());

		System.out.println("\n###### INTERSECT - UNION - SUBSET TEST ######");

		HMList hml = new HMList();

		hml.addElements(1,3,6,8,8,8,9,11,16);

		hml.addElements(2,1,3,8,10,11,14);

		hml.printList(1);
		hml.printList(2);
		System.out.println("Intersect : "+hml.intersectionOfList());
		System.out.println("Union : "+hml.unionOfLists());
		System.out.println("Subset : "+hml.isSubset());



	}
}
