/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.object;

import java.io.Serializable;

/**
 *
 * @author HOME
 */
public class ErrorObj implements Serializable {
    private String emailError, passwordError;
    private String accIDError, nameError, passwordConError;

    public ErrorObj() {
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getAccIDError() {
        return accIDError;
    }

    public void setAccIDError(String accIDError) {
        this.accIDError = accIDError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPasswordConError() {
        return passwordConError;
    }

    public void setPasswordConError(String passwordConError) {
        this.passwordConError = passwordConError;
    }
    
    
    
    
}
