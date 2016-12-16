package algo;

import java.util.ArrayList;

public class QuickSort{
	private static void swap(int i, int j, int[] tab){
		int tmp = tab[i];
		tab[i]=tab[j];
		tab[j]=tmp;
	}
	
	private static int partition(int l, int r, int[] tab){
		int x = tab[l];
		int i=l-1;
		int j=r+1;
		while (true){
			do{
				j--;
			} while (tab[j]>x);
			do{
				i++;
			} while (tab[i]<x);
			if(i<j) swap(i,j,tab);
			else return j;
		}
		
	}
	
	public static void quickSort(int l, int r, int tab[]){
		if(l<r){
			int q = partition(l,r,tab);
			quickSort(l,q,tab);
			quickSort(q+1,r,tab);
		}
	}

	public static int[] Sort(int[] array) {
		quickSort(0, array.length-1, array);
		return array;
	}
	public static int[] Sort(ArrayList<Integer> array) {
		int[] datas=new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			datas[i]=array.get(i).intValue();
		}
		return Sort(datas);
	}
}
