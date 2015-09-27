package Panthera.Services;

import java.util.List;

public class PairedList<L, R> {
	private L l;
	private R r;
	private List<L> left;
	private List<R> right;
	
	public PairedList(L l, R r) {
		this.l = l;
		this.r = r;
	}
	
	public void add(L l, R r) {
		left.add(l);
		right.add(r);
	}
	
	public L getLeft(int index) {
		return this.left.get(index);
	}
	
	public R getRight(int index) {
		return this.right.get(index);
	}
	
	public void remove(int index) {
		left.remove(index);
		right.remove(index);
	}
	
	public void clear() {
		left.clear();
		right.clear();
	}
}
