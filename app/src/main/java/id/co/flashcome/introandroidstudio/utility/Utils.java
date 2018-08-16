package id.co.flashcome.introandroidstudio.utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.widget.Toast;

import java.io.File;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Utils {
	private Context c;

	public Utils(Context c){
		this.c = c;
	}

	public String getVersionName(){
		try {
			return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	public int getVersionCode(){
		int version;
		try {
			version = c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionCode;
		}
		catch (NameNotFoundException e) {
			e.printStackTrace();
			version = 0;
		}
		return version;
	}

    public String getKeyHash(){
        String keyHash = "";
        try {
            PackageInfo info = c.getPackageManager().getPackageInfo(
                    c.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                L.x("KeyHash: "+keyHash);
            }
        } catch (NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyHash;
    }

	public boolean isUnderApi18(){
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2;
	}

	public boolean isUnderApi23(){
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
	}

	public boolean isUnderApi24(){
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.N;
	}

    public String getAndroidVersion(){
        return Build.VERSION.RELEASE;
    }
	public String getFormattedPrice(String price){
		try{
            DecimalFormat myFormatter = new DecimalFormat("###,###.###", new DecimalFormatSymbols(Locale.US));
			String awal = myFormatter.format(new BigDecimal(price));
			String bag[] = awal.split("\\.");
			bag[0] = bag[0].replaceAll(",", ".");
			String new_price = bag[0];
			if(bag.length >= 2) new_price = new_price+","+bag[1];
			return new_price;
		}
		catch(Exception e){
			e.printStackTrace();
			return price;
		}
	}

    public String getRandomString(int len){
        String ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for( int i = 0; i < len; i++ )
            sb.append( ab.charAt( rnd.nextInt(ab.length()) ) );

        return sb.toString();
    }

    public void openUrl(String url){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        //ini untuk mengecek apakah ada app yg mau menerima data dari kita
        List<ResolveInfo> activities = c.getPackageManager().queryIntentActivities(i, 0);

        if(activities.size() > 0)
            c.startActivity(i);
        else
			Toast.makeText(c, "No application can open this url", Toast.LENGTH_LONG).show();
    }

	private boolean isSdMounted(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public boolean is_email_valid(String email){
		boolean status = true;
		
		int atpos=email.indexOf("@");
		int dotpos=email.lastIndexOf(".");
		if (atpos<1 || dotpos<atpos+2 || dotpos+2 >= email.length()){
		  status = false;
		}
		return status;
	}

	public String getAppVersion(){
		try {
			return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public int getPixelByDP(float dp){
		float scale = c.getResources().getDisplayMetrics().density;
        return (int) (dp * scale);
	}

	public int getPixelByDP(double dp){
		float scale = c.getResources().getDisplayMetrics().density;
		return (int) (dp * scale);
	}

	public void hapusFile(String path){
		File f = new File(path);
		if(f.isFile() && f.exists()) f.delete();
	}

	public boolean isUnderAndroidM(){
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
	}

	public boolean permission_granted(Context c, String permission){
		return isUnderAndroidM() || ActivityCompat.checkSelfPermission(c, permission) == PackageManager.PERMISSION_GRANTED;
	}

}
