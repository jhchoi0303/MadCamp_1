package com.example.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ImageGridAdapter extends BaseAdapter {

    Context context = null;
    //-----------------------------------------------------------
    // imageIDs는 이미지 파일들의 리소스 ID들을 담는 배열입니다.
    // 이 배열의 원소들은 자식 뷰들인 ImageView 뷰들이 무엇을 보여주는지를
    // 결정하는데 활용될 것입니다.

    private String intStorageDirectory;
    //int[] imageIDs = null;
    private ArrayList<Integer> imageIDs = new ArrayList<>();
    private ArrayList<String> image_titles = new ArrayList<>();
    private ArrayList<Bitmap> bitmap_images = new ArrayList<>();

    public ImageGridAdapter(Context context, int[] imageIds, String iNtStorageDirectory){
        this.context = context;
        String title;
        for(int i=1;i<=imageIds.length;i++) {
            this.imageIDs.add(imageIds[i-1]);
            title = "gallery_image_";
            if(i>=10) title = title + i;
            else title = title + "0" + i;

            this.image_titles.add(title);

        }
        this.intStorageDirectory = iNtStorageDirectory;
    }

    public int getCount(){
        return (null != imageIDs) ? imageIDs.size() : 0;
    }

    public Object getItem(int position){
        return (null != imageIDs) ? imageIDs.get(position) : 0;
    }

    public long getItemId(int position){
        return position;
    }

    public void addItem(String name, int imageId) {
        image_titles.add(name);
        imageIDs.add(imageId);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView = null;


        if(convertView != null) {
            imageView = (ImageView)convertView;

            try{
                //Toast.makeText(context, image_titles.get(position),Toast.LENGTH_SHORT).show();

                Bitmap b = null;
                if(position < bitmap_images.size()) b = bitmap_images.get(position);
                else {
                    File f = new File(intStorageDirectory, image_titles.get(position));
                    //System.out.println(image_titles.get(position) + " " + position);

                    b = BitmapFactory.decodeStream(new FileInputStream(f));
                    b = Bitmap.createScaledBitmap(b, 320, 240, false);
                    bitmap_images.add(b);
                }

                imageView.setAdjustViewBounds(true);
                imageView.setImageBitmap(b);

                ImageClickListener imageViewClickListener = new ImageClickListener(context, imageIDs.get(position), b);
                imageView.setOnClickListener(imageViewClickListener);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        else{
            //---------------------------------------------------------------
            // GridView 뷰를 구성할 ImageView 뷰의 비트맵을 정의합니다.
            // 그리고 그것의 크기를 320*240으로 줄입니다.
            // 크기를 줄이는 이유는 메모리 부족 문제를 막을 수 있기 때문입니다.
            try{
                //Toast.makeText(context, image_titles.get(position),Toast.LENGTH_SHORT).show();

                Bitmap b = null;
                if(position < bitmap_images.size()) b = bitmap_images.get(position);
                else {
                    File f = new File(intStorageDirectory, image_titles.get(position));
                    //System.out.println(image_titles.get(position) + " " + position);

                    b = BitmapFactory.decodeStream(new FileInputStream(f));
                    b = Bitmap.createScaledBitmap(b, 320, 240, false);
                    bitmap_images.add(b);
                }

                imageView = new ImageView(context);
                imageView.setAdjustViewBounds(true);
                imageView.setImageBitmap(b);

                ImageClickListener imageViewClickListener = new ImageClickListener(context, imageIDs.get(position), b);
                imageView.setOnClickListener(imageViewClickListener);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

/*            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs.get(position));
            bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);

            //---------------------------------------------------------------
            // GridView 뷰를 구성할 ImageView 뷰들을 정의합니다.
            // 뷰에 지정할 이미지는 앞에서 정의한 비트맵 객체입니다.
            imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bmp);

            //---------------------------------------------------------------
            // 사진 항목들의 클릭을 처리하는 ImageClickListener 객체를 정의합니다.
            // 그리고 그것을 ImageView의 클릭 리스너로 설정합니다.

            ImageClickListener imageViewClickListener = new ImageClickListener(context, imageIDs.get(position));
            imageView.setOnClickListener(imageViewClickListener);
*/

        }

        return imageView;

    }
}