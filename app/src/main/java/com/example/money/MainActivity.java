//package com.example.money_management;
//
//import android.annotation.SuppressLint;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.Toast;
//import android.widget.SearchView;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    private ListView listViewPersons;
//    private Button btnAddExpense;
//    private List<String> peopleList = new ArrayList<>();
//    private String selectedPerson;
//    private View btnRecords;
//    private Button btnAddGroup;
//    private ImageButton btnAddPerson;  // Button to add a new person
//    private ArrayAdapter<String> adapter;
//    private SearchView searchView;
//    private ArrayList<String> personList;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnRecords = findViewById(R.id.list_btn);
//        listViewPersons = findViewById(R.id.listview_persons);
//        btnAddExpense = findViewById(R.id.btn_add_expense);
//        btnAddGroup = findViewById(R.id.btn_add_group);
//        btnAddPerson = findViewById(R.id.btn_add_person);
//        searchView = findViewById(R.id.search_view);
//
//
//        SharedPreferences sharedPreferences = getSharedPreferences("Persons", MODE_PRIVATE);
//        String persons = sharedPreferences.getString("person_list", "");
//
//        if (!persons.isEmpty()) {
//            personList = new ArrayList<>();
//            String[] personArray = persons.split("\n");
//            for (String person : personArray) {
//                personList.add(person);
//            }
//        }
//
//        public boolean onCreateOptionsMenu(Menu menu){
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.main_menu, menu);
//            MenuItem searchItem = menu.findItem(R.id.action_search);
//            SearchView searchView = (SearchView) searchItem.getActionView();
//
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    // Start SearchActivity with the query
//                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                    intent.putExtra("search_query", query);
//                    startActivity(intent);
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    return false;
//                }
//            });
//            return true;
//        }
//
//
//
//
//
//        // Initialize adapter with the people list
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, peopleList);
//        listViewPersons.setAdapter(adapter);
//
//        listViewPersons.setOnItemClickListener((parent, view, position, id) -> {
//            selectedPerson = (String) parent.getItemAtPosition(position);
//        });
//
//        btnAddExpense.setOnClickListener(v -> {
//            if (selectedPerson == null) {
//                Toast.makeText(MainActivity.this, "Please select a person first", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
//            intent.putExtra("selected_person", selectedPerson);
//            startActivity(intent);
//        });
//
//
//
//        btnRecords.setOnClickListener(v -> {
//            if (selectedPerson == null) {
//                Toast.makeText(MainActivity.this, "Please select a person first", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            Intent intent = new Intent(MainActivity.this, ExpenseListActivity.class);
//            intent.putExtra("selected_person", selectedPerson);
//            startActivity(intent);
//        });
//
//        btnAddGroup.setOnClickListener(v -> {
////            if (selectedPerson == null) {
////                Toast.makeText(MainActivity.this, "Please select a person first", Toast.LENGTH_SHORT).show();
////                return;
////            }
//            Intent intent = new Intent(MainActivity.this, GroupActivity.class);
////            intent.putExtra("selected_person", selectedPerson);
//            startActivity(intent);
//        });
//
//        // Add person functionality
//        btnAddPerson.setOnClickListener(v -> {
//            showAddPersonDialog();  // Show dialog to add a new person
//        });
//    }
//
//    // Method to show a dialog for adding a new person
//    private void showAddPersonDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add Person");
//
//        // Set up the input
//        final EditText input = new EditText(this);
//        input.setHint("Enter person's name");
//        builder.setView(input);
//
//        // Set up the buttons
//        builder.setPositiveButton("Add", (dialog, which) -> {
//            String newPerson = input.getText().toString().trim();
//            if (!newPerson.isEmpty()) {
//                peopleList.add(newPerson);  // Add new person to the list
//                adapter.notifyDataSetChanged();  // Notify adapter to refresh ListView
//            } else {
//                Toast.makeText(MainActivity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
//
//        builder.show();
//    }
//}
















