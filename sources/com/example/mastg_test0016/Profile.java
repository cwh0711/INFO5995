package com.example.mastg_test0016;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/* JADX INFO: loaded from: classes.dex */
public class Profile extends AppCompatActivity {
    private static final String KEY_SESSION_TOKEN = "sessionToken";
    private static final String SESSION_PREF_NAME = "SessionPrefs";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() { // from class: com.example.mastg_test0016.Profile$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return Profile.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(SESSION_PREF_NAME, 0);
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        ((Button) findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() { // from class: com.example.mastg_test0016.Profile.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Profile.this.clearSession();
            }
        });
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        return windowInsetsCompat;
    }

    public void clearSession() {
        this.editor.remove(KEY_SESSION_TOKEN);
        this.editor.apply();
    }
}
