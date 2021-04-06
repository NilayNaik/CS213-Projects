package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Main activity to calculate admission prices.
 * Info shown is dependent on museum selected in previous activity.
 * @author Nilay Naik, Evan Maggio
 */
public class MuseumAdmissions extends AppCompatActivity {

    private String museumChoice = "";
    private TextView studentTicketText, adultTicketText, seniorTicketText, studentTicketCountText, adultTicketCountText, seniorTicketCountText, studentTicketPriceText, adultTicketPriceText, seniorTicketPriceText;
    private TextView totalTicketPrice, salesTax, totalAfterTax;
    private ImageView museumImage;
    private Button decreaseStudentTicketCount, increaseStudentTicketCount, decreaseAdultTicketCount, increaseAdultTicketCount, decreaseSeniorTicketCount, increaseSeniorTicketCount;
    private int studentTickets = 0, adultTickets = 0, seniorTickets = 0;
    private final int MUSEUM1_STUDENT_TICKET_PRICE = 14, MUSEUM1_ADULT_TICKET_PRICE = 25, MUSEUM1_SENIOR_TICKET_PRICE = 18;
    private final int MUSEUM2_STUDENT_TICKET_PRICE = 18, MUSEUM2_ADULT_TICKET_PRICE = 23, MUSEUM2_SENIOR_TICKET_PRICE = 18;
    private final int MUSEUM3_STUDENT_TICKET_PRICE = 12, MUSEUM3_ADULT_TICKET_PRICE = 25, MUSEUM3_SENIOR_TICKET_PRICE = 17;
    private final int MUSEUM4_STUDENT_TICKET_PRICE = 14, MUSEUM4_ADULT_TICKET_PRICE = 20, MUSEUM4_SENIOR_TICKET_PRICE = 14;
    private int studentTicketPrice = 0, adultTicketPrice = 0, seniorTicketPrice = 0;
    private final double NY_TAX_RATE = .08875;

    private DecimalFormat df = new DecimalFormat("#.00");

