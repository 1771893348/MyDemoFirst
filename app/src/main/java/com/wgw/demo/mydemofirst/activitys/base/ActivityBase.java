package com.wgw.demo.mydemofirst.activitys.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wgw.demo.mydemofirst.interfaces.Annotations.InjectView;

import java.lang.reflect.Field;

public class ActivityBase extends Activity {
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public View getViewById(int viewId){

        return  findViewById(viewId);
    }
    public void autoInjectAllField(Activity activity) throws IllegalAccessException ,IllegalArgumentException{
        //�õ�Activity��Ӧ��class
        Class clazz = this.getClass();
        //�õ���Activity �����ֶ�
        Field []fields = clazz.getDeclaredFields();
        for (Field field : fields){
            //�ж��ֶ��Ƿ��עInjectView
            if (field.isAnnotationPresent(InjectView.class)){
                Log.d("wgw_"+field.getName(),"is injectView");
                //�����ע�ˣ��ͻ������id
                InjectView injectView = field.getAnnotation(InjectView.class);
                int id = injectView.id();
                if (id>0){
                    //�������˽�г�Ա������������
                    field.setAccessible(true);
                    //
                    field.set(activity,activity.findViewById(id));
                }
            }
        }
    }
}
