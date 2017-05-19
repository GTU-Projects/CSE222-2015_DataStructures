import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hmenn on 3/30/16.
 */
public class LinkedListRecTest {

	@Test
	public void remove() throws Exception {

		LinkedListRec<Integer> list = new LinkedListRec<>();

		// null durumlari
		assertEquals(false,list.remove(null));


		// tek elemanli ve yanlisi silme
		list.add(2);
		assertEquals(false,list.remove(4));

		// ilk elemani silme
		list.add(2);
		list.add(3);
		assertEquals(true,list.remove(2));


		// listede 3 kaldi biraz daha ekle ve son elemani sil
		list.add(3);
		list.add(2);
		assertEquals(true,list.remove(2));

		// ortalardan eleman silme
		list.add(2);
		list.add(2);
		list.add(3);
		list.add(2);
		assertEquals(true,list.remove(2));

		System.out.println("After remove : "+list);

	}


}