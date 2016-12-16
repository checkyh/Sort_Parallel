package algo;

import java.util.ArrayList;

public class RankSort {
	public static int[] Sort(ArrayList<Integer> array) {
		int[] datas=new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			datas[i]=array.get(i).intValue();
		}
		return Sort(datas);
	}
	public static int[] Sort(int[] a) {
		int[] r = new int[a.length];
		rank(a, r);
		rearrange(a, r);
		return a;
	}

	private static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static void rearrange(int[] a, int[] r) {
		for (int i = 0; i < a.length; i++)
			while (r[i] != i) {
				int t = r[i];
				swap(a, i, t);
				swap(r, i, t);
			}
	}

	private static void rank(int[] a, int[] r) {
		if (r.length < a.length)
			throw new IllegalArgumentException();
		for (int i = 0; i < a.length; i++)
			r[i] = 0;
		for (int i = 1; i < a.length; i++)
			for (int j = 0; j < i; j++)
				if (a[j] <= a[i])
					r[i]++;
				else
					r[j]++;
	}
}