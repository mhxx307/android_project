package com.minhquan.pokemonracing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CheckBox cbOne, cbTwo, cbThree;
    SeekBar sbOne, sbTwo, sbThree;
    ImageButton btnPlay;
    TextView txtDiemSo;
    int soDiem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        txtDiemSo.setText(soDiem + "");

        sbOne.setEnabled(false);
        sbTwo.setEnabled(false);
        sbThree.setEnabled(false);

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long l) {
                int number = 3;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                if (sbOne.getProgress() >= sbOne.getMax()) {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, cbOne.getText() + " win", Toast.LENGTH_LONG).show();

                    if (cbOne.isChecked()) {
                        soDiem += 10;
                    } else {
                        soDiem -= 10;
                    }
                    txtDiemSo.setText(soDiem + "");
                    enableCheckbox();

                } else if (sbTwo.getProgress() >= sbTwo.getMax()) {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, cbTwo.getText() + " win", Toast.LENGTH_LONG).show();

                    if (cbTwo.isChecked()) {
                        soDiem += 10;
                    } else {
                        soDiem -= 10;
                    }
                    txtDiemSo.setText(soDiem + "");
                    enableCheckbox();

                } else if (sbThree.getProgress() >= sbThree.getMax()) {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, cbThree.getText() + " win", Toast.LENGTH_LONG).show();

                    if (cbThree.isChecked()) {
                        soDiem += 10;
                    } else {
                        soDiem -= 10;
                    }
                    txtDiemSo.setText(soDiem + "");
                    enableCheckbox();

                }

                sbOne.setProgress(sbOne.getProgress() + one);
                sbTwo.setProgress(sbTwo.getProgress() + two);
                sbThree.setProgress(sbThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) {
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);

                    btnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();

                    disableCheckbox();
                } else {
                    Toast.makeText(MainActivity.this, "Please, chosen your pokemon to racing", Toast.LENGTH_LONG).show();
                }
            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbTwo.setChecked(false);
                    cbOne.setChecked(false);
                }
            }
        });
    }

    private void anhXa() {
        cbOne = findViewById(R.id.cbOne);
        cbTwo = findViewById(R.id.cbTwo);
        cbThree = findViewById(R.id.cbThree);
        sbOne = findViewById(R.id.sbOne);
        sbTwo = findViewById(R.id.sbTwo);
        sbThree = findViewById(R.id.sbThree);
        btnPlay = findViewById(R.id.btnPlay);
        txtDiemSo = findViewById(R.id.diemSo);
    }

    private void disableCheckbox() {
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    private void enableCheckbox() {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }
}