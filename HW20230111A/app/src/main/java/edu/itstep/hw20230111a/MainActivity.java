package edu.itstep.hw20230111a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Змінні екземпляра класу, що відповідають активним елеменнтам Актівіті
    private TextView tvHelloMessage;
    private Button btnGetUsers;
    private ProgressBar pbGetUsers;

    private List<User> users = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініціалізація (отримання за id) змінних екземпляра класу
        initView();

        // Підключення слухачів
        btnGetUsers.setOnClickListener(this::getUsersAndGoNextActivity);
    }

    private void initView() {
        tvHelloMessage = findViewById(R.id.tvHelloMessage);
        btnGetUsers = findViewById(R.id.btnGetUsers);
        pbGetUsers = findViewById(R.id.pbGetUsers);
    }

    // Отримання даних з сайту, наповнення моделі та перехід до UsersActivity
    private void getUsersAndGoNextActivity(View view) {
        pbGetUsers.setVisibility(View.VISIBLE); // прогресбар - видимий

        PlaceholderAPI placeholderAPI = NetworkService.getInstance().getApi();
        Call<List<User>> call = placeholderAPI.getAllUsers();

        call.enqueue(new Callback<List<User>>() { // Метод виконається в момент отримання результату з сервера
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) { // метод, що виконається після отримання відповіді
                users = response.body();

                pbGetUsers.setVisibility(View.INVISIBLE); // прогресбар - НЕвидимий

                goUsersActivity();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) { // Метод виконається при виникненні помилки
                Toast.makeText(MainActivity.this, "ERROR: " + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Перехід до наступного Актівіті
    private void goUsersActivity(){
        Intent intent = new Intent(MainActivity.this, UsersActivity.class);
        //intent.putParcelableArrayListExtra("usersList", (ArrayList<? extends Parcelable>) users);
        intent.putParcelableArrayListExtra(ConstantsStore.USER_LIST, (ArrayList<? extends Parcelable>) users);
        startActivity(intent);
    }

}