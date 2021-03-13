package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order.
     * ₹ 40 per cup of Coffee
     * ₹ 10 extra for whipped cream
     * ₹ 20 extra for chocolate
     * @param addWhippedCream is to check whether Whipped Cream checkbox is checked or not
     * @param addChocolate is to check whether Whipped Cream checkbox is checked or not
     */
    private int calculatePrice(String addWhippedCream, String addChocolate) {
        int basePrice = 40;
        if(addWhippedCream == "Yes")
        {
            basePrice += 10;
        }
        if(addChocolate == "Yes")
        {
            basePrice += 20;
        }
        return quantity*basePrice;
    }

    /**
     * This method returns the state of checkbox named whipped cream
     *
     * @return
     */

    private String hasWhippedCream() {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        String WhippedCream = "No";
        if(hasWhippedCream)
            WhippedCream = "Yes";
        return WhippedCream;
    }

    /**
     * This method returns the state of checkbox named chocolate
     *
     * @return
     */

    private String hasChocolate()
    {
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String Chocolate = "No";
        if(hasChocolate)
            Chocolate = "Yes";
        return Chocolate;
    }

    /**
     * returns the editable name of the input user
     * @return
     */

    private String inputName()
    {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        String nameOfUser = nameEditText.getText().toString();
        return nameOfUser;
    }

    /**
     * Creates order summary for display
     *
     * @param price
     */

    public String createOrderSummary(int price) {
        String orderSummary = "Hello!! Sir/Ma'am " + inputName() +  "\n You Have Ordered : ";

        orderSummary = orderSummary + quantity + " cups of Coffee";
        orderSummary = orderSummary + "\n\n Add Ons :";
        orderSummary = orderSummary + "\n Added Whipped Cream : " + hasWhippedCream();
        orderSummary = orderSummary + "\n Added Chocolate : " + hasChocolate();
        orderSummary = orderSummary + "\n\n Your Total : ₹" + price + "/- only(Including GST)";
        orderSummary = orderSummary + "\n\n Thank You For choosing Us! Have a Nice Day :)";
        return orderSummary;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice(hasWhippedCream(),hasChocolate());
        String message = createOrderSummary(price);
//        displayMessage(message);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"pragyakmr00@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order Bill And Summary");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }


    int quantity = 2;

    /**
     * This method is used to increase the value of quantity by 1 globally
     *
     * @param view
     */

    public void increment(View view) {

        if(quantity == 100)
        {
            Toast.makeText(this,"You cannot have more than 100 coffees at a time",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is used to decrease the value of quantity by 1 globally
     *
     * @param view
     */

    public void decrement(View view) {

        if(quantity == 1)
        {
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }
}