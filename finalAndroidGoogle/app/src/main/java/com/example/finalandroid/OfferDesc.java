package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OfferDesc extends AppCompatActivity {


    private MenuItem menuItemAdd;
    private boolean fav;
    private Integer count;
    private Integer position;

    private ImageView imageView;
    private TextView titleTextView;
    private TextView priceTextView;
    private TextView descriptionTextView;
    private TextView countTextView;
    private String newDesc ="\nAre nevoie de atentie in plus si multa rabdare." +
            "Poste deveni cel mai bun prieten al tau.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        this.imageView = findViewById(R.id.image);
        this.titleTextView = findViewById(R.id.titleOffer);
        this.priceTextView = findViewById(R.id.price);
        this.descriptionTextView = findViewById(R.id.description);
        this.countTextView = findViewById(R.id.count);


        Intent myIntent	=	getIntent();

        Offer offer = (Offer) getIntent().getSerializableExtra("offer");

        String	title =	offer.getTitle();
        Integer	image =	offer.getImageId();
        Integer	price =	offer.getPrice();
        String	desc =	offer.getDescription() + newDesc;
        fav =	offer.isFav();

        count =	myIntent.getIntExtra("count", 0);
        position = myIntent.getIntExtra("position", 0);

        imageView.setImageResource(image);
        titleTextView.setText(title);
        priceTextView.setText(price+" LEI intretinere");
        descriptionTextView.setText(desc);
        countTextView.setText("Details page displayed: "+count+ " times");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorites_menu, menu);
        this.menuItemAdd = menu.getItem(0);
        if(fav == false){
            menuItemAdd.setTitle("ADD FAVORITES");
        }
        else{
            menuItemAdd.setTitle("REMOVE FROM FAVORITES");
        }
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.fav){
            if (fav == false){
                menuItemAdd.setTitle("REMOVE FROM FAVORITES");
                fav = true;
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_LONG).show();
            }
            else
            {
                menuItemAdd.setTitle("ADD FAVORITES");
                fav = false;
                Toast.makeText(this, "Removed from favosites", Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void	onBackPressed(){
        Intent	returnIntent =	 new Intent();
        returnIntent.putExtra("fav", fav);
        returnIntent.putExtra("position", position);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
