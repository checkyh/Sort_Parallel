package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import algo.*;

public class Main {
	static File inputfile = new File("src/random.txt");
	static File[] outputfile;

	static void initialize() {
		outputfile = new File[7];
		outputfile[1] = new File("src/order1.txt");
		outputfile[2] = new File("src/order2.txt");
		outputfile[3] = new File("src/order3.txt");
		outputfile[4] = new File("src/order4.txt");
		outputfile[5] = new File("src/order5.txt");
		outputfile[6] = new File("src/order6.txt");
	}

	public static void output(int index, int[] result) {
		try {
			BufferedWriter bf = new BufferedWriter(new PrintWriter(outputfile[index]));
			for (int i = 0; i < result.length; i++) {
				bf.write(String.valueOf(result[i]));
				bf.write(" ");
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		initialize();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputfile));
			line = br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] arrayString = line.split(" ");
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (String str : arrayString) {
			list.add(Integer.valueOf(str));
		}
		long startMili=System.currentTimeMillis();
		long endMili=0;
		output(1,QuickSort.Sort(list));
		endMili=System.currentTimeMillis();
		System.out.println(1+":"+String.valueOf(endMili-startMili)+"ms");
		startMili=endMili;
		output(2,QuickSort_P.Sort(list));
		endMili=System.currentTimeMillis();
		System.out.println(2+":"+String.valueOf(endMili-startMili)+"ms");
		startMili=endMili;
		output(3,RankSort.Sort(list));
		endMili=System.currentTimeMillis();
		System.out.println(3+":"+String.valueOf(endMili-startMili)+"ms");
		startMili=endMili;
		output(4,RankSort_P.Sort(list));
		endMili=System.currentTimeMillis();
		System.out.println(4+":"+String.valueOf(endMili-startMili)+"ms");
		startMili=endMili;
		output(5,MergeSort.Sort(list));
		endMili=System.currentTimeMillis();
		System.out.println(5+":"+String.valueOf(endMili-startMili)+"ms");
		startMili=endMili;
		output(6,MergeSort_P.Sort(list));
		endMili=System.currentTimeMillis();
		System.out.println(6+":"+String.valueOf(endMili-startMili)+"ms");
		startMili=endMili;
	}
}
