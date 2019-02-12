package com.example.hadeer.cashutask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RepositoriesAdapter extends ArrayAdapter<OneRepositoryModel> {

    public RepositoriesAdapter(Context context, List<OneRepositoryModel> repositories) {
        super(context, 0, repositories);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent ) {

        OneRepositoryModel repository = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_repositories, parent, false);
        }
        TextView name_textview = convertView.findViewById(R.id.name_row_id);
        TextView description_textview =  convertView.findViewById(R.id.des_row_id);

        name_textview.setText(repository.getName());
        description_textview.setText(repository.getDescription());

        return convertView;
    }

}
