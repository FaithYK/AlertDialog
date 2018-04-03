package com.yk.alertdialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * @author YK
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Calendar calender = null;
    final int DATE_DIALOG = 0;
    final int TIME_DIALOG = 1;
    /**
     * 声明进度条对话框ID
     */
    final int PROGRESS_DIALOG = 3;
    /**
     * Handler消息类型
     */
    final int INCREASE = 0;
    /**
     * 进度对话框对象引用
     */
    ProgressDialog progressDialog;
    /**
     * Handler对象引用
     */
    Handler handler;
    /**
     * 进度计数
     */
    public int intCounter = 0;
    /**
     * 同步消息
     */
    protected static final int GUI_STOP_NOTIFIER = 0x0;
    protected static final int GUI_THREADING_NOTIFIER = 0x1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_normal_dialog:
                //显示普通对话框
                showNormalDialog();
                break;
            case R.id.btn_list_dialog:
                //显示列表对话框
                showListDialog();
                break;
            case R.id.btn_radio_dialog:
                //显示单选对话框
                showRadioDialog();
                break;
            case R.id.btn_date_dialog:
                //显示日期对话框
                showDateOrTimeDialog(DATE_DIALOG);
                break;
            case R.id.btn_time_dialog:
                //显示时间对话框
                showDateOrTimeDialog(TIME_DIALOG);
                break;
            case R.id.btn_progress_dialog:
                //显示进度对话框
                showProgressDialog();
                break;
            default:
        }
    }

    /**
     * showNormalDialog显示普通对话框
     * NormalDialogFragment创建普通对话框
     * */
    private void showNormalDialog() {

        NormalDialogFragment normalDialogFragment = new NormalDialogFragment();
        normalDialogFragment.show(getFragmentManager(),"普通对话框");
    }

    class NormalDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Dialog mDialog = null;

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            //设置对话框图标
            builder.setIcon(R.drawable.normal_dialog_icon);
            //设置对话框标题
            builder.setTitle("我是一个普通的对话框");
            //设置对话框显示内容
            builder.setMessage("我是对话框显示的内容");

            builder.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //To-Do...............
                        }
                    });

            builder.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //To-Do...............
                        }
                    });
            mDialog = builder.create();
            return mDialog;
        }
    }

    /**
     * showListDialog显示普通对话框
     * ListDialog创建列表对话框
     * */
    private void showListDialog() {

        ListDialog listDialog = new ListDialog();
        listDialog.show(getFragmentManager(),"列表对话框");

    }

    class ListDialog extends DialogFragment{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Dialog dialog = null;
            AlertDialog.Builder listDialog = new AlertDialog.Builder(getActivity());
            //设置对话框图标
            listDialog.setIcon(R.drawable.normal_dialog_icon);
            //设置对话框标题
            listDialog.setTitle("我是一个列表对话框");
            //设置列表中的各个属性
            listDialog.setItems(R.array.msa,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            dialog = listDialog.create();
            return dialog;
        }
    }

    /**
     * showRadioDialog显示单选对话框
     * RadioDialog创建单选对话框
     * */
    private void showRadioDialog() {

        RadioDialog radioDialog = new RadioDialog();
        radioDialog.show(getFragmentManager(),"单选对话框");
    }

    class RadioDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Dialog radio_dialog = null;
            AlertDialog.Builder radioDialog = new AlertDialog.Builder(getActivity());
            //设置对话框图标
            radioDialog.setIcon(R.drawable.normal_dialog_icon);
            //设置对话框标题
            radioDialog.setTitle("我是一个单选对话框");
            //设置单选列表选项
            radioDialog.setSingleChoiceItems(R.array.msa,
                    0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //To-do..........
                            Toast.makeText(MainActivity.this,
                                    "您选择了："+getResources().getStringArray(R.array.msa)[which],
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            radioDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //To-do..........
                }
            });
            radioDialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //To-do..........
                }
            });
            //生成Dialog对象
            radio_dialog = radioDialog.create();
            //返回生成的Dialog对象
            return radio_dialog;
        }
    }

    /**
     * showDateDialog显示日期时间对话框
     * DateDialog创建日期时间对话框
     * */

    private void showDateOrTimeDialog(int id) {

        DateDialog dateDialog = new DateDialog().newInstance(id);
        dateDialog.show(getFragmentManager(),"日期时间对话框");
    }

    class  DateDialog extends DialogFragment{

        public DateDialog newInstance(int tittle){

            DateDialog dateDialog = new DateDialog();
            Bundle bundle = new Bundle();
            bundle.putInt("cmd",tittle);
            dateDialog.setArguments(bundle);
            return dateDialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            int id = getArguments().getInt("cmd");
            Dialog dialog = null;
            //对id进行判断
            switch (id){
                case DATE_DIALOG:
                    //获取日期对象
                    calender = Calendar.getInstance();
                    dialog = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    EditText et_date = (EditText) findViewById(R.id.et_date);
                                    et_date.setText("您选择了;"+ year + "年" + month + "月" + dayOfMonth + "日" );
                                }
                            },
                            //传入年份
                            calender.get(Calendar.YEAR),
                            //传入月份
                            calender.get(Calendar.MONTH),
                            //传入天数
                            calender.get(Calendar.DAY_OF_MONTH)
                    );
                    break;
                case TIME_DIALOG:
                    //获取日期对象
                    calender = Calendar.getInstance();
                    dialog = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    EditText et_date = (EditText) findViewById(R.id.et_date);
                                    et_date.setText("您选择了;"+ hourOfDay + "时" + minute + "分");
                                }
                            },
                            calender.get(Calendar.HOUR_OF_DAY),
                            calender.get(Calendar.MINUTE),false
                    );
                    break;
                default:
            }
            return dialog;
        }
    }

    /**
     * showProgressDialog显示进度条对话框
     * DateDialog创建进度条对话框
     * */
    private void showProgressDialog() {

        MyProgressDialog myProgressDialog = new MyProgressDialog();
        myProgressDialog.show(getFragmentManager(),"进度对话框");
    }

    class MyProgressDialog extends DialogFragment{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //创建进度对话框
            progressDialog = new ProgressDialog(getActivity());
            //设置最大值
            progressDialog.setMax(100);
            //设置样式
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //设置标题
            progressDialog.setTitle(R.string.title);
            //设置进度对话框不能用回退按钮关闭
            progressDialog.setCancelable(false);

            //通过线程来改变ProgressBar的值
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0;i < 10;i++){
                        try {
                            //设置进度值
                            intCounter = (i + 1) * 10;
                            //睡眠1000毫秒
                            Thread.sleep(1000);
                            if (i == 9){
                                Message message = new Message();
                                //终止
                                message.what = GUI_STOP_NOTIFIER;
                                //发送消息
                                myMessageHandler.sendMessage(message);
                                break;
                            }else {
                                Message msg = new Message();
                                //进入添加进度
                                msg.what = GUI_THREADING_NOTIFIER;
                                //发送消息
                                myMessageHandler.sendMessage(msg);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //线程运行
            }).start();
            return progressDialog;
        }

        Handler myMessageHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    //ProgressBar已经是最大值
                    case GUI_STOP_NOTIFIER:
                        progressDialog.cancel();
                        Thread.currentThread().interrupt();
                    break;
                    case GUI_THREADING_NOTIFIER:
                        if (!Thread.currentThread().isInterrupted()){
                            //改变ProgressBar的值
                            progressDialog.setProgress(intCounter);
                            //设置标题栏中前景的一个进度条进度值
                            setProgress(intCounter * 100);
                        }
                    break;
                    default:
                }
                super.handleMessage(msg);
            }
        };
    }
}
