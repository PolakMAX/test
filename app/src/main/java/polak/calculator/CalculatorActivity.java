package polak.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CalculatorActivity extends Activity {

    private Button mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7,
                    mButton8, mButton9, mButtonTacka ,mButtonPlus, mButtonMinus, mButtonPuta, mButtonPodeljeno,
                    mButtonC, mButtonCE ,mButtonJednako;

    private TextView mTextView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        mButton0 = findViewById(R.id.button0);
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);
        mButton5 = findViewById(R.id.button5);
        mButton6 = findViewById(R.id.button6);
        mButton7 = findViewById(R.id.button7);
        mButton8 = findViewById(R.id.button8);
        mButton9 = findViewById(R.id.button9);
        mButtonTacka = findViewById(R.id.buttonTacka);
        mButtonPlus = findViewById(R.id.buttonPlus);
        mButtonMinus = findViewById(R.id.buttonMinus);
        mButtonPuta = findViewById(R.id.buttonPuta);
        mButtonPodeljeno = findViewById(R.id.buttonPodeljeno);
        mButtonJednako = findViewById(R.id.buttonJednako);
        mButtonC = findViewById(R.id.buttonC);
        mButtonCE = findViewById(R.id.buttonCE);
        mTextView = findViewById(R.id.textViewBrojevi);


        mButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+ "0");
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+"1");
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+ "2");
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+"3");
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+"4");
            }
        });

        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+"5");
            }
        });

        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+"6");
            }
        });

        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+"7");
            }
        });

        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+"8");
            }
        });

        mButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+"9");
            }
        });

        mButtonTacka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mTextView.getText().toString()+ ".");
            }
        });



        mButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextView.length() != 0){
                    mTextView.setText(mTextView.getText().toString().substring(0,mTextView.length()-1));
                }
                else {mTextView.setText("");}
            }
        });

        mButtonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText("");
            }
        });

        mButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTextView.setText(mTextView.getText().toString()+"+");

            }
        });

        mButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTextView.setText(mTextView.getText().toString()+"-");
            }
        });

        mButtonPuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTextView.setText(mTextView.getText().toString()+"*");
            }
        });

        mButtonPodeljeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTextView.setText(mTextView.getText().toString()+"/");
            }
        });

        mButtonJednako.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(""+ eval(mTextView.getText().toString()));
            }
        });
    }

    public static final double eval(final String str) {

        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }
        }.parse();
    }
}
