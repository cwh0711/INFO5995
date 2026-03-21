package com.example.mastg_test0016;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    public int randomNumberGenerator() {
        int iNextInt = new Random().nextInt(100);
        Log.d("Random Number: ", "" + iNextInt);
        return iNextInt;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.editTextText);
        final EditText editText2 = (EditText) findViewById(R.id.editTextTextPassword);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.lg);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.example.mastg_test0016.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = editText.getText().toString();
                String string2 = editText2.getText().toString();
                if (!string.equals("") && !string2.equals("")) {
                    MainActivity.this.saveCredentialsToFile(string, string2);
                    MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) Login.class));
                    return;
                }
                Toast.makeText(MainActivity.this, "Fill the Form", 0).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.example.mastg_test0016.MainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) Login.class));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0056 -> B:23:0x0059). Please report as a decompilation issue!!! */
    public void saveCredentialsToFile(String str, String str2) {
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                fileOutputStreamOpenFileOutput = openFileOutput("credentials.txt", 32768);
                fileOutputStreamOpenFileOutput.write(("Username: " + str + " Password: " + str2 + "\n").getBytes());
                Toast.makeText(this, "Credentials saved to file", 0).show();
                if (fileOutputStreamOpenFileOutput != null) {
                    fileOutputStreamOpenFileOutput.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                Toast.makeText(this, "Error saving credentials", 0).show();
                if (fileOutputStreamOpenFileOutput != null) {
                    fileOutputStreamOpenFileOutput.close();
                }
            }
        } catch (Throwable th) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }
}
