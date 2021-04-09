package br.edu.ifsp.scl.ads.pdm.sharedjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText nomeEt;
    private EditText emailEt;
    private CheckBox emailCk;
    private EditText telefoneEt;
    private RadioGroup telefoneRg;
    private RadioButton comercialRb;
    private RadioButton residencialRb;
    private CheckBox celularCk;
    private EditText celularEt;
    private RadioGroup sexoRg;
    private RadioButton masculinoRb;
    private RadioButton femininoRb;
    private EditText dataNascimentoEt;
    private Spinner formacaoSp;
    private EditText anoFormaturaEt;
    private EditText anoConclusaoEt;
    private EditText instituicaoEt;
    private EditText tituloMonografiaEt;
    private EditText orientadorEt;
    private EditText vagasInteresseEt;
    private Button salvarBt;
    private Button limparBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        formacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((TextView) view).getText().equals("Fundamental") || ((TextView) view).getText().equals("Médio")) {
                    anoFormaturaEt.setVisibility(View.VISIBLE);
                    anoConclusaoEt.setVisibility(View.GONE);
                    instituicaoEt.setVisibility(View.GONE);
                    tituloMonografiaEt.setVisibility(View.GONE);
                    orientadorEt.setVisibility(View.GONE);
                }
                if (((TextView) view).getText().equals("Graduação") || ((TextView) view).getText().equals("Especialização")) {
                    anoFormaturaEt.setVisibility(View.GONE);
                    anoConclusaoEt.setVisibility(View.VISIBLE);
                    instituicaoEt.setVisibility(View.VISIBLE);
                    tituloMonografiaEt.setVisibility(View.GONE);
                    orientadorEt.setVisibility(View.GONE);
                }
                if (((TextView) view).getText().equals("Mestrado") || ((TextView) view).getText().equals("Doutorado")) {
                    anoFormaturaEt.setVisibility(View.GONE);
                    anoConclusaoEt.setVisibility(View.VISIBLE);
                    instituicaoEt.setVisibility(View.VISIBLE);
                    tituloMonografiaEt.setVisibility(View.VISIBLE);
                    orientadorEt.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void onClickButton(View view) {
        switch(view.getId()) {
            case R.id.salvarBt:
                saveForm();
                break;
            case R.id.limparBt:
                cleanForm();
                break;
            case R.id.celularCk:
                checked();
            default:
                break;
        }
    }

    private void checked() {
        if (celularCk.isChecked()) {
            celularEt.setVisibility(View.VISIBLE);
        } else if (!celularCk.isChecked()) {
            celularEt.setVisibility(View.GONE);
            celularEt.setText("");
        }
    }

    private void saveForm() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Nome: ").append(nomeEt.getText()).append("\n");
        buffer.append("Email: ").append(emailEt.getText()).append("\n");

        //comercial/residencial
        switch (telefoneRg.getCheckedRadioButtonId()) {
            case R.id.comercialRb:
                buffer.append("Telefone comercial: ");
                break;
            case R.id.residencialRb:
                buffer.append("Telefone residencial: ");
                break;
        }
        buffer.append(telefoneEt.getText()).append("\n");

        //Checkbox celular
        if(celularCk.isChecked()){
            buffer.append("Celular: ").append(celularEt.getText()).append("\n");
        }

        //masculino/feminino
        switch (sexoRg.getCheckedRadioButtonId()) {
            case R.id.masculinoRb:
                buffer.append("Sexo: Masculino").append("\n");
                break;
            case R.id.femininoRb:
                buffer.append("Sexo: Feminino").append("\n");
                break;
        }
        buffer.append("Data de nascimento: ").append(dataNascimentoEt.getText()).append("\n");

        buffer.append("Formação: ").append(((TextView) formacaoSp.getSelectedView()).getText()).append("\n");

        //Spinner formacao
        if (formacaoSp.getSelectedItemPosition() == 0 || formacaoSp.getSelectedItemPosition() == 1) {
            buffer.append("Ano de formatura: ").append(anoFormaturaEt.getText()).append("\n");
        }
        if (formacaoSp.getSelectedItemPosition() == 2 || formacaoSp.getSelectedItemPosition() == 3) {
            buffer.append("Ano de conclusão: ").append(anoConclusaoEt.getText()).append("\n");
            buffer.append("Instituição: ").append(instituicaoEt.getText()).append("\n");
        }
        if (formacaoSp.getSelectedItemPosition() == 4 || formacaoSp.getSelectedItemPosition() == 5) {
            buffer.append("Ano de conclusão: ").append(anoConclusaoEt.getText()).append("\n");
            buffer.append("Instituição: ").append(instituicaoEt.getText()).append("\n");
            buffer.append("Título de monografia: ").append(tituloMonografiaEt.getText()).append("\n");
            buffer.append("Orientador: ").append(orientadorEt.getText()).append("\n");
        }

        buffer.append("Vagas de Interesse: ").append(vagasInteresseEt.getText()).append("\n");
        Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();
    }

    private void cleanForm() {
        nomeEt.setText("");
        emailEt.setText("");
        emailCk.setChecked(false);
        telefoneEt.setText("");
        comercialRb.setChecked(true);
        celularCk.setChecked(false);
        checked();
        celularEt.setText("");
        masculinoRb.setChecked(true);
        dataNascimentoEt.setText("");
        formacaoSp.setSelection(0);
        anoFormaturaEt.setText("");
        anoConclusaoEt.setText("");
        instituicaoEt.setText("");
        tituloMonografiaEt.setText("");
        orientadorEt.setText("");
        vagasInteresseEt.setText("");
    }

    private void bindViews() {
        nomeEt = findViewById(R.id.nomeEt);
        emailEt = findViewById(R.id.emailEt);
        emailCk = findViewById(R.id.emailCk);
        telefoneEt = findViewById(R.id.telefoneEt);
        telefoneRg = findViewById(R.id.telefoneRg);
        comercialRb = findViewById(R.id.comercialRb);
        residencialRb = findViewById(R.id.residencialRb);
        celularCk = findViewById(R.id.celularCk);
        celularEt = findViewById(R.id.celularEt);
        sexoRg = findViewById(R.id.sexoRg);
        masculinoRb = findViewById(R.id.masculinoRb);
        femininoRb = findViewById(R.id.femininoRb);
        dataNascimentoEt = findViewById(R.id.dataNascimentoEt);
        formacaoSp = findViewById(R.id.formacaoSp);
        anoFormaturaEt = findViewById(R.id.anoFormaturaEt);
        anoConclusaoEt = findViewById(R.id.anoConclusaoEt);
        instituicaoEt = findViewById(R.id.instituicaoEt);
        tituloMonografiaEt = findViewById(R.id.tituloMonografiaEt);
        orientadorEt = findViewById(R.id.orientadorEt);
        vagasInteresseEt = findViewById(R.id.vagasInteresseEt);
        salvarBt = findViewById(R.id.salvarBt);
        limparBt = findViewById(R.id.limparBt);
    }
}