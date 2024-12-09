package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

	private List<Integer> valuesList = new ArrayList<>();

	public LogicsImpl(int size) {
		this.valuesList.addAll(Collections.nCopies(size, 0));
	}

	@Override
	public int size() {
		return this.valuesList.size();
	}

	@Override
	public List<Integer> values() {
		return this.valuesList;
	}

	@Override
	public List<Boolean> enablings() {
		return values().stream().map(t -> t < size()).collect(Collectors.toList()); 
	}

	@Override
	public int hit(int elem) {
		final int newElem = this.valuesList.get(elem) + 1;
		if(newElem > size()) {
			return this.valuesList.get(elem);
		}
		else {
			this.valuesList.set(elem, newElem);
			return newElem;
		}
	}

	@Override
	public String result() {
		return "<<" + this.valuesList.stream().map(t -> String.valueOf(t)).collect(Collectors.joining("|")) + ">>";
	}

	@Override
	public boolean toQuit() {
		return this.valuesList.stream().allMatch(t -> t == this.valuesList.getFirst());
	}
}
