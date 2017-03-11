package me.toptas.fancyshowcase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.view.View;

/**
 * Created by faruktoptas on 09/03/17.
 * Util class for {@link FancyShowCaseView}
 */

public class FancyShowCaseUtils {

    /**
     * Circular reveal animation condition
     * @return true if enabled
     */
    public static boolean shouldShowCircularAnimation() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Calculates focus point values
     * @param view view to focus
     * @param circleRadiusFactor radius factor of circle
     * @return x, y, radius values for the circle
     */
    public static int[] calculateFocusPointValues(View view, double circleRadiusFactor) {
        int[] point = new int[3];
        if (view != null) {
            int[] viewPoint = new int[2];
            view.getLocationInWindow(viewPoint);

            point[0] = viewPoint[0] + view.getWidth() / 2;
            point[1] = viewPoint[1] + view.getHeight() / 2 - getStatusBarHeight(view.getContext());
            int radius = (int) ((int) (Math.hypot(view.getWidth(), view.getHeight()) / 2) * circleRadiusFactor);
            point[2] = radius;
            return point;
        }
        return null;
    }

    /**
     * Draws focus circle
     * @param bitmap bitmap to draw
     * @param point circle point
     * @param radius circle radius
     */
    public static void drawFocusCircle(Bitmap bitmap, int[] point, int radius){
        Paint p = new Paint();
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Canvas c = new Canvas(bitmap);
        c.drawCircle(point[0], point[1], radius, p);
    }

    /**
     * Returns statusBar height
     * @param context context to access resources
     * @return statusBar height
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
