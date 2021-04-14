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

import br.edu.ifsp.scl.ads.pdm.sharedjobs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.formacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = ((TextView) view).getText().toString();
                if (itemSelected.equals("Fundamental") || itemSelected.equals("Médio")) {
                    cleanFieldsFormacao();
                    activityMainBinding.anoFormaturaEt.setVisibility(View.VISIBLE);
                    activityMainBinding.anoConclusaoEt.setVisibility(View.GONE);
                    activityMainBinding.instituicaoEt.setVisibility(View.GONE);
                    activityMainBinding.tituloMonografiaEt.setVisibility(View.GONE);
                    activityMainBinding.orientadorEt.setVisibility(View.GONE);
                }
                if (itemSelected.equals("Graduação") || itemSelected.equals("Especialização")) {
                    cleanFieldsFormacao();
                    activityMainBinding.anoFormaturaEt.setVisibility(View.GONE);
                    activityMainBinding.anoConclusaoEt.setVisibility(View.VISIBLE);
                    activityMainBinding.instituicaoEt.setVisibility(View.VISIBLE);
                }
                if (itemSelected.equals("Mestrado") || itemSelected.equals("Doutorado")) {
                    cleanFieldsFormacao();
                    activityMainBinding.tituloMonografiaEt.setVisibility(View.VISIBLE);
                    activityMainBinding.orientadorEt.setVisibility(View.VISIBLE);
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

    private void cleanFieldsFormacao() {
        activityMainBinding.anoFormaturaEt.setText("");
        activityMainBinding.anoConclusaoEt.setText("");
        activityMainBinding.instituicaoEt.setText("");
        activityMainBinding.tituloMonografiaEt.setText("");
        activityMainBinding.orientadorEt.setText("");
    }


    private void checked() {
        if (activityMainBinding.celularCk.isChecked()) {
            activityMainBinding.celularEt.setVisibility(View.VISIBLE);
        } else if (!activityMainBinding.celularCk.isChecked()) {
            activityMainBinding.celularEt.setVisibility(View.GONE);
            activityMainBinding.celularEt.setText("");
        }
    }

    private void saveForm() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Nome: ").append(activityMainBinding.nomeEt.getText()).append("\n");
        buffer.append("Email: ").append(activityMainBinding.emailEt.getText()).append("\n");

        //comercial/residencial
        switch (activityMainBinding.telefoneRg.getCheckedRadioButtonId()) {
            case R.id.comercialRb:
                buffer.append("Telefone comercial: ");
                break;
            case R.id.residencialRb:
                buffer.append("Telefone residencial: ");
                break;
        }
        buffer.append(activityMainBinding.telefoneEt.getText()).append("\n");

        //Checkbox celular
        if(activityMainBinding.celularCk.isChecked()){
            buffer.append("Celular: ").append(activityMainBinding.celularEt.getText()).append("\n");
        }

        //masculino/feminino
        switch (activityMainBinding.sexoRg.getCheckedRadioButtonId()) {
            case R.id.masculinoRb:
                buffer.append("Sexo: Masculino").append("\n");
                break;
            case R.id.femininoRb:
                buffer.append("Sexo: Feminino").append("\n");
                break;
        }
        buffer.append("Data de nascimento: ").append(activityMainBinding.dataNascimentoEt.getText()).append("\n");

        buffer.append("Formação: ").append(((TextView) activityMainBinding.formacaoSp.getSelectedView()).getText()).append("\n");

        //Spinner formacao
        int formacaoIdx = activityMainBinding.formacaoSp.getSelectedItemPosition();
        if (formacaoIdx == 0 || formacaoIdx == 1) {
            buffer.append("Ano de formatura: ").append(activityMainBinding.anoFormaturaEt.getText()).append("\n");
        }
        if (formacaoIdx == 2 || formacaoIdx == 3) {
            buffer.append("Ano de conclusão: ").append(activityMainBinding.anoConclusaoEt.getText()).append("\n");
            buffer.append("Instituição: ").append(activityMainBinding.instituicaoEt.getText()).append("\n");
        }
        if (formacaoIdx == 4 || formacaoIdx == 5) {
            buffer.append("Ano de conclusão: ").append(activityMainBinding.anoConclusaoEt.getText()).append("\n");
            buffer.append("Instituição: ").append(activityMainBinding.instituicaoEt.getText()).append("\n");
            buffer.append("Título de monografia: ").append(activityMainBinding.tituloMonografiaEt.getText()).append("\n");
            buffer.append("Orientador: ").append(activityMainBinding.orientadorEt.getText()).append("\n");
        }

        buffer.append("Vagas de Interesse: ").append(activityMainBinding.vagasInteresseEt.getText()).append("\n");
        Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();
    }

    private void cleanForm() {
        activityMainBinding.nomeEt.setText("");
        activityMainBinding.emailEt.setText("");
        activityMainBinding.emailCk.setChecked(false);
        activityMainBinding.telefoneEt.setText("");
        activityMainBinding.comercialRb.setChecked(true);
        activityMainBinding.celularCk.setChecked(false);
        checked();
        activityMainBinding.celularEt.setText("");
        activityMainBinding.masculinoRb.setChecked(true);
        activityMainBinding.dataNascimentoEt.setText("");
        activityMainBinding.formacaoSp.setSelection(0);
        activityMainBinding.anoFormaturaEt.setText("");
        activityMainBinding.anoConclusaoEt.setText("");
        activityMainBinding.instituicaoEt.setText("");
        activityMainBinding.tituloMonografiaEt.setText("");
        activityMainBinding.orientadorEt.setText("");
        activityMainBinding.vagasInteresseEt.setText("");
    }

}