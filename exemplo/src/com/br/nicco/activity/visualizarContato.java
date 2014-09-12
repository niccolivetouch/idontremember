package com.br.nicco.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.br.nicco.model.Contato;
import com.example.exemplo.R;

public class visualizarContato extends Activity {

	private List<Contato> contato;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_contato);

	}
}
