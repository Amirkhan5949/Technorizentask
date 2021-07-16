package com.codeinger.technorizentask.data.room.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class ImageConverter {

//    @TypeConverter
//    public byte[] frombitmap(Bitmap  bitmap){
//        ByteArrayOutputStream  stream;
//        stream=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);
//        return stream.toByteArray();
//    }

    @TypeConverter
    public byte[] frombitmap(Bitmap  bitmapImage){
        int nh = (int) ( bitmapImage.getHeight() * (512.0 / bitmapImage.getWidth()) );
        Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true);
        ByteArrayOutputStream  stream;
        stream=new ByteArrayOutputStream();
        scaled.compress(Bitmap.CompressFormat.PNG,50,stream);
        return stream.toByteArray();
    }

    @TypeConverter
    public Bitmap tobitmap(byte byteArray[]){
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
    }

//    private byte[] imagemTratada(byte[] imagem_img){
//
//        while (imagem_img.length > 500000){
//            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem_img, 0, imagem_img.length);
//            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            resized.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            imagem_img = stream.toByteArray();
//        }
//        return imagem_img;
//
//    }
}