//package com.example.money;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.SearchView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//public class MainActivity extends AppCompatActivity {
//
//    private ListView listViewPersons;
//    private Button btnAddPerson, btnRecords, btnAddExpense;
//    private EditText etNewPerson;
//    private ArrayList<String> personList;
//    private ArrayAdapter<String> personAdapter;
//    private SharedPreferences sharedPreferences;
//    private static final String PREFS_PERSON_LIST = "PersonList";
//
//    @SuppressLint("WrongViewCast")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        //        btnRecords = findViewById(R.id.list_btn);
////        listViewPersons = findViewById(R.id.listview_persons);
////        btnAddExpense = findViewById(R.id.btn_add_expense);
////        btnAddGroup = findViewById(R.id.btn_add_group);
////        btnAddPerson = findViewById(R.id.btn_add_person);
////        searchView = findViewById(R.id.search_view);
//
//
//
//        btnAddExpense = findViewById(R.id.btn_add_expense);
//        listViewPersons = findViewById(R.id.listview_persons);
//        btnAddPerson = findViewById(R.id.btn_add_person);
//        btnRecords = findViewById(R.id.list_btn);
//        etNewPerson = findViewById(R.id.et_new_person);
//
//        sharedPreferences = getSharedPreferences(PREFS_PERSON_LIST, Context.MODE_PRIVATE);
//
//        // Load the person list from SharedPreferences
//        personList = new ArrayList<>(getSavedPersonList());
//
//        personAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personList);
//        listViewPersons.setAdapter(personAdapter);
//
//        btnAddExpense.setOnClickListener(v -> {
////            if (selectedPerson == null) {
////               Toast.makeText(MainActivity.this, "Please select a person first", Toast.LENGTH_SHORT).show();
////               return;
////            }
//            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
////            intent.putExtra("selected_person", selectedPerson);
//            startActivity(intent);
//        });
//
//        // Add a new person
//        btnAddPerson.setOnClickListener(v -> {
//            String newPerson = etNewPerson.getText().toString().trim();
//            if (!newPerson.isEmpty()) {
//                personList.add(newPerson);
//                personAdapter.notifyDataSetChanged();
//                etNewPerson.setText("");
//
//                // Save the updated list
//                savePersonList(personList);
//            } else {
//                Toast.makeText(MainActivity.this, "Please enter a person's name", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Handle selecting a person from the list to add an expense
//        listViewPersons.setOnItemClickListener((parent, view, position, id) -> {
//            String selectedPerson = personList.get(position);
//            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
//            intent.putExtra("selected_person", selectedPerson);
//            startActivity(intent);
//        });
//
//        // View all records
////        btnViewRecords
//        btnRecords.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ExpenseListActivity.class);
//            startActivity(intent);
//        });
//    }
//
////    // Add the search functionality
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.main_menu, menu);
////
////        MenuItem searchItem = menu.findItem(R.id.action_search);
////        SearchView searchView = (SearchView) searchItem.getActionView();
////
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
////                intent.putExtra("search_query", query);
////                startActivity(intent);
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////                return false;
////            }
////        });
////        return true;
////    }
//
//    private Set<String> getSavedPersonList() {
//        return sharedPreferences.getStringSet("person_list", new HashSet<>());
//    }
//
//    private void savePersonList(ArrayList<String> personList) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Set<String> set = new HashSet<>(personList);
//        editor.putStringSet("person_list", set);
//        editor.apply();
//    }
//}











package com.example.money;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPersons;
    private EditText etNewPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        listViewPersons = findViewById(R.id.listview_persons);
        etNewPerson = findViewById(R.id.et_new_person);

        // Set up Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_add_expense:
                    Toast.makeText(MainActivity.this, "Add Expense Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_records:
                    Toast.makeText(MainActivity.this, "Records Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_add_group:
                    Toast.makeText(MainActivity.this, "Add Group Clicked", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
    }

    // Inflate the Toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // Handle Toolbar menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_person) {
            String personName = etNewPerson.getText().toString();
            Toast.makeText(this, "Person Added: " + personName, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
