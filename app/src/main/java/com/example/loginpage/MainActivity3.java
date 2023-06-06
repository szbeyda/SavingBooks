package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.loginpage.database.ApplicationDatabase;
import com.example.loginpage.database.user.User;

public class MainActivity3 extends AppCompatActivity {
    RelativeLayout login_panel;
    Button btnsignup;
    EditText editpsw, editfirstname, editlastname, edituser, editemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnsignup = findViewById(R.id.btnsignup);
        login_panel = findViewById(R.id.login_panel2);

        editpsw = findViewById(R.id.editPsw);
        editlastname = findViewById(R.id.editlastName);
        editfirstname = findViewById(R.id.editfirstName);
        edituser = findViewById(R.id.editUser);
        editemail = findViewById(R.id.editEmail);


        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loginpanel);
        login_panel.startAnimation(anim1);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationDatabase db = ApplicationDatabase.getDbInstance(MainActivity3.this);
                User user = new User();
                user.setEmail(editemail.getText().toString());
                user.setFirstName(editfirstname.getText().toString());
                user.setLastName(editlastname.getText().toString());
                user.setPassword(editpsw.getText().toString());
                user.setUserName(edituser.getText().toString());
                db.userDao().insert(user);

                finish();
            }
        });
    }
}