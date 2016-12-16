package algo;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class MergeSort_P {
	public static int[] Sort(int[] datas) {
		parallelSort(datas, 0, datas.length - 1);
		return datas;
	}
	public static int[] Sort(ArrayList<Integer> array) {
		int[] datas=new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			datas[i]=array.get(i).intValue();
		}
		return Sort(datas);
	}
	public static void parallelSort(int[] datas, int low, int high) {
		ForkJoinPool pool = new ForkJoinPool();
		int[] bufs = new int[datas.length];
		MergeSortAction action = new MergeSortAction(datas, low, high, bufs);
		pool.invoke(action);
		pool.shutdown();
		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
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

	static class MergeSortAction extends RecursiveAction {
		private static final long serialVersionUID = -6274247871903861027L;
		private int[] mDatas;
		private int mLow;
		private int mHigh;
		private int[] mBuf;

		public MergeSortAction(int[] datas, int low, int high, int[] mBuf2) {
			mDatas = datas;
			mLow = low;
			mHigh = high;
			mBuf = mBuf2;
		}

		@Override
		protected void compute() {
			if (mLow >= mHigh)
				return;

			int center = (mLow + mHigh) / 2;
			if (mLow + 2 >= mHigh) {
				if (mDatas[mLow] > mDatas[center])
					swap(mDatas, mLow, center);
				if (mDatas[center] > mDatas[mHigh]) {
					swap(mDatas, center, mHigh);
					if (mDatas[mLow] > mDatas[center])
						swap(mDatas, mLow, center);
				}
				return;
			}
			invokeAll(new MergeSortAction(mDatas, mLow, center, mBuf),
					new MergeSortAction(mDatas, center + 1, mHigh, mBuf));
			merge(mDatas, mLow, center, mHigh, mBuf);
		}

		private void swap(int[] datas, int a, int b) {
			int tmp = datas[a];
			datas[a] = datas[b];
			datas[b] = tmp;
		}
	}
}