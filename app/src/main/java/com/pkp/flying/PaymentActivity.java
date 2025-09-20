package com.pkp.flying;
import org.json.JSONObject;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());

        Button payNowButton = findViewById(R.id.payNowButton);
        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Checkout checkout = new Checkout();
               checkout.setKeyID("rzp_test_5vKqz3Hwa5ibf7");

                try {
                    JSONObject options = new JSONObject();

                    options.put("name", "pkp institute");
                    options.put("description", "purchasing 999 plan");
                    options.put("image", "http://example.com/image/rzp.jpg");
                    options.put("order_id", "order_QzG3EFiMeWfNBz");//from response of step 3.
                    options.put("theme.color", "#3399cc");
                    options.put("currency", "INR");
                    options.put("amount", "99900");//pass amount in currency subunits
                    options.put("prefill.email", "sksss@gmail.com");
                    options.put("prefill.contact","+919876543210");
                    JSONObject retryObj = new JSONObject();
                    retryObj.put("enabled", true);
                    retryObj.put("max_count", 4);
                    options.put("retry", retryObj);

                    checkout.open(PaymentActivity.this, options);

                } catch(Exception e) {
                    Log.e("Error message", "Error in starting Razorpay Checkout", e);
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
Log.d("payment success", razorpayPaymentID);
    }

    @Override
    public void onPaymentError(int code, String response) {
Log.e("payment error", response);
    }
}