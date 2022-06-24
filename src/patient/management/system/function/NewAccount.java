/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/**
 * Factory Pattern
 * @author James Man
 */
public interface NewAccount {
    boolean register(Account account, AccountRequest account_request) throws IOException, NoSuchAlgorithmException;
}
