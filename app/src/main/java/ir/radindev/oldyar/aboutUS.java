package ir.radindev.oldyar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class aboutUS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        CardView contact = findViewById(R.id.contact);
        contact.setOnClickListener(v -> {

            Intent sendtelegram = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.telegram_link)));
            startActivity(sendtelegram);

        });

        CardView email = findViewById(R.id.email);
        email.setOnClickListener(v -> {
            Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
            String mail = "mailto:" + getString(R.string.mail_id);
            sendEmail.setData(Uri.parse(mail));
            startActivity(sendEmail);
        });

        CardView instagram = findViewById(R.id.instagram);
        instagram.setOnClickListener(v -> {

            Intent sendinstagram = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.insta_link)));
            startActivity(sendinstagram);

        });

    }

    private String getAppVersion() {

        String result = "";
        try {
            result = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0)
                    .versionName;
            result = result.replaceAll("[a-zA-Z]|-", "");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}