package com.example.lenovo.uiinputcontrol;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ToggleButton tbOF;
    EditText etnName;
    TextView tvLang, tvGender, tvaAge, tvSB;
    CheckBox cbEng,cbHin;
    RadioGroup rg;
    RadioButton rbMale,rbFemale;
    Button bSave, bAlert, bProgress;
    Spinner sAge;
    SeekBar sb;
    Switch s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbOF = (ToggleButton) findViewById(R.id.toggleOnOff);
        etnName = (EditText) findViewById(R.id.editTextName);
        tvLang = (TextView) findViewById(R.id.textViewLanuage);
        tvGender = (TextView) findViewById(R.id.textViewGender);
        tvaAge = (TextView) findViewById(R.id.textViewAge);
        cbEng = (CheckBox) findViewById(R.id.checkBoxEng);
        cbHin = (CheckBox) findViewById(R.id.checkBoxHin);
       // rbFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
       // rbMale = (RadioButton) findViewById(R.id.radioButtonMale);
        bSave = (Button) findViewById(R.id.buttonSave);
        sAge = (Spinner) findViewById(R.id.spinnerAge);
        sb = (SeekBar) findViewById(R.id.seekBar);
        s = (Switch) findViewById(R.id.switchButton);
        tvSB=(TextView)findViewById(R.id.textViewSeekbar);

        cbHin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbHin.isChecked()){
                    Toast.makeText(MainActivity.this, "You checked the Hindi Language", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "You unchecked Hindi Language", Toast.LENGTH_SHORT).show();
            }
        });
        cbEng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbEng.isChecked()){
                    Toast.makeText(MainActivity.this, "You checked the English Language", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "You unchecked English Language", Toast.LENGTH_SHORT).show();
            }
        });

        tbOF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tbOF.isChecked()) {
                    Toast.makeText(MainActivity.this, "You turn ON the button", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "You turn OFF the button", Toast.LENGTH_SHORT).show();
            }
        });

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(s.isChecked()){
                    Toast.makeText(MainActivity.this,"Switch button ON",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Switch button OFF",Toast.LENGTH_SHORT).show();
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Content saved",Toast.LENGTH_SHORT).show();
            }
        });


        List<String> list=new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        // Create an ArrayAdapter using the string array and a default spinner layout
        //FOR LIST GIVEN ON JAVA FILE
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        //FOR LIST GIVEN IN STRING FILE
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
               // R.array.spinner_age_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sAge.setAdapter(adapter);


        //SEEKBAR AND TEXTVIEW
        tvSB.setText("covered : " + sb.getProgress() + "/" + sb.getMax());
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser)
            {
                progress = progresValue;
                tvSB.setText("Covered : " + progress + "/" + sb.getMax());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });




    }

//  EXIT BUTTON
    public void openAlert(View view) {
        AlertDialog.Builder a=new AlertDialog.Builder(this);
        a.setTitle("Exit");
        a.setMessage("Are you sure ?");
        a.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int i) {
                finish();
            }
        });

        a.setNegativeButton("No",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int i) {
                dialogInterface.dismiss();
            }
        });

        a.setCancelable(false); //it force user to choose one of the positive/negative button
                                //It forbiden the user get back by clicking in back ground activity
        a.show();

    }

    //PROGRESS BUTTON
    public void openProgress(View view) {
        ProgressDialog p=new ProgressDialog(this);
        p.setTitle("Downloading");
        p.setMessage("Never complete !\nPlease take action immediatly");
        p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        p.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        p.show();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked=((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.radioButtonFemale : if (checked) Toast.makeText(MainActivity.this,"Gender selected as Female",Toast.LENGTH_SHORT).show();
            break;
            case R.id.radioButtonMale : if (checked) Toast.makeText(MainActivity.this,"Gender selected as Male",Toast.LENGTH_SHORT).show();
            break;
         }
    }

/*
    public void spinnerSelect(View view) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_age_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sAge.setAdapter(adapter);

    }
    */
}
