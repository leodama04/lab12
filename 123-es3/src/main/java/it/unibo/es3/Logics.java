package it.unibo.es3;

import java.util.List;

public interface Logics {

    public void addButton(final Pair<Integer, Integer> randomPosition);

    public List<Pair<Integer, Integer>> values();

    public void advance();

}
