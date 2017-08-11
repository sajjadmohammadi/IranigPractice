package ir.s_mohammadi.iranigpractice.Utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ir.s_mohammadi.iranigpractice.R;

/**
 * Created by Sajjad on 8/8/2017.
 */
public class G extends Application {
    public static Typeface typeFace_Regular = null;
    public static Typeface typeFace_Bold = null;
    public static Typeface typeFace_Light = null;
    public static Typeface typeFace_Medium = null;
    public static Typeface typeFace_UltraLight = null;

    public static Context context;

    public static String FOLDER = Environment.getExternalStorageDirectory() + "/.iranig/";

    public static String SERVER = "http://198.143.181.70:8090/";

    public static Long time = System.currentTimeMillis();
    public static String device_id;
    public static String code;
    public static SharedPreferences prefs;
    public static String random_text = ")+9:n*>}sa8Q>tWLAB2*";
    public static String MY_PREFS_NAME = "pref_setting";


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        device_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        typeFace_Regular = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        typeFace_Bold = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile_Bold.ttf");
        typeFace_Light = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile_Light.ttf");
        typeFace_Medium = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile_Medium.ttf");
        typeFace_UltraLight = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile_UltraLight.ttf");

        code = md5(device_id + random_text);
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    }

    public static void Log(String msg) {
        Log.i("LOG", msg);
    }

    public static void ToastLong(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void ToastShort(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void loadImage(final String path, final String imagename, ImageView imageview, boolean extra, int radius) {

        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }

        if (imagename.length() < 5) {
            imageview.setImageResource(R.drawable.placeholder);
        } else {
            Transformation transform;
            if (extra == true) {
                transform = new RoundedTransformation(radius, 0);
            } else {
                transform = new RoundedTransformation(0, 0);
            }
            File file = new File(G.FOLDER + "/" + md5(path) + imagename);
            if (file.exists()) {
                Picasso.with(G.context).load(file).noFade().transform(transform).into(imageview);
            } else {
                Picasso.with(G.context).load(path + imagename).noFade().transform(transform).into(imageview);
                Target target = new Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom from) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                File file = new File(G.FOLDER + "/" + md5(path) + imagename);
                                try {
                                    file.createNewFile();
                                    FileOutputStream ostream = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, ostream);
                                    ostream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        if (placeHolderDrawable != null) {
                        }
                    }
                };
                Picasso.with(G.context).load(path + imagename).into(target);
            }
        }
    }

    public static boolean NetAvailable() {
        try {
            ConnectivityManager cm = (ConnectivityManager) G.context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }
}
