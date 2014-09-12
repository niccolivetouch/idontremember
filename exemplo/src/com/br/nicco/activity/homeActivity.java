package com.br.nicco.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.exemplo.R;

public class homeActivity extends Activity {
	
	private Button atualizarContato;
	private Button visualizarContato;
	private Button relembrarNovosContatos;
	
	@Override
	protected void onCreate(Bundle iclice) {
		super.onCreate(iclice);
		setContentView(R.layout.home_activity);
		atualizarContato = (Button) findViewById(R.id.atualizarContato);
		visualizarContato = (Button) findViewById(R.id.visualizarContato);
		relembrarNovosContatos = (Button) findViewById(R.id.relembrarNovosContatos);


		atualizarContato.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						ListaContatosActivity.class);
				startActivity(intent);
	        }
	    });
		
		visualizarContato.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						visualizarContato.class);
				startActivity(intent);
	        }
	    });
	}
}
