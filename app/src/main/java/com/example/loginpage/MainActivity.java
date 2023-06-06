package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.loginpage.database.ApplicationDatabase;
import com.example.loginpage.database.user.User;

public class MainActivity extends AppCompatActivity {
    RelativeLayout login_panel;
    Button btn, btnsignup;
    EditText textUser, textPsw;
    String userId, userPsw, trueUser = "Sahra", truePsw = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_panel = findViewById(R.id.login_panel);
        btn = findViewById(R.id.btnlogin);
        btnsignup = findViewById(R.id.btnsignup);
        textUser = findViewById(R.id.editTestUser);
        textPsw = findViewById(R.id.editTestPsw);

        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loginpanel);
        login_panel.startAnimation(anim1);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = textUser.getText().toString();
                userPsw = textPsw.getText().toString();
                if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(userPsw)) {
                    ApplicationDatabase db = ApplicationDatabase.getDbInstance(MainActivity.this);
                    LiveData<User> userLiveData= db.userDao().readAllData(userId, userPsw);

                    userLiveData.observe(MainActivity.this, user-> {
                        if (user != null) {
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            intent.putExtra("intentUId", user.getFie_id());
                            finish();
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "You entered the wrong username or password", Toast.LENGTH_SHORT).show();
                        }
                    });


                } else
                    Toast.makeText(MainActivity.this, "username or password cannot be empty", Toast.LENGTH_SHORT).

                            show();


            }
        });

    }
}