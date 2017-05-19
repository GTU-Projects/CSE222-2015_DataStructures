package com.hasanmen;


import java.util.*;

/**
 * Created by Hasan MEN on 5.03.2016.
 * stundetID : 131044009
 *
 * @author Hasan MEN
 *         <p>
 *         This class created to make Super Linked List, named SpecList
 *         My speclist can compare two speclist and return a new speclist which contains common elements
 *         Class can sort elements in lists with sortList function.
 *         And has a function to combine two list. Puts elements of parameter list, head of the speclist
 * @version 1.02
 */
public class SpecList<E extends Comparable<E>> extends LinkedList<E> {


    /**
     * This method takes a speclist and traverse all elements of parameter list respectively, adds head of actual list
     *
     * @param c new List to combine with actual list. This list can contaion elements wich extends generic E
     * @return if combination is success return true, otherwise returns false
     * @throws NullPointerException when param c is null
     */
    public Boolean addAllAtHead(Collection<? extends E> c) throws NullPointerException {

        // If parameter is null
        if (null == c)
            throw new NullPointerException("Null argument sent to addAllAtHead method. Please check argument!");
        // list empty
        if (c.size() == 0)
            return false;

        // traverse all list and add all of them at the head
        for (E itr : c) {
            addFirst(itr);
        }
        return true;
    }

    /**
     * This methods takes list and compares all items with actual list
     * When finds common elements created new intersectlist and adds in it.
     * Then return s intersect list
     *
     * @param c List to compare and find common elements
     * @return return intersect list which has common elements
     */
    public List<E> getIntersectList(Collection<? extends E> c) {

        if (null == c)
            return null;

        SpecList<E> intersectList = new SpecList<>();

        // NO NEED TO CREATE LIST ITERATOR BECAUSE JUST GOES FORWARD
        Iterator<E> itr = iterator();

        // compare all items
        while (itr.hasNext()) {
            E node = itr.next();
            if (c.contains(node)) {
                if (!intersectList.contains(node))
                    intersectList.add(node);
            }
        }
        return intersectList;
    }

    /**
     * This methods sorts Speclists and return this list.
     * Sorts increasing or decreasing order according to parameter
     *
     * @param increasing if true will sort increasing otherwise sorts decreasing order
     * @return ordered list
     */
    public List<E> sortList(boolean increasing) {

        /* ONEMLI !!!!!!!!!!!!!!!
         *COPY PASTE YAPMAK YERINE EGER INCREASING TRUE ISE ESITLIKLERI 1 LE DEGILSE -1 ILE CARPIP ESITLIK YONUNU
         * DEGISTIRIYORUM - TEKRAR YAZMAYA GEREK YOK
         * */

        int equalityAspect = 1; // esitlik yonu
        boolean swapped;
        ListIterator<E> specItr = this.listIterator();
        if (increasing)
            equalityAspect = 1;
        else equalityAspect = -1;


        do {
            swapped = false;
            while (specItr.hasNext()) {
                E item = specItr.next();
                if (specItr.hasNext()) {
                    E item2 = specItr.next();
                    if (item.compareTo(item2) * equalityAspect > 0) {
                        specItr.previous();
                        specItr.previous();
                        specItr.next();
                        specItr.set(item2);
                        specItr.next();
                        specItr.set(item);
                        swapped = true;
                    }
                    specItr.previous();
                }
            }

            if (!swapped)
                break;

            while (specItr.hasPrevious()) {
                E item = specItr.previous();
                if (specItr.hasPrevious()) {
                    E item2 = specItr.previous();
                    if (item.compareTo(item2) * equalityAspect < 0) {
                        specItr.next();
                        specItr.next();
                        specItr.previous();
                        specItr.set(item);
                        specItr.previous();
                        specItr.set(item2);
                        swapped = true;
                    }
                    specItr.next();
                }
            }

        } while (swapped);

        return this;
    }
}
