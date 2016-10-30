
        package info.sekut.db;

        import android.app.Activity;
        import android.content.Context;
        import android.database.Cursor;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.CheckBox;
        import android.widget.CursorAdapter;
        import android.widget.Spinner;
        import android.widget.SpinnerAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

        import info.sekut.CustomAdapterSpinner;
        import info.sekut.ItemData;
        import info.sekut.R;

/**
 * Created by Paweł on 2016-10-22.
 */
public class CursorDb extends CursorAdapter {

    private LayoutInflater layoutInflater;

    public CursorDb(Context context, Cursor c, int flags) {
        super(context, c, flags);

        layoutInflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return layoutInflater.inflate(R.layout.item_todo, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView txtTitle = (TextView) view.findViewById(R.id.task_title);
        String title = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COL_TASK_TITLE));
        txtTitle.setText(title);


        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox1);
        int isDone = cursor.getInt(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COL_TASK_STAR));

        switch (isDone) {
            case 0:
                checkBox.setChecked(false);
                break;

            case 1:
                checkBox.setChecked(true);
                break;
        }

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);
        ArrayList<ItemData> list = new ArrayList<>();
        list.add(new ItemData("normalny", R.drawable.lol));
        list.add(new ItemData("ważny", R.drawable.lol));
        list.add(new ItemData("pilny", R.drawable.lol));
        SpinnerAdapter adapter = new CustomAdapterSpinner((Activity) context, R.layout.spinner_layout, R.id.txt, list);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        Toast.makeText(parent.getContext(), "Spinner item 1!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(parent.getContext(), "Spinner item 2!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Spinner item 3!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}


