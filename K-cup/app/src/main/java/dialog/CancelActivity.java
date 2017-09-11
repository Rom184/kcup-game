package dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import utils.GameUtils;
import utils.TextUtils;

import static java.security.AccessController.getContext;


public class CancelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cancel);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setTypeface(typeface);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });


        Button notCancel = (Button) findViewById(R.id.not_cancel);
        notCancel.setTypeface(typeface);
        notCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.background);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final Bundle b = getIntent().getExtras();
            if (b != null) {
                String type = b.getString(GameUtils.EXTRA_TYPE);
                if (!TextUtils.isEmpty(type) && (type.equals(GameUtils.Type.CHALLENGE.toString()))) {
                    nestedScrollView.setBackgroundColor(getColor(R.color.red));
                    title.setTextColor(Color.WHITE);
                } else if (!TextUtils.isEmpty(type) && (type.equals(GameUtils.Type.CHOICE.toString()))) {
                    nestedScrollView.setBackgroundColor(getColor(R.color.blue));
                    title.setTextColor(Color.WHITE);
                } else if (!TextUtils.isEmpty(type) && (type.equals(GameUtils.Type.KCUPS.toString()))) {
                    nestedScrollView.setBackgroundColor(getColor(R.color.plum));
                } else if (!TextUtils.isEmpty(type) && (type.equals(GameUtils.Type.NEW_RULE.toString()))) {
                    nestedScrollView.setBackgroundColor(getColor(R.color.orange));
                } else if (!TextUtils.isEmpty(type) && (type.equals(GameUtils.Type.CHOICE.toString()))) {
                    nestedScrollView.setBackgroundColor(getColor(R.color.orange));
                }
            }
        }

    }

}
