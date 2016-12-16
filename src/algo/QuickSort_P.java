package algo;

import java.util.ArrayList;
import java.util.concurrent.*;

public class QuickSort_P extends RecursiveAction{		
	private static final long serialVersionUID = 1L;
	private int l, r;
	private final int[] tabCopy;
	
	QuickSort_P(int l, int r, int[] tab){
		this.l = l;
		this.r = r;
		this.tabCopy = tab;
	}
	
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
	
	protected void compute(){
		if(l<r){
			int q = partition(l,r,this.tabCopy);
			QuickSort_P left = new QuickSort_P(l,q,this.tabCopy);
			QuickSort_P right = new QuickSort_P(q+1,r,this.tabCopy);
			left.fork();
			right.compute();
			left.join();
		}
	}
	


	public static int[] Sort(int[] array) {
		QuickSort_P Sort_t=new QuickSort_P(0,array.length-1,array);
		Sort_t.compute();
		return Sort_t.tabCopy;
	}
	public static int[] Sort(ArrayList<Integer> array) {
		int[] datas=new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			datas[i]=array.get(i).intValue();
		}
		return Sort(datas);
	}
}