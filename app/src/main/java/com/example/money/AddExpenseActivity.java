package com.example.money;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etExpenseTitle, etExpenseAmount, etPerson1Amount, etPerson2Amount;
    private RadioGroup radioGroup;
    private RadioButton rbEqual, rbUnequal;
    private LinearLayout llUnequalSplit;
    private Button btnSubmitExpense;
    private String selectedPerson;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etExpenseTitle = findViewById(R.id.et_expense_title);
        etExpenseAmount = findViewById(R.id.et_expense_amount);
        etPerson1Amount = findViewById(R.id.et_person1_amount);
        etPerson2Amount = findViewById(R.id.et_person2_amount);
        radioGroup = findViewById(R.id.radioGroup);
        rbEqual = findViewById(R.id.rb_equal);
        rbUnequal = findViewById(R.id.rb_unequal);
        llUnequalSplit = findViewById(R.id.ll_unequal_split);
        btnSubmitExpense = findViewById(R.id.btn_submit_expense);

        selectedPerson = getIntent().getStringExtra("selected_person");
        if (selectedPerson == null) {
            Toast.makeText(this, "No person selected", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        setTitle("Add Expense for " + selectedPerson);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_unequal) {
                llUnequalSplit.setVisibility(View.VISIBLE);
                // Optionally adjust the width of EditText fields if needed
                LayoutParams params = new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                etPerson1Amount.setLayoutParams(params);
                etPerson2Amount.setLayoutParams(params);
            } else {
                llUnequalSplit.setVisibility(View.GONE);
            }
        });

        btnSubmitExpense.setOnClickListener(v -> {
            String expenseTitle = etExpenseTitle.getText().toString().trim();
            String totalAmountStr = etExpenseAmount.getText().toString().trim();

            if (expenseTitle.isEmpty() || totalAmountStr.isEmpty()) {
                Toast.makeText(AddExpenseActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                return;
            }

            double totalAmount;
            try {
                totalAmount = Double.parseDouble(totalAmountStr);
            } catch (NumberFormatException e) {
                Toast.makeText(AddExpenseActivity.this, "Invalid total amount", Toast.LENGTH_SHORT).show();
                return;
            }

            if (rbEqual.isChecked()) {
                double splitAmount = totalAmount / 2;
                String expenseDescription = expenseTitle + " - $" + totalAmount + " (Equal: $" + splitAmount + " each)";
                saveExpense(selectedPerson, expenseDescription);
                Toast.makeText(this, "Expense added: " + expenseDescription, Toast.LENGTH_SHORT).show();
                finish();
            } else if (rbUnequal.isChecked()) {
                String person1AmountStr = etPerson1Amount.getText().toString().trim();
                String person2AmountStr = etPerson2Amount.getText().toString().trim();

                if (person1AmountStr.isEmpty() || person2AmountStr.isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please enter amounts for both people", Toast.LENGTH_SHORT).show();
                    return;
                }

                double person1Amount;
                double person2Amount;
                try {
                    person1Amount = Double.parseDouble(person1AmountStr);
                    person2Amount = Double.parseDouble(person2AmountStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(AddExpenseActivity.this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (person1Amount + person2Amount != totalAmount) {
                    Toast.makeText(AddExpenseActivity.this, "Amounts don't add up to total!", Toast.LENGTH_SHORT).show();
                } else {
                    String expenseDescription = expenseTitle + " - $" + totalAmount +
                            " (Unequal: Person 1: $" + person1Amount + ", Person 2: $" + person2Amount + ")";
                    saveExpense(selectedPerson, expenseDescription);
                    Toast.makeText(this, "Expense added: " + expenseDescription, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void saveExpense(String person, String expenseDescription) {
        SharedPreferences sharedPreferences = getSharedPreferences("Expenses", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String existingExpenses = sharedPreferences.getString("all_expenses", "");
//      String existingExpenses = sharedPreferences.getString(person, "");


        String updatedExpenses = existingExpenses + expenseDescription + "\n";
//        editor.putString(person, updatedExpenses);
//        editor.apply();

//        String newExpense = selectedPerson + ": " + expenseTitle + " - $" + totalAmount;
        editor.putString("all_expenses", existingExpenses + updatedExpenses + "\n");
        editor.apply();
    }
}

