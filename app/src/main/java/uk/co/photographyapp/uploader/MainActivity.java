package uk.co.photographyapp.uploader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String UPLOAD_URL = "http://your/link/to/upload.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";


    private final int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;
    private Button btn;

    private ImageView imageView;
    private final int CAMERA = 2;

    private Bitmap bitmap;

    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        btn = (Button) findViewById(R.id.btn);
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonUpload.setVisibility(View.GONE); //To set GONE
        buttonView = (Button) findViewById(R.id.buttonViewImage);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        btn.setOnClickListener(this);


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    private void showCamera() {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case PICK_IMAGE_REQUEST:
                    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                        buttonUpload.setVisibility( View.VISIBLE ); //To set visible
                        filePath = data.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), filePath );
                            imageView.setImageBitmap( bitmap );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case CAMERA:
                    if (requestCode == CAMERA && resultCode == Activity.RESULT_OK && null != data)
                    {
                        buttonUpload.setVisibility(View.VISIBLE); //To set visible
                        filePath = data.getData();
                         try {
                             bitmap = (Bitmap) data.getExtras().get( "data" );
                             imageView.setImageBitmap( bitmap );
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                    }
                    break;
                default:
                    throw new IllegalStateException( "Unexpected value: " + requestCode );
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream );
        byte[] imageBytes = byteArrayOutputStream .toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;


       // byte[] imageBytes = byteArrayOutputStream .toByteArray();
       // return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);
                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);
                String result = rh.sendPostRequest(UPLOAD_URL,data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    private Spinner spinner1;

    // Spinner
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        };


    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if(v == buttonUpload){
            uploadImage();
        }
        if(v == buttonView){
            viewImage();
        }
        if(v == btn){
            showCamera();
        }
    }

    private void viewImage() {
        startActivity(new Intent(this, ViewImage.class));
    }
}