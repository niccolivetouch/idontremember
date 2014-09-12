package com.br.nicco.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
	private static final String NOME_BD = "Contatos";
	private static final int VERSAO_BD = 8;
	
	
	public BDCore(Context ctx){
		super(ctx, NOME_BD, null, VERSAO_BD);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase bd) {
		bd.execSQL("create table contatos(_id integer primary key autoincrement, nome text not null, telefone text not null);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
		bd.execSQL("drop table contatos;");
		onCreate(bd);
	}

}
