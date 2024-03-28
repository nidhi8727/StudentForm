package com.practice.nidhi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, ageEditText, yearEditText, emailEditText, contactEditText;
    RadioGroup courseRadioGroup;
    RadioButton selectedCourseRadioButton;

    DatePicker dp1;
    Button submitButton, clearButton, exitButton, demoAlertButton, deleteRecordButton;
    EditText marksEditText, graphicDesignMarksEditText, uiUxMarksEditText;
    TextView totalMarksValueTextView, graphicDesignTotalMarksTextView, uiUxTotalMarksTextView;
    Button calculateTotalButton;

    ListView listView;
    String[] semesters = {"Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8"};

    CheckBox dataStructuresCheckBox, graphicDesignCheckBox, uiUxCheckBox, dancing, singing, painting, coding;

    LinearLayout linearDataStructures, linearGraphicDesign, linearUiUx, totalMarksLinear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        nameEditText = findViewById(R.id.nameE);
        ageEditText = findViewById(R.id.ageE);
        yearEditText = findViewById(R.id.yearE);
        emailEditText = findViewById(R.id.emailE);
        contactEditText = findViewById(R.id.contactE);
        courseRadioGroup = findViewById(R.id.courseRadioGroup);
        marksEditText = findViewById(R.id.marksE);
        graphicDesignMarksEditText = findViewById(R.id.graphicDesignMarksE);
        uiUxMarksEditText = findViewById(R.id.uiUxMarksE);
        totalMarksValueTextView = findViewById(R.id.totalMarksValue);
