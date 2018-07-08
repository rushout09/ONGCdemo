package ml.rushabh.rushapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ComplainAdapter extends ArrayAdapter<Complain> {
    public ComplainAdapter(@NonNull Context context, int resource, @NonNull List<Complain> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_complain,parent,false);
        }
        TextView titleTextView = (TextView)convertView.findViewById(R.id.titleTextView);
        TextView queryTextView = (TextView)convertView.findViewById(R.id.queryTextView);
        TextView emailTextView = (TextView)convertView.findViewById(R.id.emailTextView);

        Complain complain = getItem(position);

        titleTextView.setText(complain.getTitle());
        queryTextView.setText(complain.getQuery());
        emailTextView.setText(complain.getEmail());
        return convertView;
    }
}
