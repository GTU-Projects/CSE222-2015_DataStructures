import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HuffmanTree implements Serializable {

    public static class HuffData implements Serializable {
        private double weight;
        private Character symbol;

        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

		@Override
		public String toString() {
			return "HuffData{" +
					"weight=" + weight +
					", symbol=" + symbol +
					'}';
			//return ""+symbol;
		}

		public Character getSymbol() {return symbol;}

		/**
		 * This method compares just huffman characters.
		 * @param o other character
		 * @return result of compraration, true if equal, otherwise false
		 */
		@Override
		public boolean equals(Object o) {
			if (this == o) return true; // check references

			if (o == null) return false;

			Character huffDataCh = (Character) o;

			return symbol != null ? symbol.equals(huffDataCh) : huffDataCh == null;
		}

    }

    protected BinaryTree<HuffData> huffTree;

	public static final String LEFT_WAY ="0"; // goto left tree
	public static final String RIGHT_WAY ="1"; // goto rigth tree

	/**
	 * This class will use for compare huffman datas.
	 */
    private static class CompareHuffmanTrees
            implements Comparator<BinaryTree<HuffData>> {
        @Override
        public int compare(BinaryTree<HuffData> treeLeft,
                BinaryTree<HuffData> treeRight) {
            double wLeft = treeLeft.getData().weight;
            double wRight = treeRight.getData().weight;
            return Double.compare(wLeft, wRight);
        }
    }

    /**
     * Builds the Huffman tree using the given alphabet and weights.
     * huffTree contains a reference to the Huffman tree.
     * @param symbols An array of HuffData objects
     */
    public void buildTree(HuffData[] symbols) {
        Queue<BinaryTree<HuffData>> theQueue =
                new PriorityQueue<BinaryTree<HuffData>>(symbols.length,
                new CompareHuffmanTrees());
        // Load the queue with the leaves.
        for (HuffData nextSymbol : symbols) {
            BinaryTree<HuffData> aBinaryTree =
                    new BinaryTree<HuffData>(nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        // Build the tree.
        while (theQueue.size() > 1) {
            BinaryTree<HuffData> left = theQueue.poll();
            BinaryTree<HuffData> right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            HuffData sum = new HuffData(wl + wr, null);
            BinaryTree<HuffData> newTree =
                    new BinaryTree<HuffData>(sum, left, right);
            theQueue.offer(newTree);
        }

        // The queue should now contain only one item.
        huffTree = theQueue.poll();
    }

    /**
     * Outputs the resulting code.
     * @param out A PrintStream to write the output to
     * @param code The code up to this node
     * @param tree The current node in the tree
     */
    private void printCode(PrintStream out, String code,BinaryTree<HuffData> tree) {
        HuffData theData = tree.getData();
        if (theData.symbol != null) {
            if (theData.symbol.equals(' ')) {
                out.println("space: " + code);
            } else {
                out.println(theData.symbol + ": " + code);
            }
        } else {
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }

    /**
     * Outputs the resulting code.
     * @param out A PrintStream to write the output to
     */
    public void printCode(PrintStream out) {
        printCode(out, "", huffTree);
    }

    /*<listing chapter="6" number="11">*/
    /**
     * Method to decode a message that is input as a string of
     * digit characters '0' and '1'.
     * @param codedMessage The input message as a String of
     *        zeros and ones.
     * @return The decoded message as a String
     */
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffData> currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            } else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        return result.toString();
    }
    
    /**
     * Print codes and their wiegths as string
    */
    public String toString(){
        
        String encodedCodes = "";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        this.printCode(ps);
        //Htree.printCode(ps);
        
        try {
            encodedCodes = os.toString("UTF8");
            System.out.println("Encoded Codes : \n"+encodedCodes);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HuffmanTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return encodedCodes;
    }

	/**
	 * This wrapper method encodes binary messages.
	 * @param message message to encode
	 * @param huffTree if tree is no initialized, will initialize
	 * @return encode value of message
	 */
	public String encode(String message,BinaryTree huffTree){
		// if tree not initialized
		if(this.huffTree == null){
			if(huffTree == null)
				return null;
			this.huffTree = huffTree; // initialize it
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0;i<message.length();++i){
			sb.append(encodeBinary(message.charAt(i),this.huffTree));
			//sb.append("_");
		}
		return sb.toString();
	}


	/**
	 * This method works recursively, Search character on tree and returns path.
	 * @param ch character to search
	 * @param huffTree tree to search on it
	 * @return path of character as a binarycodes
	 */
	private String encodeBinary(Character ch,BinaryTree huffTree){

		/*System.out.println("Way : +"+way);
		System.out.println("Tree : "+huffTree);*/

		String str;
		if(huffTree==null || huffTree.root == null)
			return null;

		if(huffTree.getData()!= null)
			if(huffTree.getData().equals(ch)){
				//System.out.println("Found");
				return ""; //if find retun empty string
			}


		//look left way
		str = encodeBinary(ch,huffTree.getLeftSubtree());

		if(str!=null)
			return LEFT_WAY +str;

		//look right way
		str = encodeBinary(ch,huffTree.getRightSubtree());

		if(str!=null)
			return RIGHT_WAY +str;

		return null; // if not found
	}
}
