package com.example.finalandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OfferAdapter extends ArrayAdapter {

    private List<Offer> mOfferList;
    private Context context;

    ImageView imageView;
    TextView titleTextView;
    TextView priceTextView;
    TextView descriptionTextView;



    public OfferAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
        mOfferList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        imageView = convertView.findViewById(R.id.image);
        titleTextView = convertView.findViewById(R.id.titleOffer);
        priceTextView = convertView.findViewById(R.id.price);
        descriptionTextView= convertView.findViewById(R.id.description);

        final Offer mOffer = mOfferList.get(position);

        if (mOffer.getImageId() != null) {
            imageView.setImageResource(mOffer.getImageId());
        }

        if (mOffer.getTitle() != null) {
            titleTextView.setText(mOffer.getTitle());
        }

        if (mOffer.getPrice() != null) {
            priceTextView.setText(mOffer.getPrice()+" LEI intretinere");
        }

        if (mOffer.getDescription() != null) {
            descriptionTextView.setText(mOffer.getDescription());
        }

        return convertView;
    }

    public void addItems(List<Offer> OfferList) {
        mOfferList.addAll(OfferList);
        notifyDataSetChanged();
    }

    public List<Offer> getOfferList() {
        return mOfferList;
    }

}
