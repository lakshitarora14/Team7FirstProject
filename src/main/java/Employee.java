import java.util.Date ;

public class Employee {
    String firstName ;
    String lastName ;
    Date dateOfBirth ;
    double experience ;
    public Employee(){}
    public Employee(String firstName, String lastName, Date dateOfBirth, double experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.experience = experience;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }





    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "ESOP{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +

                ", dateOfBirth=" + dateOfBirth +


                ", experience=" + experience +
                '}';
    }

    @Deprecated
    public String deprecatedMethod(){
        return "Test";
    }

}