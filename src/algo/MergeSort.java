package algo;

import java.util.ArrayList;

public class MergeSort {
	public static int[] Sort(int[] array) {
		int[] bufs = new int[array.length];
		sort(array, 0, array.length - 1, bufs);
		return bufs;
	}
	public static int[] Sort(ArrayList<Integer> array) {
		int[] datas=new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			datas[i]=array.get(i).intValue();
		}
		return Sort(datas);
	}
	public static void sort(int[] datas, int low, int high) {
		int[] bufs = new int[datas.length];
		sort(datas, low, high, bufs);
	}

	static void sort(int[] datas, int low, int high, int[] bufs) {
		if (low >= high)
			return;

		int center = (low + high) / 2;
		sort(datas, low, center);
		sort(datas, center + 1, high);
		merge(datas, low, center, high, bufs);
	}

	private static void merge(int[] datas, int leftPos, int center, int rightPos, int[] bufs) {
		int cur = leftPos;
		int leftCur = leftPos;
		int rightCur = center + 1;
		while (leftCur <= center && rightCur <= rightPos) {
			if (datas[leftCur] < datas[rightCur])
				bufs[cur++] = datas[leftCur++];
			else
				bufs[cur++] = datas[rightCur++];
		}
		while (leftCur <= center)
			bufs[cur++] = datas[leftCur++];
		while (rightCur <= rightPos)
			bufs[cur++] = datas[rightCur++];

		for (int i = leftPos; i <= rightPos; ++i)
			datas[i] = bufs[i];
	}

}
