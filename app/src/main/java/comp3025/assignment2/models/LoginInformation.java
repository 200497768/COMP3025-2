package comp3025.assignment2.models;

/**
 * This model is created during LoginFragment.
 *
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class LoginInformation {
    private String studentNumber = "No student number available";
    private String password = "No password available";

    public LoginInformation(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

}
