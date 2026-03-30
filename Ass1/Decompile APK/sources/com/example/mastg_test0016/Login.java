package com.example.mastg_test0016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class Login extends AppCompatActivity {
    private static final String KEY_SESSION_TOKEN = "sessionToken";
    private static final String SESSION_PREF_NAME = "SessionPrefs";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() { // from class: com.example.mastg_test0016.Login$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return Login.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar2));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences sharedPreferences = getSharedPreferences(SESSION_PREF_NAME, 0);
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        final EditText editText = (EditText) findViewById(R.id.editTextText2);
        final EditText editText2 = (EditText) findViewById(R.id.editTextTextPassword2);
        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() { // from class: com.example.mastg_test0016.Login.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws Throwable {
                boolean zCheckCredentials = Login.this.checkCredentials(editText.getText().toString(), editText2.getText().toString());
                Log.d("result func:", "" + zCheckCredentials);
                if (!zCheckCredentials) {
                    Toast.makeText(Login.this, "Wrong Credential", 0).show();
                    return;
                }
                Login.this.createSession();
                Login.this.startActivity(new Intent(Login.this, (Class<?>) Profile.class));
            }
        });
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        return windowInsetsCompat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkCredentials(String str, String str2) throws Throwable {
        FileInputStream fileInputStreamOpenFileInput;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader = null;
        try {
            try {
                fileInputStreamOpenFileInput = openFileInput("credentials.txt");
                try {
                    inputStreamReader = new InputStreamReader(fileInputStreamOpenFileInput);
                    try {
                        try {
                            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                            while (true) {
                                try {
                                    String line = bufferedReader2.readLine();
                                    if (line != null) {
                                        String[] strArrSplit = line.split(" ");
                                        if (strArrSplit.length == 4 && strArrSplit[0].equals("Username:") && strArrSplit[2].equals("Password:")) {
                                            String str3 = strArrSplit[1];
                                            String str4 = strArrSplit[3];
                                            String strTrim = str3.trim();
                                            String strTrim2 = str4.trim();
                                            if (str.equals(strTrim) && str2.equals(strTrim2)) {
                                                try {
                                                    bufferedReader2.close();
                                                    inputStreamReader.close();
                                                    if (fileInputStreamOpenFileInput != null) {
                                                        fileInputStreamOpenFileInput.close();
                                                    }
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                return true;
                                            }
                                        }
                                    } else {
                                        bufferedReader2.close();
                                        inputStreamReader.close();
                                        if (fileInputStreamOpenFileInput != null) {
                                            fileInputStreamOpenFileInput.close();
                                        }
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    bufferedReader = bufferedReader2;
                                    e.printStackTrace();
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (fileInputStreamOpenFileInput != null) {
                                        fileInputStreamOpenFileInput.close();
                                    }
                                    return false;
                                } catch (Throwable th) {
                                    th = th;
                                    bufferedReader = bufferedReader2;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                            throw th;
                                        }
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (fileInputStreamOpenFileInput != null) {
                                        fileInputStreamOpenFileInput.close();
                                    }
                                    throw th;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (IOException e5) {
                    e = e5;
                    inputStreamReader = null;
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = null;
                }
            } catch (IOException e6) {
                e = e6;
                fileInputStreamOpenFileInput = null;
                inputStreamReader = null;
            } catch (Throwable th4) {
                th = th4;
                fileInputStreamOpenFileInput = null;
                inputStreamReader = null;
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        }
    }

    public void createSession() {
        this.editor.putString(KEY_SESSION_TOKEN, generateSessionToken());
        this.editor.apply();
    }

    public String getSessionToken() {
        return this.sharedPreferences.getString(KEY_SESSION_TOKEN, null);
    }

    private String generateSessionToken() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }
}
