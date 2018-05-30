/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import sun.misc.resources.Messages_es;

/**
 *
 * @author ruben
 */
@FacesValidator("confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;
        String confirm = (String) component.getAttributes().get("confirm");
        
        if(password == null || confirm == null) {
            return;
        }
        
        if(!password.equals(confirm)) {
            throw new ValidatorException(new FacesMessage("Las contraseñas no son iguales."));
        }
    }
    
}
