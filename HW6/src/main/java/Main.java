import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by hmenn on 4/10/16.
 */
public class Main {

	// Insert solution to programming exercise 1, section 6, chapter 6 here

	private static final int ONE = 1;
	private static final int TEN = 10;
	private static final int HUNDRED = 100;
	private static final int THOUSAND = 1000;
	private static final int TEN_THOUSAND = 10000;
	private static final int HUN_THOUSAND = 100000;
	private static final int MILLION = 1000000;
	private static final int BILLION = 10000000;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		testPart1();

		testPart2();

		testPart3();
	}

	private static void testPart1() {

		System.out.println(" ################## ");
		System.out.println(" HUFFMAN TREE TESTS ");

		HuffmanTree Htree = new HuffmanTree();

		// Create symbol array
		HuffmanTree.HuffData[] symbols = {
				new HuffmanTree.HuffData(186, '_'),
				new HuffmanTree.HuffData(103, 'e'),
				new HuffmanTree.HuffData(80, 't'),
				new HuffmanTree.HuffData(64, 'a'),
				new HuffmanTree.HuffData(63, 'o'),
				new HuffmanTree.HuffData(57, 'i'),
				new HuffmanTree.HuffData(57, 'n'),
				new HuffmanTree.HuffData(51, 's'),
				new HuffmanTree.HuffData(48, 'r'),
				new HuffmanTree.HuffData(47, 'h'),
				new HuffmanTree.HuffData(32, 'd'),
				new HuffmanTree.HuffData(32, 'l'),
				new HuffmanTree.HuffData(23, 'u'),
				new HuffmanTree.HuffData(22, 'c'),
				new HuffmanTree.HuffData(21, 'f'),
				new HuffmanTree.HuffData(20, 'm'),
				new HuffmanTree.HuffData(18, 'w'),
				new HuffmanTree.HuffData(16, 'y'),
				new HuffmanTree.HuffData(15, 'g'),
				new HuffmanTree.HuffData(15, 'p'),
				new HuffmanTree.HuffData(13, 'b'),
				new HuffmanTree.HuffData(8, 'v'),
				new HuffmanTree.HuffData(5, 'k'),
				new HuffmanTree.HuffData(1, 'j'),
				new HuffmanTree.HuffData(1, 'q'),
				new HuffmanTree.HuffData(1, 'x'),
				new HuffmanTree.HuffData(1, 'z')
		};

		// Build hufffman tree
		Htree.buildTree(symbols);


		// Print huffman codes of the symbols
		Htree.toString();

		//System.out.println(Htree.huffTree);

		// Decode huffman codes to symbols
		System.out.println("Test1_1 : ");
		String code = "11000010011111110010100001";
		String decodedCode = Htree.decode(code);
		System.out.println("Code to Message : " + code + " : \t" + decodedCode);

		//to show encode method parameters work, used another object
		HuffmanTree test = new HuffmanTree();
		String pass = "q__rg";
		System.out.println("Encode    " + pass + " : " + test.encode(pass, Htree.huffTree));


		// Decode huffman codes to symbols
		System.out.println("Test1_2 : ");
		code = "000111001001001110111";
		decodedCode = Htree.decode(code);
		System.out.println("Code to Message : " + code + " : \t" + decodedCode);

		test = new HuffmanTree();
		pass = "hmenn";
		System.out.println("Encode    " + pass + " : " + test.encode(pass, Htree.huffTree));

		System.out.println("Test1_3 : ");
		test = new HuffmanTree();
		pass = "necmeddin";
		System.out.println("Encode    " + pass + " : " + test.encode(pass, Htree.huffTree));

		code = "011101000000110010010101111011101100111";
		decodedCode = Htree.decode(code);
		System.out.println("Code to Message : " + code + " : \t" + decodedCode);

		System.out.println(" END OF HUFFMAN TREE TESTS ");
		System.out.println(" ######################### ");


	}

	/**
	 * This method tests binary search tree iterator.
	 */
	private static void testPart2() {

		System.out.println(" #################################");
		System.out.println(" BINARY SEARCH TREE ITERATOR TESTS");
		BinarySearchTree<Integer> Btree = new BinarySearchTree<>();
		Random rander = new Random();

		// add random numbers
		for (int i = 0; i < 10; ++i) {
			Btree.add(rander.nextInt(20));
		}

		System.out.println(Btree.toString());

		Iterator<Integer> itr = Btree.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " -> ");
		}

		System.out.println("\n END OF BINARY SEARCH TREE ITERATOR TESTS");
		System.out.println(" ########################################");
	}

	private static void testPart3() {

		System.out.println(" ############################ ");
		System.out.println(" PART3 - PRIORITY QUEUE TESTS ");

		long startTime;
		Random rander = new Random();

		// arraylist priority queue test


		System.out.println("\nARRAYLIST PRIORITY QUEUE TESTS");
		ArrayList<Long> arrayPQTime = new ArrayList<>();
		ArrayList<Long> arrayDqPQTime = new ArrayList<>();
		HMArrayListPQ<Integer> apq = new HMArrayListPQ<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer integer, Integer t1) {
				return integer - t1;
			}
		});
		startTime = System.currentTimeMillis();
		for (int i = 1; i <= 10000000; ++i) {
			if (i == ONE || i == TEN || i == HUNDRED || i == THOUSAND || i == TEN_THOUSAND ||
					i == HUN_THOUSAND || i == BILLION || i == MILLION)
				arrayPQTime.add(System.currentTimeMillis() - startTime);
			apq.enqueue(rander.nextInt(10000000));
		}


		startTime = System.currentTimeMillis();
		for (int i = 1; i <= apq.size(); ++i) {
			if (i == ONE || i == TEN || i == HUNDRED || i == THOUSAND || i == TEN_THOUSAND ||
					i == HUN_THOUSAND || i == BILLION || i == MILLION)
				arrayDqPQTime.add(System.currentTimeMillis() - startTime);
			apq.dequeue();
		}

		System.out.println(" ## Test informations : [1,10,100,1000,100000,1000000,10000000] ##");
		System.out.println("Array Enqueue Times : " + arrayPQTime);
		System.out.println("Array Dequeue Times: " + arrayDqPQTime);


		ArrayList<Long> vectorPQTime = new ArrayList<>();
		ArrayList<Long> vectorDqPQTime = new ArrayList<>();
		HMVectorPQ<Integer> vpq = new HMVectorPQ<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer integer, Integer t1) {
				return integer - t1;
			}
		});

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= 10000000; ++i) {
			if (i == ONE || i == TEN || i == HUNDRED || i == THOUSAND || i == TEN_THOUSAND ||
					i == HUN_THOUSAND || i == BILLION || i == MILLION)
				vectorPQTime.add(System.currentTimeMillis() - startTime);
			vpq.enqueue(rander.nextInt(10000000));
		}

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= vpq.size(); ++i) {
			if (i == ONE || i == TEN || i == HUNDRED || i == THOUSAND || i == TEN_THOUSAND ||
					i == HUN_THOUSAND || i == BILLION || i == MILLION)
				vectorDqPQTime.add(System.currentTimeMillis() - startTime);
			vpq.dequeue();
		}

		System.out.println("Vector Enqueue : " + vectorPQTime);
		System.out.println("Vector Dequeue : " + vectorDqPQTime);


		ArrayList<Long> linkedDqPQTime = new ArrayList<>();
		ArrayList<Long> linkedPQTime = new ArrayList<>();
		HMLinkedListPQ<Integer> lpq = new HMLinkedListPQ<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer integer, Integer t1) {
				return integer - t1;
			}
		});
		startTime = System.currentTimeMillis();
		for (int i = 1; i <= TEN_THOUSAND; ++i) {
			if (i == ONE || i == TEN || i == HUNDRED || i == THOUSAND || i == TEN_THOUSAND ||
					i == HUN_THOUSAND || i == BILLION || i == MILLION)
				linkedPQTime.add(System.currentTimeMillis() - startTime);
			lpq.enqueue(rander.nextInt(100000));
			//System.out.println(i);
		}

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= lpq.size(); ++i) {
			if (i == ONE || i == TEN || i == HUNDRED || i == THOUSAND || i == TEN_THOUSAND ||
					i == HUN_THOUSAND || i == BILLION || i == MILLION)
				linkedDqPQTime.add(System.currentTimeMillis() - startTime);
			lpq.dequeue();
		}
		System.out.println("LinkedList Enqueue : " + linkedPQTime);
		System.out.println("LinkedList Dequeue : " + linkedDqPQTime);


		ArrayList<Long> binaryDqPQTime = new ArrayList<>();
		ArrayList<Long> binaryPQTime = new ArrayList<>();
		HMBinaryPQ<Integer> bpq = new HMBinaryPQ<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer integer, Integer t1) {
				return integer - t1;
			}
		});

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= HUNDRED; ++i) {
			//System.out.println(i);
			if (i == ONE || i == TEN || i == HUNDRED || i == THOUSAND || i == TEN_THOUSAND || i == HUN_THOUSAND || i == BILLION || i == MILLION)
				binaryPQTime.add(System.currentTimeMillis() - startTime);
			bpq.enqueue(rander.nextInt(MILLION));
		}
		System.out.println("BinarySearchTree Enqueue : " + binaryPQTime);

	}

}

