package application_managerstudentss;

import android.content.Context;
import android.widget.Toast;

import application_managerstudentss.Object.Students;
import lac.hong.application_managerstudentss.R;

public class Until {
    public static float pointAverage(float pointLiterature, float pointMath, float pointEnglish) {
        return (pointEnglish + pointLiterature + pointMath) / 3;
    }
    public static void ShowToastLong(String msg, Context context) {
        if (msg.isEmpty()) {
            msg = context.getString(R.string.error);
        }
        try {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean checkNullObject(Students students){
        if(students.getName().equalsIgnoreCase("") ||
                students.getBirthDate().equalsIgnoreCase("") ||
                students.getAddress().equalsIgnoreCase("") ||
                students.getPointAverage() == 0.0 ||
                students.getPointEngLish() == 0.0 ||
                students.getPointLiterature() ==0.0 ||
                students.getPointMath() ==0.0 ||
                students.getStudentClass().equalsIgnoreCase("")){
            return false;

        }else{
            return true;
        }

    }
}
