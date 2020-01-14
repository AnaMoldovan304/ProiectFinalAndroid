package com.example.finalandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

public class OfferActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

        private ListView listView;
        private OfferAdapter offerAdapter;
        private ArrayList<Offer> offers;
        private Integer count;
        private CountDownTimer progressTimer;
        private ProgressBar progressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_offer);

            count = 0;
            listView = findViewById(R.id.listView);
            progressBar = findViewById(R.id.progress);
            offerAdapter = new OfferAdapter(this, new ArrayList<Offer>());
            offers = new ArrayList<Offer>();

            offers.add(new Offer(getString(R.string.MayaTitle), R.drawable.offer_1, 300, getString(R.string.MayaDesc), false));
            offers.add(new Offer(getString(R.string.CocoTitle), R.drawable.offer_2, 10, getString(R.string.CocoDesc), false));
            offers.add(new Offer(getString(R.string.MocoTitle), R.drawable.offer_3, 150, getString(R.string.MocoDesc), false));

            offerAdapter.addItems(offers);

            progressTimer =	new	CountDownTimer(2000,	100)
            {
                @Override
                public void	onTick(long	millisUntilFinished)
                {

                }
                @Override
                public void	onFinish()
                {
                    listView.setAdapter(offerAdapter);
                    progressBar.getLayoutParams().width = 1;
                    progressBar.getLayoutParams().height = 1;
                    progressBar.requestLayout();
                }
            }.start();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(OfferActivity.this, OfferDesc.class);
                    Offer offer = offerAdapter.getOfferList().get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("offer",offer);
                    intent.putExtras(bundle)
                            .putExtra("count", ++count)
                            .putExtra("position", position);
                    startActivityForResult(intent,1);
                }
            });


            registerForContextMenu(listView);
        }

        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            if (v.getId() == R.id.listView) {
                ListView listView = (ListView) v;
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Offer target = (Offer) listView.getItemAtPosition(info.position);
                menu.setHeaderTitle(target.getTitle());
                getMenuInflater().inflate(R.menu.contex_menu, menu);
            }
        }

        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_with_button, menu);
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.signOut) {
                AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
                myDialog
                        .setTitle("Confirmation")
                        .setMessage("Please confirm log out intention")
                        .setPositiveButton("Confirm", this)
                        .setNegativeButton("Cancel",  this)
                        .show();
                return true;
            } else if (id == R.id.resetList) {
                offerAdapter.getOfferList().removeAll(offerAdapter.getOfferList());
                offerAdapter.getOfferList().addAll(offers);
                count = 0;
                offerAdapter.notifyDataSetChanged();
                Toast.makeText(this, "List has been reset!", Toast.LENGTH_LONG).show();
                return true;
            } else if (id == R.id.clearFavorites) {
                for(Offer offer : offerAdapter.getOfferList()){
                    offer.setFav(false);
                }
                return true;
            }else if(id == R.id.chat){
                startActivity(new Intent(OfferActivity.this, MessageActivity.class));
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case BUTTON_POSITIVE:
                    startActivity(new Intent(OfferActivity.this, MainActivity.class));
                    break;
                case BUTTON_NEGATIVE:
                    dialog.dismiss();
                default:
                    break;
            }
        }


        public void	onBackPressed()
        {
            AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
            myDialog
                    .setTitle("Confirmation")
                    .setMessage("Please confirm log out intention")
                    .setPositiveButton("Confirm", this)
                    .setNegativeButton("Cancel",  this)
                    .show();
        }


        public boolean onContextItemSelected(MenuItem item) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            if (item.getItemId() == R.id.menu_item_1) {
                offerAdapter.getOfferList().add(info.position,
                        new Offer("Rex, 5 years", R.drawable.offer_4,
                                120, "Cel mai jucaus catel, gasit abandonat langa o fabrica parasita" , false));
                offerAdapter.notifyDataSetChanged();
            } else if (item.getItemId() == R.id.menu_item_2) {
                offerAdapter.getOfferList().remove(info.position);
                offerAdapter.notifyDataSetChanged();
            }
            return super.onContextItemSelected(item);
        }

        @Override
        public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
            super.onCreateNavigateUpTaskStack(builder);

        }


        protected void onActivityResult(int requestCode, int resultCode, Intent	data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1) {
                if (resultCode == Activity.RESULT_OK) {
                    boolean favNew = data.getBooleanExtra("fav", false);
                    Integer positionNew = data.getIntExtra("position", 0);
                    offerAdapter.getOfferList().get(positionNew).setFav(favNew);
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                }
            }
        }

    }