//        graphicDesignTotalMarksTextView = findViewById(R.id.graphicDesignTotalMarksValue);
//        uiUxTotalMarksTextView = findViewById(R.id.uiUxTotalMarksValue);
        calculateTotalButton = findViewById(R.id.calculateTotalBtn);

        submitButton = findViewById(R.id.submit_btn);
        clearButton = findViewById(R.id.clear_btn);
        exitButton = findViewById(R.id.exit_btn);
        dp1 = findViewById(R.id.dp1);

        dataStructuresCheckBox = findViewById(R.id.DataStructures);
        graphicDesignCheckBox = findViewById(R.id.GraphicDesign);
        uiUxCheckBox = findViewById(R.id.UiUx);
        dancing = findViewById(R.id.Dancing);
        singing = findViewById(R.id.Singing);
        coding = findViewById(R.id.Coding);
        painting = findViewById(R.id.Painting);

        linearDataStructures = findViewById(R.id.dslinear);
        linearGraphicDesign = findViewById(R.id.gdlinear);
        linearUiUx = findViewById(R.id.uiuxlinear);
        totalMarksLinear = findViewById(R.id.totalmarks_linear);

        demoAlertButton = findViewById(R.id.b1);
        deleteRecordButton = findViewById(R.id.b2);



        dataStructuresCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                linearDataStructures.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                if (!isChecked) {
                    marksEditText.getText().clear();
                }
            }
        });

        graphicDesignCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                linearGraphicDesign.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                if (!isChecked) {
                    graphicDesignMarksEditText.getText().clear();
                }
            }
        });

        uiUxCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                linearUiUx.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                if (!isChecked) {
                    uiUxMarksEditText.getText().clear();
                }
            }
        });




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculate total marks
                calculateTotalMarks();

                // Display grades and percentage in a toast message
                StringBuilder toastMessage = new StringBuilder();
                toastMessage.append("Grades: ");
                toastMessage.append(calculateGrades());
                toastMessage.append("\nPercentage: ");
                toastMessage.append(calculatePercentage());
                Toast.makeText(MainActivity.this, toastMessage.toString(), Toast.LENGTH_LONG).show();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllFields();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exit the app
                finish();
            }
        });

        calculateTotalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculate total marks
                calculateTotalMarks();
            }
        });

        demoAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the demo alert dialog
                showDialog("Demo Alert", "This is demo alert.");
            }
        });

        deleteRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the delete record dialog
                showDialog("Delete Record", "Are you sure you want to delete this record?");
            }
        });

        listView = findViewById(R.id.listView);

        // Create an ArrayAdapter to populate the ListView with the semester items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, semesters);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Set up item click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked semester
                String selectedSemester = semesters[position];
                // Display a toast message with the selected semester
                Toast.makeText(MainActivity.this, selectedSemester, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public String getMyDate() {
        String s = "Current Date : ";
        s = s + dp1.getDayOfMonth() + "/";
        s = s + (dp1.getMonth() + 1) + "/";
        s = s + dp1.getYear();
        return s;
    }

    private void clearAllFields() {
        nameEditText.getText().clear();
        ageEditText.getText().clear();
        yearEditText.getText().clear();
        emailEditText.getText().clear();
        contactEditText.getText().clear();
        marksEditText.getText().clear();
        graphicDesignMarksEditText.getText().clear();
        uiUxMarksEditText.getText().clear();
        totalMarksValueTextView.setText("");
        dataStructuresCheckBox.setChecked(false);
        graphicDesignCheckBox.setChecked(false);
        uiUxCheckBox.setChecked(false);
        dancing.setChecked(false);
        singing.setChecked(false);
        painting.setChecked(false);
        coding.setChecked(false);
        linearDataStructures.setVisibility(View.GONE);
        linearGraphicDesign.setVisibility(View.GONE);
        linearUiUx.setVisibility(View.GONE);
        courseRadioGroup.clearCheck();
    }


    private void calculateTotalMarks() {
        // Check if any checkbox is checked
        if (dataStructuresCheckBox.isChecked() || graphicDesignCheckBox.isChecked() || uiUxCheckBox.isChecked()) {
            totalMarksLinear.setVisibility(View.VISIBLE);

            // Calculate total marks
            try {
                int dataStructuresMarks = 0, graphicDesignMarks = 0, uiUxMarks = 0;

                if (dataStructuresCheckBox.isChecked()) {
                    dataStructuresMarks = Integer.parseInt(marksEditText.getText().toString());
                }

                if (graphicDesignCheckBox.isChecked()) {
                    graphicDesignMarks = Integer.parseInt(graphicDesignMarksEditText.getText().toString());
                }

                if (uiUxCheckBox.isChecked()) {
                    uiUxMarks = Integer.parseInt(uiUxMarksEditText.getText().toString());
                }

                int totalMarks = dataStructuresMarks + graphicDesignMarks + uiUxMarks;

                // Display total marks
                totalMarksValueTextView.setText(String.valueOf(totalMarks));
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Please enter valid marks", Toast.LENGTH_SHORT).show();
            }
        } else {
            totalMarksLinear.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Please select at least one checkbox", Toast.LENGTH_SHORT).show();
        }
    }

    private String calculateGrades() {
        // Calculate total marks
        int totalMarks = 0;
        int maxMarks = 300;

        int dataStructuresMarks = dataStructuresCheckBox.isChecked() ? Integer.parseInt(marksEditText.getText().toString()) : 0;
        int graphicDesignMarks = graphicDesignCheckBox.isChecked() ? Integer.parseInt(graphicDesignMarksEditText.getText().toString()) : 0;
        int uiUxMarks = uiUxCheckBox.isChecked() ? Integer.parseInt(uiUxMarksEditText.getText().toString()) : 0;

        totalMarks = dataStructuresMarks + graphicDesignMarks + uiUxMarks;

        // Calculate percentage
        double percentage = (double) totalMarks / maxMarks * 100;

        // Calculate grades
        String grades;
        if (percentage >= 90) {
            grades = "A+";
        } else if (percentage >= 80) {
            grades = "A";
        } else if (percentage >= 70) {
            grades = "B+";
        } else if (percentage >= 60) {
            grades = "B";
        } else if (percentage >= 50) {
            grades = "C+";
        } else if (percentage >= 40) {
            grades = "C";
        } else if (percentage >= 35) {
            grades = "D";
        } else {
            grades = "F";
        }

        return grades;
    }

    private double calculatePercentage() {
        // Calculate total marks
        int totalMarks = 0;
        int maxMarks = 300;

        int dataStructuresMarks = dataStructuresCheckBox.isChecked() ? Integer.parseInt(marksEditText.getText().toString()) : 0;
        int graphicDesignMarks = graphicDesignCheckBox.isChecked() ? Integer.parseInt(graphicDesignMarksEditText.getText().toString()) : 0;
        int uiUxMarks = uiUxCheckBox.isChecked() ? Integer.parseInt(uiUxMarksEditText.getText().toString()) : 0;

        totalMarks = dataStructuresMarks + graphicDesignMarks + uiUxMarks;

        // Calculate percentage
        double percentage = (double) totalMarks / maxMarks * 100;

        return percentage;
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform any action here when OK is clicked
                dialog.dismiss(); // Dismiss the dialog
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform any action here when Cancel is clicked
                dialog.dismiss(); // Dismiss the dialog
            }
        });
        builder.show(); // Show the dialog
    }
}
