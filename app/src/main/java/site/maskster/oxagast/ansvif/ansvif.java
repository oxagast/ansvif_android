package site.maskster.oxagast.ansvif;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class ansvif extends AppCompatActivity {

    String ansvifCMD;
    String ansvifOut;
    String ansvifLoc;
    String ansvifOpts;

    @SuppressLint("StaticFieldLeak")
    private class runme extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            final TextView ansvifRun = findViewById((R.id.output));
            ansvifRun.setText("Please Wait...");
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                // Run the command
                Process process = Runtime.getRuntime().exec(ansvifLoc + ansvifCMD);
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                // Grab the results
                StringBuilder log = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    log.append(line).append("\n");
                }

                ansvifOut = log.toString();
                Log.i("Crashlog", ansvifOut);
                return(log.toString());
            } catch (IOException e) {
                Log.i("Crashlog", "wtf?");
                return ("Error...");
            }
        }


        @Override
        protected void onPostExecute(Object a) {
            final TextView ansvifRun = findViewById((R.id.output));
            ansvifRun.setText(ansvifOut);
        }
        }
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ansvif);
        final Button fuzzButton;
        final EditText raText;
        final EditText rbText;
        final EditText aaText;
        final EditText abText;
        final EditText sepText;
        final EditText maxArgsText;
        final EditText extraTemplateText;
        final EditText templateText;
        final EditText fuzzedCMD;
        final EditText bufSizeText;
        final EditText logText;
        fuzzButton = findViewById(R.id.fuzz);
        raText = findViewById(R.id.runafter);
        rbText = findViewById(R.id.runbefore);
        aaText = findViewById(R.id.alwaysafter);
        abText = findViewById(R.id.alwaysbefore);
        sepText = findViewById(R.id.sep);
        maxArgsText = findViewById(R.id.maxargs);
        extraTemplateText = findViewById(R.id.template_x);
        templateText = findViewById(R.id.template_t);
        fuzzedCMD = findViewById(R.id.fuzzme);
        bufSizeText = findViewById(R.id.buffersize);
        logText = findViewById(R.id.logtome);
        fuzzButton.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("StaticFieldLeak")
                    public void onClick(View view) {
                        ansvifLoc = "/data/data/site.maskster.oxagast.ansvif/lib/libansvif-wrapper.so ";
                        String ansvifHardOpts = " -f 1";
                        ansvifCMD = "";
                        if (!raText.getText().toString().equals("")) {
                            ansvifCMD += " -R " + raText.getText().toString();
                        }
                        if (!rbText.getText().toString().equals("")) {
                            ansvifCMD += " -E " + rbText.getText().toString();
                        }
                        if (!aaText.getText().toString().equals("")) {
                            ansvifCMD += " -A " + aaText.getText().toString();
                        }
                        if (!abText.getText().toString().equals("")) {
                            ansvifCMD += " -B " + abText.getText().toString();
                        }
                        if (!sepText.getText().toString().equals("")) {
                            ansvifCMD += " -S " + sepText.getText().toString();
                        }
                        if (!maxArgsText.getText().toString().equals("")) {
                            ansvifCMD += " -M " + maxArgsText.getText().toString();
                        }
                        if (!extraTemplateText.getText().toString().equals("")) {
                            ansvifCMD += " -x " + extraTemplateText.getText().toString();
                        }
                        if (!templateText.getText().toString().equals("")) {
                            ansvifCMD += " -t " + templateText.getText().toString();
                        }
                        if (!fuzzedCMD.getText().toString().equals("")) {
                            ansvifCMD += " -c " + fuzzedCMD.getText().toString();
                        }
                        if (!bufSizeText.getText().toString().equals("")) {
                            ansvifCMD += " -b " + bufSizeText.getText().toString();
                        }
                        if (!logText.getText().toString().equals("")) {
                            ansvifCMD += " -o " + logText.getText().toString();
                        }
                        ansvifOpts = ansvifCMD + ansvifHardOpts;
                        final TextView ansvifComm = findViewById((R.id.opt));
                        ansvifComm.setText(ansvifCMD);
                        new runme().execute();
                    }
                });
    }
}