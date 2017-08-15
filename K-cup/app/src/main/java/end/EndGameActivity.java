package end;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EndGameActivity extends AppCompatActivity{

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
    }

}
