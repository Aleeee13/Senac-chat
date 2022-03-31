package ale.banco;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Listar extends Activity {
    private TextView msg;
    private Gerenciador dbm;
    private Listardados ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        dbm = new Gerenciador(getApplicationContext());
        msg = findViewById(R.id.text);

        atualizaLista();

    }

    public void atualizaLista() {
        // Criando Lista de nomes
        ArrayList<String> lNomes = new ArrayList<>();
        Cursor dados = dbm.selectRecords();
        while (!dados.isLast()) {
            String registro = "Cod: " + dados.getString(0);
            registro += " - Nome: " + dados.getString(1);
            lNomes.add(registro);
            dados.moveToNext();
        }

        // Atualizando RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ld = new Listardados(this, lNomes);
        ld.setClickListener(this::onItemClick);
        recyclerView.setAdapter(ld);
    }

    public void onItemClick(View view, int position) {
        msg.setText("Você clicou em: " + ld.getItem(position) + " N: " + position);
    }

}