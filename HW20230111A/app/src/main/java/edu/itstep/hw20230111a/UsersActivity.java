package edu.itstep.hw20230111a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersActivity extends AppCompatActivity {

    private ListView lvUsers;
    private SimpleAdapter adapter;
    private List<User> users = new ArrayList<>();
    private List<Map<String, String>> usersNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        loudDataFromMainActivity();

        initMapCollection();

        lvUsers = findViewById(R.id.lvUsers);

        createAndSetAdapter();

        setListeners();
    }

    // Отримання даних з попередньго Актівіті - використовується об'єкт Intent
    private void loudDataFromMainActivity() {
        Intent intent = getIntent();
        //users = (List<User>) intent.getSerializableExtra("usersList");
        //users = (List<User>) intent.getSerializableExtra(ConstantsStore.USER_LIST);
        users = intent.getParcelableArrayListExtra(ConstantsStore.USER_LIST);
    }

    // Формування колекції Map для відображення у simple_list_item_2
    private void initMapCollection() {
        Map<String, String> map;
        for (User user : users) {
            map = new HashMap<>();
            map.put("name", user.getName());
            map.put("userName", user.getUsername());
            usersNames.add(map);
        }
    }

    // Створення та підключення Адаптера
    private void createAndSetAdapter() {
        adapter = new SimpleAdapter(
                this,
                usersNames,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "userName"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        lvUsers.setAdapter(adapter);
    }

    // Встановлення слухача - клік по елементу списка
    private void setListeners() {
        lvUsers.setOnItemClickListener((adapterView, view, position, id) -> {
            //Toast.makeText(this, "pos="+position + " id=" + id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "phone="+users.get(position).getPhone(), Toast.LENGTH_SHORT).show();
            ArrayList<String> userPhone = new ArrayList<>();
            userPhone.add(users.get(position).getName());
            userPhone.add(users.get(position).getPhone());

            Intent newIntent = new Intent(UsersActivity.this, PhoneActivity.class);
            //newIntent.putStringArrayListExtra("userPhone", userPhone);
            newIntent.putStringArrayListExtra(ConstantsStore.USER_INFO, userPhone);
            startActivity(newIntent);
        });
    }
}
