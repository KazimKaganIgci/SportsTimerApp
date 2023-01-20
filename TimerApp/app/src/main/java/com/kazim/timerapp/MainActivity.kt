package com.kazim.timerapp
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazim.timerapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userList: List<User>
    private var userAdapter:UserAdapter ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



            binding.hareketEklebtn.setOnClickListener {
                addUser()
            }


        binding.baslabtn.setOnClickListener {
            val intent=Intent(this@MainActivity,DetailActivity::class.java)
            startActivity(intent)

        }
        binding.veritemizle.setOnClickListener {
            lifecycleScope.launch{
                AppDatabase(this@MainActivity).userDao().allDelete()

            }
            userAdapter?.veriTemizle()
        }

    }



    private fun setAdapter(list:List<User>){
        userAdapter?.setData(list)


    }

    private fun addUser() {
        val hareketadi =binding.bilgitext.text.toString()
        val hareketsaniye=binding.timetext.text.toString().toInt()
        val harekettekrar=binding.tekrarsayisiText.text.toString().toInt()
        val hareketmola=binding.molaText.text.toString().toInt()


        lifecycleScope.launch {
            val user=User(hareketadi,hareketsaniye,harekettekrar,hareketmola)
            AppDatabase(this@MainActivity).userDao().addUser(user)
            val list =AppDatabase(this@MainActivity).userDao().getAllUser()
            setAdapter(list)
        }
        binding.bilgitext.text=null
        binding.timetext.text = null
        binding.tekrarsayisiText.text = null
        binding.molaText.text = null


    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
           userList= AppDatabase(this@MainActivity).userDao().getAllUser()
            userAdapter = UserAdapter()
            binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
            binding.recyclerView.adapter =userAdapter
            setAdapter(userList)



            userAdapter?.setOnActionEditListener {

                val builder =AlertDialog.Builder(this@MainActivity,R.style.AlertDialogTheme)
                builder.setTitle("Android Alert")
                builder.setMessage("Emin misin?")
                builder.setPositiveButton("Evet") { dialog, _ ->
                    lifecycleScope.launch{
                        AppDatabase(this@MainActivity).userDao().deleteUser(it)
                        val list =AppDatabase(this@MainActivity).userDao().getAllUser()
                        setAdapter(list)
                    }
                    dialog.dismiss()
                }
                builder.setNegativeButton("Hayır") { _, _ ->
                    Toast.makeText(this@MainActivity, "Silme işlemi yapılmadı", Toast.LENGTH_SHORT).show()
                }

                builder.create().show()
            }

        }

        }
    }


