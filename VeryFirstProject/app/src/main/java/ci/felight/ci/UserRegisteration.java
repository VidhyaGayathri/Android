package ci.felight.ci;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by Vidhya Gayathri Raja on 2/2/2016.
 */
public class UserRegisteration extends Activity{

    private final String TAG = "registration";
    TextView tvResult;
    EditText etResult1,etResult,etResult2,etResult3 ;
    int day,month,year;
    String name = "";
    Session session;
    ProgressDialog pDialog;
    String subject, mailBody, userName, emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregistraion);
        etResult = (EditText) findViewById(R.id.etName);
        etResult2 =(EditText)findViewById(R.id.etPassword);
        etResult3=(EditText)findViewById(R.id.etRptPassword);



        etResult2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                int min = 10;
                if (etResult2.getText().toString().length() > min) {
                    Toast.makeText(getApplicationContext(), "Limited to only 10 characters!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1,
                                      int arg2, int arg3) {
            }

        });

        etResult3.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable arg0) {
                int min = 10;
                if (etResult3.getText().toString().length() > min) {
                    Toast.makeText(getApplicationContext(), "Limited to only 10 characters!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1,
                                      int arg2, int arg3) {
            }

        });

                }

    public void validate(View view) {

        Log.i(TAG, "inside method");

        etResult = (EditText) findViewById(R.id.etName);
        if (etResult.getText().toString().isEmpty()) {
            etResult.setError("UserName Should not be blank");
        }

        userName = etResult.getText().toString();

        EditText etResult1 = (EditText) findViewById(R.id.etEmail);
        if (etResult1.getText().toString().isEmpty())
            etResult1.setError("You cannot leave this empty");

        emailId = etResult1.getText().toString();


        String EMAIL_REGEX = "^[\\w -_\\.+]*[\\w -_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if (!etResult1.getText().toString().matches(EMAIL_REGEX))
            etResult1.setError("Please enter a valid EmailID");




         etResult2 = (EditText) findViewById(R.id.etPassword);
        if (etResult2.getText().toString().isEmpty())
            etResult2.setError("You cannot leave this empty");

        if(etResult2.getText().toString().length()>10)
            Toast.makeText(getBaseContext(),"Your password should not exceed more than 10 charactes",Toast.LENGTH_LONG).show();

         etResult3 = (EditText) findViewById(R.id.etRptPassword);
        if (etResult3.getText().toString().isEmpty())
            etResult3.setError("You cannot leave this empty");

        if(etResult3.getText().toString().length()>10)
            Toast.makeText(getBaseContext(),"Your password should not exceed more than 10 charactes",Toast.LENGTH_LONG).show();


        if (!etResult2.getText().toString().equals(etResult3.getText().toString()))
            // Toast.makeText(getApplicationContext(), "These Password does not match!! Try Again??", Toast.LENGTH_LONG).show();
            etResult3.setError("These Password does not match!! Try Again??");

        RadioGroup gender = (RadioGroup) findViewById(R.id.rbRadioGroup);
        if (gender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();

        }


        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth() + 1;
        year = datePicker.getYear();
        int age = getAge(year, month, day);
        int min = 13;
        if (age < min)
            Toast.makeText(getApplicationContext(), "Sorry !! You are not allowed to Register this form ", Toast.LENGTH_LONG).show();


        // Sending Email to the user
        sendEmail();
    }

    // This method is responsible for sending an email to the registered user
    public void sendEmail(){
        //Log.d(NavigatorActivity.TAG, "sendEmail() of UserRegistrationActivity");
        subject = "Thanks for registering....";
        mailBody ="Hi "+userName+",\n\n you just registered in my App.\n\nDon't reply to this mail, this is an auto generated mail";

        Properties props = new Properties();
        /*props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.SocketFactory.port","465");
        props.put("mail.smtp.SocketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port", "465");
        */
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gayumystry@gmail.com", "#Gayuraja");
            }
        });
        try {
            pDialog = ProgressDialog.show(this, "", "Please Wait.......", true);
            RetrieveFeedTask task = new RetrieveFeedTask();
            task.execute();
        }catch(Exception e){
           // Log.d(NavigatorActivity.TAG, e + ". : Exception in task.execute() of sendEmail()");
            Toast toast = Toast.makeText(getBaseContext(), e+". : exception occurred", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();
            return;
        }
    }
    class RetrieveFeedTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
           // Log.d(NavigatorActivity.TAG, "doInBackground() of sendEmail() in UserRegistrationActivity");
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("gayumystry@gmail.com"));

                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
                message.setSubject(subject);
                message.setText(mailBody);
                //message.setContent(message, "text/html; charset=utf-8");
                Transport.send(message);
               // Log.d(NavigatorActivity.TAG, "Transport.send() in sendEmail() of UserRegistrationActivity");
            } catch (MessagingException e) {
               // Log.d(NavigatorActivity.TAG, e+". : doInBackground() Messaging exception of sendEmail()");
                e.printStackTrace();
            } catch (Exception e) {
               // Log.d(NavigatorActivity.TAG, e+". : doInBackground() Exception of sendEmail()");
                e.printStackTrace();
            }
            return null;
        }


       /* Intent intentProfile = new Intent(getBaseContext(),Profile.class);
        etResult = (EditText) findViewById(R.id.etName);
        name = etResult.getText().toString();
        intentProfile.putExtra("Username",name);
        startActivity(intentProfile);*/
    }


    private int getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);


        return ageInt;
    }



}



