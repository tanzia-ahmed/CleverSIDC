
public class Student {
    private long SIDC;
    private String familyName;
    private String firstName;
    private String DOB;

    public Student(){
        SIDC = 0;
        familyName = "";
        firstName = "";
        DOB = "";
    }

    public Student(String familyName, String firstName, String dob){
        this.familyName = familyName;
        this.firstName = firstName;
        this.DOB = dob;
    }

    public long getSIDC() {
        return SIDC;
    }

    public void setSIDC(long SIDC) {
        this.SIDC = SIDC;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Student{" +
                "SIDC=" + SIDC +
                ", familyName='" + familyName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", DOB='" + DOB + '\'' +
                '}';
    }
}
