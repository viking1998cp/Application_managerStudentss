package application_managerstudentss.Object;

import android.util.Log;

import java.io.Serializable;

public class Students  extends People implements Serializable {
    private float pointLiterature;
    private float pointMath;
    private float pointEngLish;
    private float pointAverage;
    private String studentClass;

    public Students() {
        Log.d("BBB", "Students: "+pointAverage);
    }

    public Students(String name, String address, String birthDate, float pointLiterature, float pointMath, float pointEngLish, float pointAverage, String studentClass) {
        super.setName(name);
        super.setAddress(address);
        super.setBirthDate(birthDate);
        this.pointLiterature = pointLiterature;
        this.pointMath = pointMath;
        this.pointEngLish = pointEngLish;
        this.pointAverage = pointAverage;
        this.studentClass = studentClass;
    }



    public float getPointLiterature() {
        return pointLiterature;
    }

    public void setPointLiterature(float pointLiterature) {
        this.pointLiterature = pointLiterature;
    }

    public float getPointMath() {
        return pointMath;
    }

    public void setPointMath(float pointMath) {
        this.pointMath = pointMath;
    }

    public float getPointEngLish() {
        return pointEngLish;
    }

    public void setPointEngLish(float pointEngLish) {
        this.pointEngLish = pointEngLish;
    }

    public float getPointAverage() {
        return pointAverage;
    }

    public void setPointAverage(float pointAverage) {
        this.pointAverage = pointAverage;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

}
