package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import android.os.Bundle;
import android.util.DisplayMetrics;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.ControllerFragmentBase;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask.util.ImageCache;
import baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask.util.ImageFetcher;

/**
 * An activity representing a single EbsalmodiaPray detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link EbsalmodiaPrayListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link EbsalmodiaPrayDetailFragment}.
 */
public class EbsalmodiaPrayDetailActivity extends TaskBaseDetailActivity {
    private static final String IMAGE_CACHE_DIR = "images";
    public static final String EXTRA_IMAGE = "extra_image";

    private ImageFetcher mImageFetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        final int longest = (height > width ? height : width) / 2;

        ImageCache.ImageCacheParams cacheParams =
                new ImageCache.ImageCacheParams(this, IMAGE_CACHE_DIR);
        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory

        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        mImageFetcher = new ImageFetcher(this, longest);
        mImageFetcher.addImageCache(getSupportFragmentManager(), cacheParams);
        mImageFetcher.setImageFadeIn(false);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_agpeyapray_detail;
    }

    @Override
    protected void updateFragmentController(TaskBaseDetailFragment fragment, ControllerFragmentBase controller) {
        ((EbsalmodiaPrayDetailFragment) fragment).setController((EbsalmodiaController) controller);
        controller.setControllerInterface((EbsalmodiaPrayDetailFragment) fragment);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.agpeyapray_detail_container;
    }

    @Override
    protected ControllerFragmentBase getController() {
        return new EbsalmodiaController();
    }

    @Override
    protected TaskBaseDetailFragment getFragment() {
        return new EbsalmodiaPrayDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mImageFetcher.setExitTasksEarly(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mImageFetcher.setExitTasksEarly(true);
        mImageFetcher.flushCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImageFetcher.closeCache();
    }

    public ImageFetcher getImageFetcher() {
        return mImageFetcher;
    }

}
