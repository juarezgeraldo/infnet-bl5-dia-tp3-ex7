package com.juarezjunior.festafimdeano.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.juarezjunior.festafimdeano.R;
import com.juarezjunior.festafimdeano.constant.FimDeAnoConstants;
import com.juarezjunior.festafimdeano.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.text_today = findViewById(R.id.text_today);
        this.mViewHolder.text_label_days_left = findViewById(R.id.text_label_days_left);
        this.mViewHolder.text_days_left = findViewById(R.id.text_days_left);
        this.mViewHolder.button_confirm = findViewById(R.id.button_confirm);
        this.mViewHolder.view_traco = findViewById(R.id.view_traco);
        this.mViewHolder.button_end = findViewById(R.id.button_end);

        this.mViewHolder.button_confirm.setOnClickListener(this);

        this.mViewHolder.button_end.setOnClickListener(this);

        this.mViewHolder.text_today.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this.mViewHolder.text_days_left.setText(daysLeft);

    }
/* ==== Estas funções representam o ciclo de vida das Activitys ====
    @Override
    protected void onStart() {
        super.onStart();
    }
*/
    @Override
    protected void onResume() {
        this.verifyPresence();

        super.onResume();
    }
/*
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
*/
    private void verifyPresence() {
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);
        if(presence.equals("")){
            this.mViewHolder.button_confirm.setText(getString(R.string.nao_confirmado));
        }
        else {
            if (presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mViewHolder.button_confirm.setText(getString(R.string.sim));
            }
            else {
                this.mViewHolder.button_confirm.setText(getString(R.string.nao));
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_confirm) {

            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);

            Intent intent = new Intent(this, NewDetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY, presence);

            startActivity(intent);
        }
        else {
            if(id == R.id.button_end){
                this.finish();
            }
        }
    }

    private int getDaysLeft() {
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - today;
    }

    private static class ViewHolder {
        TextView text_today;
        TextView text_label_days_left;
        TextView text_days_left;
        Button button_confirm;
        View view_traco;
        Button button_end;

    }
}
