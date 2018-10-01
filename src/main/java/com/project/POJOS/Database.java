package com.project.POJOS;

import java.util.ArrayList;
import java.util.List;

public class Database {
    List<Sequence> sequences;
    public static int SDC;

    public Database() {
        sequences = new ArrayList<>();
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(List<Sequence> sequences) {
        this.sequences = sequences;
    }


    public boolean removeSequence(Sequence sequence) {
        return sequences.remove(sequence);

    }

    public void addSequence(Sequence sequence) {
        sequences.add(sequence);

    }
}
