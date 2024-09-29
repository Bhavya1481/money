package com.example.money;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ExpenseListActivity extends AppCompatActivity {

    private ListView listViewExpenses;
    //    private String[] expenses = {"Expense 1 - $50", "Expense 2 - $30"}; // Example data
    private TextView textViewPerson;
    private ArrayList<String>expenses;
    private String selectedPerson;
    //    private String
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        listViewExpenses = findViewById(R.id.listview_expenses);
        textViewPerson = findViewById(R.id.tv_person);

        selectedPerson = getIntent().getStringExtra("selected_person");
        textViewPerson.setText("Transactions with " + selectedPerson);


        // Retrieve saved expenses
        SharedPreferences sharedPreferences = getSharedPreferences("Expenses", Context.MODE_PRIVATE);
        String savedExpenses = sharedPreferences.getString(selectedPerson, "");

        // Convert the saved expenses into a list
        if (!savedExpenses.isEmpty()) {
            String[] expenseArray = savedExpenses.split("\n");
            ArrayList<String> expenses = new ArrayList<>(Arrays.asList(expenseArray));





            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenses);
            listViewExpenses.setAdapter(adapter);
        }
    }
}
