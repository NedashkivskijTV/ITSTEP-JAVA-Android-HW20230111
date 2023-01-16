package edu.itstep.hw20230111a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneActivity extends AppCompatActivity {

    private TextView tvUserName;
    private TextView tvUserPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        initView();

        initData();
    }

    // Ініціалізація даних
    private void initView() {
        tvUserName = findViewById(R.id.tvUserName);
        tvUserPhone = findViewById(R.id.tvUserPhone);
    }

    // Отримання даних з попереднього Актівіті (використання об'єкта Intent)
    // Передача даних до відповідних елементів TextView
    private void initData() {
        Intent intent = getIntent();
        //ArrayList<String> userInfo = intent.getStringArrayListExtra("userPhone");
        ArrayList<String> userInfo = intent.getStringArrayListExtra(ConstantsStore.USER_INFO);

        tvUserName.setText(userInfo.get(0));
        tvUserPhone.setText(userInfo.get(1));
    }
}