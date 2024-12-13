package it.unibo.es3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LogicsImpl implements Logics{

    private List<Pair<Integer, Integer>> positons;
    private final int size;

    LogicsImpl(final int size) {
        this.positons = new LinkedList<>();
        this.size = size; 
    }

    @Override
    public void addButton(final Pair<Integer, Integer> pos) {
        this.positons.add(pos);
    }

    @Override
    public List<Pair<Integer, Integer>> values() {
        return this.positons;
    }

    @Override
    public void advance() {
        List<Pair<Integer, Integer>> neighboursPositions = new ArrayList<>();
        this.positons.stream().distinct().forEach(t -> {
                neighboursPositions.addAll(getNeighbours(t));
            }); 
        for (Pair<Integer,Integer> positon : neighboursPositions) {
            this.positons.add(positon);
        }
    }

    private List<Pair<Integer, Integer>> getNeighbours(final Pair<Integer, Integer> pos) {
        List<Pair<Integer, Integer>> neighbours = new ArrayList<>();
        List<Pair<Integer, Integer>> neighboursPositions = new ArrayList<>();
        neighboursPositions.add(new Pair<Integer,Integer>(pos.getX() + 1, pos.getY() + 1)); 
        neighboursPositions.add(new Pair<Integer,Integer>(pos.getX() - 1, pos.getY() - 1)); 
        neighboursPositions.add(new Pair<Integer,Integer>(pos.getX() + 1, pos.getY() - 1)); 
        neighboursPositions.add(new Pair<Integer,Integer>(pos.getX() - 1, pos.getY() + 1)); 
        neighboursPositions.add(new Pair<Integer,Integer>(pos.getX(), pos.getY() + 1)); 
        neighboursPositions.add(new Pair<Integer,Integer>(pos.getX() , pos.getY() - 1)); 
        neighboursPositions.add(new Pair<Integer,Integer>(pos.getX() + 1, pos.getY())); 
        neighboursPositions.add(new Pair<Integer,Integer>(pos.getX() - 1, pos.getY())); 
        for (Pair<Integer,Integer> current : neighboursPositions) {
            if(controlPosition(current)) {
                neighbours.add(current);
            }
        }
        return neighbours;
    }

    private Boolean controlPosition(final Pair<Integer, Integer> pos) {
        if(pos.getX() >= size || pos.getX() < 0 || pos.getY() >= size || pos.getY() < 0) {
            return false;
        }
        return true;
    }
    
}
