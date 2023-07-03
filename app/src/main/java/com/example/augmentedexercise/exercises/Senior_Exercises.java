package com.example.augmentedexercise.exercises;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.augmentedexercise.R;
import com.example.augmentedexercise.bmi_module.BMI_Senior;

public class Senior_Exercises extends AppCompatActivity {

    ImageView btnBack;
    CardView btnWalk, btnArmCircle, btnSpineTwist, btnStandingMC, btnStepJacks, btnDoubleCE
             ,btnBackFistSTS, btnChairSquat, btnDoubleHipTouches, btnFlamingoStand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_exercises);

        btnBack = (ImageView) findViewById(R.id.backbtn);

        btnWalk = (CardView) findViewById(R.id.cdWalk);
        btnArmCircle = (CardView) findViewById(R.id.cdArmCircle);
        btnSpineTwist = (CardView) findViewById(R.id.cdSpineTwist);
        btnStandingMC = (CardView) findViewById(R.id.cdStandingMountainClimbing);
        btnStepJacks = (CardView) findViewById(R.id.cdStepJacks);
        btnDoubleCE = (CardView) findViewById(R.id.cdDoubleChessExpansion);
        btnBackFistSTS = (CardView) findViewById(R.id.cdBackFistSide);
        btnChairSquat = (CardView) findViewById(R.id.cdChairSquat);
        btnDoubleHipTouches = (CardView) findViewById(R.id.cdFlutterKicks);
        btnFlamingoStand = (CardView) findViewById(R.id.cdFlamingoStand);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Senior_Exercises.this, BMI_Senior.class);
                startActivity(intent);
            }
        });
        btnWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Walking_2ydrqpn77r"));
                startActivity(intent);
            }
        });
        btnArmCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Project_1_uso4asz9ii"));
                startActivity(intent);
            }
        });
        btnSpineTwist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/SpineTwist_vz7vvqaql4"));
                startActivity(intent);
            }
        });
        btnStandingMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/StandingMountainClimber_7ltjgqodei"));
                startActivity(intent);
            }
        });
        btnStepJacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/StepJacks_2llx3zoqh5"));
                startActivity(intent);
            }
        });
        btnDoubleCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Project_3_nonhct4smh"));
                startActivity(intent);
            }
        });
        btnBackFistSTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Project_2_cu4fot1xnn"));
                startActivity(intent);
            }
        });
        btnChairSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/ChairSquat_spuelqhkjg"));
                startActivity(intent);
            }
        });
        btnDoubleHipTouches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Project_2_fwd6eyh9af"));
                startActivity(intent);
            }
        });
        btnFlamingoStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/FlamingoStand_a8btjpw24s"));
                startActivity(intent);
            }
        });
    }
}