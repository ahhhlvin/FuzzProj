package ahhhlvin.c4q.nyc.fuzzproj;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alvin2 on 10/6/15.
 */
public class FuzzAdapter extends ArrayAdapter<FuzzItem> {

    private ArrayList<FuzzItem> fuzzItems;
    LayoutInflater inflater;
    ViewHolder holder;
    int textViewResourceId;

    public FuzzAdapter(Context context, int textViewResourceId, ArrayList<FuzzItem> objects) {
        super(context, textViewResourceId, objects);
        this.textViewResourceId = textViewResourceId;
        this.fuzzItems = objects;

    }

    public static class ViewHolder {
        TextView id;
        TextView type;
        TextView date;
        TextView data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(textViewResourceId, parent, false);
            convertView.setBackgroundColor(Color.BLACK);


            holder = new ViewHolder();

            holder.data = (TextView) convertView.findViewById(R.id.data_tv);
            holder.date = (TextView) convertView.findViewById(R.id.date_tv);
            holder.id = (TextView) convertView.findViewById(R.id.id_tv);
            holder.type = (TextView) convertView.findViewById(R.id.type_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final FuzzItem item = fuzzItems.get(position);

        if (item != null) {
//
            if (item.getType().equals("text")) {
                holder.data.setText("TEXT DATA: " + item.getData());
                holder.date.setVisibility(View.GONE);
                holder.id.setVisibility(View.GONE);
                holder.type.setVisibility(View.GONE);
                holder.data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webviewIntent = new Intent(getContext(), WebviewActivity.class);
                        webviewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(webviewIntent);
                    }

                });


            } else if (item.getType().equals("image")) {
                holder.data.setText("IMAGE DATA: " + item.getData());
                holder.date.setVisibility(View.GONE);
                holder.id.setVisibility(View.GONE);
                holder.type.setVisibility(View.GONE);

//                Picasso.with(getContext()).load(item.getData()).into(holder.img);
                holder.data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), BigImageActivity.class);
                        intent.putExtra("image", item.getData());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(intent);
                    }
                });
            } else {


                holder.data.setText("DATA: " + item.getData());
                holder.date.setText("DATE: " + item.getDate());
                holder.id.setText("ID: " + item.getId());
                holder.type.setText("TYPE: " + item.getType());


            }
        }

        return convertView;
    }


}
