package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseListAdapterFragment;


/**
 * A list fragment representing a list of EbsalmodiaPrays. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link EbsalmodiaPrayDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class EbsalmodiaPrayListFragment  extends TaskBaseListAdapterFragment{
 @Override
    protected String getTaskName() {
        return getResources().getString(R.string.ebsalmodia).toLowerCase();
    }


    @Override
    public List<Integer> getIconList() {
        List<Integer> iconList = new ArrayList<>();
        iconList.add(R.drawable.kiama);
        iconList.add(R.drawable.heilige_geist);
        iconList.add(R.drawable.cross_way);
        iconList.add(R.drawable.crossing);
        iconList.add(R.drawable.jesus_down_of_the_cross_3);
        iconList.add(R.drawable.jesus_in_the_grave);
        iconList.add(R.drawable.jesus_walking_on_the_water);
        iconList.add(R.drawable.virgin);
        iconList.add(R.drawable.gulti_woman);
        iconList.add(R.drawable.jesus_coming_);
        return iconList;
    }
    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     *//*
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    *//**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     *//*
    private Callbacks mCallbacks = sDummyCallbacks;

    *//**
     * The current activated item position. Only used on tablets.
     *//*
    private int mActivatedPosition = ListView.INVALID_POSITION;

    *//**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     *//*
    public interface Callbacks {
        *//**
         * Callback for when an item has been selected.
         * @param id
         *//*
        public void onItemSelected(int id);
    }

    *//**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     *//*
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(int id) {
        }
    };

    *//**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     *//*
    public EbsalmodiaPrayListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: replace with a real list adapter.
        *//*setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.ITEMS));*//*
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    *//**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     *//*
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }*/
}
