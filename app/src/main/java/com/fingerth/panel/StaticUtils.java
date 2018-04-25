package com.fingerth.panel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StaticUtils {


    public static final String TAG = "StaticUtils";

    public static final int SUCCESS_CODE = 0;
    public static final String THEME_COLOR = "#FF6262";


    public final static int BIG_SIZE = 18;
    public final static int NORMAL_SIZE = 16;
    public final static int SMALL_SIZE = 14;
    public final static int MIN_SIZE = 12;

    private static int sysWidth = 0;
    private static int sysHeight = 0;

    public static double NO_CONNECTED_PRODUCT_TOTAL_MONEY = -888d;//沒網時，本地校驗標誌


    /**
     * 获取手机的分比率，高和宽
     */
    public static void getScreen(Activity activity) {
        if (StaticUtils.sysWidth <= 0 || StaticUtils.sysHeight <= 0) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            StaticUtils.sysWidth = dm.widthPixels;
            StaticUtils.sysHeight = dm.heightPixels;
        }
    }

    public static int getSysWidth(Activity activity) {
        if (StaticUtils.sysWidth <= 0) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            StaticUtils.sysWidth = dm.widthPixels;
        }
        return StaticUtils.sysWidth;
    }

    public static int getSysHeight(Activity activity) {
        if (StaticUtils.sysHeight <= 0) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            StaticUtils.sysHeight = dm.heightPixels;
        }
        return StaticUtils.sysHeight;
    }

    public static int getStatusBarHeight(Context c) {
        int result = 0;
        int resourceId = c.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = c.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());

    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context) {
        String imeistring = "";
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                assert telephonyManager != null;
                imeistring = telephonyManager.getDeviceId();
            }
            if (TextUtils.isEmpty(imeistring)) {
                imeistring = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imeistring;
    }


    public static Date formatData(String dataStr) {//2017-09-05
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String formatData(Date date) {//2017-09-05
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }

    public static String formatData2(Date date) {//2017-09-05
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }


    /**
     * 获取系统时间
     */
    public static String getSystemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    public static String getSystemDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }


    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }


    /**
     * 关闭输入软键盘  然而没有什么卵用  TODO
     */
    public static void closeSoftInput(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (editText != null) {
            if (imm != null) {
                try {
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            View view = activity.getWindow().peekDecorView();
            if (view != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * 點擊某個EditText，不用彈軟鍵盤
     */
    public static void softKeyHide(Activity activity, Object et) {
        try {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            Class<EditText> cls = EditText.class;
            Method setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setSoftInputShownOnFocus.setAccessible(true);
            setSoftInputShownOnFocus.invoke(et, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static final String REBOOT_APP = "reboot_app";


    public static void copyText(Context context, String str) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(str);
        //ToastUtils.showToast(context, LanguageDaoUtils.getStrByFlag(context, AppConstants.CopySuccessfully));
    }

    /**
     * @param isZreo true 如果為數字 0 也是無效的
     * @param str
     * @return
     */
    public static boolean valueIsEmpty(boolean isZreo, String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals("null", str)) {
            return true;
        }
        if (isZreo) {
            try {
                double v = Double.parseDouble(str);
                if (v == 0)
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean listStrIsEmpty(String str) {
        return TextUtils.isEmpty(str) || TextUtils.equals("null", str) || str.length() <= 2;
    }

}
