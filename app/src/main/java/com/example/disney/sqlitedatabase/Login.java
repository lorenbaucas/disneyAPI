package com.example.disney.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.disney.activity.MainActivity;
import com.example.disney.R;

public class Login extends AppCompatActivity {

    //Declaro las imágenes
    private ImageView imgDisney, imgCastle;

    //Declaro los EditText y los botones
    private EditText etUsername, etPassword;
    private Button bSignin, bSignup;

    //Declaro la base de datos
    private DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Dejo esto por si quiero que aparezca el action bar y le cambio el título y el color
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80DEEA")));
        //getSupportActionBar().setTitle("Personajes Disney");

        //Oculto el action bar porque no me gusta
        getSupportActionBar().hide();

        //Imagen de Disney de la portada
        imgDisney = (ImageView) findViewById(R.id.introDisney);
        Glide.with(Login.this)
                .load("https://www.freepnglogos.com/uploads/disney-channel-png-logo/golden-disney-logos-clipart-png-19.png")
                .error(R.mipmap.ic_launcher)
                .into(imgDisney);

        //Imagen del castillo de la portada
        imgCastle = (ImageView) findViewById(R.id.introCastle);
        Glide.with(Login.this)
                .load("https://imgfz.com/i/FR3BfTa.png")
                .error(R.mipmap.ic_launcher)
                .into(imgCastle);

        //Imagen de Mickey de la portada
        imgDisney = (ImageView) findViewById(R.id.introMickey);
        Glide.with(Login.this)
                .load("https://assets.stickpng.com/images/580b57fbd9996e24bc43bd2f.png")
                .error(R.mipmap.ic_launcher)
                .into(imgDisney);

        //Imagen de Minnie de la portada
        imgDisney = (ImageView) findViewById(R.id.introMinnie);
        Glide.with(Login.this)
                .load("https://www.freeiconspng.com/uploads/minnie-mouse-png-transparent-7.png")
                .error(R.mipmap.ic_launcher)
                .into(imgDisney);


        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bSignin = (Button) findViewById(R.id.bSignin);
        bSignup = (Button) findViewById(R.id.bSignup);

        //Inicializo el acceso a la base de datos
        db=new DBAccess(this);

        //Método para cuando pulse en el botón de registrarse
        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                //Si el campo del usuario o de la contraseña están vacíos le dirá que los rellene mediante un Toast
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Rellena ambos campos", Toast.LENGTH_SHORT).show();
                }else{
                    //Comprueba si ya existe el usuario
                    Boolean checkUsername = db.checkUsername(username);
                    if(checkUsername==false){
                        //Si no existe lo añade
                        Boolean insert = db.insertData(username, password);
                        if(insert==true){
                            //Si se añade con éxito se le comunica al usuario con el Toast
                            Toast.makeText(Login.this, "Registrado con éxito", Toast.LENGTH_SHORT).show();
                            //Salto a la otra actividad
                            Intent intent=new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this, "El registro ha fallado", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Login.this, "El usuario ya existe, inicia sesión o crea otra cuenta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Método para cuando pulse en el botón de iniciar sesión
        bSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                //Si el campo del usuario o de la contraseña están vacíos le dirá que los rellene mediante un Toast
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Rellena ambos campos", Toast.LENGTH_SHORT).show();
                }else{
                    //Comprueba si la contraseña es correcta o si el usuario ya existe
                    Boolean checkPassword= db.checkPassword(username, password);
                    if(checkPassword==true){
                        //Si se inicia sesión con éxito se le comunica al usuario con el Toast
                        Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Login.this, MainActivity.class);
                        intent.putExtra("user",username);

                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Error, comprueba los datos introducidos o cree una nueva cuenta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}