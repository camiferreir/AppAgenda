package br.ulbra.appagenda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListarPessoasActitivity extends AppCompatActivity {

    private ListView listview;

    private PessoaDAO dao;

    private List<Pessoa> pessoas;
    private List<Pessoa> pessoasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoa);

        listview = findViewById(R.id.lvPessoas);

        dao = new PessoaDAO(this);

        pessoas = dao.obterTodos();

        pessoasFiltradas.addAll(pessoas);

        ArrayAdapter<Pessoa> adaptador =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        pessoasFiltradas
                );

        listview.setAdapter(adaptador);

        registerForContextMenu(listview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_principal, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        androidx.appcompat.widget.SearchView searchView =
                (androidx.appcompat.widget.SearchView)
                        searchItem.getActionView();

        searchView.setOnQueryTextListener(
                new androidx.appcompat.widget.SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        procuraPessoa(newText);

                        return true;
                    }
                });

        return true;
    }

    @Override
    public void onCreateContextMenu(
            ContextMenu menu,
            View v,
            ContextMenu.ContextMenuInfo menuInfo
    ) {

        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraPessoa(String nome) {

        pessoasFiltradas.clear();

        for (Pessoa p : pessoas) {

            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {

                pessoasFiltradas.add(p);
            }
        }

        listview.invalidateViews();
    }

    public void cadastrar(MenuItem item) {

        Intent it = new Intent(this, MainActivity.class);

        startActivity(it);
    }

    public void excluir(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo)
                        item.getMenuInfo();

        final Pessoa pessoaExcluir =
                pessoasFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja excluir " + pessoaExcluir.getNome() + "?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                pessoasFiltradas.remove(pessoaExcluir);
                                pessoas.remove(pessoaExcluir);

                                dao.excluir(pessoaExcluir);

                                listview.invalidateViews();
                            }
                        }).create();

        dialog.show();
    }

    public void atualizar(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo)
                        item.getMenuInfo();

        Pessoa pessoaAtualizar =
                pessoasFiltradas.get(menuInfo.position);

        Intent it = new Intent(this, MainActivity.class);

        it.putExtra("pessoa", pessoaAtualizar);

        startActivity(it);
    }

    @Override
    protected void onResume() {

        super.onResume();

        pessoas = dao.obterTodos();

        pessoasFiltradas.clear();

        pessoasFiltradas.addAll(pessoas);

        listview.invalidateViews();
    }
}