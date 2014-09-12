package com.br.nicco.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.nicco.model.Contato;
import com.example.exemplo.R;

public class AdapterListaDeContatos extends BaseAdapter {

	private Context context;
	private List<Contato> ListaContato;

	public AdapterListaDeContatos(Context context, List<Contato> ListaContato) {
		super();
		this.context = context;
		this.ListaContato = ListaContato;
	}

	@Override
	public int getCount() {
		return ListaContato.size();
	}

	@Override
	public Object getItem(int position) {
		return ListaContato.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		IdontRememberHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(R.layout.row_lista_contatos, parent, false);
//			Contato c = ListaContato.get(position);
//			TextView t = (TextView) view.findViewById(R.id.Nome);
//			t.setText(c.getNome());
//
//			TextView t2 = (TextView) view.findViewById(R.id.Telefone);
//			t2.setText(c.getTelefone());
			
			holder = new IdontRememberHolder();
			holder.Nome = (TextView) row.findViewById(R.id.Nome);
			holder.Telefone = (TextView) row.findViewById(R.id.Telefone);

			row.setTag(holder);
			
		} else {
			holder = (IdontRememberHolder) row.getTag();
		}
		Contato c = ListaContato.get(position);
		holder.Nome.setText(c.getNome());
		holder.Telefone.setText(c.getTelefone());
		
		return row;
	}

	static class IdontRememberHolder {
		TextView Nome;
		TextView Telefone;
	}

}
