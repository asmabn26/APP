package com.example.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.classes.PersonRecord;
import com.example.app.service.PersonService;

public class MainActivity extends AppCompatActivity {

    private EditText inputNom;
    private EditText inputPrenom;
    private EditText inputId;
    private Button btnAjouter;
    private Button btnChercher;
    private Button btnSupprimer;
    private TextView resultat;

    private PersonService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new PersonService(this);

        inputNom = findViewById(R.id.inputNom);
        inputPrenom = findViewById(R.id.inputPrenom);
        inputId = findViewById(R.id.inputId);

        btnAjouter = findViewById(R.id.btnAjouter);
        btnChercher = findViewById(R.id.btnChercher);
        btnSupprimer = findViewById(R.id.btnSupprimer);

        resultat = findViewById(R.id.resultat);

        // AJOUT
        btnAjouter.setOnClickListener(v -> {
            String nom = inputNom.getText().toString().trim();
            String prenom = inputPrenom.getText().toString().trim();

            if (nom.isEmpty() || prenom.isEmpty()) {
                Toast.makeText(MainActivity.this, "Champs requis", Toast.LENGTH_SHORT).show();
                return;
            }

            service.insertPerson(new PersonRecord(nom, prenom));

            inputNom.setText("");
            inputPrenom.setText("");

            Log.d("UI", "Ajout effectué");
            Toast.makeText(MainActivity.this, "Ajout réussi", Toast.LENGTH_SHORT).show();
        });

        // CHERCHER
        btnChercher.setOnClickListener(v -> {
            String idStr = inputId.getText().toString().trim();

            if (idStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Entrer un ID", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                PersonRecord p = service.getPersonById(id);

                if (p != null) {
                    String info = p.getLastName() + " " + p.getFirstName();
                    resultat.setText(info);
                } else {
                    resultat.setText("");
                    Toast.makeText(MainActivity.this, "Introuvable", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "ID invalide", Toast.LENGTH_SHORT).show();
            }
        });

        // SUPPRIMER
        btnSupprimer.setOnClickListener(v -> {
            String idStr = inputId.getText().toString().trim();

            if (idStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Entrer un ID", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                service.deletePerson(id);
                resultat.setText("");
                Toast.makeText(MainActivity.this, "Supprimé", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "ID invalide", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
