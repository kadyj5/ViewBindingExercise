package com.apartments.zadaniedomowe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apartments.zadaniedomowe.databinding.ActivityMainBinding
import com.apartments.zadaniedomowe.databinding.ItemMainAdapterBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerData = mutableListOf<RecyclerItemData>()
        recyclerData.add(RecyclerItemData("1", "Kot"))
        recyclerData.add(RecyclerItemData("2", "Pies"))
        recyclerData.add(RecyclerItemData("3", "Chomik"))
        recyclerData.add(RecyclerItemData("4", "Koń"))
        recyclerData.add(RecyclerItemData("5", "Rybka"))
        recyclerData.add(RecyclerItemData("6", "Pelikan"))
        recyclerData.add(RecyclerItemData("7", "Bocian"))
        recyclerData.add(RecyclerItemData("8", "Kura"))

        val adapter = MainActivityAdapter(recyclerData) { text ->
            Toast.makeText(this@MainActivity, "Kliknięte zwierzątko", Toast.LENGTH_SHORT).show()
        }

        binding.mainActivityList.adapter = adapter
        binding.mainActivityList.layoutManager = LinearLayoutManager(this)
    }

    class MainActivityAdapter(private val data: List<RecyclerItemData>,
                              val clickListener: (RecyclerItemData) -> Unit
    ): RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val binding: ItemMainAdapterBinding = ItemMainAdapterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return MainViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val currentItemData = data[position]

            holder.binding.adapterItemTextView.text = currentItemData.text
            holder.binding.adapterItemButton.text = currentItemData.buttonText

            holder.binding.adapterItemButton.setOnClickListener {
                clickListener.invoke(currentItemData)
            }
        }

        override fun getItemCount() = data.size
    }

    class MainViewHolder(val binding: ItemMainAdapterBinding): RecyclerView.ViewHolder(binding.root)
}

