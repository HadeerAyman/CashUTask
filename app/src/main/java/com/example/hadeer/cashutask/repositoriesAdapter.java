package com.example.hadeer.cashutask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class repositoriesAdapter extends ArrayAdapter<OneRepositoryModel> {
    public repositoriesAdapter(@NonNull Context context) {
        super(context, 0);
    }

    public repositoriesAdapter(Context context, List<OneRepositoryModel> repositories) {
        super(context, 0, repositories);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent ) {

        OneRepositoryModel repository = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_repositories, parent, false);
        }
        TextView name_tv = convertView.findViewById(R.id.name_row_id);
        TextView description_tv =  convertView.findViewById(R.id.des_row_id);

        name_tv.setText(repository.getName());
        description_tv.setText(repository.getDescription());

        return convertView;
    }

}
