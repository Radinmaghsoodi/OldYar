package ir.radindev.oldyar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private EditText nextKmEditText, currentKmEditText, oilNameEditText;
    private CheckBox airFilterCheckBox, cabinFilterCheckBox, fuelFilterCheckBox, oilFilterCheckBox;
    private Button saveButton, delButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // گرفتن ریفرنس‌ها به عناصر
        nextKmEditText = findViewById(R.id.nextKmEditText);
        currentKmEditText = findViewById(R.id.currentKmEditText);
        oilNameEditText = findViewById(R.id.oilNameEditText);
        airFilterCheckBox = findViewById(R.id.airFilterCheckBox);
        cabinFilterCheckBox = findViewById(R.id.cabinFilterCheckBox);
        fuelFilterCheckBox = findViewById(R.id.fuelFilterCheckBox);
        oilFilterCheckBox = findViewById(R.id.oilFilterCheckBox);
        saveButton = findViewById(R.id.saveButton);
        delButton = findViewById(R.id.delButton);

        // بارگذاری داده‌ها هنگام شروع برنامه
        loadData();

        // ذخیره داده‌ها با کلیک روی دکمه saveButton
        saveButton.setOnClickListener(v -> saveData());

        // پاک کردن داده‌ها با کلیک روی دکمه delButton
        delButton.setOnClickListener(v -> clearData());
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // ذخیره مقادیر EditText ها
        editor.putString("nextKm", nextKmEditText.getText().toString());
        editor.putString("currentKm", currentKmEditText.getText().toString());
        editor.putString("oilName", oilNameEditText.getText().toString());

        // ذخیره وضعیت CheckBox ها
        editor.putBoolean("airFilterChecked", airFilterCheckBox.isChecked());
        editor.putBoolean("cabinFilterChecked", cabinFilterCheckBox.isChecked());
        editor.putBoolean("fuelFilterChecked", fuelFilterCheckBox.isChecked());
        editor.putBoolean("oilFilterChecked", oilFilterCheckBox.isChecked());

        // اعمال تغییرات به صورت همزمان و نمایش پیغام
        if (editor.commit()) {
            // پیام موفقیت
            Toast.makeText(this, "داده ها با موفقیت ذخیره شدند!", Toast.LENGTH_SHORT).show();
        } else {
            // پیام خطا
            Toast.makeText(this, "داده ها ذخیره نشد", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // بازیابی مقادیر EditText ها
        nextKmEditText.setText(sharedPreferences.getString("nextKm", ""));
        currentKmEditText.setText(sharedPreferences.getString("currentKm", ""));
        oilNameEditText.setText(sharedPreferences.getString("oilName", ""));

        // بازیابی وضعیت CheckBox ها
        airFilterCheckBox.setChecked(sharedPreferences.getBoolean("airFilterChecked", false));
        cabinFilterCheckBox.setChecked(sharedPreferences.getBoolean("cabinFilterChecked", false));
        fuelFilterCheckBox.setChecked(sharedPreferences.getBoolean("fuelFilterChecked", false));
        oilFilterCheckBox.setChecked(sharedPreferences.getBoolean("oilFilterChecked", false));
    }

    private void clearData() {
        // پاک کردن مقادیر EditText ها
        nextKmEditText.setText("");
        currentKmEditText.setText("");
        oilNameEditText.setText("");

        // غیرفعال کردن CheckBox ها
        airFilterCheckBox.setChecked(false);
        cabinFilterCheckBox.setChecked(false);
        fuelFilterCheckBox.setChecked(false);
        oilFilterCheckBox.setChecked(false);
        // پاک کردن داده‌ها از SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        if (editor.commit()) {
            // پیام موفقیت
            Toast.makeText(this, "داده ها با موفقیت پاک شد!", Toast.LENGTH_SHORT).show();
        } else {
            // پیام خطا
            Toast.makeText(this, "داده ها پاک نشد", Toast.LENGTH_SHORT).show();
        }
    }

    //menu
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_aboutUs) {
            startActivity(new Intent(getApplicationContext(), aboutUS.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        return super.onContextItemSelected(item);
    }
}



