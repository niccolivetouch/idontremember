package com.br.nicco.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.nicco.model.Contato;

public class BD {
	private static SQLiteDatabase bd;

	public BD(Context context) {
		BDCore auxBd = new BDCore(context);
		bd = auxBd.getWritableDatabase();
	}

	public void inserir(Contato contato) {
		ContentValues valores = new ContentValues();
		valores.put("nome", contato.getNome());
		valores.put("telefone", contato.getTelefone());

		bd.insert("contatos", null, valores);
	}

	public void atualizar(Contato contato) {
		ContentValues valores = new ContentValues();
		valores.put("nome", contato.getNome());
		valores.put("telefone", contato.getTelefone());

		bd.update("contatos", valores, "_id = ?", new String[] { "" + contato.getId() });
	}

	public void deletar(Contato contato) {
		bd.delete("contatos", "_id = " + contato.getId(), null);
	}

	public List<Contato> buscar() {
		List<Contato> list = new ArrayList<Contato>();
		String[] colunas = new String[] { "_id", "nome", "telefone" };

		Cursor cursor = bd.query("contatos", colunas, null, null, null, null, "nome ASC");
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				Contato u = new Contato();
				u.setId(cursor.getLong(0));
				u.setNome(cursor.getString(1));
				u.setTelefone(cursor.getString(2));
				list.add(u);

			} while (cursor.moveToNext());
		}

		return (list);
	}

	public boolean CheckIsDataAlreadyInDBorNot(String TableName, String dbfield, String fieldValue) {
		String Query = "Select * from " + TableName + " where " + "'"+dbfield+"'"+"=" +"'"+ fieldValue+"'";
		Cursor cursor = bd.rawQuery(Query, null);
		if (cursor.getCount() <= 0) {
			return false;
		}
		return true;
	}
	
}
