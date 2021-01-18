package com.company;

import java.util.Comparator;
import java.io.*;
import java.util.*;


public class ChromosomeComparator implements Comparator<Chromosome> {


    @Override
    public int compare(Chromosome o1, Chromosome o2) {
        int i = o2.fitness - o1.fitness;
        return i;
    }
}
