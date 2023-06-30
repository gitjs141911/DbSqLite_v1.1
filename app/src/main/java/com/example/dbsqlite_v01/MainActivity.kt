package com.example.dbsqlite_v01

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.dbsqlite_v01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var context= this
        binding.btnCadastrar.setOnClickListener {
            val nome = binding.eNome.text.toString().trim()
            val fone = binding.eFone.text.toString().trim()
            if (nome.isEmpty() || fone.isEmpty()){
                Toast.makeText(applicationContext,"Favor digitar o Nome e Fone", Toast.LENGTH_SHORT).show()
            }else{
                // below we have created
                // a new DBHelper class,
                // and passed context to it
                val db = DBHelper(this, null)
                // calling method to add
                // name to our database
                db.addName(nome, fone)

                // Toast to message on the screen
                Toast.makeText(this, nome + " added to database", Toast.LENGTH_LONG).show()

                // at last, clearing edit texts
                binding.eNome.text.clear()
                binding.eFone.text.clear()
            }
         }

        binding.btnListar.setOnClickListener {
            // creating a DBHelper class
            // and passing context to it
            binding.txtNome.setText("")
            binding.txtFone.setText("")

            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            binding.txtNome.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            binding.txtFone.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            /*
            Attributes.Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

             */

            // moving our cursor to next
            // position and appending values
            while (cursor.moveToNext()) {
                binding.txtNome.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                binding.txtFone.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
        binding.btnLimpar.setOnClickListener {
            binding.txtNome.setText("")
            binding.txtFone.setText("")
        }

        //Instaciando DB

        val db = DBHelper(this, null)

        val listaUtilizadores =db.utilizadorListSelectAll()

        binding.listUser.adapter= ArrayAdapter(this, R.layout.simple_list_item_1,listaUtilizadores)
        //-------- FIM ------
      }



    }


