/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import javax.swing.text.*;

/**
 *
 * @author James Man
 */
public class JTextFieldCharLimit extends PlainDocument {
    
    private int limit;
    
    public JTextFieldCharLimit(int limit) {
        this.limit = limit;
    }
    
    public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
        if(str == null) {
            return;
        } else if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, set);
        } 
    }
}
