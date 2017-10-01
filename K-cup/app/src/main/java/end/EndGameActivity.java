package end;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kcup.drinkgame.k_cup.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static dialog.homeOptionActivity.FACEBOOK_PAGE_ID;
import static dialog.homeOptionActivity.FACEBOOK_URL;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_end);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        Button endButton = (Button) findViewById(R.id.end_button);
        endButton.setTypeface(typeface);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);

        String[] endArray = getResources().getStringArray(R.array.end_array);
        List<String> end = new ArrayList<>();
        Collections.addAll(end, endArray);

        Collections.shuffle(end);

        TextView content = (TextView) findViewById(R.id.content_end);
        content.setTypeface(typeface);
        content.setText(end.get(0));

        TextView optionTitle = (TextView) findViewById(R.id.option_title);
        optionTitle.setTypeface(typeface);

        final Button optionButton = (Button) findViewById(R.id.option_button);
        optionButton.setTypeface(typeface);

        Random r = new Random();
        int randomOption = r.nextInt(3) + 1;
        String optionContent;

        if (randomOption == 1) {
            optionContent = getString(R.string.dialog_option_facebook);
        } else if (randomOption == 2) {
            optionContent = getString(R.string.dialog_option_mail);
        } else {
            optionContent = getString(R.string.dialog_option_appli);
        }

        optionButton.setText(optionContent);

        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optionButton.getText().toString().equals(getString(R.string.dialog_option_facebook))) {
                    try {
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        String facebookUrl = getFacebookPageURL(EndGameActivity.this);
                        facebookIntent.setData(Uri.parse(facebookUrl));
                        startActivity(facebookIntent);
                    } catch (Exception e) {
                        Toast.makeText(EndGameActivity.this, getString(R.string.error_open_facebook), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String[] TO = {"kcup-drink-game@hotmail.com"};
                    String[] CC = {""};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject_mail));

                    try {
                        startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(EndGameActivity.this, getString(R.string.error_send_mail), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);
        title.setAnimation(anim1);
        content.setAnimation(anim1);
        optionTitle.setAnimation(anim1);
        optionButton.setAnimation(anim1);
        endButton.setAnimation(anim1);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

}
