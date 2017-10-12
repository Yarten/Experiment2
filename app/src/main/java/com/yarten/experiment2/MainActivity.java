package com.yarten.experiment2;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog();
        initSnackbar();
        initEvent();
    }

    //region 对话框初始化
    private AlertDialog.Builder dialog;

    private void initDialog()
    {
        String[] items = {this.getString(R.string.dialogItem1), this.getString(R.string.dialogItem2)};
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.dialogTitle)
                .setNegativeButton(R.string.dialogButton, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        makeToast(R.string.dialogButtonMsg);
                    }
                });

        dialog.setItems(items, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                int id = 0;
                switch (i)
                {
                    case 0: id = R.string.dialogItem1Msg; break;
                    case 1: id = R.string.dialogItem2Msg; break;
                }
                makeToast(id);
            }
        });

        ImageView imageView = findViewById(R.id.sysuLogo);
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.show();
            }
        });
    }
    //endregion

    //region Snackbar初始化
    private RadioGroup radioGroup;

    private void initSnackbar()
    {
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                int id = 0;
                switch (i)
                {
                    case R.id.radioStudent: id = R.string.optionStudent; break;
                    case R.id.radioTeacher: id = R.string.optionTeacher; break;
                }
                Log.i("RadioGroup", String.format("%d", i));

                String msg = MainActivity.this.getString(R.string.onChooseMsg) + ID2S(id);

                makeSnarckbar(msg);
            }
        });
    }
    //endregion

    //region 按钮事件初始化
    private TextInputLayout inputIDLayout, inputPasswordLayout;
    private EditText inputID, inputPassword;
    private Button login, register;


    private void initEvent()
    {
        //region 输入框设置
        inputIDLayout = findViewById(R.id.inputID);
        inputPasswordLayout = findViewById(R.id.inputPassword);
        inputID = inputIDLayout.getEditText();
        inputPassword = inputPasswordLayout.getEditText();

        inputID.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                inputIDLayout.setErrorEnabled(false);
            }
        });

        inputPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                inputPasswordLayout.setErrorEnabled(false);
            }
        });
        //endregion

        //region 登录按钮设置
        login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String id = inputID.getText().toString();
                String password = inputPassword.getText().toString();

                //region 判断学号是否为空，为空则打印信息且返回
                if(id.length() == 0)
                {
                    inputIDLayout.setErrorEnabled(true);
                    inputIDLayout.setError(ID2CS(R.string.errormsgEmptyID));
                    return;
                } //endregion

                //region 判断密码是否为空，为空则打印信息且返回
                if(password.length() == 0)
                {
                    inputPasswordLayout.setErrorEnabled(true);
                    inputPasswordLayout.setError(ID2CS(R.string.errormsgEmptyPassword));
                    return;
                } //endregion

                //region 判断学号和密码是否正确，并作相关处理
                if(UserInfoManager.checkLogin(id, password))
                {
                    makeSnarckbar(R.string.msgRightInfo);
                }
                else
                {
                    makeSnarckbar(R.string.msgWrongInfo);
                } //endregion
            }
        });
        //endregion

        //region 注册按钮设置
        register = findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String msg = "";
                switch (radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.radioStudent:
                        msg = ID2S(R.string.optionStudent) + ID2S(R.string.msgRegisterNone);
                        break;
                    case R.id.radioTeacher:
                        msg = ID2S(R.string.optionTeacher) + ID2S(R.string.msgRegisterNone);
                        break;
                }
                makeToast(msg);
            }
        });
        //endregion
    }
    //endregion

    //region 工具函数
    private CharSequence ID2CS(int id)
    {
        return ID2S(id);
    }

    private String ID2S(int id)
    {
        return MainActivity.this.getString(id);
    }

    private void makeToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void makeToast(int id)
    {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

    private void makeSnarckbar(String msg)
    {
        Snackbar.make(findViewById(R.id.radioGroup), msg, Snackbar.LENGTH_SHORT)
                .setAction(R.string.snackbarButton, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        makeToast(R.string.onSnackbarClickMsg);
                    }
                })
                .show();
    }

    private void makeSnarckbar(int id)
    {
        Snackbar.make(findViewById(R.id.radioGroup), id, Snackbar.LENGTH_SHORT)
                .setAction(R.string.snackbarButton, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        makeToast(R.string.onSnackbarClickMsg);
                    }
                })
                .show();
    }
    //endregion
}
