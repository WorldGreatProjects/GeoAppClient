package com.example.geoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geoapp.api_controller.UserActionController;
import com.example.geoapp.api_controller.UserController;
import com.example.geoapp.models.Mark;
import com.example.geoapp.models.User;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable bag = new CompositeDisposable();
    User userFromServer = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tw = findViewById(R.id.test);
        Button btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button4);
        Button btn3 = findViewById(R.id.button5);


        btn3.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, MapMainActivity.class));
        });
        btn2.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intent);
        });
        btn.setText("hello");

        btn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        User user = new User();
        user.setName("Костя");
        user.setUserName("malay777");
        user.setEmail("kostik_lap_ochka@gmail.com");
        user.setDescription("Я с Барнаула, мне поебать веришь?");
        user.setPassword("sladkiy7182");


        UserController userController = new UserController();
/*
        Disposable disposable = userController.registration( user ).subscribe(()->{
                    tw.setText("Необходимо подтвердить по почте");},
                throwable -> {
                    if ( throwable instanceof RegistrationException)
                        tw.setText("Попробуйте еще раз");
                    else
                        tw.setText(throwable.getMessage());
                });
        bag.add(disposable);





        bag.add( userController.login("malay777" , "sladkiy7182")
                .subscribe(usr->{
                    userFromServer = usr;
                    tw.setText(userFromServer.toString());
                }, throwable -> {
                    String str = "login " + throwable.getMessage();
                    tw.setText(str);
                }));

*/
        UserActionController userActionController = new UserActionController( userFromServer );
        //userFromServer.setId("469f7e06-ca5d-485d-89e7-db22ddac370b-1595684849");
        userFromServer.setId("f928c3ec-9c5f-42e2-850d-d9a684ee8337");
        //TODO user change email
        /*userFromServer.setEmail("kostik_sladkiy@gmail.com");

        bag.add(userActionController.changeUserInfo( userFromServer ).subscribe(()->{
            tw.setText("Исключения не было");
        },throwable -> tw.setText(throwable.getMessage())));*/

        Mark mark = new Mark(userFromServer.getId(), 54.776, 78.6311, "ЛЭТИ");


        //TODO mark creation
        bag.add(userActionController.createMark(userFromServer,mark)
                .subscribe(()->{
                    tw.setText("Исключения не было");
                },throwable -> {
                    tw.setText(throwable.getMessage());
                }));




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bag.clear();
    }
}
