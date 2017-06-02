package selectmultiple.vaibhav.com.selectmultiple;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.security.Permission;
import java.util.ArrayList;
import java.util.jar.Manifest;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = getApplicationContext().getPackageManager();
        int hasPerm = pm.checkPermission(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                getApplicationContext().getPackageName());
        if (hasPerm != PackageManager.PERMISSION_GRANTED) {
            // do stuff
            Toast.makeText(getApplicationContext(),"Grant Permisiion from settings",Toast.LENGTH_SHORT).show();
            // Ask for runtime permission
        }
        else
        {
            Button button = (Button)findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),FileSelectionActivity.class);
                    startActivityForResult(intent,101);
                }
            });

        }

        textView = (TextView)findViewById(R.id.textView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101)
        {
            Object files =  data.getStringArrayListExtra("upload");

            ArrayList<File> fileArrayList = (ArrayList<File>) files;
            String s= "\n";
            for (int i = 0 ; i <fileArrayList.size();i++)
            {

                s =  s +fileArrayList.get(i).getName()+"\n";

            }
            textView.setText("Selected files :--->"+""+s);
        }
    }
}
