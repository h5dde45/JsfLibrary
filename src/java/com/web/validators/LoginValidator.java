package com.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;

@FacesValidator
public class LoginValidator implements Validator{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if(o.toString().length()<5){
            ResourceBundle bundle=ResourceBundle.getBundle("com.web.locales.messages",
                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
            FacesMessage message=new FacesMessage(bundle.getString("login_length_error"));
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw  new  ValidatorException(message);
        }
    }
}
