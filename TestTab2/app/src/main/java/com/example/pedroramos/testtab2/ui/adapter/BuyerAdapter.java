package com.example.pedroramos.testtab2.ui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pedroramos.testtab2.R;
import com.example.pedroramos.testtab2.data.model.Buyer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BuyerAdapter extends ArrayAdapter<Buyer> {
    Activity activity;
    int layoutResourceId;
    Buyer buyer;
    ArrayList<Buyer> data = new ArrayList<>();
    ListView buyerListview;

    public BuyerAdapter(Activity act, int layoutResourceId, ArrayList<Buyer> data) {
        super(act, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.activity = act;
        this.data = data;
        //this.buyerListview = buyerListview;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BuyerHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);

            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new BuyerHolder();
            holder.ivProfilePic = (CircleImageView) row.findViewById(R.id.ivProfilePic);
            holder.tvBuyerName  = (TextView) row.findViewById(R.id.tvBuyerName);
            holder.tvBuyerCategory  = (TextView) row.findViewById(R.id.tvBuyerCategory);
            holder.tvBuyerDate = (TextView) row.findViewById(R.id.tvBuyerDate);
            holder.tvBuyerDesc = (TextView) row.findViewById(R.id.tvBuyerDesc);
            holder.rbBuyerRating = (RatingBar) row.findViewById(R.id.rbBuyerRating);
            holder.tvBuyerPrice  = (TextView) row.findViewById(R.id.tvBuyerPrice);
            holder.touchArea = (LinearLayout) row.findViewById(R.id.touchArea);
            row.setTag(holder);
        } else {
            holder = (BuyerHolder) row.getTag();
        }
        buyer = data.get(position);
        holder.touchArea.setTag(buyer.getId());
        buyerListview.setTag(buyer.getId());

        //holder.ivProfilePic.setImageBitmap(buyer.setProfilePic);
        holder.tvBuyerName.setText(buyer.getName());
        holder.tvBuyerCategory.setText(buyer.getCategory());
        holder.tvBuyerDate.setText(buyer.getDate());
        holder.tvBuyerDesc.setText(buyer.getDescription());
        holder.rbBuyerRating.setProgress(buyer.getRating());
        holder.tvBuyerPrice.setText(buyer.getPrice());

        holder.touchArea.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(final View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(activity);
                adb.setTitle("Eliminar");
                adb.setMessage("Se borrará el contacto seleccionado");
                //final int user_id = Integer.parseInt(v.getTag().toString());
                adb.setNegativeButton("Cancelar", null);
                adb.setPositiveButton("Aceptar",
                        new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               /* DatabaseHandler dBHandler = new DatabaseHandler(
                                        activity.getApplicationContext());
                                dBHandler.Delete_Contact(user_id);
                                Main_Screen.this.onResume();*/
                            }
                        });
                adb.show();
                return true;
            }
        });
        holder.touchArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
        return row;
    }

    class BuyerHolder {
        CircleImageView ivProfilePic;
        TextView tvBuyerName;
        TextView tvBuyerCategory;
        TextView tvBuyerDate;
        TextView tvBuyerDesc;
        RatingBar rbBuyerRating;
        TextView tvBuyerPrice;
        LinearLayout touchArea;
    }
}
