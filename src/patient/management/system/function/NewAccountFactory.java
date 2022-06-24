/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;


/**
 * Factory Pattern
 * @author James Man
 */
public class NewAccountFactory {
    
    public NewAccount getRole(String role) {
        if(role.equals("A")) {
            return new AdministratorAccount();
        } else if (role.equals("S")) {
            return new SecretaryAccount();
        } else if (role.equals("D")) {
            return new DoctorAccount();
        } else if (role.equals("P")) {
            return new PatientAccount();
        }
        return null;
    }
}
