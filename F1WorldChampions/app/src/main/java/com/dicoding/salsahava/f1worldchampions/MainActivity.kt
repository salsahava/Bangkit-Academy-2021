package com.dicoding.salsahava.f1worldchampions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvWorldChamps: RecyclerView
    private var list: ArrayList<WorldChamp> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvWorldChamps = findViewById(R.id.rv_worldchamps)
        rvWorldChamps.setHasFixedSize(true)

        list.addAll(WorldChampsData.listData)
        showRecyclerCardView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun onClickAbout(item: MenuItem) {
        val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(aboutIntent)
    }

    private fun showRecyclerCardView() {
        rvWorldChamps.layoutManager = LinearLayoutManager(this)
        val cardViewWorldChampAdapter = CardViewWorldChampAdapter(list)
        rvWorldChamps.adapter = cardViewWorldChampAdapter

        cardViewWorldChampAdapter.setOnItemClickCallback(object :
            CardViewWorldChampAdapter.OnItemClickCallback {
            override fun onItemClicked(data: WorldChamp) {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_PHOTO, data.photo)
                detailIntent.putExtra(DetailActivity.EXTRA_NAME, data.name)
                detailIntent.putExtra(DetailActivity.EXTRA_ENTRIES, data.entries.toString())
                detailIntent.putExtra(DetailActivity.EXTRA_WINS, data.wins.toString())
                detailIntent.putExtra(DetailActivity.EXTRA_PODIUMS, data.podiums.toString())
                detailIntent.putExtra(
                    DetailActivity.EXTRA_CAREER_POINTS,
                    data.careerPoints.toString()
                )
                detailIntent.putExtra(
                    DetailActivity.EXTRA_POLE_POSITIONS,
                    data.polePositions.toString()
                )
                detailIntent.putExtra(
                    DetailActivity.EXTRA_FASTEST_LAPS,
                    data.fastestLaps.toString()
                )
                detailIntent.putExtra(DetailActivity.EXTRA_DETAIL, data.detail)
                startActivity(detailIntent)
            }
        })
    }
}