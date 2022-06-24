/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import patient.management.system.function.LoginStrategy;

/**
 *
 * @author James Man
 */
public class LoginContext {
    private LoginStrategy strategy;
    
    public LoginContext(LoginStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executeStrategy (String letter) {
        return strategy.login(letter);
    }
}