    /**
     * Sets up views and GUI elements on activity creation.
     * @param savedInstanceState bundle sent to activity on creation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_admissions);

        Intent intent = getIntent();
        museumChoice = intent.getStringExtra(Intent.EXTRA_TEXT);

        studentTicketText = (TextView) findViewById(R.id.studentTicketsText);
        adultTicketText = (TextView) findViewById(R.id.adultTicketsText);
        seniorTicketText = (TextView) findViewById(R.id.seniorTicketsText);
        studentTicketCountText = (TextView) findViewById(R.id.studentTicketCountText);
        adultTicketCountText = (TextView) findViewById(R.id.adultTicketCountText);
        seniorTicketCountText = (TextView) findViewById(R.id.seniorTicketCountText);
        studentTicketPriceText = (TextView) findViewById(R.id.studentTicketPriceText);
        adultTicketPriceText = (TextView) findViewById(R.id.adultTicketPriceText);
        seniorTicketPriceText = (TextView) findViewById(R.id.seniorTicketPriceText);
        totalTicketPrice = (TextView) findViewById(R.id.totalTicketPrice);
        salesTax = (TextView) findViewById(R.id.salesTax);
        totalAfterTax = (TextView) findViewById(R.id.totalAfterTax);

        museumImage = (ImageView) findViewById(R.id.museumImageView);

        decreaseStudentTicketCount = (Button) findViewById(R.id.decreaseStudentTicketCount);
        increaseStudentTicketCount = (Button) findViewById(R.id.increaseStudentTicketCount);
        decreaseAdultTicketCount = (Button) findViewById(R.id.decreaseAdultTicketCount);
        increaseAdultTicketCount = (Button) findViewById(R.id.increaseAdultTicketCount);
        decreaseSeniorTicketCount = (Button) findViewById(R.id.decreaseSeniorTicketCount);
        increaseSeniorTicketCount = (Button) findViewById(R.id.increaseSeniorTicketCount);

        decreaseStudentTicketCount.setEnabled(false);
        decreaseAdultTicketCount.setEnabled(false);
        decreaseSeniorTicketCount.setEnabled(false);

        studentTicketCountText.setText("" + studentTickets);
        adultTicketCountText.setText("" + adultTickets);
        seniorTicketCountText.setText("" + seniorTickets);

        studentTicketPriceText.setText(R.string.zeroIntMoney);
        adultTicketPriceText.setText(R.string.zeroIntMoney);
        seniorTicketPriceText.setText(R.string.zeroIntMoney);

        totalTicketPrice.setText(R.string.zerDoubleMoney);
        salesTax.setText(R.string.zerDoubleMoney);
        totalAfterTax.setText(R.string.zerDoubleMoney);

    }

    /**
     * Initializes info based on museum choice.
     */
    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, R.string.maxTicketNotice, Toast.LENGTH_LONG).show();

        switch (museumChoice){
            case "museum1":
                museumImage.setImageResource(R.drawable.museum_of_modern_art);
                studentTicketText.setText(R.string.museum1StudentTicket);
                adultTicketText.setText(R.string.museum1AdultTicket);
                seniorTicketText.setText(R.string.museum1SeniorTicket);
                studentTicketPrice = MUSEUM1_STUDENT_TICKET_PRICE;
                adultTicketPrice = MUSEUM1_ADULT_TICKET_PRICE;
                seniorTicketPrice = MUSEUM1_SENIOR_TICKET_PRICE;
                break;
            case "museum2":
                museumImage.setImageResource(R.drawable.american_museum_of_natural_history);
                studentTicketText.setText(R.string.museum2StudentTicket);
                adultTicketText.setText(R.string.museum2AdultTicket);
                seniorTicketText.setText(R.string.museum2SeniorTicket);
                studentTicketPrice = MUSEUM2_STUDENT_TICKET_PRICE;
                adultTicketPrice = MUSEUM2_ADULT_TICKET_PRICE;
                seniorTicketPrice = MUSEUM2_SENIOR_TICKET_PRICE;
                break;
            case "museum3":
                museumImage.setImageResource(R.drawable.metropolitan_museum_of_art);
                studentTicketText.setText(R.string.museum3StudentTicket);
                adultTicketText.setText(R.string.museum3AdultTicket);
                seniorTicketText.setText(R.string.museum3SeniorTicket);
                studentTicketPrice = MUSEUM3_STUDENT_TICKET_PRICE;
                adultTicketPrice = MUSEUM3_ADULT_TICKET_PRICE;
                seniorTicketPrice = MUSEUM3_SENIOR_TICKET_PRICE;
                break;
            case "museum4":
                museumImage.setImageResource(R.drawable.museum_of_the_city_of_new_york);
                studentTicketText.setText(R.string.museum4StudentTicket);
                adultTicketText.setText(R.string.museum4AdultTicket);
                seniorTicketText.setText(R.string.museum4SeniorTicket);
                studentTicketPrice = MUSEUM4_STUDENT_TICKET_PRICE;
                adultTicketPrice = MUSEUM4_ADULT_TICKET_PRICE;
                seniorTicketPrice = MUSEUM4_SENIOR_TICKET_PRICE;
                break;
        }

    }

    /**
     * Modifies GUI when a button is pressed.
     * @param v View to obtain ID of selected button.
     */
    public void onChange(View v){
        switch (v.getId()){
            case R.id.decreaseStudentTicketCount:
                increaseStudentTicketCount.setEnabled(true);
                studentTickets--;
                studentTicketCountText.setText("" + studentTickets);
                if (studentTickets <= 0){
                    decreaseStudentTicketCount.setEnabled(false);
                }
                break;
            case R.id.decreaseAdultTicketCount:
                increaseAdultTicketCount.setEnabled(true);
                adultTickets--;
                adultTicketCountText.setText("" + adultTickets);
                if (adultTickets <= 0){
                    decreaseAdultTicketCount.setEnabled(false);
                }
                break;
            case R.id.decreaseSeniorTicketCount:
                increaseSeniorTicketCount.setEnabled(true);
                seniorTickets--;
                seniorTicketCountText.setText("" + seniorTickets);
                if (seniorTickets <= 0){
                    decreaseSeniorTicketCount.setEnabled(false);
                }
                break;
            case R.id.increaseStudentTicketCount:
                decreaseStudentTicketCount.setEnabled(true);
                studentTickets++;
                studentTicketCountText.setText("" + studentTickets);
                if (studentTickets >= 5){
                    increaseStudentTicketCount.setEnabled(false);
                }
                break;
            case R.id.increaseAdultTicketCount:
                decreaseAdultTicketCount.setEnabled(true);
                adultTickets++;
                adultTicketCountText.setText("" + adultTickets);
                if (adultTickets >= 5){
                    increaseAdultTicketCount.setEnabled(false);
                }
                break;
            case R.id.increaseSeniorTicketCount:
                decreaseSeniorTicketCount.setEnabled(true);
                seniorTickets++;
                seniorTicketCountText.setText("" + seniorTickets);
                if (seniorTickets >= 5){
                    increaseSeniorTicketCount.setEnabled(false);
                }
                break;
        }
        int totalStudentPrice = studentTickets * studentTicketPrice;
        int totalAdultPrice = adultTickets * adultTicketPrice;
        int totalSeniorPrice = seniorTickets * seniorTicketPrice;
        studentTicketPriceText.setText("$" + totalStudentPrice);
        adultTicketPriceText.setText("$" + totalAdultPrice);
        seniorTicketPriceText.setText("$" + totalSeniorPrice);

        int total = totalStudentPrice + totalAdultPrice + totalSeniorPrice;
        totalTicketPrice.setText("$" + total);
        double tax = total * NY_TAX_RATE;
        salesTax.setText("$" + df.format(tax));
        totalAfterTax.setText("$" + df.format(total + tax));
    }



}