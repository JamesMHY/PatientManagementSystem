/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

/**
 *
 * @author James Man
 */
public class SecretaryLogin implements LoginStrategy {

    public boolean login(String letter) {
        return letter.equals("S");
    }
}
