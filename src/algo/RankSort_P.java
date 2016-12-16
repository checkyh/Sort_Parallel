package algo;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class RankSort_P extends RecursiveAction {
	public static int[] datas;
	private static final long serialVersionUID = -3915224688607929671L;
	private int l, r;
	private int[] ranklist = null;

	public RankSort_P(int l, int r, int[] ranklist) {
		this.l = l;
		this.r = r;
		this.ranklist = ranklist;
	}

	public int rankit(int pos) {
		int rankv = 0;
		for (int i = 0; i < datas.length; i++) {
			if (datas[i] < datas[pos])
				rankv++;
			if (datas[i] == datas[pos] && pos > i)
				rankv++;
		}
		return rankv;
	}

	@Override
	protected void compute() {
		if (r-l<=20)
		{
			for (int i=l;i<=r;i++)
			ranklist[i] = rankit(i);
		}
		else {
				int center = (r + l) / 2;
				RankSort_P Left = new RankSort_P(l, center, ranklist);
				RankSort_P Right = new RankSort_P(center, r, ranklist);
				Left.fork();
				Right.compute();
				Left.join();
		}
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
	public static int[] Sort(int[] array) {
		int[] ranklist = new int[array.length];
		datas = array.clone();
		RankSort_P rankaction = new RankSort_P(0, array.length - 1,ranklist);
		rankaction.compute();
		rearrange(datas, ranklist);
		return datas;
	}

	public static int[] Sort(ArrayList<Integer> array) {
		int[] datas = new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			datas[i] = array.get(i).intValue();
		}
		return Sort(datas);
	}
}
