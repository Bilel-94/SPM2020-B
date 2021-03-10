package SPMB.Parkin.models.requestBody;

import SPMB.Parkin.constants.RegexConstants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ChangeMailInfo {

    @NotNull(message="Current email must not be null")
    @NotEmpty(message = "Current email must not be empty")
    @Pattern(regexp= RegexConstants.MAIL_REGEX,message="Invalid Email format")
    private String currentEmail;

    @NotNull(message="New Email must not be null")
    @NotEmpty(message = "New Email must not be empty")
    @Pattern(regexp=RegexConstants.MAIL_REGEX,message="Invalid Email format")
    private String newEmail;

    public ChangeMailInfo() {}

    public ChangeMailInfo(String currentEmail, String newEmail) {
        this.setCurrentEmail(currentEmail);
        this.setNewEmail(newEmail);
    }

    public String getCurrentEmail() {
        return currentEmail;
    }
    public void setCurrentEmail(String email) {
        if(email != null && email != "" &&
                email.matches(RegexConstants.MAIL_REGEX)) {
            this.currentEmail = email;
        }
        else {
            throw new IllegalArgumentException("Email is invalid");
        }
    }

    public String getNewEmail() {
        return newEmail;
    }
    public void setNewEmail(String email) {
        if(email != null && email != "" &&
                email.matches(RegexConstants.MAIL_REGEX)) {
            this.newEmail = email;
        }
        else {
            throw new IllegalArgumentException("Email is invalid");
        }
    }
}


