package org.example;

import java.util.Stack;

public class TextEditor {
    private GapBuffer gapBuffer;
    private Stack<Operation> undo;
    private Stack<Operation> redo;

    public TextEditor(int capacity) {
        this.gapBuffer = new GapBuffer(capacity);
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    public int getCursorPosition() {
        return gapBuffer.size() - (gapBuffer.getText().length() - gapBuffer.size());
    }

    public void insert(char ch) {
        int position = getCursorPosition();
        gapBuffer.insert(ch);
        undo.push(new Operation("insert", ch, position));
        redo.clear();
    }

    public void delete() {
        int position = getCursorPosition();
        char deleted = gapBuffer.delete();
        if (deleted != '\0') {
            undo.push(new Operation("delete", deleted, position));
            redo.clear();
        }
    }

    public void moveCursor(int position) {
        gapBuffer.moveCursor(position);
    }

    public void undo() {
        if (undo.isEmpty()) {
            return;
        }
        Operation operation = undo.pop();
        gapBuffer.moveCursor(operation.position + (operation.type.equals("insert") ? 1 : 0));

        if (operation.type.equals("insert")) {
            gapBuffer.delete();
        } else {
            gapBuffer.insert(operation.ch);
        }
        redo.push(operation);
    }

    public void redo() {
        if (redo.isEmpty()) {
            return;
        }
        Operation operation = redo.pop();
        gapBuffer.moveCursor(operation.position);

        if (operation.type.equals("insert")) {
            gapBuffer.insert(operation.ch);
        } else {
            gapBuffer.delete();
        }
        undo.push(operation);
    }

    public void print() {
        System.out.println("Text = " + gapBuffer.getText()) ;
    }

}
