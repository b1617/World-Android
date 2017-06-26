package org.vinci.dhinesh.world;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;


/**
 * Created by SATTIA Dhinesh on 04/04/2017.
 */

public class MyAdapter extends ArrayAdapter<Pays> {


    //tweets est la liste des models à afficher
    public MyAdapter(Context context, List<Pays> pays) {
        super(context, 0, pays);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.design, parent, false);
        }

        PaysViewHolder viewHolder = (PaysViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new PaysViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);

            convertView.setTag(viewHolder);


        }


        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Pays pays = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.pseudo.setText(pays.getName());
        viewHolder.text.setText(pays.getText());
        viewHolder.avatar.setImageResource(pays.getId());



        return convertView;

    }



    private class PaysViewHolder {
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;

    }


}


