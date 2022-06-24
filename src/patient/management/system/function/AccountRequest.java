/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.util.Date;

/**
 *
 * @author James Man
 */
public class AccountRequest {
    
    private Date now;
    private String user_id;

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }
    
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    public AccountRequest(String user_id, Date now) {
        this.now = now;
        this.user_id = user_id;
    }
}

