package mx.cicese.wearphonecommunication;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.internal.zzcj;


public class MainActivity extends WearableActivity  {

    private TextView mTextView;
    private Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        boton = (Button) findViewById(R.id.button2);

        mDataClient = Wearable.getDataClient(this);
        // Enables Always-on
        setAmbientEnabled();
    }

    private static final String COUNT_KEY = "com.example.key.count";
    private DataClient mDataClient;
    private int count = 0;

    public void increaseCounter(View v) {
        count++;
        Log.d("counter", count +"");
        boton.setText("count = " + count);
        PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/count");
        putDataMapReq.getDataMap().putInt(COUNT_KEY, count);
        putDataMapReq.setUrgent();
        PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
        Task<DataItem> putDataTask = mDataClient.putDataItem(putDataReq);
        boolean respuesta = putDataTask.isSuccessful();
        Log.d("respueta", respuesta + " ");
    }


}
