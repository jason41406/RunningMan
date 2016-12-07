package com.example.yu_chan.runningman;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yu_chan.runningman.data.MemberDbHelper;
import static com.example.yu_chan.runningman.data.MemberContract.MemberEntry;


/**
 * Created by Yu_Chan on 2016/11/3.
 */
public class EditorActivity extends AppCompatActivity {
    private EditText mNameEditText ;
    private Spinner mGenderSpinner;
    private EditText mAgeEditText;
    private EditText mHeightEditText;
    private EditText mWeightEditText;
    private EditText mAccountEditText;
    private EditText mPasswordEditText;
    private int mGender = MemberEntry.GENDER_UNKNOWN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_name);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        mAgeEditText = (EditText) findViewById(R.id.edit_age);
        mHeightEditText =(EditText) findViewById(R.id.edit_height);
        mWeightEditText = (EditText) findViewById(R.id.edit_weight);
        mAccountEditText = (EditText) findViewById(R.id.edit_account);
        mPasswordEditText = (EditText) findViewById(R.id.edit_password);
        setupSpinner();
    }
    private void setupSpinner() {

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public int mGender;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = MemberEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = MemberEntry.GENDER_FEMALE;
                    } else {
                        mGender = MemberEntry.GENDER_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = MemberEntry.GENDER_UNKNOWN;
            }
        });
    }
//  End setupSpinner

    private void insertMember() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String ageString = mAgeEditText.getText().toString().trim();
        int age = Integer.parseInt(ageString);

        String heightString = mHeightEditText.getText().toString().trim();
        int height = Integer.parseInt(heightString);
        String weightString = mWeightEditText.getText().toString().trim();
        int weight = Integer.parseInt(weightString);
        String accountString = mAccountEditText.getText().toString().trim();
        String passwordString = mPasswordEditText.getText().toString().trim();



        // Create database helper
        MemberDbHelper mDbHelper = new MemberDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(MemberEntry.COLUMN_NAME, nameString);
        values.put(MemberEntry.COLUMN_GENDER, mGender);
        values.put(MemberEntry.COLUMN_AGE, age);
        values.put(MemberEntry.COLUMN_HEIGHT, height);
        values.put(MemberEntry.COLUMN_WEIGHT, weight);
        values.put(MemberEntry.COLUMN_ACCOUNT, accountString);
        values.put(MemberEntry.COLUMN_PASSWORD, passwordString);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(MemberEntry.TABLE_NAME, null, values);
        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving Members", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Members saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                insertMember();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
