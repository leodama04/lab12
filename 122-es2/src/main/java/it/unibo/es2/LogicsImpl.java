package it.unibo.es2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics{
    
    private List<List<String>> matrix;

    LogicsImpl(final int size) {
        this.matrix = new ArrayList<>();
        for(int i = 0; i < size ; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(size, ""));
            this.matrix.add(row);
        }
    }

    @Override
    public boolean toQuit() {
        if ( isAnyRowFull() || isAnyColumnFull() ) {
            return true;
        }
        else {
            return false;
        }
    }

    private List<List<String>> valuesMatrix() {
       return this.matrix;
    }

    private List<List<Boolean>> booleansMatrix() {
        return this.matrix.stream()
        .map(row -> row.stream().map(t -> t.equals("*")).collect(Collectors.toList()))
        .collect(Collectors.toList());
    }


    @Override
    public String setButton(Pair<Integer, Integer> position) {
        if(booleansMatrix().get(position.getX()).get(position.getY())) {
            valuesMatrix().get(position.getX()).set(position.getY(), "");
            return "";
        }
        else {
            valuesMatrix().get(position.getX()).set(position.getY(), "*");
            return "*";
        }
    }

    private boolean isAnyRowFull() {
		return this.booleansMatrix().stream().anyMatch(row -> row.stream().allMatch(t -> t));
	}

    private boolean isAnyColumnFull() {
		return IntStream.range(0,this.booleansMatrix().size())
            .anyMatch(index -> this.booleansMatrix().stream().allMatch(l -> l.get(index)));
	}

}
