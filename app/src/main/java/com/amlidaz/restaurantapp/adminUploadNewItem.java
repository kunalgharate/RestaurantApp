package com.amlidaz.restaurantapp;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amlidaz.restaurantapp.model.ImageUpload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class adminUploadNewItem extends AppCompatActivity implements View.OnClickListener {

    public static final String FB_Storage_Path = "image/";
    public static final String FB_Database_Path = "image";
    public static final int Request_Code = 1234;
    Button btnUploadImage, btnAddProductType, btnUploadItem;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ImageView imageView;
    private EditText editTextItemName, editTextItemDesc, editTextItemPrice;
    private Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_upload_new_item);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_Database_Path);

        imageView = (ImageView) findViewById(R.id.imageView);
        editTextItemName = (EditText) findViewById(R.id.editTextItemName);
        editTextItemDesc = (EditText) findViewById(R.id.editTextItemDesc);
        editTextItemPrice = (EditText) findViewById(R.id.editTextItemPrice);
        btnAddProductType = (Button) findViewById(R.id.btn_addnewproduct);
        btnUploadImage = (Button) findViewById(R.id.btn_uploadimage);
        btnUploadItem = (Button) findViewById(R.id.btn_submitdata);

        btnAddProductType.setOnClickListener(this);
        btnUploadImage.setOnClickListener(this);
        btnUploadItem.setOnClickListener(this);

    }

   /* public void btnBrowse_Click(View v) {
        Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), Request_Code);
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imguri);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImageExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @SuppressWarnings("VisibleForTests")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_uploadimage:
                //Browse Image
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select image"), Request_Code);
                break;
            case R.id.btn_addnewproduct:

                addProductType();
                break;
            case R.id.btn_submitdata:
                //Submit Data Ti firebase

        if (imguri != null) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Uploading image");
            dialog.show();

            //Get the storage reference
            StorageReference ref = storageReference.child(FB_Storage_Path + System.currentTimeMillis() + "." + getImageExt(imguri));

            //Add file to reference

            ref.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    //Dimiss dialog when success
                    dialog.dismiss();
                    //Display success toast msg
                    Toast.makeText(getApplicationContext(), "Image uploaded", Toast.LENGTH_SHORT).show();
                    //  ImageUpload imageUpload = new ImageUpload(editText.getText().toString(), taskSnapshot.getDownloadUrl().toString());
                    ImageUpload imageUpload = new ImageUpload(editTextItemName.getText().toString(), editTextItemDesc.getText().toString(), editTextItemPrice.getText().toString(), taskSnapshot.getDownloadUrl().toString());


                    //Save image info in to firebase database
                    String uploadId = databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(imageUpload);

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            //Dimiss dialog when error
                            dialog.dismiss();
                            //Display err toast msg
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            //Show upload progress

                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });

            Intent i = new Intent(adminUploadNewItem.this, WaiterDashBoard.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Please select image", Toast.LENGTH_SHORT).show();
        }
                break;
        }


    }

    private void addProductType() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_product_type, null);
        dialogBuilder.setView(dialogView);

        final EditText neweditproductype = (EditText) dialogView.findViewById(R.id.edit_product_type);
        final Button btnAddProductType = (Button) dialogView.findViewById(R.id.btn_add_product_type);
        final Button btnCancelProductType = (Button) dialogView.findViewById(R.id.btn_cancel_product_type);
        final ProgressBar progressBar1 = (ProgressBar) dialogView.findViewById(R.id.progressBar);


        //dialogBuilder.setTitle("Send Photos");
        final AlertDialog dialog = dialogBuilder.create();

        btnCancelProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnAddProductType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String editproductype = neweditproductype.getText().toString().trim();

                if (TextUtils.isEmpty(editproductype)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }

               /* progressBar1.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                                progressBar1.setVisibility(View.GONE);
                                dialog.dismiss();
                            }
                        });*/

            }
        });
        dialog.show();
    }


}
