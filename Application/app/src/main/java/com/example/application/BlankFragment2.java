package com.example.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageGridAdapter imageGridAdapter;

    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    private String intStorageDirectory;

    private Button button;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int[] imageIDs = new int[] {
            R.drawable.gallery_image_01,
            R.drawable.gallery_image_02,
            R.drawable.gallery_image_03,
            R.drawable.gallery_image_04,
            R.drawable.gallery_image_05,
            R.drawable.gallery_image_06,
            R.drawable.gallery_image_07,
            R.drawable.gallery_image_08,
            R.drawable.gallery_image_09,
            R.drawable.gallery_image_10,
            R.drawable.gallery_image_11,
            R.drawable.gallery_image_12,

    };

    private String[] mImagesTitle = new String[] {

            "gallery_image_01",
            "gallery_image_02",
            "gallery_image_03",
            "gallery_image_04",
            "gallery_image_05",
            "gallery_image_06",
            "gallery_image_07",
            "gallery_image_08",
            "gallery_image_09",
            "gallery_image_10",
            "gallery_image_11",
            "gallery_image_12",

    };


    public BlankFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment2 newInstance(String param1, String param2) {
        BlankFragment2 fragment = new BlankFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank2, null);

        //getActivity().setContentView(R.layout.fragment_blank2);

        intStorageDirectory = getContext().getFilesDir().toString();

        FileOutputStream fos = null;

        Bitmap bm;
        for(int i=0;i<12;i++) {

            bm = BitmapFactory.decodeResource(getResources(), imageIDs[i]);
            File file = new File(intStorageDirectory, mImagesTitle[i]);

            try {

                fos = new FileOutputStream(file);

                bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        GridView gridViewImages = (GridView) view.findViewById(R.id.gridViewImages);
        imageGridAdapter = new ImageGridAdapter(getActivity(), imageIDs, intStorageDirectory);
        gridViewImages.setAdapter(imageGridAdapter);

        CarouselView carouselView= view.findViewById(R.id.carousel);
        carouselView.setAlpha(0.9f);
        carouselView.setPageCount(imageIDs.length);

        carouselView.setImageListener(new ImageListener(){

            @Override
            public void setImageForPosition(int position, ImageView imageView){
                imageView.setImageResource(imageIDs[position]);

            }

        });


        carouselView.setImageClickListener(new ImageClickListener(){

            @Override
            public void onClick(int position) {

                Toast.makeText(getContext(), mImagesTitle[position],Toast.LENGTH_SHORT).show();
            }

        });


        button = (Button) view.findViewById(R.id.add_button);

        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                goToAlbum();
            }
        });

        return view;
    }

    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode != Activity.RESULT_OK){

            Toast.makeText(getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();

            if(tempFile != null){
                if(tempFile.exists()){
                    if(tempFile.delete()){
                        Log.e(TAG, tempFile.getAbsolutePath() + "삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return ;
        }

        if(requestCode == PICK_FROM_ALBUM){

            Uri photoUri = data.getData();

            Cursor cursor = null;

            try{
                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */

                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getActivity().getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));


            } finally{
                if(cursor != null){
                    cursor.close();
                }
            }

            setImage();
        }
    }

    private void setImage() {

        FileOutputStream fos = null;
        Bitmap bm;

        try {
            fos = new FileOutputStream(new File(intStorageDirectory, tempFile.getName()));
            try {
                bm = BitmapFactory.decodeStream(new FileInputStream(tempFile));
                bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //추가된 이미지는 drawable처럼 id를 찾을 수 없어 -1이라는 id를 갖게 함.
        imageGridAdapter.addItem(tempFile.getName(), -1);
        imageGridAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "추가되었습니다.", Toast.LENGTH_SHORT).show();
    }

}

