package ua.home.kubic.zilab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText key;
    EditText message;
    EditText result;
    Button encrypt;

    int n = 32;
    private char [] upper = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К',
            'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'};
    private char [] lower = {'а', 'б', 'в', 'г', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й', 'к',
            'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = (EditText) findViewById(R.id.key);
        message = (EditText) findViewById(R.id.message);
        result = (EditText) findViewById(R.id.result);
        encrypt = (Button) findViewById(R.id.btn_encrypt);
    }

    public void onClick(View v) {
        Toast toast = null;
        if (key.getText().toString().isEmpty()) {
            if (toast != null) toast.cancel();
            toast = Toast.makeText(this, "Please enter key!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            char c;
            int k = Integer.parseInt(key.getText().toString());
            char[] res = new char[message.getText().length()];
            switch (v.getId()) {
                case R.id.btn_encrypt:
                    for (int i = 0; i < message.getText().length(); i++) {
                        c = message.getText().charAt(i);
                        for (int j = 0; j < n; j++) {
                            if(c == upper[j]){
                                res[i] = upper[(j + k) % n];
                                break;
                            }else if (c == lower[j]){
                                res[i] = lower[(j + k) % (n)];
                                break;
                            }else {
                                res[i] = c;
                            }
                        }
                    }
                    result.setText(res, 0, res.length);
                    break;
                case R.id.btn_decrypt:
                    for (int i = 0; i < message.getText().length(); i++) {
                        c = message.getText().charAt(i);
                        for (int j = 0; j < n; j++) {
                            if(c == upper[j]){
                                res[i] = upper[((j - k + n) % (n))];
                                break;
                            }else if (c == lower[j]){
                                res[i] = lower[((j - k + n) % (n))];
                                break;
                            }else {
                                res[i] = c;
                            }
                        }
                    }
                    result.setText(res, 0, res.length);
                    break;
            }
        }
    }
}
