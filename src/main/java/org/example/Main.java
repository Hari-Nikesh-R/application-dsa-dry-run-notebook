package org.example;

/**
 * Consider that we have word
 * This is the way the world started [] out.
 * This is the way the world started out.[                     ]
 *
 * Step 1:
 * buffer = [____________]
 * gapStart = 0
 * gapEnd = 12
 * Undo = [] (stack)
 * Redo = [] (stack)
 * Text = ""
 *
 * Step 2:
 * Now you typing 'H'
 * buffer = [H___________]
 * gapStart = 1
 * gapEnd = 12
 * Undo = [ insert(H) ]
 * Redo = []
 * Text = "H"
 *
 * Step 3:
 * Now typing 'E', 'L', 'L', '0'
 * buffer = [HELLO_______]
 * gapStart = 5
 * gapEnd = 12
 * Undo = [insert(H), insert(E), insert(L), insert(L), insert(O)]
 * Redo = []
 * Text= "HELLO"
 *
 * Step 4: Consider that you have a cursor movement
 * Cursor to position 2
 * buffer = [HE(cursor)_______LLO]
 * gapStart = 2
 * gapEnd = 8
 * Undo = [insert(H), insert(E), insert(L), insert(L), insert(O)]
 * Redo = []
 * Text= "HE|(cursor)LLO"
 *
 * Step 5: Insert a character 'X'
 * buffer = [HEX______LLO]
 * gapStart = 3
 * gapEnd = 8
 * Undo = [insert(H), insert(E), insert(L), insert(L), insert(O), insert(X)]
 * Redo = []
 *
 * Step 6: Delete a character 'X'
 * buffer = [HE_______LLO]
 * gapStart = 2
 * gapEnd = 8
 * Undo = [insert(H), insert(E), insert(L), insert(L), insert(O), insert(X), delete(X)]
 * Redo = []
 * Text = "HELLO"
 *
 * Step 7: Undo
 * Pop from the Undo stack
 * delete(X)
 * gapStart = 2 (Initially)
 * buffer = [HE_______LLO]
 * buffer = [HEX______LLO]
 * gapStart = 3
 * gapEnd = 8
 * Undo = [insert(H), insert(E), insert(L), insert(L), insert(O), insert(X)]
 *  Redo = [ delete(X) ]
 */
public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(10);
        textEditor.insert('H');
        textEditor.insert('e');
        textEditor.insert('l');
        textEditor.insert('l');
        textEditor.insert('o');

        textEditor.print();
        textEditor.moveCursor(2);
        textEditor.insert('X');
        textEditor.print();
    }
}