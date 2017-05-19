package Hmenn_Part2;

/**
 * Hash table implementation using open addressing.
 *
 * @author Koffman and Wolfgang
 */

public class HashTableOpen<K, V> {

	// data fields
	private Entry<K, V>[] table;
	private static final int START_CAPACITY = 101;
	private double LOAD_THRESHOLD = 0.75;
	private int numKeys;
	private int numDeletes;
	private int numCollision;
	private final Entry<K, V> DELETED = new Entry<>(null, null);


	// constructor
	public HashTableOpen() {
		this.table = new Entry[START_CAPACITY];
		this.numKeys = 0;
		this.numDeletes = 0;
		this.numCollision = 0;

	}

	/**
	 * Returns number of entries
	 *
	 * @return number of entries
	 */
	public int size() {
		return numKeys;
	}

	/**
	 * Return is map empty
	 *
	 * @return true if empty, otherwise false
	 */
	public boolean isEmpty() {
		return 0 == numKeys;
	}

	/**
	 * Finds either the target key or the first empty slot in the
	 * search chain using linear probing.
	 * pre: The table is not full.
	 *
	 * @param key The key of the target object
	 * @return The position of the target or the first empty slot if
	 * the target is not in the table.
	 */
	public int find(Object key) {

		// calculate the starting index
		int index = key.hashCode() % table.length;

		if (index < 0)
			index += table.length; // make it positive

		// Icrement index until an empty reached or the key is found
		while (table[index] != null && !key.equals(table[index].key)) {
			++index;

			if (index >= table.length)
				index = 0;
			//index = index % table.length;

		}

		return index;
	}


	/**
	 * Method get for class HashtableOpen.
	 *
	 * @param key The key being sought
	 * @return the value associated with this key if found;
	 * otherwise, null
	 */
	public V get(Object key) {

		//find element index in table
		int index = find(key);

		// if in table return value,otherwise null
		return table[index] == null ? null : table[index].value;

	}


	/**
	 * Method put for class HashtableOpen.
	 * post: This key-value pair is inserted in the
	 * table and numKeys is incremented. If the key is already
	 * in the table, its value is changed to the argument
	 * value and numKeys is not changed. If the LOAD_THRESHOLD
	 * is exceeded, the table is expanded.
	 *
	 * @param key   The key of item being inserted
	 * @param value The value for this key
	 * @return Old value associated with this key if found;
	 * otherwise, null
	 */
	public V put(K key, V value) {

		// Find the first table element that is empty
		// or the table element that contains the key.

		int index = find(key);

		if (table[index] != null) {
			this.numCollision+=1;
			//System.out.println("C "+this.numCollision);
		}

		if (table[index] == null) {
			//System.out.println(index);
			table[index] = new Entry<>(key, value);
			++numKeys;


			double loadFactor = (double) (numKeys + numDeletes) / table.length;

			if (loadFactor > LOAD_THRESHOLD)
				rehash();
			return null;
		}


		V oldVal = table[index].value;
		table[index].value = value;
		return oldVal;
	}


	/**
	 * Expands table size when loadFactor exceeds LOAD_THRESHOLD
	 * post: The size of table is doubled and is an odd integer.
	 * Each nondeleted entry from the original table is
	 * reinserted into the expanded table.
	 * The value of numKeys is reset to the number of items
	 * actually inserted; numDeletes is reset to 0.
	 */
	public void rehash() {
		// save a reference to oldTable

		System.out.println("When size : "+this.size()+" Founded  " + this.numCollision + " collusion. Table rehashing...");
		numCollision = 0;

		Entry<K, V>[] oldTable = table;

		// double capacity of this table
		table = new Entry[2 * oldTable.length + 1];

		// reinsert all times in old table into expanded table
		numKeys = 0;
		numDeletes = 0;

		for (int i = 0; i < oldTable.length; ++i) {
			if (oldTable[i] != null && oldTable[i] != DELETED) {
				// insert entry i expanded table
				put(oldTable[i].key, oldTable[i].value);
			}
		}
	}


	/**
	 * Remove the item with a given key value
	 *
	 * @param key The key to be removed
	 * @return The value associated with this key, or null
	 * if the key is not in the table.
	 */
	public V remove(K key) {

		int index = find(key);


		if (table[index] == null)
			return null;
		else {
			V value = table[index].value;
			table[index] = DELETED;
			++numDeletes;
			--numKeys;

			return value;
		}
	}


	/**
	 * Contains key-value pairs for a hash table
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private static class Entry<K, V> {

		/**
		 * Key
		 */
		private K key;

		/**
		 * Value
		 */
		private V value;

		/**
		 * Contructor
		 * Creates new key-value pairs
		 *
		 * @param key   The key
		 * @param value The value
		 */
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * retrieves the key
		 *
		 * @return the key
		 */
		public K getKey() {
			return key;
		}


		/**
		 * Retrieves the value
		 *
		 * @return the value
		 */
		public V getValue() {
			return value;
		}

		/**
		 * Sets the value
		 *
		 * @param value The new value
		 * @return The old value
		 */
		public V setValue(V value) {
			V oldVal = this.value;
			this.value = value;
			return oldVal;
		}
	}
}
