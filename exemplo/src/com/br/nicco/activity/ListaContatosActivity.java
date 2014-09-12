package com.br.nicco.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.br.nicco.adapter.AdapterListaDeContatos;
import com.br.nicco.dao.BD;
import com.br.nicco.model.Contato;
import com.example.exemplo.R;

public class ListaContatosActivity extends Activity {

	private ListView list;
	private TextView textView;
	private TextView numeroDeContatos;
	private ArrayAdapter<String> listAdapter;
	private List<Contato> listaDeContatos;
	private String phoneNo = null;
	private int tamanho;
	Contato contato;
	private String nome;

	private List<Contato> listaContato;

	@Override
	protected void onCreate(Bundle iclice) {
		super.onCreate(iclice);
		setContentView(R.layout.lista_contatos_existentes);
		textView = (TextView) findViewById(R.id.texto);
		numeroDeContatos = (TextView) findViewById(R.id.numeroDeContatos);
		list = (ListView) findViewById(R.id.lista);

		listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

		GetContacts2();

	}

	private void GetContacts2() {

		contato = new Contato(null, null);
		listaDeContatos = new ArrayList<Contato>();
		listaContato = new ArrayList<Contato>();
		try {
			ContentResolver cr = getContentResolver();
			Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

			if (cur.getCount() > 0) {
				while (cur.moveToNext()) {
					String contactId = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
					if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
						if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
							Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { contactId }, null);
							while (pCur.moveToNext()) {
								nome = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
								phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

								listAdapter.add("Nome do contato: " + nome + " Numero: " + phoneNo);

							}

							Contato contato = new Contato(nome, phoneNo);
							contato.setNome(nome);
							contato.setTelefone(phoneNo);

							listaDeContatos.add(contato);
							phoneNo = null;
							pCur.close();
						}
					}
				}
			}
			tamanho = listaDeContatos.size();
			String valor = String.valueOf(tamanho);
			numeroDeContatos.setText(valor);
			list.setAdapter(new AdapterListaDeContatos(getBaseContext(), listaDeContatos));
			
			BD bd = new BD(this);
			
			try {
				for(Contato c : listaDeContatos){
					if(!bd.CheckIsDataAlreadyInDBorNot("contatos",c.getNome(),c.getNome())){
						bd.inserir(c);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
//			dh = new DatabaseHelper(ListaContatosActivity.this);
//
//			try {
//				contatoDao = new ContatoDao(dh.getConnectionSource());
//				for (Contato c : listaDeContatos) {
//					contatoDao.createOrUpdate(c);
//				}
//				listaContato = contatoDao.queryForAll();
//				for (Contato c2 : listaContato) {
//					Log.i("Tabela", "Name " +c2.getNome()+" Telefone: "+ c2.getTelefone()+" Id:"+c2.getId()+"\n");
//				}		
//						
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}

		} catch (Exception e) {
		}

	}

}
