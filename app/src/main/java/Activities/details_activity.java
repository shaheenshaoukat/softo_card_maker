package Activities;

import static android.R.layout.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.R.layout;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import softocardmaker.softoappa.softobook.com.R;
import softocardmaker.softoappa.softobook.com.databinding.ActivityDetailsBinding;
import yuku.ambilwarna.AmbilWarnaDialog;

public class details_activity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    ImageView image;
    EditText editText;
    String cardname;


    private boolean isBold = false;
    private Typeface originalTypeface;
    private boolean isItalic = false;
    private boolean isStrikethrough = false;


    private Typeface[] typefaces;
    private String[] fontItems = {"default.ttf","notonastaliq.ttf", "oswald.ttf", "robotoslab.ttf", "schoolbell.ttf"
            , "serif.ttf", "sixcaps.ttf", "specialelite.ttf", "tilt.ttf", "titilliumweb.ttf",
            "wallpoet.ttf", "yellowtail.ttf"};

    int defaultcolourone, defaultcolourtwo, defaultcolourthree;

    String text;

    private static final int REQUEST_PERMISSION = 1;

    DatabaseHelper myDb;
    private boolean isFavorite = false;

    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;
    private boolean isZoomAndRotate;
    private boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    float oldDist = 1f;

    private float xCoOrdinate, yCoOrdinate;

    List<String> items;

    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        image = findViewById(R.id.image);














        /*ads code start*/
        MobileAds.initialize(this
                , new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });


        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        binding.adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });

        /*ads code end*/




        /*font code stary multiple font in tv one and two and three*/


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.font_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.fontOne.setAdapter(adapter);
        binding.fontTwo.setAdapter(adapter);
        binding.fontThree.setAdapter(adapter);

        binding.fontOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Typeface selectedTypeface = typefaces[i];
                binding.tvOne.setTypeface(selectedTypeface);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.fontTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Typeface selectedTypeface = typefaces[i];
                binding.tvTwo.setTypeface(selectedTypeface);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.fontThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Typeface selectedTypeface = typefaces[i];
                binding.tvThree.setTypeface(selectedTypeface);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        loadTypefaces();
        /*font code end multiple font in tv one and two and three*/


        myDb = new DatabaseHelper(this);
        defaultcolourone = ContextCompat.getColor(getApplicationContext(), R.color.black);
        defaultcolourtwo = ContextCompat.getColor(getApplicationContext(), R.color.black);
        defaultcolourthree = ContextCompat.getColor(getApplicationContext(), R.color.black);

        myDb.getWritableDatabase();

        value = getIntent().getStringExtra("image");
        binding.image.setImageResource(Integer.parseInt(value));
        cardname = getIntent().getStringExtra("name");
        binding.quotes.setText(cardname);


        binding.tvOne.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, motionEvent);
                return false;
            }
        });
        binding.tvTwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, motionEvent);
                return false;
            }
        });
        binding.tvThree.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, motionEvent);
                return false;
            }
        });

        binding.emoji.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {


                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, motionEvent);
                return true;
            }
        });

        binding.btnemoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEmojiDialog();


            }
        });
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLayoutToGallery(binding.constraint);

            }
        });
        binding.tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.tvTwo.setBackground(null);
                binding.tvThree.setBackground(null);

                Drawable boundingBox = getResources().getDrawable(R.drawable.bounding_box);
                binding.tvOne.setBackground(boundingBox);

                binding.colorOne.setVisibility(View.VISIBLE);
                binding.fontOne.setVisibility(View.VISIBLE);
                binding.clearOne.setVisibility(View.VISIBLE);
                binding.boldOne.setVisibility(View.VISIBLE);
                binding.strikeOne.setVisibility(View.VISIBLE);
                binding.italicOne.setVisibility(View.VISIBLE);

                binding.colorTwo.setVisibility(View.GONE);
                binding.fontTwo.setVisibility(View.GONE);
                binding.clearTwo.setVisibility(View.GONE);
                binding.italicTwo.setVisibility(View.GONE);
                binding.strikeTwo.setVisibility(View.GONE);
                binding.boldTwo.setVisibility(View.GONE);

                binding.colorThree.setVisibility(View.GONE);
                binding.fontThree.setVisibility(View.GONE);
                binding.clearThree.setVisibility(View.GONE);
                binding.italicThree.setVisibility(View.GONE);
                binding.strikeThree.setVisibility(View.GONE);
                binding.boldThree.setVisibility(View.GONE);


            }
        });

        binding.boldOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bold(binding.tvOne);
            }
        });
        binding.italicOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                italic(binding.tvOne);
            }
        });
        binding.strikeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strike(binding.tvOne);
            }
        });

        binding.boldTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bold(binding.tvTwo);
            }
        });
        binding.italicTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                italic(binding.tvTwo);
            }
        });
        binding.strikeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strike(binding.tvTwo);
            }
        });

        binding.boldThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bold(binding.tvThree);
            }
        });
        binding.italicThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                italic(binding.tvThree);
            }
        });
        binding.strikeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strike(binding.tvThree);
            }
        });


        binding.tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.tvOne.setBackground(null);
                binding.tvThree.setBackground(null);
                Drawable boundingBox = getResources().getDrawable(R.drawable.bounding_box);
                binding.tvTwo.setBackground(boundingBox);


                binding.colorTwo.setVisibility(View.VISIBLE);
                binding.fontTwo.setVisibility(View.VISIBLE);
                binding.clearTwo.setVisibility(View.VISIBLE);
                binding.boldTwo.setVisibility(View.VISIBLE);
                binding.italicTwo.setVisibility(View.VISIBLE);
                binding.strikeTwo.setVisibility(View.VISIBLE);


                binding.colorOne.setVisibility(View.GONE);
                binding.fontOne.setVisibility(View.GONE);
                binding.clearOne.setVisibility(View.GONE);
                binding.boldOne.setVisibility(View.GONE);
                binding.italicOne.setVisibility(View.GONE);
                binding.strikeOne.setVisibility(View.GONE);

                binding.colorThree.setVisibility(View.GONE);
                binding.fontThree.setVisibility(View.GONE);
                binding.clearThree.setVisibility(View.GONE);
                binding.boldThree.setVisibility(View.GONE);
                binding.strikeThree.setVisibility(View.GONE);
                binding.italicThree.setVisibility(View.GONE);

            }
        });

        binding.tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvTwo.setBackground(null);
                binding.tvOne.setBackground(null);

                Drawable boundingBox = getResources().getDrawable(R.drawable.bounding_box);
                binding.tvThree.setBackground(boundingBox);


                binding.colorThree.setVisibility(View.VISIBLE);
                binding.fontThree.setVisibility(View.VISIBLE);
                binding.clearThree.setVisibility(View.VISIBLE);
                binding.boldThree.setVisibility(View.VISIBLE);
                binding.boldThree.setVisibility(View.VISIBLE);
                binding.italicThree.setVisibility(View.VISIBLE);
                binding.strikeThree.setVisibility(View.VISIBLE);


                binding.colorOne.setVisibility(View.GONE);
                binding.fontOne.setVisibility(View.GONE);
                binding.clearOne.setVisibility(View.GONE);
                binding.boldOne.setVisibility(View.GONE);
                binding.strikeOne.setVisibility(View.GONE);
                binding.italicOne.setVisibility(View.GONE);

                binding.colorTwo.setVisibility(View.GONE);
                binding.fontTwo.setVisibility(View.GONE);
                binding.clearTwo.setVisibility(View.GONE);
                binding.boldTwo.setVisibility(View.GONE);
                binding.italicTwo.setVisibility(View.GONE);
                binding.strikeTwo.setVisibility(View.GONE);


            }
        });


        binding.clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.colorOne.setVisibility(View.GONE);
                binding.fontOne.setVisibility(View.GONE);
                binding.clearOne.setVisibility(View.GONE);
                binding.boldOne.setVisibility(View.GONE);
                binding.strikeOne.setVisibility(View.GONE);
                binding.italicOne.setVisibility(View.GONE);

                binding.colorTwo.setVisibility(View.GONE);
                binding.fontTwo.setVisibility(View.GONE);
                binding.clearTwo.setVisibility(View.GONE);
                binding.boldTwo.setVisibility(View.GONE);
                binding.italicTwo.setVisibility(View.GONE);
                binding.strikeTwo.setVisibility(View.GONE);

                binding.colorThree.setVisibility(View.GONE);
                binding.fontThree.setVisibility(View.GONE);
                binding.clearThree.setVisibility(View.GONE);
                binding.boldThree.setVisibility(View.GONE);
                binding.strikeThree.setVisibility(View.GONE);
                binding.italicThree.setVisibility(View.GONE);
            }
        });
        binding.clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.colorOne.setVisibility(View.GONE);
                binding.fontOne.setVisibility(View.GONE);
                binding.clearOne.setVisibility(View.GONE);
                binding.boldOne.setVisibility(View.GONE);
                binding.strikeOne.setVisibility(View.GONE);
                binding.italicOne.setVisibility(View.GONE);

                binding.colorTwo.setVisibility(View.GONE);
                binding.fontTwo.setVisibility(View.GONE);
                binding.clearTwo.setVisibility(View.GONE);
                binding.boldTwo.setVisibility(View.GONE);
                binding.italicTwo.setVisibility(View.GONE);
                binding.strikeTwo.setVisibility(View.GONE);

                binding.colorThree.setVisibility(View.GONE);
                binding.fontThree.setVisibility(View.GONE);
                binding.clearThree.setVisibility(View.GONE);
                binding.boldThree.setVisibility(View.GONE);
                binding.strikeThree.setVisibility(View.GONE);
                binding.italicThree.setVisibility(View.GONE);
            }
        });
        binding.clearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.colorOne.setVisibility(View.GONE);
                binding.fontOne.setVisibility(View.GONE);
                binding.clearOne.setVisibility(View.GONE);
                binding.boldOne.setVisibility(View.GONE);
                binding.strikeOne.setVisibility(View.GONE);
                binding.italicOne.setVisibility(View.GONE);

                binding.colorTwo.setVisibility(View.GONE);
                binding.fontTwo.setVisibility(View.GONE);
                binding.clearTwo.setVisibility(View.GONE);
                binding.boldTwo.setVisibility(View.GONE);
                binding.italicTwo.setVisibility(View.GONE);
                binding.strikeTwo.setVisibility(View.GONE);

                binding.colorThree.setVisibility(View.GONE);
                binding.fontThree.setVisibility(View.GONE);
                binding.clearThree.setVisibility(View.GONE);
                binding.boldThree.setVisibility(View.GONE);
                binding.strikeThree.setVisibility(View.GONE);
                binding.italicThree.setVisibility(View.GONE);
            }
        });

        binding.colorOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencolourpickerone();


            }
        });

        binding.colorTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencolourpickertwo();


            }
        });
        binding.colorThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencolourpickerthree();


            }
        });

    }


    public void dialog(View view) {

        Dialog dialog = new Dialog(details_activity.this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(false);
        EditText ed_one = dialog.findViewById(R.id.ed_one);
        EditText ed_two = dialog.findViewById(R.id.ed_two);
        EditText ed_three = dialog.findViewById(R.id.ed_three);
        Spinner spinner = dialog.findViewById(R.id.spineer);

        Button btn = dialog.findViewById(R.id.save);

        ed_one.setText(binding.tvOne.getText().toString());
        ed_two.setText(binding.tvTwo.getText().toString());
        ed_three.setText(binding.tvThree.getText().toString());

        List<String> list = new ArrayList<String>();
        if (cardname.equals("Cusin Birthday")) {
            list.add("");
            list.add("You are just one of the best cousins I could have had. HBD!");
            list.add("I feel so lucky to have you as my cousin,so Happy Birthday!");
            list.add("Happy birthday, my dear!I’m wishing you much greatness and joy on your special day");
            list.add("Happy birthday to my best friend, who just sohappens to also be my cousin!");
            list.add("Happy birthday to you, and thank you for all the happy memories, my dear.");
            list.add("I’m sending the very best birthday wishes your way today, my adorable little cousin");
            list.add("Happy birthday, my dear, and I hope that today brings you all the happiness in the world");
            list.add("Happy birthday to my partner in crime!Or should I say, my fabulous sidekick!");
            list.add("In you, I found a true friend and a brother!Happy Birthday");
            list.add(" Happy birthday, cousin! May your life be blessed with joy and success.");


        } else if (cardname.equals("Brother")) {
            list.add("");
            list.add("Wishing my awesome brother an awesome birthday!");
            list.add("Happy birthday, brother! I love you!");
            list.add("Today we celebrate you. Happy birthday!");
            list.add("Happy birthday to my first protector.");
            list.add("Time to party! Happy birthday, brother.");
            list.add("Brothers are the best gift. Happy birthday!");
            list.add("I can’t wait to celebrate with you. Enjoy your special day!");
            list.add("Cheers to another year around the sun!");
            list.add("You’re the best brother anyone could ever ask for. HBD!");
            list.add("Wishing you the best birthday yet!");

        } else if (cardname.equals("Sister")) {
            list.add("");
            list.add("You'll always be my greatest role model. HBD, sissy!");
            list.add("Sibling love today and always! Happy birthday, sister.");
            list.add("You are one of my biggest inspirations. Wishing you the happiest birthday!");
            list.add("Sister, you're simply the best! Happy birthday.");
            list.add("I love you more than you'll ever know. Happy birthday!");
            list.add("I have the best sister on Earth! Happy birthday.");
            list.add("You'll always be my best friend. Happy birthday, sister!");
            list.add("Sisters are the best gift. Happy birthday!");
            list.add("I'm so lucky to be your sister. HBD!");
            list.add("Happy birthday, sister! I love you so much.");


        } else if (cardname.equals("Grandpa")) {
            list.add("");
            list.add("I hope this day will bring you all the joy and happiness!");
            list.add("Sit down, relax, and enjoy yourself on this special day!HBD.");
            list.add("Grandpa, you mean so much to all of us.Happy Birthday!");
            list.add(" Thank you for what you do for us.Wishing you HBD!");
            list.add("Today is for you to enjoy and relax .Happy Birthday grandpa");
            list.add("Happy birthday Grandpa, thank you for the way you take the time to care for us. ");
            list.add("Thanks Grandpa for your wit and wisdom, which brightens every day.Happy Birthday");
            list.add("Thanks Grandpa for your wit and wisdom, which brightens every day.Happy Birthday");
            list.add("Happy Birthday Grandpa! May this day bring you everything you desire.");
            list.add("Happy birthday Grandpa.Have a wonderful birthday Grandpa,");
            list.add("Happy birthday Grandpa.Have a wonderful birthday Grandpa,");

        } else if (cardname.equals("Grandma")) {
            list.add("");
            list.add(" May you stay healthy and happy for the years to come.");
            list.add(" We are all happy to have you here with us!");
            list.add("Happy birthday Grandma, I wish another wonderful year. ");
            list.add("We are all here to celebrate your special day!HBD");
            list.add("We are all here to celebrate your special day!HBD");
            list.add("THanks for your love and  many warm hugs all these years. Happy loving birthday!");
            list.add("Thanks for your love and  many warm hugs all these years. Happy loving birthday!");
            list.add("May your birthday make you feel like an 18th years old woman!");
            list.add("You are the woman I admire. May your birthday be unforgettable.");
            list.add("A woman who taught me how to live and love! Happy Birthday!");


        } else if (cardname.equals("Brother In Law")) {
            list.add("");
            list.add("Happy Birthday brother-in-law! We are so lucky to have you be a part of this family.");
            list.add("Happy Birthday brother-in-law! Wishing you a fantastic birthday !");
            list.add("To my dearest brother-in-law, Happy Birthday! Have a blast!");
            list.add("I hope your birthday is as amazing as you are! ");
            list.add("Thank you for being a wonderful addition to this family. Happy birthday brother-in-law!");
            list.add("Happy birthday, brother-in-law. I hope this upcoming year is even better than the last!");
            list.add("May this birthday mark the beginning of a new period of flying high and success in your life.");
            list.add("May this birthday mark the beginning of a new period of flying high and success in your life.");
            list.add("May this birthday mark the beginning of a new period of flying high and success in your life.");
            list.add("May this birthday mark the beginning of a new period of flying high and success in your life. ");
            list.add("May you continue to be a blessing to others.Happy birthday!");

        } else if (cardname.equals("Sister In Law")) {
            list.add("");
            list.add("May this birthday be the most joyful and unforgettable day in your life");
            list.add("A happy special birthday, sister-in-law!");
            list.add("Having you as my sister-in-law is a wish come true. ");
            list.add("I hope you have a wonderful year with all your wishes fulfilled. Happy birthday!");
            list.add("Happy birthday to my gorgeous sister-in-law! Sending all the love.");
            list.add("I hope your day is full of beautiful and memorable moments.");
            list.add("Happy birthday, darling sister-in-law! You make everyday brighter with your smile.");
            list.add("I hope you are spoiled with surprises today and forever.Happy Birthday.");
            list.add("To my dearest sister-in-law,happy to celebrate another wonderful year. Happy birthday!");
            list.add("To my terrific sister-in-law, happy birthday! ");
            list.add(" We are celebrating the birthday of one of our dreamers!My sister-in-law.");

        } else if (cardname.equals("Mother in law")) {
            list.add("");
            list.add("I know you're my mother-in-law, but you're also my friend.");
            list.add("Thank you for your kindness, your love and your cooking. Happy birthday!");
            list.add("Thank you for raising such an amazing child. I'm lucky to know and love you both.");
            list.add("I'm so happy I get to call such a cool, inspiring and kind woman my mother-in-law.");
            list.add("You're more like a mom than a mother-in-law. Happy birthday.");
            list.add("You're more like a mom than a mother-in-law. Happy birthday.");
            list.add("Here's to another year of the best holiday meals at your house (don't tell my family).");
            list.add("I'm a lucky guy to be your son-in-law.Happy Birthday!");
            list.add("I'm so glad I have a mother-in-law that I actually like. Happy birthday!");
            list.add("I'm so glad I have a mother-in-law that I actually like. Happy birthday!");


        } else if (cardname.equals("Father")) {
            list.add("");
            list.add("I’m the luckiest person to have a dad like you. Happy birthday!");
            list.add("You’re one-of-a-kind and I love you so much. Happy birthday, dad!");
            list.add("I love you today, tomorrow and forever. Happy birthday, dad!");
            list.add("Wishing the most wonderful dad the best birthday ever!");
            list.add("Happy birthday, dad! I love you!");
            list.add("Happy birthday to the best dad in the universe!");
            list.add("Wishing the bravest man in the world a happy birthday.");
            list.add("Time to make some wishes, dad. Happy birthday!");
            list.add("Fathers are the best gift. Happy birthday!");
            list.add("I can’t wait to celebrate your special day with you. Happy birthday!");


        } else if (cardname.equals("Mother")) {
            list.add("");
            list.add("Wishing the sweetest woman in the world a happy birthday.");
            list.add("Time to make some wishes, mom. Happy birthday!");
            list.add("Time to make some wishes, mom. Happy birthday!");
            list.add("I can’t wait to celebrate your special day with you. Happy birthday!");
            list.add("Cheers to another trip around the sun!");
            list.add("Happy cake day! Enjoy");
            list.add("You’re the best mother anyone could ever ask for. Happy birthday!");
            list.add("Wishing you the best birthday yet!");
            list.add("Happy birthday to my favorite person in the whole world.");
            list.add("Happy birthday, my beloved mother.");


        } else if (cardname.equals("BoyFraind")) {
            list.add("");
            list.add("Happy Birthday to the one I love.");
            list.add("Happiest of birthdays to my favourite human.");
            list.add("Happy Birthday to the most good-looking guy I know.");
            list.add("Happy Birthday to my number one.");
            list.add("Happy Birthday to my other half.");
            list.add("Happy Birthday to my amazing boyfriend.");
            list.add("Happy Birthday to the best boyfriend.");
            list.add("Happy Birthday to the best boyfriend.");
            list.add("Happy Birthday my love.");
            list.add("Happy Birthday to my person.");


        } else if (cardname.equals("GirlFraind")) {
            list.add("");
            list.add("Wishing the most amazing birthday ever to my sweet girlfriend!");
            list.add("It’s not every day I get to say happy birthday to you, my love!");
            list.add("May you have the fabulous birthday that you so deserve, my love!");
            list.add("Happy birthday to my sweet, beautiful girlfriend! ");
            list.add("I’m sending you lots of kisses today as you celebrate your special day!");
            list.add("I’m sending all my love your way!Happy Birthday!");
            list.add("Lots of love for you today, my sweetheart!HBD");
            list.add(".Happy birthday with all my love, sweetheart.");
            list.add(".Happy birthday with all my love, sweetheart.");
            list.add("Birthday greetings from my heart to yours, my sweet girlfriend.");

        } else if (cardname.equals("Son")) {
            list.add("");
            list.add("Son, you will outgrow my lap, but never my heart.");
            list.add(" “Of all the animals, the boy is the most unmanageable");
            list.add("A good son always care for his family.");
            list.add("A good son will open your eyes and broaden your knowledge.");
            list.add("Son is a hero and a greatest gift for his parents.");
            list.add("Sons are born to make their fathers better men");
            list.add("“Sons are the anchors of a mother’s life");
            list.add("“Happy is the son whose faith in his mother remains unchallenged.");
            list.add("Men are what their mothers made them");
            list.add("The only man who has stolen my heart is my son");


        } else if (cardname.equals("Daughter")) {
            list.add("");
            list.add("Wishing my sweet baby girl a fabulous birthday");
            list.add("Happy birthday! You are the absolute best daughter any mother could ever have!");
            list.add("I hope your special day is as special as you! Happy birthday, daughter.");
            list.add("Nothing lights up my world more than you! Wishing you the happiest birthday ever.");
            list.add("Wishing my sweetheart, a very happy birthday. I love you more than you’ll ever know.");
            list.add("May all your wishes come true today and every day. Happy birthday!");
            list.add("You’re the best thing that ever happened to me. Happy birthday!");
            list.add("You’re my biggest inspiration. Happy birthday, dear!");
            list.add("Happy birthday to my darling daughter!");
            list.add("Happy birthday, daughter! The world is a better place because you’re in it!\n");

        } else if (cardname.equals("Nephew")) {
            list.add("");
            list.add("Happy Birthday Nephew. Have an amazing year. I love you!");
            list.add("Happy Birthday. Wishing you a great year for my nephew!");
            list.add(" Happy Birthday to my Nephew. I hope your day is filled with lots of smiles");
            list.add(" You are smart and sweet nephew! Wishing you a great year.");
            list.add("May all your birthday wishes come true.Happy Birthday Nephew!");
            list.add(" Happy Birthday Nephew. May this day be full of fun.");
            list.add("Happy Birthday. You are my favorite nephew. Have a great birthday!");
            list.add("I hope your birthday is everything you hope it will be and more.");
            list.add("Happy Birthday. Nephew, you amaze me! Dream big this birthday");
            list.add("Happy Birthday Nephew.  Live large. Hope this birthday is nothin’ but net.");

        } else if (cardname.equals("Niece")) {
            list.add("");
            list.add("Happy birthday to my cute and beautiful niece!");
            list.add("You are my true gift to the world. Have the best birthday, cutie pie!");
            list.add("On my cloudiest in life, you are an exquisite rainbow.HBD!");
            list.add("May your year be full of wondrous moments. Happy birthday niece!");
            list.add("May God bless you with many, wonderful, healthy years!");
            list.add("May God bless you with many, wonderful, healthy years!");
            list.add("Happy birthday, cutie! I love you so much!");
            list.add("Wishing you an amazing and wonderful birthday just as you deserve!");
            list.add("Wishing you an amazing and wonderful birthday just as you deserve!HBD!");
            list.add("Happy birthday adorable niece!Smile big and laugh loudly.");

        } else if (cardname.equals("Husband")) {
            list.add("");
            list.add("Happy birthday to a guy who's forever young at heart.");
            list.add("Wishing you a birthday as amazing as you are.");
            list.add("I love you the most. Happy birthday!");
            list.add("Life is more fun with you by my side.");
            list.add("HBD to my amazing husband.");
            list.add("I hope all your birthday wishes come true!");
            list.add("Today is all about you, my love! Happy birthday!");
            list.add("My life would suck without you.");
            list.add("Let's make this year's birthday one for the record books.");
            list.add("I can't wait to celebrate you all day.");
            list.add("Happy birthday to the guy who brings out the best in me.");

        } else if (cardname.equals("Wife")) {
            list.add("");
            list.add("I love you so very much. Happy Birthday!");
            list.add("Happy Birthday to my lovely wife.");
            list.add("Wishing you the happiest of birthdays, my darling.");
            list.add("Many happy returns to my darling wife.");
            list.add("Happy Birthday to the woman I love.");
            list.add("Have a wonderful day on your birthday!");
            list.add("Happy Birthday to my beautiful and loving wife.");
            list.add("Happy Birthday to my beautiful and loving wife.");
            list.add("It's your birthday, darling! Let's celebrate!");
            list.add("Congratulations on another year, darling.");
            list.add("Happy Birthday to the woman who gave meaning to my life.");

        } else if (cardname.equals("Wedding Anniversary")) {
            list.add("");
            list.add("“Thanks for joining me on this lifetime journey! Here's to many more years.”");
            list.add("“Finding a love like yours feels like a dream. Please don't wake me up!”");
            list.add("I read a lot of books, but our love story is my favorite.\"");
            list.add("On this day, and every day, I am reminded why we are meant for each other.");
            list.add("Every day, every month, and every year... I love you more than the last.");
            list.add("“No matter what, I'll always be your number 1 fan.”");
            list.add("“There's no words to explain what I feel when I look into your eyes. Just love.”");
            list.add("Happy anniversary to the love of my life!.");
            list.add("“Life without you would be life without air. I love you.”");
            list.add(". Cheers to another year with my favorite person.”");

        } else if (cardname.equals("BestFraind")) {
            list.add("");
            list.add("I am Blessed to have a friend like you. Happy birthday to my best friend!");
            list.add(" I’m so grateful to have you as my friend.Happy birthday!");
            list.add("Thank you for your unwavering friendship. Happy birthday!");
            list.add(" Cheers to more fun times together. Best wishes on your birthday!");
            list.add("You always make me laugh.Happy birthday, bff!");
            list.add("If there’s one person I can always count on, you’re the one. Wishing you a happy birthday!");
            list.add("I cherish you today and always my bestie! Happy birthday.");
            list.add("Nothing can shake our unbreakable bond. Happy birthday, bestie");
            list.add("The time spent with my friend is the best! Happy birthday!");
            list.add("You’re the best friend any girl could have.Happy Birthday!");


        } else if (cardname.equals("Thanks")) {
            list.add("");
            list.add("Thank you for being the reason I smile");
            list.add("Here’s to those who inspire you and don’t even know it");
            list.add("Thank you for brightening my world.");
            list.add("Thank you for being an important part of my story.");
            list.add("You’ve always believed in me. Thank you!");
            list.add("Saying thank you is more than good manners, it is good spirituality");
            list.add("There is always, always, always something to be thankful for");
            list.add("Never let the things you want make you forget the things you have.");
            list.add("No duty is more urgent than that of returning thanks.");
            list.add("Thankfulness is the quickest path to joy");


        } else if (cardname.equals("Funny")) {
            list.add("");
            list.add("Clothes make the man. Naked people have little or no influence in society.");
            list.add("“Ned, I would love to stand here and talk with you—but I’m not going to.");
            list.add("Never let your best friends get lonely, keep disturbing them.");
            list.add("Sometimes I wish I was an octopus, so I could slap eight people at once.");
            list.add("If Cinderella’s shoe fit perfectly, then why did it fall off?");
            list.add("If you’re hotter than me, then that means I’m cooler than you.");
            list.add("My wallet is like an onion, opening it makes me cry.");
            list.add("Friends buy you food. Best friends eat your food.");
            list.add("Lazy people fact #2347827309018287. You were too lazy to read that number.");
            list.add(" Friends buy you food. Best friends eat your food.");

        } else if (cardname.equals("Happy Birthday")) {
            list.add("");
            list.add("May your birthday be filled with joy.");
            list.add("Wishing you happiness and success always.");
            list.add("Another year older, another year wiser.");
            list.add("Celebrate your special day with great memories.");
            list.add("May all your dreams come true today.");
            list.add("Sending you love and blessings on your birthday.");
            list.add("Cheers to another year of adventures ahead.");
            list.add("May this birthday bring you endless laughter.");
            list.add("Enjoy your day to the fullest!");
            list.add("Happy birthday! Make it an unforgettable celebration.");


        } else if (cardname.equals("Kids")) {
            list.add("");
            list.add("Flowers are words which even a baby can understand");
            list.add("Fairy tales do come true. Look at us, we had you.");
            list.add("A baby’s smile is an antidote to melt your day’s stress away.");
            list.add("Babies are bits of stardust, blown from the hand of God.");
            list.add("Sometimes, the smallest things take up the most room in your heart.");
            list.add("Sometimes, the smallest things take up the most room in your heart.");
            list.add("Babies smile in their sleep because they’re listening to the whispering of angels");
            list.add("Ten little fingers, ten perfect toes, fill our hearts with love that overflows.");
            list.add("The very first moment I beheld him, my heart was irrevocably gone.");
            list.add("A baby boy may leave smudges in the house and also on your heart.");


        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text = adapterView.getItemAtPosition(i).toString();
                ed_three.setText(text);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.tvOne.setText(ed_one.getText().toString());
                binding.tvTwo.setText(ed_two.getText().toString());
                binding.tvThree.setText(ed_three.getText().toString());

                dialog.dismiss();


            }
        });


        dialog.show();
    }


    private void viewTransformation(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xCoOrdinate = view.getX() - event.getRawX();
                yCoOrdinate = view.getY() - event.getRawY();

                start.set(event.getX(), event.getY());
                isOutSide = false;
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    midPoint(mid, event);
                    mode = ZOOM;
                }

                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
                isZoomAndRotate = false;
                if (mode == DRAG) {
                    float x = event.getX();
                    float y = event.getY();
                }
            case MotionEvent.ACTION_OUTSIDE:
                isOutSide = true;
                mode = NONE;
                lastEvent = null;
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isOutSide) {
                    if (mode == DRAG) {
                        isZoomAndRotate = false;
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                    }
                    if (mode == ZOOM && event.getPointerCount() == 2) {
                        float newDist1 = spacing(event);
                        if (newDist1 > 10f) {
                            float scale = newDist1 / oldDist * view.getScaleX();
                            view.setScaleX(scale);
                            view.setScaleY(scale);
                        }
                        if (lastEvent != null) {
                            newRot = rotation(event);
                            view.setRotation((float) (view.getRotation() + (newRot - d)));
                        }
                    }
                }
                break;
        }
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (int) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /*private void toggleFavorite() {


        isFavorite = !isFavorite;
        if (isFavorite) {
            binding.fav.setImageResource(R.drawable.ic_baseline_favorite_24);
            binding.fav.setEnabled(false);

            boolean IsInserted = myDb.insertData(Integer.parseInt(value));

            if (IsInserted) {
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(details_activity.this, Favourite.class);
                startActivity(intent);


            } else {
                Toast.makeText(details_activity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
            }
        } else {
            binding.fav.setImageResource(R.drawable.ic_baseline_favorite_border_24); // Set the appropriate non-favorite image resource
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        }

    }*/


    private void saveLayoutToGallery(View view) {
        // Create a bitmap from the layout view
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        // Save the bitmap to the device's gallery
        OutputStream outputStream = null;
        File imageFile = null;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentResolver resolver = getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "card_image");
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (imageUri != null) {
                    outputStream = resolver.openOutputStream(imageUri);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.close();
                    Toast.makeText(this, "Layout saved to gallery", Toast.LENGTH_SHORT).show();
                }
            } else {
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                imageFile = new File(directory, "image.jpg");
                outputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
                Toast.makeText(this, "Layout saved to gallery", Toast.LENGTH_SHORT).show();
            }

            // Add the saved image to the device's media library
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "layout_image", "Layout Image");

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save layout", Toast.LENGTH_SHORT).show();
        } finally {
            // Release resources
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Release bitmap and canvas resources
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
            if (canvas != null) {
                canvas.setBitmap(null);
                canvas = null;
            }
        }
    }

    private void showEmojiDialog() {
        final String[] emojis = {
                "\uD83D\uDE00", "\uD83D\uDE02", "\uD83D\uDE0D", "\uD83C\uDF89", "\uD83C\uDF1F",
                "\uD83D\uDE01", "\uD83D\uDE03", "\uD83D\uDE09", "\uD83D\uDE0A", "\uD83D\uDE0B",
                "\uD83D\uDE0E", "\uD83D\uDE0F", "\uD83D\uDE14", "\uD83D\uDE16", "\uD83D\uDE18",
                "\uD83D\uDE1A", "\uD83D\uDE1C", "\uD83D\uDE1D", "\uD83D\uDE1E", "\uD83D\uDE22",
                "\uD83D\uDE2D", "\uD83D\uDE31", "\uD83D\uDE36", "\uD83D\uDE37", "\uD83D\uDE3A",
                "\uD83D\uDE3C", "\uD83D\uDE3D", "\uD83D\uDE3E", "\uD83D\uDE3F", "\uD83D\uDE40",
                "\uD83D\uDE48", "\uD83D\uDE49", "\uD83D\uDE4A", "\uD83D\uDE4F", "\uD83D\uDE80",
                "\uD83D\uDE81", "\uD83D\uDE82", "\uD83D\uDE83", "\uD83D\uDE84", "\uD83D\uDE85",
                "\uD83D\uDE87", "\uD83D\uDE89", "\uD83D\uDE8A", "\uD83D\uDE8B", "\uD83D\uDE8C",
                "\uD83D\uDE8D", "\uD83D\uDE8E", "\uD83D\uDE8F", "\uD83D\uDE91", "\uD83D\uDE92",
                "\uD83D\uDE93", "\uD83D\uDE95", "\uD83D\uDE97", "\uD83D\uDE99", "\uD83D\uDE9A",
                "\uD83D\uDE9B", "\uD83D\uDE9C", "\uD83D\uDE9D", "\uD83D\uDEA4", "\uD83D\uDEA7",
                "\uD83D\uDEA8", "\uD83D\uDEA9", "\uD83D\uDEAB", "\uD83D\uDEAC", "\uD83D\uDEAD",
                "\uD83D\uDEB2", "\uD83D\uDEB6", "\uD83D\uDEB9", "\uD83D\uDEBA", "\uD83D\uDEBB",
                "\uD83D\uDEBC", "\uD83D\uDEBD", "\uD83D\uDEBE", "\uD83D\uDEBF", "\uD83D\uDEC0",
                "\uD83C\uDF31", "\uD83C\uDF38", "\uD83C\uDF39", "\uD83C\uDF3A", "\uD83C\uDF3B",
                "\uD83C\uDF70", "\uD83C\uDF6A", "\uD83C\uDF82", "\uD83C\uDF6E", "\uD83C\uDF6F",
                "\u2764", "\uD83D\uDC9E", "\uD83D\uDC9F", "\uD83D\uDC99", "\uD83D\uDC9A",
                "\u2764", "\uD83D\uDE0D", "\uD83D\uDC96", "\uD83D\uDC95", "\uD83D\uDC6B\u200D❤️\u200D\uD83D\uDC6B",
                "\u2764", "\uD83D\uDE17", "\uD83D\uDE37", "\uD83D\uDC76", "\uD83D\uDE17", "\uD83D\uDE37", "\uD83C\uDF54",
                "\uD83C\uDF55", "\uD83C\uDF56", "\uD83C\uDF57", "\uD83C\uDF58",
                "\uD83C\uDF59", "\uD83C\uDF5A", "\uD83C\uDF5B", "\uD83C\uDF5C", "\uD83C\uDF5D", // Noodles, Spaghetti, Sushi, Bento Box, Curry Rice
                "\uD83C\uDF5E", "\uD83C\uDF5F", "\uD83C\uDF60", "\uD83C\uDF61", "\uD83C\uDF62", // Fish Cake, Dango, Soft Ice Cream, Shaved Ice, Ice Cream
                "\uD83C\uDF63", "\uD83C\uDF64", "\uD83C\uDF65", "\uD83C\uDF66", "\uD83C\uDF67", // Doughnut, Cookie, Chocolate Bar, Candy, Lollipop
                "\uD83D\uDEC1", "\uD83D\uDEC2", "\uD83D\uDEC3", "\uD83D\uDEC4", "\uD83D\uDEC5",
                "\uD83D\uDEC6", "\uD83D\uDECB", "\uD83D\uDECD", "\uD83D\uDECE", "\uD83D\uDECF",
                "\uD83D\uDED0", "\uD83D\uDED1", "\uD83D\uDED2", "\uD83D\uDED3", "\uD83D\uDED4",
                "\uD83D\uDED5", "\uD83D\uDED7", "\uD83D\uDEE0", "\uD83D\uDEE1", "\uD83D\uDEE2",
                "\uD83D\uDEE3", "\uD83D\uDEE5", "\uD83D\uDEE9", "\uD83D\uDEEB", "\uD83D\uDEEC",
                "\uD83D\uDEED", "\uD83D\uDEEE", "\uD83D\uDEEF", "\uD83D\uDEF0"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Emoji");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_emoji, null);
        builder.setView(dialogView);

        GridView gridView = dialogView.findViewById(R.id.gridView);
        ArrayAdapter<String> emojiAdapter = new ArrayAdapter<>(this, simple_list_item_1, emojis);
        gridView.setAdapter(emojiAdapter);

        final AlertDialog dialog = builder.create();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedEmoji = emojis[position];
                binding.emoji.setText(selectedEmoji);
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    private void opencolourpickerone() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultcolourone, new AmbilWarnaDialog.OnAmbilWarnaListener() {


            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolourone = color;
                binding.tvOne.setTextColor(defaultcolourone);

            }
        });
        ambilWarnaDialog.show();
    }

    private void opencolourpickertwo() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultcolourtwo, new AmbilWarnaDialog.OnAmbilWarnaListener() {


            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolourtwo = color;
                binding.tvTwo.setTextColor(defaultcolourtwo);

            }
        });
        ambilWarnaDialog.show();
    }

    private void opencolourpickerthree() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultcolourthree, new AmbilWarnaDialog.OnAmbilWarnaListener() {


            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolourthree = color;
                binding.tvThree.setTextColor(defaultcolourthree);

            }
        });
        ambilWarnaDialog.show();
    }


    public void bold(TextView txt) {
        if (!isBold) {
            originalTypeface = txt.getTypeface(); // Store the original typeface
            txt.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            txt.setTypeface(originalTypeface); // Reapply the original typeface
        }
        isBold = !isBold;
    }

    public void italic(TextView txt) {


        if (!isItalic) {
            originalTypeface = txt.getTypeface();
            txt.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);

        } else {
            txt.setTypeface(originalTypeface);

        }
        isItalic = !isItalic;
    }

    public void strike(TextView txt) {


        if (isStrikethrough) {
            txt.setPaintFlags(txt.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        } else {
            txt.setPaintFlags(txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        isStrikethrough = !isStrikethrough;
    }


    public void click(View view) {
        binding.tvOne.setBackground(null);
        binding.tvTwo.setBackground(null);
        binding.tvThree.setBackground(null);
    }

    private void loadTypefaces() {
        typefaces = new Typeface[fontItems.length];

        for (int i = 0; i < fontItems.length; i++) {
            String fontPath = "fonts/" + fontItems[i];
            typefaces[i] = Typeface.createFromAsset(getAssets(), fontPath);
        }
    }
}



