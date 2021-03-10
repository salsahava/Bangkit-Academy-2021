package com.dicoding.salsahava.f1worldchampions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ENTRIES = "extra_entries"
        const val EXTRA_WINS = "extra_wins"
        const val EXTRA_PODIUMS = "extra_podiums"
        const val EXTRA_CAREER_POINTS = "extra_career_points"
        const val EXTRA_POLE_POSITIONS = "extra_pole_positions"
        const val EXTRA_FASTEST_LAPS = "extra_fastest_laps"
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        receiveData()
    }

    private fun receiveData() {
        val imgPhotoDt: ImageView = findViewById(R.id.img_photo)
        val tvNameDt: TextView = findViewById(R.id.tv_name)
        val tvEntries: TextView = findViewById(R.id.tv_entries)
        val tvWins: TextView = findViewById(R.id.tv_wins)
        val tvPodiums: TextView = findViewById(R.id.tv_podiums)
        val tvCareerPoints: TextView = findViewById(R.id.tv_career_points)
        val tvPolePositions: TextView = findViewById(R.id.tv_pole_positions)
        val tvFastestLaps: TextView = findViewById(R.id.tv_fastest_laps)
        val tvDetailDt: TextView = findViewById(R.id.tv_detail)

        val photo = intent.getStringExtra(EXTRA_PHOTO)
        val name = intent.getStringExtra(EXTRA_NAME)
        val entries = intent.getStringExtra(EXTRA_ENTRIES)
        val wins = intent.getStringExtra(EXTRA_WINS)
        val podiums = intent.getStringExtra(EXTRA_PODIUMS)
        val careerPoints = intent.getStringExtra(EXTRA_CAREER_POINTS)
        val polePositions = intent.getStringExtra(EXTRA_POLE_POSITIONS)
        val fastestLaps = intent.getStringExtra(EXTRA_FASTEST_LAPS)
        val detail = intent.getStringExtra(EXTRA_DETAIL)

        Glide.with(this)
            .load(photo)
            .apply(RequestOptions().override(350, 550))
            .into(imgPhotoDt)

        tvNameDt.text = name
        tvEntries.text = entries
        tvWins.text = wins
        tvPodiums.text = podiums
        tvCareerPoints.text = careerPoints
        tvPolePositions.text = polePositions
        tvFastestLaps.text = fastestLaps
        tvDetailDt.text = detail
    }
}