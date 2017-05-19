package com.hasanmen;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.LinkedList;

/**
 * Created by Hasan MEN on 6.03.2016.
 *
 * @author Hasan MEN @hmenn
 * @version 1.02
 *          <p>
 *          Main class to test SpecList Class
 */
public class Main {
    public static void main(String... args) {

        try {

            System.out.println("\n## FIRST TEST WITH INTEGER CLASS ##\n");
            SpecList<Integer> intSpecListOne = new SpecList<>();
            SpecList<Integer> intSpecListTwo = new SpecList<>();
            SpecList<Integer> intIntersect;

            intSpecListOne.add(9);
            intSpecListOne.add(8);
            intSpecListOne.add(2);
            intSpecListOne.add(4);
            intSpecListOne.add(-5);

            System.out.println("1-> intSpecListOne : " + intSpecListOne);

            intSpecListTwo.add(18);
            intSpecListTwo.add(-2);
            intSpecListTwo.add(0);
            intSpecListTwo.add(12);
            intSpecListTwo.add(5);
            intSpecListTwo.add(3);

            System.out.println("2-> intSpecListTwo : " + intSpecListTwo);

            intSpecListOne.addAllAtHead(intSpecListTwo);
            System.out.println("3-> intSpecListOne.addAllAtHead(intSpecListTwo) : " + intSpecListOne);
            intSpecListOne.sortList(true);
            System.out.println("4-> Ordered new intSpecListOne : " + intSpecListOne);

            intIntersect = (SpecList<Integer>) intSpecListOne.getIntersectList(intSpecListTwo);
            System.out.println("5-> intSpecListOne.getIntersectList(intSpecListTwo) test: " +intIntersect );
            intIntersect.sortList(false);
            System.out.println("6-> Sorted (decrease) intersectList : "+intIntersect);
            intIntersect.sortList(true);
            System.out.println("7-> Sorted (increase) intersectList : "+intIntersect);

            System.out.println("\n## SECOND TEST WITH DOUBLE CLASS ##\n");
            SpecList<Double> dblSpecListOne = new SpecList<>();
            SpecList<Double> dblSpecListTwo = new SpecList<>();
            SpecList<Double> dblIntersect;

            dblSpecListOne.add(29.0);
            dblSpecListOne.add(-2.1);
            dblSpecListOne.add(-2.0);
            dblSpecListOne.add(1.56);
            dblSpecListOne.add(15.0);

            System.out.println("1-> dblSpecListOne : " + dblSpecListOne);

            dblSpecListTwo.add(4.61);
            dblSpecListTwo.add(1.56);
            dblSpecListTwo.add(0.0);
            dblSpecListTwo.add(-8.5);
            dblSpecListTwo.add(-2.0);
            dblSpecListTwo.add(3.0);

            System.out.println("2-> dblSpecListTwo : " + dblSpecListTwo);

            dblSpecListOne.addAllAtHead(dblSpecListTwo);
            System.out.println("3-> dblSpecListOne.addAllAtHead(dblSpecListTwo) : " + dblSpecListOne);
            dblSpecListOne.sortList(true);
            System.out.println("4-> Ordered new dblSpecListOne : " + dblSpecListOne);


            dblIntersect = (SpecList<Double>) dblSpecListOne.getIntersectList(dblSpecListTwo);
            System.out.println("5-> dblSpecListOne.getIntersectList(dblSpecListTwo) test: " +dblIntersect );
            dblIntersect.sortList(false);
            System.out.println("6-> Sorted (decrease) double intersectList : "+dblIntersect);
            dblIntersect.sortList(true);
            System.out.println("7-> Sorted (increase) double intersectList : "+dblIntersect);

            System.out.println("\n## FIRST TEST WITH INTEGER CLASS ##\n");
            SpecList<Character> chSpecListOne = new SpecList<>();
            SpecList<Character> chSpecListTwo = new SpecList<>();
            SpecList<Character> chIntersect;

            chSpecListOne.add('s');
            chSpecListOne.add('c');
            chSpecListOne.add('a');
            chSpecListOne.add('y');
            chSpecListOne.add('b');

            System.out.println("1-> chSpecListOne : " + chSpecListOne);

            chSpecListTwo.add('h');
            chSpecListTwo.add('o');
            chSpecListTwo.add('a');
            chSpecListTwo.add('x');
            chSpecListTwo.add('u');
            chSpecListTwo.add('c');

            System.out.println("2-> chSpecListTwo : " + chSpecListTwo);

            chSpecListOne.addAllAtHead(chSpecListTwo);
            System.out.println("3-> chSpecListOne.addAllAtHead(chSpecListTwo) : " + chSpecListOne);
            chSpecListOne.sortList(true);
            System.out.println("4-> Ordered new chSpecListOne : " + chSpecListOne);

            chIntersect = (SpecList<Character>) chSpecListOne.getIntersectList(chSpecListTwo);
            System.out.println("5-> chSpecListOne.getIntersectList(dblSpecListTwo) test: " +chIntersect );
            chIntersect.sortList(false);
            System.out.println("6-> Sorted (decrease) double intersectList : "+chIntersect);
            chIntersect.sortList(true);
            System.out.println("7-> Sorted (increase) double intersectList : "+chIntersect);

        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
