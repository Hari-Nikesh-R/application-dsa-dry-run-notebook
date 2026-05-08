package org.example;

public class GapBuffer {
    private char[] buffer;
    private int gapStart;
    private int gapEnd;

    public GapBuffer(int capacity) {
        this.buffer = new char[capacity];
        this.gapStart = 0;
        this.gapEnd = capacity;
    }

    public void moveCursor(int position) {
        if (position < 0 || position > size()) {
            return;
        }
        if (position < gapStart) {
            int shiftSize = gapStart - position;
            System.arraycopy(buffer, position, buffer, gapEnd - shiftSize, shiftSize);
            gapStart = gapStart - shiftSize;
            gapEnd = gapEnd - shiftSize;
        }
        else if (position > gapStart) {
            int shiftSize = position - gapStart;
            System.arraycopy(buffer, gapEnd, buffer, gapStart, shiftSize);
            gapStart = gapStart + shiftSize;
            gapEnd = gapEnd + shiftSize;
        }
    }

    public void insert(char ch) {
        if (gapStart == gapEnd) {
            growTheBuffer();
        }
        buffer[gapStart++] = ch;
    }

    public char delete() {
        if (gapStart == 0) {
            return '\0';
        }
        return buffer[--gapStart];
    }

    private void growTheBuffer() {
        int oldSize = buffer.length;
        int newSize = oldSize * 2;
        char[] newBuffer = new char[newSize];

        int leftSize = gapStart;
        int rightSize = oldSize - gapEnd;
        int newGapSize = newSize - leftSize - rightSize;

        System.arraycopy(buffer, 0, newBuffer, 0, leftSize);
        System.arraycopy(buffer, gapEnd, newBuffer, leftSize + newGapSize, rightSize);

        gapEnd = leftSize + newGapSize;
        buffer = newBuffer;
    }

    public String getText() {
        String left = new String(buffer, 0, gapStart);
        String right = new String(buffer, gapEnd, buffer.length - gapEnd);
        return left + right;
    }

    public int size() {
        return buffer.length - (gapEnd - gapStart);
    }
}
