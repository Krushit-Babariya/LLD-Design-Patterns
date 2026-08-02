package IteratorDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class MagazineCollection implements IterableCollection<Magazine> {
    private final List<Magazine> magazines;

    public MagazineCollection() {
        this.magazines = new ArrayList<>();
    }

    public void addMagazine(Magazine magazine) {
        magazines.add(magazine);
    }

    public void removeMagazine(Magazine magazine) {
        magazines.remove(magazine);
    }

    public int size() {
        return magazines.size();
    }

    @Override
    public Iterator<Magazine> createIterator() {
        return new MagazineIterator(magazines);
    }

    private static class MagazineIterator implements Iterator<Magazine> {
        private final List<Magazine> magazines;
        private int position;

        public MagazineIterator(List<Magazine> magazines) {
            this.magazines = magazines;
            this.position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < magazines.size();
        }

        @Override
        public Magazine next() {
            if (hasNext()) {
                return magazines.get(position++);
            }
            return null;
        }

        @Override
        public void reset() {
            position = 0;
        }
    }
}

