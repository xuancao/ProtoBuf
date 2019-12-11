package com.xuancao.protobufdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xuancao.pro.ResponesePB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_set_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testGetBytes(MainActivity.this);
                testDeBytes(MainActivity.this);
            }
        });
    }

    public byte[] testGetBytes(Context context){
        ResponesePB.Tab.Builder tabBuilder = ResponesePB.Tab.newBuilder().setF("sss").setType(2);
        ResponesePB.ItemData.Builder itemData = ResponesePB.ItemData.newBuilder();
        itemData.setPackageid("22222");
        itemData.setSname("eiiii");
        itemData.addTabs(tabBuilder);

        ResponesePB.Response.Builder responseBuilder = ResponesePB.Response.newBuilder();
        responseBuilder.setHasNextPage(true);
        responseBuilder.setDirtag("soft");
        ResponesePB.DataItem.Builder dataItem = ResponesePB.DataItem.newBuilder().setDatatype(1).setItemdata(itemData);

        ResponesePB.Response response = responseBuilder.addData(dataItem).build();

        Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show();

        byte[] out = response.toByteArray();
        return out;
    }

    public void testDeBytes(Context context){
        byte[] out = testGetBytes(context);
        try {
            ResponesePB.Response test = ResponesePB.Response.parseFrom(out);
            Toast.makeText(context,test.getData(0).toString(),Toast.LENGTH_LONG).show();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
