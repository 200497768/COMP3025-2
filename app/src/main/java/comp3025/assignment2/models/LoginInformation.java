package comp3025.assignment2.models;

/**
 * This model is created during LoginFragment.
<<<<<<< HEAD
 *
=======
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class LoginInformation {
    private String studentNumber = "No student number available";
    private String password = "No password available";

    public LoginInformation(String studentNumber, String password) {

        //Change the studentNumber field, but only if the field was provided.
        if (studentNumber == null) {
            //Don't change the field.
        } else {
            //Change the field.
            this.studentNumber = studentNumber;
        }

        //Change the password field, but only if the field was provided.
        if (password == null) {
            //Don't change the field.
        } else {
            //Change the field.
            this.password = password;
        }
    }

    public LoginInformation() {

    }

    public String getPassword() {
        return password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

}
